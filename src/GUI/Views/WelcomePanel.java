/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import GUI.Configuration;
import GUI.Services.HandlerServiceBack;
import static GUI.Views.Welcome.card;
import static GUI.Views.Welcome.container;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Joalcapa
 */
public class WelcomePanel extends JPanel implements MouseListener {
    private JButton two_team = new JButton("2 Equipos");
    private JButton four_player = new JButton("4 Jugadores");
    private JButton stadistics_two_team = new JButton("Estadisticas");
    private JButton stadistics_four_player = new JButton("Estadisticas");
    private CardLayout card;
    private JFrame frame;
    private Configuration configuration;
    
    public WelcomePanel(CardLayout card, JFrame frame, Configuration configuration){
        super();
        this.card = card;
        this.frame = frame;
        this.configuration = configuration;
        setLayout(null);
        setSize(720,520);
        add(two_team);
        add(four_player);
        add(stadistics_two_team);
        add(stadistics_four_player);
        setVisible(true);
        
        two_team.setBounds(285,140,150,30);
        stadistics_two_team.setBounds(285,180,150,30);
        four_player.setBounds(285,280,150,30);
        stadistics_four_player.setBounds(285,320,150,30);
        
        two_team.addMouseListener(this);
        four_player.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(e.getSource() == two_team) {
           configuration.setDual(true);
           card.show(container, Configuration.DUAL_TEAM_INFO);
       }
       
       if(e.getSource() == four_player) {
           configuration.setDual(false);
           card.show(container, Configuration.DUAL_TEAM_INFO);
       }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
