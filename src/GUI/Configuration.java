/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joalcapa
 */
public class Configuration {
    private boolean dualOne;
    private boolean dual;
    
    private List listUserOne = new ArrayList();
    private List listUserTwo = new ArrayList();
    private List listUsers = new ArrayList();
    
    public static final String WELCOME = "WELCOME";
    public static final String DUAL_TEAM_INFO = "DUAL_TEAM_INFO";
    public static final String LOAD_USER = "LOAD_USER";
    
    public Configuration() {
        dual = dualOne = true;
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
    
    public void addUser(String name) {
        if(dual) {
            if(dualOne)
                listUserOne.add(name);
            else
                listUserTwo.add(name);
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
}
