/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Joalcapa
 */
public class Welcome extends JFrame {
    private WelcomePanel welcomePanel;
    private DualTeamInfo dualTeaminfo;
    public static  CardLayout card = new CardLayout();
    public static Container container;
    
    public Welcome() {
        super("dominota");
        container = this.getContentPane();
        setBounds(0,0,720,520);
        setLocationRelativeTo(null);
        container.setLayout(card);
        
        welcomePanel = new WelcomePanel(card);
        dualTeaminfo = new DualTeamInfo(card);
        
        container.add(welcomePanel);
        container.add(dualTeaminfo);
        
        card.addLayoutComponent(welcomePanel, "WELCOME");
        card.addLayoutComponent(dualTeaminfo, "DUAL_TEAM_INFO");
        
        card.show(container, "WELCOME");
        setVisible(true);
        
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt ) { 
                System.exit( 0 ); 
            } 
        } ); 
    }    
}
