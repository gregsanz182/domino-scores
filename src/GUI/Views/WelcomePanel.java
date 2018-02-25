/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Joalcapa
 */
public class WelcomePanel extends JPanel{
    private JButton two_team = new JButton("2 Equipos");
    private JButton four_player = new JButton("4 Jugadores");
    
    public WelcomePanel(Welcome welcome){
        setLayout(new FlowLayout());
        this.setSize(320, 308);
        add(two_team);
        add(four_player);
    }
}
