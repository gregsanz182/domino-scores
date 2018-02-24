/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

import java.awt.*;
import java.awt.Font.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Anny Chacon
 */
public class Principal extends JFrame {

    int height = 600, width = 650;
    JPanel menuPrincipal,titulo,opciones;
    JButton juegoEquipo,juegoIndividual,estadisticas;
    
    Principal(){
        initComponent();
    }

    private void initComponent() {
        setTitle("Dominota II");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menuPrincipal = new JPanel();
        menuPrincipal.setLayout(null);
        menuPrincipal.setPreferredSize(new Dimension(width, height));
        
        titulo = new JPanel();
        titulo.setLayout(null);
        titulo.setBounds(0, 0, width, 250);
        
        opciones = new JPanel();
        opciones.setLayout(null);
        opciones.setBounds(0, 250, width, 350);
        
        juegoEquipo = new JButton("Juego en Equipo");
        juegoEquipo.setBounds(50, 50, 200, 50); 
        juegoEquipo.setFont(new Font("Verdana", Font.BOLD, 16));
        
        juegoIndividual = new JButton("Juego Individual");
        juegoIndividual.setBounds(400, 50, 200, 50);
        juegoIndividual.setFont(new Font("Verdana", Font.BOLD, 16));
        
        estadisticas = new JButton("Estadísticas");
        estadisticas.setBounds(225, 150, 200, 50);
        estadisticas.setFont(new Font("Verdana", Font.BOLD, 16));
            
        opciones.add(juegoEquipo);
        opciones.add(juegoIndividual);
        opciones.add(estadisticas);
        
        menuPrincipal.add(titulo);
        menuPrincipal.add(opciones);

        getContentPane().add(menuPrincipal);
        pack();
        setLocationRelativeTo(null);
        
    }
    
}
