/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

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
public class DualTeamInfo extends JPanel implements MouseListener{
    private JLabel titleMax = new JLabel("Maximo");
    private JLabel pointsMax = new JLabel("100");
    private JButton add_one = new JButton("Agregar");
    private JButton add_two = new JButton("Agregar");
    private JButton edit_one = new JButton("Agregar puntos");
    private JButton edit_two = new JButton("Agregar puntos");
    private JPanel panelPoints = new JPanel();
    
    private int points_one = 0;
    private int points_two = 0;
    private int posY = 0;
    private int tamPanel = 340;
    private boolean isGameTerminated = false;
    
    private CardLayout card;
    private JFrame frame;
    
    public DualTeamInfo(CardLayout card, JFrame frame){
        super();
        this.frame = frame;
        this.card = card;
        setLayout(null);
        setSize(720,520);
        
        add(titleMax);
        add(pointsMax);
        add(add_one);
        add(add_two);
        add(edit_one);
        add(edit_two);
        setVisible(true);
        
        titleMax.setBounds(335,20,50,20);
        pointsMax.setBounds(345,40,40,20);
        add_one.setBounds(210,100,100,30);
        add_two.setBounds(410,100,100,30);
        edit_one.setBounds(185,140,150,30);
        edit_two.setBounds(385,140,150,30);
        
        JScrollPane scrollPane = new JScrollPane(panelPoints);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 180, 720, 340);
        panelPoints.setPreferredSize(new Dimension(720, 390));
        panelPoints.setLayout(null);
        add(scrollPane);
        
        edit_one.addMouseListener(this);
        edit_two.addMouseListener(this);
    }
    
    private void restart() {
        posY = points_one = points_two = 0;
        panelPoints.removeAll();
        isGameTerminated = false;
        frame.paintAll(frame.getGraphics());
    }
    
    private void addPoints(boolean isOne, int points){
        JLabel newPoint = null;
        int posX = 0;
        
        if(isOne) {
            points_one += points;
            if(points_one > 100) {
                points_one = 100;
                isGameTerminated = true;
            }
            newPoint = new JLabel(points + "-" + points_one);
            posX = 185;
        } else {
            points_two += points;
            if(points_two > 100) {
                points_two = 100;
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
        
        if(points_one == 100 || points_two == 100)
            isGameTerminated = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!isGameTerminated) {
            if(e.getSource() == edit_one) {
                String seleccion = JOptionPane.showInputDialog(
                    new JTextField(""),
                    "Puntos",
                    JOptionPane.QUESTION_MESSAGE);
                if(seleccion != null)
                   addPoints(true, Integer.parseInt(seleccion));
            } 
        
            if(e.getSource() == edit_two) {
                 String seleccion = JOptionPane.showInputDialog(
                    new JTextField(""),
                    "Puntos",
                    JOptionPane.QUESTION_MESSAGE);
                 if(seleccion != null)
                    addPoints(false, Integer.parseInt(seleccion));
            }
        } else {
            int seleccion = JOptionPane.showOptionDialog(
                    new JLabel(""),
                    "Seleccione opcion", 
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
