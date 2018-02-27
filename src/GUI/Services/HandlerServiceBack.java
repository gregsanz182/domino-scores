/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Services;

import domino.scores.Dominota;
import java.util.ArrayList;
import java.util.Map;
import GUI.Configuration;

/**
 *
 * @author Joalcapa
 */
public class HandlerServiceBack {
    private static HandlerServiceBack handlerServiceBack;
    private Dominota dominota;
    
    private HandlerServiceBack() {
        this.dominota = new Dominota(150);
    }
    
    public static HandlerServiceBack getSingletonInstance() {
        if (handlerServiceBack == null)
            handlerServiceBack = new HandlerServiceBack();
        return handlerServiceBack;
    }
    
    @Override
    public HandlerServiceBack clone(){
        try {
           throw new CloneNotSupportedException();
        } catch (CloneNotSupportedException ex) {
           System.out.println("No se puede clonar un objeto de la clase SoyUnico");
        }
        return null; 
    }
    
    public static boolean createGame(
            String nameTeamOne, ArrayList<String> listTeamOne,
            String nameTeamTwo, ArrayList<String> ListTeamTwo) {
        return handlerServiceBack.
                dominota.iniciarPartidaGrupal(nameTeamOne, listTeamOne, nameTeamTwo, ListTeamTwo);
    }
    
    public static boolean createGame(ArrayList<String> listPlayers) {
        return handlerServiceBack.dominota.iniciarPartidaIndividual(listPlayers);
    }
    
    public static int asignPoints(int positionList, int points) {
        return handlerServiceBack.dominota.ganarRonda(positionList, points);
    }
    
    public static boolean saveGame() {
        return handlerServiceBack.dominota.guardarPartida();
    }
    
    public static Map<String, Integer[]> getFirstQuery() {
        return handlerServiceBack.dominota.partidasJugadas();
    }
    
    public static Map<String, Integer[]> getSecondQuery() {
        return handlerServiceBack.dominota.partidasGanadasYZapatos();
    }
    
    public static Map<String, Integer> getThirdQuery() {
        return handlerServiceBack.dominota.jugadorMaxPuntajePorRonda();
    }
    
    public static Map<String, Float> getQuarterQuery() {
        return handlerServiceBack.dominota.porcentajeVictorias();
    }
    
    public static Map<Integer, String[]> getExtraQuery() {
        return handlerServiceBack.dominota.equipoConMasRondas();
    }
    
    public static ArrayList<String> allUsers() {
        return handlerServiceBack.dominota.obtenerJugdores();
    }
    
    public static void restartDominota() {
        handlerServiceBack.dominota = new Dominota(Configuration.POINTS_MAX);
    }
}
