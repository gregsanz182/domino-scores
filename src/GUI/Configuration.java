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

/**
 *
 * @author Joalcapa
 */
public class Configuration {
    private LoadUser loadUser;
    
    private boolean dualOne;
    private boolean dual;
    private boolean runningGame;
    
    private ArrayList<String> listUserOne = new ArrayList();
    private ArrayList<String> listUserTwo = new ArrayList();
    private ArrayList<String> listUsers = new ArrayList();
    private ArrayList<String> listPlayers;
    private ArrayList<String> listPlayersDB;
    
    private String nameTeamOne;
    private String nameTeamTwo;
    
    public static final String WELCOME = "WELCOME";
    public static final String DUAL_TEAM_INFO = "DUAL_TEAM_INFO";
    public static final String LOAD_USER = "LOAD_USER";
    public static final String INDIVIDUAL_INFO = "INDIVIDUAL_INFO";
    public static final String STATISTICS = "STATISTICS";
    public static final String FIRST_QUERY = "FIRST_QUERY";
    public static final String SECOND_QUERY = "FIRST_QUERY";
    public static final String THIRD_QUERY = "FIRST_QUERY";
    public static final String QUARTER_QUERY = "FIRST_QUERY";
    
    private int maxPoints;
    private int individualPlayer;
    private String queryType;
    
    public Configuration(int maxPoints) {
        dual = dualOne = true;
        runningGame = false;
        loadUser = null;
        this.maxPoints = maxPoints;
        listUsers.add("");
        listUsers.add("");
        listUsers.add("");
        listUsers.add("");
    }
    
    public void restart() {
       listUserOne = new ArrayList();
       listUserTwo = new ArrayList();
       listUsers = new ArrayList();
       listUsers.add("");
       listUsers.add("");
       listUsers.add("");
       listUsers.add("");
    }
    
    public void allUsers(){
        listPlayersDB = HandlerServiceBack.allUsers();
        for(int i= 0; i<listPlayersDB.size(); i++)
            System.out.println(listPlayersDB.get(i));
    }
    
    public ArrayList<String> getUserDb() {
        return listPlayersDB;
    }
    
    public Object[] getUserDbObject() {
        Object [] obj = new Object[listPlayersDB.size()];
        for(int i=0; i<listPlayersDB.size(); i++)
            obj[i] = listPlayersDB.get(i);
        return obj;
    }
    
    public String getQueryType() {
        return queryType;
    }
    
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
    
    public int getMaxPoints() {
        return maxPoints;
    }
    
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
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
    
    public void cleanUsers() {
        listUserOne = listUserTwo = listUsers = null;
        listUserOne = new ArrayList();
        listUserTwo = new ArrayList();
        listUsers = new ArrayList();
    }
    
    public void addUser(String name) {
        if(dual) {
            if(dualOne)
                listUserOne.add(name);
            else
                listUserTwo.add(name);
            
            if(!runningGame)
                if(listUserOne.size() == 2 && listUserTwo.size() == 2) {
                    runningGame = 
                    HandlerServiceBack.createGame(
                            (String) listUserOne.get(0),
                            listUserOne,
                            (String) listUserTwo.get(0),
                            listUserTwo);
                    
                    if(!runningGame)
                        cleanUsers();
                    
                }
        } else {
            listUsers.remove(individualPlayer);
            listUsers.add(individualPlayer, name);
        }
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
    
    public boolean isRunningGame() {
        return runningGame;
    }
    
    public boolean setRunningGame(boolean runningGame) {
        return this.runningGame = runningGame;
    }
    
    public boolean IndividualValidate(int typePlayer) {
        if(listUsers.get(typePlayer).length() == 0)
            return false;
        else
            return true;
    }
    
    public String namePlayerIndividual() {
        return listUsers.get(individualPlayer);
    }
    
    public boolean IndividualGame() {
        int count = 0;
        for(int i=0; i<listUsers.size(); i++)
            if(listUsers.get(i).length() != 0)
                count++;
        
        if(count >= 1)
            return true;
        return false;
    }
    
    public void setIndividualPlayer(int individualPlayer) {
        this.individualPlayer = individualPlayer;
    }
    
    public int getIndividualPlayer() {
        return individualPlayer;
    }
    
    public boolean savedPlayersIndividual() {
        listPlayers = new ArrayList();
        for(int i=0; i<listUsers.size(); i++)
            if(listUsers.get(i).length() != 0)
                listPlayers.add(listUsers.get(i));
        return HandlerServiceBack.createGame(listPlayers);
    }
    
    public int searchPositionPlayer(int position) {
        for(int i=0; i<listUsers.size(); i++)
            if(listUsers.get(i).equals(listPlayers.get(position).toString()))
                return i;
        return -1;
    }
}
