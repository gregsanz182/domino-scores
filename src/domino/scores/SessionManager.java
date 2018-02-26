/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

import Modelos.*;
import java.util.*;
import org.hibernate.*;

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
    
    public void consulta3 (){
        try {
            //  consulta 3 Cuál ha sido el jugador que ha obtenido más puntos en una RONDA
            Query q = session.createQuery("select p.jugadoresByJugadorUnoId.apodo, max(r.puntaje) "
                    + "from Participantes p join p.rondases r "
                    + "group by p.jugadoresByJugadorUnoId.apodo "
                    + "order by 2 desc");
            List<Object[]> lista = q.list();
            int mayor = 0;
            ArrayList<String> jugadores = new ArrayList<String>();
            for (Object[] row: lista){
                if (mayor == 0) {
                    mayor = (int) row[1];
                }
                if(mayor == (int) row[1]){
                    jugadores.add(row[0].toString());
                }
            }
            System.out.println("Puntaje: "+mayor);
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println("\t Jugador: "+jugadores.get(i));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void consulta4(){
        // consulta 4 Porcentaje de victorias en RONDAS por cada jugador (número de victorias entre rondas jugadas).
        // numero de rondas jugadas por jugador
        Query a = session.createQuery("select p.jugadoresByJugadorUnoId.apodo, count(p.jugadoresByJugadorUnoId.apodo) "
                + "from Participantes p join p.rondases r "
                + "group by p.jugadoresByJugadorUnoId.apodo");
        List<Object[]> listaa = a.list();
        System.out.println("jugador uno");
        for (Object[] row: listaa){
            System.out.println(row[0] + " -- " + row[1] + " -- " );
        }
        Query b = session.createQuery("select p.jugadoresByJugadorDosId.apodo, count(p.jugadoresByJugadorDosId.apodo) "
                + "from Participantes p join p.rondases r "
                + "where p.jugadoresByJugadorDosId is not null "
                + "group by p.jugadoresByJugadorDosId.apodo");
        List<Object[]> listab = b.list();
        System.out.println("jugador dos");
        for (Object[] row: listab){
            System.out.println(row[0] + " -- " + row[1] + " -- " );
        }
        // numero de victorias por jugador
        Query c = session.createQuery("select p.jugadoresByJugadorUnoId.apodo, count(par.id) "
                + "from Partidas par join par.participanteses p "
                + "join par.rondases r "
                + "where p.puntaje >= par.puntajeMax "
                + "group by par.id, p.jugadoresByJugadorUnoId.apodo ");
        List<Object[]> listac = c.list();
        System.out.println("victorias jugador uno");
        for (Object[] row: listac){
            System.out.println(row[0] + " -- " + row[1] + " -- " );
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
