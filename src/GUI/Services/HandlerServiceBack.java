/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Services;

import domino.scores.Dominota;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

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
}
