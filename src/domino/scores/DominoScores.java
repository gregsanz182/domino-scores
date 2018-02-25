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
        mPruebas();
    }
    
    public static void mPruebas() throws IOException {
        SessionManager sm = new SessionManager();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice, puntaje, punt;
        choice = puntaje = punt = 0;
        String cad1, cad2;
        cad1 = cad2 = null;
        ArrayList<Participantes> participantes = new ArrayList<>();
        int numRonda = 1;
        Rondas ronda = null;
        
        System.out.print("1. Individual         2. Pareja: ");
        choice = Integer.parseInt(br.readLine());
        
        Partidas par = new Partidas(new Date(2017, 11, 23), 100);
        if (choice == 1) {
            int cont = 0;
            while (cont < 4) {
                System.out.print("Nombre jugador " + (cont+1) + ": ");
                cad1 = br.readLine();
                if (cad1.equals("listo")) {
                    break;
                }
                participantes.add(new Participantes(sm.getAndSaveJugador(cad1)));
                participantes.get(participantes.size()-1).setPartidas(par);
                par.getParticipanteses().add(participantes.get(participantes.size()-1));
                cont++;
            }
        }
        
        if (choice == 2) {
            int cont = 0;
            while (cont < 2) {
                System.out.print("Nombre equipo " + (cont+1) + ": ");
                cad1 = br.readLine();
                
                System.out.print("Nombre jugador 1: ");
                cad1 = br.readLine();
                System.out.print("Nombre jugador 2: ");
                cad2 = br.readLine();
                participantes.add(new Participantes(sm.getAndSaveJugador(cad1), sm.getAndSaveJugador(cad2), cad1));
 
                
                participantes.get(participantes.size()-1).setPartidas(par);
                par.getParticipanteses().add(participantes.get(participantes.size()-1));
                cont++;
            }
        }
        
        while (puntaje < 100) {
            System.out.print("Cual participante ganó ronda? ");
            choice = Integer.parseInt(br.readLine());
            System.out.print("Puntaje: ");
            punt = Integer.parseInt(br.readLine());
            
            ronda = new Rondas(participantes.get(choice-1), par, numRonda+1, punt);
            par.getRondases().add(ronda);
            participantes.get(choice-1).getRondases().add(ronda);
            puntaje += punt;
            numRonda++;
        }
        
        sm.savePartida(par);
    }
    
}
