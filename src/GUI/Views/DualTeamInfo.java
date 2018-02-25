/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private CardLayout card;
    
    public DualTeamInfo(CardLayout card){
        super();
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
        
        edit_one.addMouseListener(this);
        edit_two.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == edit_one) {
            String seleccion = JOptionPane.showInputDialog(
                    new JTextField(""),
                    "Puntos",
                    JOptionPane.QUESTION_MESSAGE);
        } 
        
        if(e.getSource() == edit_two) {
            String seleccion = JOptionPane.showInputDialog(
                    new JTextField(""),
                    "Puntos",
                    JOptionPane.QUESTION_MESSAGE);
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
