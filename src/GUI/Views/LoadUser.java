/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import GUI.Configuration;
import static GUI.Views.Welcome.container;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class LoadUser extends JPanel implements MouseListener{
    private CardLayout card;
    private JFrame frame;
    private Configuration configuration;
    private JPanel panelListUser = new JPanel();
    private JButton btn_new = new JButton("Nuevo");
    private JButton btn_back = new JButton("Atras");
    
    private int posY = 0;
    private int tamPanel = 400;
    
    public LoadUser(CardLayout card, JFrame frame, Configuration configuration){
        super();
        this.configuration = configuration;
        configuration.setLoadUser(this);
        this.card = card;
        this.frame = frame;
        
        setLayout(null);
        setSize(720,520);
        
        add(btn_new);
        add(btn_back);
        
        btn_new.addMouseListener(this);
        btn_back.addMouseListener(this);
        
        btn_new.setBounds(210, 0, 100, 30);
        btn_back.setBounds(410, 0, 100, 30);
        
        JScrollPane scrollPane = new JScrollPane(panelListUser);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 40, 720, 480);
        panelListUser.setPreferredSize(new Dimension(720, 480));
        panelListUser.setLayout(null);
        add(scrollPane);
    }
    
    public void drawListUser() {
        panelListUser.removeAll();
        posY = 0;
        
        for(int i=0; i<configuration.getUsers().size(); i++) {
            JLabel newUserText = new JLabel((String) configuration.getUsers().get(i));
            newUserText.setBounds(50, posY, 100, 20);
            posY += 30;
            panelListUser.add(newUserText);
        }
        
        if(posY > tamPanel)
            panelListUser.setPreferredSize(new Dimension(720, posY + 50));
        
        frame.paintAll(frame.getGraphics());
    }
    
    private void addUser(String name){
        configuration.addUser(name);
        drawListUser();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(e.getSource() == btn_new) {
            String seleccion = JOptionPane.showInputDialog(
                    new JTextField(""),
                    "Nombre",
                    JOptionPane.QUESTION_MESSAGE);
            if(seleccion != null)
                addUser(seleccion);
       }
       
       if(e.getSource() == btn_back)
           card.show(container, Configuration.DUAL_TEAM_INFO);
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
