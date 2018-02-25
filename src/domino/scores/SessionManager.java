/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

import Modelos.Jugadores;
import Modelos.Partidas;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author fmlia
 */
public class SessionManager {
    private HibernateUtil hibernate = new HibernateUtil();
    private static SessionFactory sessionFactory = null;
    Session session = null;
    
    public SessionManager() {
        session = this.getSession();
    }
    
    public Session getSession() {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return session;
        }
    }
    
    /* Guarda jugador y devuelve un objeto Jugadores */
    public Jugadores getAndSaveJugador(String apodo_jugador) {
        try {
            Query q = session.createQuery("from Jugadores where apodo=:apodo_jugador");
            q.setParameter("apodo_jugador", apodo_jugador);
            Jugadores jugador = (Jugadores) q.uniqueResult();
            if (jugador == null) {
                jugador = new Jugadores(apodo_jugador);
                this.saveJugador(jugador);
            }
            return jugador;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void saveJugador(Jugadores jugador) {
        session.beginTransaction();
        session.save(jugador);
        session.flush();
        session.getTransaction().commit();
    }
    
    public void savePartida(Partidas partida) {
        session.beginTransaction();
        session.save(partida);
        session.flush();
        session.getTransaction().commit();
    }
    
    public void consulta1 (){
        ArrayList<JugadorPartidas> lista = null;
        try {
            Query q = session.createQuery("select p.apodo, sum(p.individual), sum(p.grupal) "
                    + "from ((select p.jugadoresByJugadorDosId.apodo apodo, count(*) individual, 0 grupal "
                    + "from Partidas par join par.participanteses p"
                    + "where p.jugadoresByJugadorDosId is null "
                    + "group by p.jugadoresByJugadorDosId.apodo) "
                    + "union all "
                    + "(select j.apodo apodo, 0 individual, count(*) grupal "
                    + "from Jugadores j join Participantes p with (j = p.jugadoresByJugadorUnoId or j = p.jugadoresByJugadorDosId) "
                    + "join Partidas par on "
                    + "where p.jugadoresByJugadorDosId is not null "
                    + "group by j.apodo)) p "
                    + "group by p.apodo");
            //Query q = session.createQuery("select i.apodo,g.apodo, (i.individual + g.individual), (i.grupal + g.grupal) from (select j.apodo as apodo, count(*) as individual, 0 as grupal from jugadores j join participantes p on (j.id = p.jugador_uno_id) join partidas par on (par.id = p.partida_id) where p.jugador_dos_id is null group by j.apodo) as i full join (select j.apodo as apodo, 0 as individual, count(*) as grupal from jugadores j join participantes p on (j.id = p.jugador_uno_id) or (j.id = p.jugador_dos_id) join partidas par on (par.id = p.partida_id) where p.jugador_dos_id is not null group by j.apodo) as g on i.apodo = g.apodo;");
            lista = (ArrayList<JugadorPartidas>) q.list();
            lista.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public class JugadorPartidas {
        private String apodo;
        private int individual,grupal;
        
        JugadorPartidas(){
        }
        
        JugadorPartidas(String apodo, int individual,int grupal){
            this.apodo = apodo;
            this.individual = individual;
            this.grupal = grupal;
        }

        public String getApodo() {
            return apodo;
        }

        public void setApodo(String apodo) {
            this.apodo = apodo;
        }

        public int getIndividual() {
            return individual;
        }

        public void setIndividual(int individual) {
            this.individual = individual;
        }

        public int getGrupal() {
            return grupal;
        }

        public void setGrupal(int grupal) {
            this.grupal = grupal;
        }
        
    }
}
