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
public class IndividualInfo extends JPanel implements MouseListener {
    private CardLayout card;
    private JFrame frame;
    private Configuration configuration;
    private JPanel panelPoints = new JPanel();
    private JButton add_one = new JButton("Agregar");
    private JButton add_two = new JButton("Agregar");
    private JButton add_three = new JButton("Agregar");
    private JButton add_four = new JButton("Agregar");
    private JButton edit_one = new JButton("Puntos");
    private JButton edit_two = new JButton("Puntos");
    private JButton edit_three = new JButton("Puntos");
    private JButton edit_four = new JButton("Puntos");
    private JButton back_button = new JButton("ATRAS");
    private JButton play_game = new JButton("Jugar");
    
    private int points_one = 0;
    private int points_two = 0;
    private int points_three = 0;
    private int points_four = 0;
    private int posY = 0;
    private int tamPanel = 340;
    private boolean isGameTerminated = false;
    
    public IndividualInfo(CardLayout card, JFrame frame, Configuration configuration) {
        super();
        this.card = card;
        this.frame = frame;
        this.configuration = configuration;
        
        setLayout(null);
        setSize(720,520);
        
        add(add_one);
        add(add_two);
        add(add_three);
        add(add_four);
        add(edit_one);
        add(edit_two);
        add(edit_three);
        add(edit_four);
        setVisible(true);
        
        JScrollPane scrollPane = new JScrollPane(panelPoints);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 180, 720, 340);
        panelPoints.setPreferredSize(new Dimension(720, 390));
        panelPoints.setLayout(null);
        add(scrollPane);
        
        add_one.setBounds(85,100,100,30);
        add_two.setBounds(235,100,100,30);
        add_three.setBounds(385,100,100,30);
        add_four.setBounds(535,100,100,30);
        add_one.addMouseListener(this);
        add_two.addMouseListener(this);
        add_three.addMouseListener(this);
        add_four.addMouseListener(this);
        
        edit_one.setBounds(85,140,100,30);
        edit_two.setBounds(235,140,100,30);
        edit_three.setBounds(385,140,100,30);
        edit_four.setBounds(535,140,100,30);
        edit_one.addMouseListener(this);
        edit_two.addMouseListener(this);
        edit_three.addMouseListener(this);
        edit_four.addMouseListener(this);
        
        edit_one.setVisible(false);
        edit_two.setVisible(false);
        edit_three.setVisible(false);
        edit_four.setVisible(false);
        
        play_game.setBounds(310,150,100,30);
        play_game.addMouseListener(this);
        play_game.setVisible(false);
        add(play_game);
        add(back_button);
        
        back_button.setBounds(0,0,100,30);
        back_button.addMouseListener(this);
    }
    
    private void restart() {
        posY = points_one = points_two = points_three = points_four = 0;
        panelPoints.removeAll();
        isGameTerminated = false;
        frame.paintAll(frame.getGraphics());
    }
    
    private void addPoints(int player, int points){
        JLabel newPoint = null;
        int posX = 0;
        
        switch(player) {
            case 0:
                points_one += points;
                if(points_one > configuration.getMaxPoints()) {
                    points_one = configuration.getMaxPoints();
                    isGameTerminated = true;
                }
                newPoint = new JLabel(points + "-" + points_one);
                posX = 85;
                break;
            case 1:
                points_two += points;
                if(points_two > configuration.getMaxPoints()) {
                    points_two = configuration.getMaxPoints();
                    isGameTerminated = true;
                }
                newPoint = new JLabel(points + "-" + points_two);
                posX = 235;
                break;
            case 2:
                points_three += points;
                if(points_three > configuration.getMaxPoints()) {
                    points_three = configuration.getMaxPoints();
                    isGameTerminated = true;
                }
                newPoint = new JLabel(points + "-" + points_three);
                posX = 385;
                break;
            default:
                points_four += points;
                if(points_four > configuration.getMaxPoints()) {
                    points_four = configuration.getMaxPoints();
                    isGameTerminated = true;
                }
                newPoint = new JLabel(points + "-" + points_four);
                posX = 535;
                break;
        }
        
        newPoint.setBounds(posX,posY,100,30);
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
                if(configuration.IndividualValidate(0) && configuration.IndividualGame()) {
                    String seleccion = JOptionPane.showInputDialog(
                       new JTextField(""),
                       "Puntos",
                       JOptionPane.QUESTION_MESSAGE);
                    if(seleccion != null) {
                        int pointsWinner = Integer.parseInt(seleccion);
                        addPoints(0, pointsWinner);
                        HandlerServiceBack.asignPoints(configuration.searchPositionPlayer(0), pointsWinner);
                    }
                } else {
                    String message = "";
                    if(!configuration.IndividualGame())
                        message = "Se necesita minimo 2 jugadores para jugar";
                    if(!configuration.IndividualValidate(0))
                        message = "No hay jugador asignado para este puesto";
                    
                    JOptionPane.showOptionDialog(
                       new JLabel(""),
                       message,
                       "",
                       JOptionPane.YES_NO_CANCEL_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       new Object[] { "Aceptar"},   // null para YES, NO y CANCEL
                       "opcion 1"); 
                }
            }
        
            if(e.getSource() == edit_two) {
                if(configuration.IndividualValidate(1) && configuration.IndividualGame()) {
                    String seleccion = JOptionPane.showInputDialog(
                       new JTextField(""),
                       "Puntos",
                       JOptionPane.QUESTION_MESSAGE);
                    if(seleccion != null) {
                        int pointsWinner = Integer.parseInt(seleccion);
                       addPoints(1, pointsWinner);
                       HandlerServiceBack.asignPoints(configuration.searchPositionPlayer(1), pointsWinner);
                    }
                } else {
                    String message = "";
                    if(!configuration.IndividualGame())
                        message = "Se necesita minimo 2 jugadores para jugar";
                    if(!configuration.IndividualValidate(1))
                        message = "No hay jugador asignado para este puesto";
                    
                    JOptionPane.showOptionDialog(
                       new JLabel(""),
                       message,
                       "",
                       JOptionPane.YES_NO_CANCEL_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       new Object[] { "Aceptar"},   // null para YES, NO y CANCEL
                       "opcion 1"); 
                }
            }
            
            if(e.getSource() == edit_three) {
                if(configuration.IndividualValidate(2) && configuration.IndividualGame()) {
                    String seleccion = JOptionPane.showInputDialog(
                       new JTextField(""),
                       "Puntos",
                       JOptionPane.QUESTION_MESSAGE);
                    if(seleccion != null) {
                        int pointsWinner = Integer.parseInt(seleccion);
                       addPoints(2, pointsWinner);
                       HandlerServiceBack.asignPoints(configuration.searchPositionPlayer(2), pointsWinner);
                    }
                } else {
                    String message = "";
                    if(!configuration.IndividualGame())
                        message = "Se necesita minimo 2 jugadores para jugar";
                    if(!configuration.IndividualValidate(2))
                        message = "No hay jugador asignado para este puesto";
                    
                    JOptionPane.showOptionDialog(
                       new JLabel(""),
                       message,
                       "",
                       JOptionPane.YES_NO_CANCEL_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       new Object[] { "Aceptar"},   // null para YES, NO y CANCEL
                       "opcion 1"); 
                }
            }
            
            if(e.getSource() == edit_four) {
                if(configuration.IndividualValidate(3) && configuration.IndividualGame()) {
                    String seleccion = JOptionPane.showInputDialog(
                       new JTextField(""),
                       "Puntos",
                       JOptionPane.QUESTION_MESSAGE);
                    if(seleccion != null) {
                        int pointsWinner = Integer.parseInt(seleccion);
                       addPoints(3, pointsWinner);
                       HandlerServiceBack.asignPoints(configuration.searchPositionPlayer(3), pointsWinner);
                    }
                } else {
                    String message = "";
                    if(!configuration.IndividualGame())
                        message = "Se necesita minimo 2 jugadores para jugar";
                    if(!configuration.IndividualValidate(3))
                        message = "No hay jugador asignado para este puesto";
                    
                    JOptionPane.showOptionDialog(
                       new JLabel(""),
                       message,
                       "",
                       JOptionPane.YES_NO_CANCEL_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       new Object[] { "Aceptar"},   // null para YES, NO y CANCEL
                       "opcion 1"); 
                }
            }
            
            if(e.getSource() == add_one || e.getSource() == add_two || e.getSource() == add_three || e.getSource() == add_four) {
                if(e.getSource() == add_one)
                    configuration.setIndividualPlayer(0);
                if(e.getSource() == add_two)
                    configuration.setIndividualPlayer(1);
                if(e.getSource() == add_three)
                    configuration.setIndividualPlayer(2);
                if(e.getSource() == add_four)
                    configuration.setIndividualPlayer(3);
                configuration.getLoadUser().drawListUser();
                card.show(container, Configuration.LOAD_USER);
            }
            
        } else 
            gameTrunc();
        
       
       if(configuration.IndividualGame() && !configuration.isRunningGame()) {
           play_game.setVisible(true);
       }
       
       if(e.getSource() == play_game) {
            if(configuration.IndividualValidate(0)) edit_one.setVisible(true);
            if(configuration.IndividualValidate(1)) edit_two.setVisible(true);
            if(configuration.IndividualValidate(2)) edit_three.setVisible(true);
            if(configuration.IndividualValidate(3)) edit_four.setVisible(true);
            configuration.savedPlayersIndividual();
            play_game.setVisible(false);
            configuration.setRunningGame(true);
       }
       
       if(points_one >= configuration.getMaxPoints() || points_two >= configuration.getMaxPoints()
                || points_three >= configuration.getMaxPoints() || points_four >= configuration.getMaxPoints()) {            
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
