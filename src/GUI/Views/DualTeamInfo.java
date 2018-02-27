/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import GUI.Configuration;
import GUI.Services.HandlerServiceBack;
import static GUI.Views.Welcome.container;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Joalcapa
 */
public class DualTeamInfo extends JPanel implements MouseListener {
    private JLabel titleMax = new JLabel("Maximo");
    private JLabel pointsMax = new JLabel("100");
    private JButton add_one = new JButton("Agregar");
    private JButton add_two = new JButton("Agregar");
    private JButton edit_one = new JButton("Agregar puntos");
    private JButton edit_two = new JButton("Agregar puntos");
    private JButton back_button = new JButton("ATRAS");
    private JPanel panelPoints = new JPanel();
    
    private int points_one = 0;
    private int points_two = 0;
    private int posY = 0;
    private int tamPanel = 340;
    private boolean isGameTerminated = false;
    
    private CardLayout card;
    private JFrame frame;
    private Configuration configuration;
    
    public DualTeamInfo(CardLayout card, JFrame frame, Configuration configuration){
        super();
        this.frame = frame;
        this.configuration = configuration;
        this.card = card;
        setLayout(null);
        setSize(720,520);
        
        add(titleMax);
        add(pointsMax);
        add(add_one);
        add(add_two);
        add(edit_one);
        add(edit_two);
        add(back_button);
        setVisible(true);
        
        titleMax.setBounds(335,20,50,20);
        pointsMax.setBounds(345,40,40,20);
        add_one.setBounds(210,100,100,30);
        add_two.setBounds(410,100,100,30);
        edit_one.setBounds(185,140,150,30);
        edit_two.setBounds(385,140,150,30);
        back_button.setBounds(0,0,100,30);
        
        JScrollPane scrollPane = new JScrollPane(panelPoints);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 180, 720, 340);
        panelPoints.setPreferredSize(new Dimension(720, 390));
        panelPoints.setLayout(null);
        add(scrollPane);
        
        add_one.addMouseListener(this);
        add_two.addMouseListener(this);
        edit_one.addMouseListener(this);
        edit_two.addMouseListener(this);
        back_button.addMouseListener(this);
    }
    
    private void restart() {
        posY = points_one = points_two = 0;
        panelPoints.removeAll();
        isGameTerminated = false;
        configuration.restart();
        frame.paintAll(frame.getGraphics());
    }
    
    private void addPoints(boolean isOne, int points){
        JLabel newPoint = null;
        int posX = 0;
        
        if(isOne) {
            points_one += points;
            if(points_one > configuration.getMaxPoints()) {
                points_one = configuration.getMaxPoints();
                isGameTerminated = true;
            }
            newPoint = new JLabel(points + "-" + points_one);
            posX = 185;
        } else {
            points_two += points;
            if(points_two > configuration.getMaxPoints()) {
                points_two = configuration.getMaxPoints();
                isGameTerminated = true;
            }
            newPoint = new JLabel(points + "-" + points_two);
            posX = 360;
        }
        
        newPoint.setBounds(posX,posY,175,30);
        newPoint.setBorder(BorderFactory.createLineBorder(Color.black));
        
        posY += 31;
        panelPoints.add(newPoint);
        if(posY > tamPanel)
            panelPoints.setPreferredSize(new Dimension(720, posY + 50));
        frame.paintAll(frame.getGraphics());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == back_button)
            card.show(container, Configuration.WELCOME);
        
        if(!isGameTerminated) {
            if(e.getSource() == edit_one) {
                if(configuration.dualTeamValidate()) {
                /*    configuration.setDualOne(true);
                    Object [] players;
                    players = new Object[2];
                    players[0] = configuration.getUsers().get(0);
                    players[1] = configuration.getUsers().get(1);
                    Object seleccionObject = JOptionPane.showInputDialog(
                        new JLabel(),
                        "Seleccione opcion",
                        "Selector de opciones",
                        JOptionPane.QUESTION_MESSAGE,
                        null,  // null para icono defecto
                        players, 
                        "opcion 1");
                 */   
                    String seleccion = JOptionPane.showInputDialog(
                       new JTextField(""),
                       "Puntos",
                       JOptionPane.QUESTION_MESSAGE);
                    if(seleccion != null) {
                        int pointsWinner = Integer.parseInt(seleccion);
                        
                    /*    if(players[0].toString().equals(seleccionObject.toString()))
                            HandlerServiceBack.asignPoints(0, pointsWinner);
                        else */
                            HandlerServiceBack.asignPoints(0, pointsWinner);
                        
                       addPoints(true, Integer.parseInt(seleccion));
                    }
                    
                } else {
                   JOptionPane.showOptionDialog(
                       new JLabel(""),
                       "La partida no puede comenzar hasta no tener los 2 equipos con sus integrantes completos",
                       "",
                       JOptionPane.YES_NO_CANCEL_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       new Object[] { "Aceptar"},   // null para YES, NO y CANCEL
                       "opcion 1"); 
                }
            } 
        
            if(e.getSource() == edit_two) {
                if(configuration.dualTeamValidate()) {
                /*    configuration.setDualOne(false);
                    Object [] players;
                    players = new Object[2];
                    players[0] = configuration.getUsers().get(0);
                    players[1] = configuration.getUsers().get(1);
                    Object seleccionObject = JOptionPane.showInputDialog(
                        new JLabel(),
                        "Seleccione opcion",
                        "Selector de opciones",
                        JOptionPane.QUESTION_MESSAGE,
                        null,  // null para icono defecto
                        players, 
                        "opcion 1");
                    */
                    String seleccion = JOptionPane.showInputDialog(
                       new JTextField(""),
                       "Puntos",
                       JOptionPane.QUESTION_MESSAGE);
                    if(seleccion != null) {
                        int pointsWinner = Integer.parseInt(seleccion);
                        
                    /*    if(players[0].toString().equals(seleccionObject.toString()))
                            HandlerServiceBack.asignPoints(1, pointsWinner);
                        else */
                            HandlerServiceBack.asignPoints(1, pointsWinner);
                        
                       addPoints(false, Integer.parseInt(seleccion));
                    }
                } else {
                   JOptionPane.showOptionDialog(
                       new JLabel(""),
                       "La partida no puede comenzar hasta no tener los 2 equipos con sus integrantes completos",
                       "",
                       JOptionPane.YES_NO_CANCEL_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       new Object[] { "Aceptar"},   // null para YES, NO y CANCEL
                       "opcion 1"); 
                }
            }
            
            if(e.getSource() == add_one || e.getSource() == add_two) {
                if(e.getSource() == add_one)
                    configuration.setDualOne(true);
                else
                    configuration.setDualOne(false);
                configuration.getLoadUser().drawListUser();
                card.show(container, Configuration.LOAD_USER);
            }
            
        } else 
            gameTrunc();
        
        if(points_one >= configuration.getMaxPoints() || points_two >= configuration.getMaxPoints()) {            
            HandlerServiceBack.saveGame();
            isGameTerminated = true;
            gameTrunc();
        }
    }
    
    public void gameTrunc() {
            int seleccion = JOptionPane.showOptionDialog(
                    new JLabel(""),
                    "Reiniciar partida", 
                    "Haz ganado",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] { "Cancelar", "Aceptar" },   // null para YES, NO y CANCEL
                    "opcion 1");
            
            if (seleccion != -1)
                switch(seleccion){
                    case 0:
                        break;
                    default:
                        restart();
                        break;
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
