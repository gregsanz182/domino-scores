/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

import Modelos.Jugadores;
import Modelos.Participantes;
import Modelos.Partidas;
import Modelos.Rondas;
import java.util.ArrayList;

/**
 *
 * @author fmlia
 */
public class Dominota {
    
    private Partidas partida = null;
    private ArrayList<Participantes> participantes = null;
    private int puntajeMax = 100;
    private int numRonda;
    
    SessionManager sessionManager = null;
    
    public Dominota(int puntajeMax) {
        this.numRonda = 1;
        sessionManager = new SessionManager();
        this.puntajeMax = puntajeMax;
    }
    
    private void crearPartida() {
        partida = new Partidas(this.puntajeMax);
    }
    
    public boolean iniciarPartidaIndividual(ArrayList<String> nombreJugadores) {
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        Jugadores aux = null;
        for(String nombre: nombreJugadores) {
            aux = sessionManager.getAndSaveJugador(nombre);
            if (aux == null) {
                return false;
            } 
            jugadores.add(aux);
        }
        
        participantes = new ArrayList<>();
        partida = new Partidas(this.puntajeMax);
       
        jugadores.forEach((jugador) -> {
            participantes.add(new Participantes(jugador, partida));
        });
        
        return true;
    }
    
    public boolean iniciarPartidaGrupal(String nombreEquipo1, ArrayList<String> nombreJugadores1, String nombreEquipo2, ArrayList<String> nombreJugadores2) {
        ArrayList<Jugadores> jugadores1 = new ArrayList<>();
        ArrayList<Jugadores> jugadores2 = new ArrayList<>();
        Jugadores aux = null;
        for(String nombre: nombreJugadores1) {
            aux = sessionManager.getAndSaveJugador(nombre);
            if (aux == null) {
                return false;
            } 
            jugadores1.add(aux);
        }
        for(String nombre: nombreJugadores2) {
            aux = sessionManager.getAndSaveJugador(nombre);
            if (aux == null) {
                return false;
            } 
            jugadores2.add(aux);
        }
        
        participantes = new ArrayList<>();
        partida = new Partidas(this.puntajeMax);
        
        participantes.add(new Participantes(jugadores1.get(0), jugadores1.get(1), partida, nombreEquipo1));
        participantes.add(new Participantes(jugadores2.get(0), jugadores2.get(1), partida, nombreEquipo2));
        
        return true;
    }
    
    public int ganarRonda(int numeroParticipante, int puntaje) {
        Participantes part = participantes.get(numeroParticipante);
        Rondas ronda = new Rondas(part, this.partida, this.numRonda++, puntaje);
        return part.getPuntaje();
    }
    
    public int ganarRonda(Participantes participante, int puntaje) {
        Rondas ronda = new Rondas(participante, this.partida, this.numRonda++, puntaje);
        return participante.getPuntaje();
    }
    
    public boolean guardarPartida() {
        return sessionManager.savePartida(this.partida);
    }
}