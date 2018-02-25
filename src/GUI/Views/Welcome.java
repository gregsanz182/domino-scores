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
public class Welcome extends JFrame implements MouseListener{
    private WelcomePanel welcomePanel;
    public static  CardLayout card = new CardLayout();
    public static Container container;
    
    public Welcome() {
        super("");
        container = this.getContentPane();
        setBounds(0,0,720,520);
        setLocationRelativeTo(null);
        setLayout(card);
        
        welcomePanel = new WelcomePanel(this);
        card.addLayoutComponent(welcomePanel, "WELCOME");
        card.show(container, "WELCOME");
        container.add(welcomePanel);
        setVisible(true);
        
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt ) { 
                System.exit( 0 ); 
            } 
        } ); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
