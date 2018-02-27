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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Joalcapa
 */
public class StatisticsIllustrator extends JPanel implements MouseListener {
    private JButton back_button = new JButton("ATRAS");
    private CardLayout card;
    private JFrame frame;
    private Configuration configuration;
    
    public StatisticsIllustrator(CardLayout card, JFrame frame, Configuration configuration) {
        super();
        this.card = card;
        this.frame = frame;
        this.configuration = configuration;
        setLayout(null);
        setSize(720,520);
        add(back_button);
        setVisible(true);
        
        back_button.setBounds(310,50,100,30);
        
        back_button.addMouseListener(this);
    }
    
    public void restructure() {
        if(configuration.getQueryType() == Configuration.FIRST_QUERY) 
            firstQueryReport();
        
        if(configuration.getQueryType() == Configuration.SECOND_QUERY) 
            secondQueryReport();
        
        if(configuration.getQueryType() == Configuration.THIRD_QUERY) 
            thirdQueryReport();
        
        if(configuration.getQueryType() == Configuration.QUARTER_QUERY) 
            quarterQueryReport();
    }
    
    public void firstQueryReport() {
        Map<String, Integer[]> c = HandlerServiceBack.getFirstQuery();
        for(String key: c.keySet()) {
            System.out.println(key + "          " + c.get(key)[0] + "      " + c.get(key)[1]);
        }
    }
    
    public void secondQueryReport() {
        
    }
    
    public void thirdQueryReport() {
        
    }
    
    public void quarterQueryReport() {
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(e.getSource() == back_button)
            card.show(container, Configuration.STATISTICS);
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
