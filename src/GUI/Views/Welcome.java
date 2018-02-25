/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import GUI.Configuration;
import GUI.Services.HandlerServiceBack;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Joalcapa
 */
public class Welcome extends JFrame {
    private WelcomePanel welcomePanel;
    private DualTeamInfo dualTeaminfo;
    private LoadUser loadUser;
    private Configuration configuration;
    
    public static  CardLayout card = new CardLayout();
    public static Container container;
    
    public Welcome(Configuration configuration) {
        super("dominota");
        HandlerServiceBack.getSingletonInstance();
        this.configuration = configuration;
        container = this.getContentPane();
        setBounds(0,0,720,520);
        setLocationRelativeTo(null);
        container.setLayout(card);
        
        welcomePanel = new WelcomePanel(card, this, configuration);
        dualTeaminfo = new DualTeamInfo(card, this, configuration);
        loadUser = new LoadUser(card, this, configuration);
        
        container.add(welcomePanel);
        container.add(dualTeaminfo);
        container.add(loadUser);
        
        card.addLayoutComponent(welcomePanel, Configuration.WELCOME);
        card.addLayoutComponent(dualTeaminfo, Configuration.DUAL_TEAM_INFO);
        card.addLayoutComponent(loadUser, Configuration.LOAD_USER);
        
       card.show(container, Configuration.WELCOME);
        setVisible(true);
        
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt ) { 
                System.exit( 0 ); 
            } 
        } ); 
    }    
}
