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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
    
    public ArrayList<String> traerJugadores() {
        List<String> lista = null;
        ArrayList<String> nombres = new ArrayList<>();
        try {
            Query q = session.createQuery("select apodo from Jugadores");
            lista = q.list();
            for(String row: lista){
                nombres.add(row);
            }
            
            return nombres;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            List<Object[]> lista = null;
            Map<String, Integer[]> objetos = new HashMap<>();
            
            Query q = session.createQuery("select j.apodo, count(pu.partidas.id), 0 "
                    + "from Jugadores j join j.participantesesForJugadorUnoId pu "
                    + "where pu.jugadoresByJugadorDosId is null "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            q = session.createQuery("select j.apodo, 0, count(pu.partidas.id) "
                    + "from Jugadores j join j.participantesesForJugadorDosId pu "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            q = session.createQuery("select j.apodo, 0, count(pu.partidas.id) "
                    + "from Jugadores j join j.participantesesForJugadorUnoId pu "
                    + "where pu.jugadoresByJugadorDosId is not null "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            
            for(String key: objetos.keySet()) {
                System.out.println(key + " -- " + objetos.get(key)[0] + " -- " + objetos.get(key)[1]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void consulta2 (){
        try {
            List<Object[]> lista = null;
            Map<String, Integer[]> objetos = new HashMap<>();
            
            Query q = session.createQuery("select j.apodo, count(pu.partidas.id), 0 "
                    + "from Jugadores j join j.participantesesForJugadorUnoId pu "
                    + "where pu.puntaje >= pu.partidas.puntajeMax "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            q = session.createQuery("select j.apodo, count(pu.partidas.id), 0 "
                    + "from Jugadores j join j.participantesesForJugadorDosId pu "
                    + "where pu.puntaje >= pu.partidas.puntajeMax "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            q = session.createQuery("select j.apodo, 0, count(pu.partidas.id) "
                    + "from Jugadores j join j.participantesesForJugadorUnoId pu "
                    + "where pu.puntaje = 0 "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            q = session.createQuery("select j.apodo, 0, count(pu.partidas.id) "
                    + "from Jugadores j join j.participantesesForJugadorDosId pu "
                    + "where pu.puntaje = 0 "
                    + "group by j.apodo ");
            lista = q.list();
            this.unirResultadoConsulta(objetos, lista);
            
            for(String key: objetos.keySet()) {
                System.out.println(key + " -- " + objetos.get(key)[0] + " -- " + objetos.get(key)[1]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    private void unirResultadoConsulta(Map<String, Integer[]> objetos, List<Object[]> lista) {
        for(Object[] row: lista){
            if(objetos.containsKey(row[0].toString()) == false) {
                objetos.put(row[0].toString(), new Integer[]{Integer.parseInt(row[1].toString()), Integer.parseInt(row[2].toString())});
            } else {
                objetos.get(row[0].toString())[0] += Integer.parseInt(row[1].toString());
                objetos.get(row[0].toString())[1] += Integer.parseInt(row[2].toString());
            }
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
