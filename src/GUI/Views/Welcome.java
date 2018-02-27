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
    private IndividualInfo individualInfo;
    private StatisticsMenu statisticsMenu;
    private Configuration configuration;
    
    public static  CardLayout card = new CardLayout();
    public static Container container;
    
    public Welcome(Configuration configuration) {
        super("dominota");
        HandlerServiceBack.getSingletonInstance();
        this.configuration = configuration;
        this.configuration.allUsers();
        container = this.getContentPane();
        setBounds(0,0,720,520);
        setLocationRelativeTo(null);
        container.setLayout(card);
        
        welcomePanel = new WelcomePanel(card, this, configuration);
        dualTeaminfo = new DualTeamInfo(card, this, configuration);
        loadUser = new LoadUser(card, this, configuration);
        individualInfo = new IndividualInfo(card, this, configuration);
        statisticsMenu = new StatisticsMenu(card, this, configuration);
        
        container.add(welcomePanel);
        container.add(dualTeaminfo);
        container.add(individualInfo);
        container.add(loadUser);
        container.add(statisticsMenu);
        
        card.addLayoutComponent(welcomePanel, Configuration.WELCOME);
        card.addLayoutComponent(dualTeaminfo, Configuration.DUAL_TEAM_INFO);
        card.addLayoutComponent(loadUser, Configuration.LOAD_USER);
        card.addLayoutComponent(individualInfo, Configuration.INDIVIDUAL_INFO);
        card.addLayoutComponent(statisticsMenu, Configuration.STATISTICS);
        
        card.show(container, Configuration.WELCOME);
        setVisible(true);
        
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt ) { 
                System.exit( 0 ); 
            } 
        } ); 
    }    
}
