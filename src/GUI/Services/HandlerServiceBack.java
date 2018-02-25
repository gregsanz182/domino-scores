/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Services;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Joalcapa
 */
public class HandlerServiceBack {
    private static HandlerServiceBack handlerServiceBack;
    
    private HandlerServiceBack() {
        
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
    
    public static void createGame(
        String userOneTeamOne, String userTwoTeamOne,
        String userOneTeamTwo, String userTwoTeamTwo) throws JSONException {
        
       JSONObject teamOne = new JSONObject();
       teamOne.put("player_one", userOneTeamOne);
       teamOne.put("player_two", userTwoTeamOne);
       
       JSONObject teamTwo = new JSONObject();
       teamTwo.put("player_one", userOneTeamOne);
       teamTwo.put("player_two", userTwoTeamOne);
       
       JSONObject data = new JSONObject();
       teamTwo.put("team_one", teamOne);
       teamTwo.put("team_two", teamTwo);
       
       
    }
}
