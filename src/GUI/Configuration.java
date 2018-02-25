/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Services.HandlerServiceBack;
import GUI.Views.LoadUser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/**
 *
 * @author Joalcapa
 */
public class Configuration {
    private LoadUser loadUser;
    
    private boolean dualOne;
    private boolean dual;
    private boolean isRunningGame;
    
    private ArrayList<String> listUserOne = new ArrayList();
    private ArrayList<String> listUserTwo = new ArrayList();
    private ArrayList<String> listUsers = new ArrayList();
    
    private String nameTeamOne;
    private String nameTeamTwo;
    
    public static final String WELCOME = "WELCOME";
    public static final String DUAL_TEAM_INFO = "DUAL_TEAM_INFO";
    public static final String LOAD_USER = "LOAD_USER";
    
    public Configuration() {
        dual = dualOne = true;
        isRunningGame = false;
        loadUser = null;
    }
    
    public boolean isDualOne() {
        return dualOne;
    }
    
    public void setDualOne(boolean dualOne) {
        this.dualOne = dualOne;
    }
    
    public boolean isDual() {
        return dual;
    }
    
    public void setDual(boolean dual) {
        this.dual = dual;
    }
    
    public void addUser(String name) throws JSONException {
        if(dual) {
            if(dualOne)
                listUserOne.add(name);
            else
                listUserTwo.add(name);
            
            if(!isRunningGame)
                if(listUserOne.size() == 2 && listUserTwo.size() == 2) {
                    isRunningGame = 
                    HandlerServiceBack.createGame(
                            (String) listUserOne.get(0),
                            listUserOne,
                            (String) listUserTwo.get(0),
                            listUserTwo);
                    
                    System.out.println(isRunningGame);
                }
        } else
            listUsers.add(name);
    }
    
    public List getUsers() {
        if(dual) {
            if(dualOne)
                return listUserOne;
            else
                return listUserTwo;
        } else
            return listUsers;
    }
    
    public LoadUser getLoadUser() {
        return loadUser;
    }
    
    public void setLoadUser(LoadUser loadUser){
        this.loadUser = loadUser;
    }
    
    public boolean dualTeamValidate() {
        if(listUserOne.size() == 2 && listUserTwo.size() == 2)
            return true;
        return false;
    }
}
