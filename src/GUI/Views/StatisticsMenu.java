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
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Joalcapa
 */
public class StatisticsMenu extends JPanel implements MouseListener{
    private CardLayout card;
    private JFrame frame;
    private Configuration configuration;
    private JButton first_query = new JButton("Partidas individual y grupal por jugador");
    private JButton second_query = new JButton("Partidas ganadas y en veces que quedo en cero por cada jugador");
    private JButton third_query = new JButton("Jugador con mas puntos en una ronda");
    private JButton quarter_query = new JButton("Porcentajes de victorias en rondas por cada jugador");
    private JButton extra_query = new JButton("Equipo con mas rondas");
    private JButton back_button = new JButton("ATRAS");
    
    public StatisticsMenu(CardLayout card, JFrame frame, Configuration configuration){
        super();
        this.card = card;
        this.frame = frame;
        this.configuration = configuration;
        setLayout(null);
        setSize(720,520);
        add(first_query);
        add(second_query);
        add(third_query);
        add(quarter_query);
        add(extra_query);
        add(back_button);
        setVisible(true);
        
        first_query.setBounds(110,80,500,30);
        second_query.setBounds(110,120,500,30);
        third_query.setBounds(110,160,500,30);
        quarter_query.setBounds(110,200,500,30);
        extra_query.setBounds(110,240,500,30);
        back_button.setBounds(310,290,100,30);
        
        first_query.addMouseListener(this);
        second_query.addMouseListener(this);
        third_query.addMouseListener(this);
        quarter_query.addMouseListener(this);
        extra_query.addMouseListener(this);
        back_button.addMouseListener(this);
    }
    
    public void firstQueryReport() {
        Map<String, Integer[]> c = HandlerServiceBack.getFirstQuery();
        DefaultPieDataset datasetGamesIndividual = new DefaultPieDataset();
        DefaultPieDataset datasetGamesDual = new DefaultPieDataset();
        
        for(String key: c.keySet()) {
            datasetGamesIndividual.setValue(key, new Double(c.get(key)[0]));
            datasetGamesDual.setValue(key, new Double(c.get(key)[1]));
        }
        
        JFreeChart chart = ChartFactory.createPieChart("Partidas Individuales", datasetGamesIndividual, true, true, false);
        ChartPanel panelIndividual = new ChartPanel(chart);
        JFrame windowIndividual = new JFrame();
        windowIndividual.setVisible(true);
        windowIndividual.setSize(400,400);
        windowIndividual.add(panelIndividual);
        
        JFreeChart chartDual = ChartFactory.createPieChart("Partidas Grupales", datasetGamesDual, true, true, false);
        ChartPanel panelDual = new ChartPanel(chartDual);
        JFrame windowDual = new JFrame();
        windowDual.setVisible(true);
        windowDual.setSize(400,400);
        windowDual.add(panelDual);
    }
    
    public void secondQueryReport() {
        Map<String, Integer[]> c = HandlerServiceBack.getSecondQuery();
        DefaultPieDataset datasetGamesWinner = new DefaultPieDataset();
        DefaultPieDataset datasetGamesZero = new DefaultPieDataset();
        
        for(String key: c.keySet()) {
            datasetGamesWinner.setValue(key, new Double(c.get(key)[0]));
            datasetGamesZero.setValue(key, new Double(c.get(key)[1]));
        }
        
        JFreeChart chart = ChartFactory.createPieChart("Partidas Ganadas", datasetGamesWinner, true, true, false);
        ChartPanel panelWinner = new ChartPanel(chart);
        JFrame windowWinner = new JFrame();
        windowWinner.setVisible(true);
        windowWinner.setSize(400,400);
        windowWinner.add(panelWinner);
        
        JFreeChart chartZero = ChartFactory.createPieChart("Veces que quedaron en CERO", datasetGamesZero, true, true, false);
        ChartPanel panelZero = new ChartPanel(chartZero);
        JFrame windowZero = new JFrame();
        windowZero.setVisible(true);
        windowZero.setSize(400,400);
        windowZero.add(panelZero);
    }
    
    public void thirdQueryReport() {
        Map<String, Integer> c = HandlerServiceBack.getThirdQuery();
        DefaultPieDataset datasetMaxPointsRound = new DefaultPieDataset();
        
        for(String key: c.keySet())
            datasetMaxPointsRound.setValue(key, new Double((double)c.get(key)));
        
        JFreeChart chart = ChartFactory.createPieChart("Jugador con mas puntos en una ronda", datasetMaxPointsRound, true, true, false);
        ChartPanel panelIndividual = new ChartPanel(chart);
        JFrame windowIndividual = new JFrame();
        windowIndividual.setVisible(true);
        windowIndividual.setSize(400,400);
        windowIndividual.add(panelIndividual);
    }
    
    public void quarterQueryReport() {
        Map<String, Float> c = HandlerServiceBack.getQuarterQuery();
        DefaultPieDataset datasetWinPercentage = new DefaultPieDataset();
        
        for(String key: c.keySet())
            datasetWinPercentage.setValue(key, new Double(c.get(key)));
        
        JFreeChart chart = ChartFactory.createPieChart("Porcentaje de victorias en rondas", datasetWinPercentage, true, true, false);
        ChartPanel panelWinPercentage = new ChartPanel(chart);
        JFrame windowWinPercentage = new JFrame();
        windowWinPercentage.setVisible(true);
        windowWinPercentage.setSize(400,400);
        windowWinPercentage.add(panelWinPercentage);
    }
    
    public void extraQueryReport() {
        String message = "No hay equipos registrados";
        Map<Integer, String[]> c = HandlerServiceBack.getExtraQuery();
        
        if(c != null) {
           int count = 0;
           String name_one = "";
           String name_two = "";
        
           for(Integer key: c.keySet()) {
               count = key;
               name_one = c.get(key)[0];
               name_two = c.get(key)[1];
           }
        
            message = "Cantidad: " + count + " Jugador: " + name_one + " Jugador: " + name_two;
        }
        
        JLabel box = new JLabel(message);
        
        JFrame windowWinPercentage = new JFrame();
        windowWinPercentage.setVisible(true);
        windowWinPercentage.setSize(500, 150);
        windowWinPercentage.add(box);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == back_button)
            card.show(container, Configuration.WELCOME);
        
        if(e.getSource() == first_query)
            firstQueryReport();
        
        if(e.getSource() == second_query)
            secondQueryReport();
        
        if(e.getSource() == third_query)
            thirdQueryReport();
        
        if(e.getSource() == quarter_query)
            quarterQueryReport();
        
        if(e.getSource() == extra_query)
            extraQueryReport();
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
