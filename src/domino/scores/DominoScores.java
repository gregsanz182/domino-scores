/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

import Modelos.Participantes;
import Modelos.Partidas;
import Modelos.Rondas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author fmlia
 */
public class DominoScores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Principal p = new Principal();
        //pruebas();
        //mPruebas();
        //aPruebas();
        dpruebas();
    }
    
    public static void dpruebas() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice, puntaje, punt;
        choice = 0;
        String cad1, cad2;
        cad1 = cad2 = null;
        
        Dominota dom = new Dominota(150);
        ArrayList<String> nombres = new ArrayList<>();
        
        System.out.print("1. Individual         2. Pareja: ");
        choice = Integer.parseInt(br.readLine());
        
        if (choice == 1) {
            int cont = 0;
            while (cont < 4) {
                System.out.print("Nombre jugador " + (cont+1) + ": ");
                cad1 = br.readLine();
                if (cad1.equals("listo")) {
                    break;
                }
                nombres.add(cad1);
                cont++;
            }
            dom.iniciarPartidaIndividual(nombres);
        }
        
        if (choice == 2) {
            ArrayList<String> nombres2 = new ArrayList<>();
            int cont = 0;
            
            System.out.print("Nombre equipo 1: ");
            cad1 = br.readLine();

            System.out.print("Nombre jugador 1: ");
            cad1 = br.readLine();
            System.out.print("Nombre jugador 2: ");
            cad2 = br.readLine();
            nombres.add(cad1);
            nombres.add(cad2);

            System.out.print("Nombre equipo 2: ");
            cad1 = br.readLine();

            System.out.print("Nombre jugador 1: ");
            cad1 = br.readLine();
            System.out.print("Nombre jugador 2: ");
            cad2 = br.readLine();
            nombres2.add(cad1);
            nombres2.add(cad2);
            
            dom.iniciarPartidaGrupal(nombres, nombres2);
        }
        
        boolean vandera = false;
        while (vandera == false) {
            System.out.print("Cual participante ganó ronda? ");
            choice = Integer.parseInt(br.readLine());
            System.out.print("Puntaje: ");
            punt = Integer.parseInt(br.readLine());
            
            
            if (dom.ganarRonda(choice-1, punt) >= 100) {
                vandera = true;
            }
        }
        
        System.out.println(dom.guardarPartida());
    }
    
    public static void aPruebas() {
        SessionManager sm = new SessionManager();
        sm.consulta1();
    }
}
