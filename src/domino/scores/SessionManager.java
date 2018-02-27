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
        // consulta 4 Porcentaje de victorias en RONDAS por cada jugador (número de victorias entre rondas jugadas)
        Query q = null;
        List<Object[]> lista = null;
        Map<String, Integer[]> jugadas = new HashMap<String, Integer[]>();
        Map<String, Integer[]> victorias = new HashMap<String, Integer[]>();
        Map<String, float[]> porcentaje = new HashMap<String, float[]>();
        // numero de rondas jugadas por jugador uno
        q = session.createQuery("select p.jugadoresByJugadorUnoId.apodo, count(r) "
                + "from Partidas par join par.rondases r "
                + "join par.participanteses p "
                + "group by p.jugadoresByJugadorUnoId.apodo ");
        lista = q.list();
        this.unirRondasJugadas(jugadas,lista);
        // numero de rondas jugadas por jugador dos
        q = session.createQuery("select p.jugadoresByJugadorDosId.apodo, count(r) "
                + "from Partidas par join par.rondases r "
                + "join par.participanteses p "
                + "group by p.jugadoresByJugadorDosId.apodo ");
        lista = q.list();
        this.unirRondasJugadas(jugadas,lista);
        // numero de victorias por jugador
        q = session.createQuery("select p.jugadoresByJugadorUnoId.apodo, count(r) "
                + "from Participantes p join p.rondases r "
                + "group by p.jugadoresByJugadorUnoId.apodo ");
        lista = q.list();
        this.unirVictorias(victorias,lista);
        // numero de victorias por jugador
        q = session.createQuery("select p.jugadoresByJugadorDosId.apodo, count(r) "
                + "from Participantes p join p.rondases r "
                + "where p.jugadoresByJugadorDosId is not null "
                + "group by p.jugadoresByJugadorDosId.apodo ");
        lista = q.list();
        this.unirVictorias(victorias,lista);
        this.calcularPorcentaje(porcentaje,victorias,jugadas);
        // PORCENTAJE DE VICTORIAS POR JUGADOR
        for (String key: porcentaje.keySet()){
            System.out.println(key + " -- " + porcentaje.get(key)[0]);
        }
    }

    private void unirRondasJugadas(Map<String, Integer[]> jugadas, List<Object[]> lista) {
        for (Object[] row : lista) {
            if (jugadas.containsKey(row[0].toString()) == false) {
                jugadas.put(row[0].toString(), new Integer[]{Integer.parseInt(row[1].toString())});
            }else{
                jugadas.get(row[0].toString())[0] += Integer.parseInt(row[1].toString());
            }
        }
    }

    private void unirVictorias(Map<String, Integer[]> victorias, List<Object[]> lista) {
        for (Object[] row : lista) {
            if (victorias.containsKey(row[0].toString()) == false) {
                victorias.put(row[0].toString(), new Integer[]{Integer.parseInt(row[1].toString())});
            }else{
                victorias.get(row[0].toString())[0] += Integer.parseInt(row[1].toString());
            }
        }
    }

    private void calcularPorcentaje(Map<String, float[]> porcentaje, Map<String, Integer[]> victorias, Map<String, Integer[]> jugadas) {
        for ( String key: jugadas.keySet() ){
            porcentaje.put(key, new float[]{((float)victorias.get(key)[0]/(float)jugadas.get(key)[0])*100});
        }
    }
}
