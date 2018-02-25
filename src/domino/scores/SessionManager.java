/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

import Modelos.Jugadores;
import java.util.ArrayList;
import Modelos.Participantes;
import Modelos.Partidas;
import Modelos.Rondas;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

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
    
    public boolean savePartida(Partidas partida) {
        try {
            session.beginTransaction();
            session.save(partida);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public void consulta1 (){
        try {
            /* cantidad por partidas individuales sirve
            Query q = session.createQuery("select j.apodo as apodo, count(p) as individual "
                    + "from Jugadores j left join j.participantesesForJugadorUnoId p "
                    + "where p.jugadoresByJugadorDosId is null "
                    + "group by j.apodo ");
            
            List<Object[]> lista = q.list();
            for (Object[] row: lista){
                System.out.println(row[0] + " -- " + row[1]);
            }
            */
            
            /*Query q = session.createQuery("select ju.apodo as apodo, count(*) "
                    + "from Participantes par join par.jugadoresByJugadorUnoId ju "
                    + "join par.jugadoresByJugadorDosId jd "
                    + "where par.jugadoresByJugadorDosId is not null "
                    + "group by ju.apodo ");*/
            
            Query q = session.createQuery("select j.apodo as apodo, count(pu), count(pd) "
                    + "from Jugadores j left join j.participantesesForJugadorUnoId pu "
                    + "join j.participantesesForJugadorDosId pd "
                    + "where pu.jugadoresByJugadorDosId is not null "
                    + "group by j.apodo ");
            List<Object[]> lista = q.list();
            for (Object[] row: lista){
                System.out.println(row[0] + " -- " + row[1] + " -- " + row[2]);
            }
            
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
