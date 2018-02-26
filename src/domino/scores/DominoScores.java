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
        aPruebas();
        
        //Comienza partida en modo CLI
        //dpruebas();
    }
    
    public static void dpruebas() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice, puntaje, punt;
        choice = 0;
        String cad1, cad2, cad3, cad4;
        cad1 = cad2 = cad3 = cad4 = null;
        
        //Objeto que manejara los objetos de la Partida.
        //Construtor recibe como parametro el puntaje tope
        Dominota dom = new Dominota(150);
        
        //Array para nombres de jugadores
        ArrayList<String> nombres = new ArrayList<>();
        
        //Se pide el tipo de partida
        System.out.print("1. Individual         2. Pareja: ");
        choice = Integer.parseInt(br.readLine());
        
        //Partida Individual
        if (choice == 1) {
            int cont = 0;
            while (cont < 4) {
                System.out.print("Nombre jugador " + (cont+1) + ": ");
                cad1 = br.readLine();
                
                //Como se esta en CLI, al escribir 'listo' no se agregarán más 
                //jugadores
                if (cad1.equals("listo")) {
                    break;
                }
                
                //Se añade nombre de jugador al array
                nombres.add(cad1);
                cont++;
            }
            
            /*
             * Se inicializa la partida de forma individual
             * El metodo recibe un array<string> con los nombres de los jugadores
             * Devuelve falso si ocurrió un error en la conexion a la BD
             * Verdadero si fue exitoso
             */
            dom.iniciarPartidaIndividual(nombres);
        }
        
        //Partida Grupal
        if (choice == 2) {
            //Array para nombres del segundo equipo
            ArrayList<String> nombres2 = new ArrayList<>();
            
            //Se pide informacion de equipo
            System.out.print("Nombre equipo 1: ");
            cad3 = br.readLine();

            System.out.print("Nombre jugador 1: ");
            cad1 = br.readLine();
            System.out.print("Nombre jugador 2: ");
            cad2 = br.readLine();
            nombres.add(cad1);
            nombres.add(cad2);

            //Se pide informacion de equipo 2
            System.out.print("Nombre equipo 2: ");
            cad4 = br.readLine();

            System.out.print("Nombre jugador 1: ");
            cad1 = br.readLine();
            System.out.print("Nombre jugador 2: ");
            cad2 = br.readLine();
            nombres2.add(cad1);
            nombres2.add(cad2);
            
            /* 
             * Se inicializa la partida de forma grupal
             * El metodo recibe:
             *        una cadena con el nombre de primer equipo
             *        un array de cadenas con los nombres de los jugadores del primer equipo
             *        una cadena con el nombre del segundo equipo
             *        un array de cadenas con los nombres de los jugadores del segundo equipo
             * Devuelve falso si ocurrió un error en la conexion a la BD
             * Verdadero si fue exitoso
             */
            dom.iniciarPartidaGrupal(cad3, nombres, cad4, nombres2);
        }
        
        //Comienza la partida
        
        //Bandera para saber si acabó la partida
        boolean vandera = false;
        while (vandera == false) {
            /* 
             * Se pide el participante. Donde el numero es el orden en que se
             * ingresaron los participantes.
             * Comienza a partir de 1
             */
            System.out.print("Cual participante ganó ronda? ");
            choice = Integer.parseInt(br.readLine());
            System.out.print("Puntaje: ");
            punt = Integer.parseInt(br.readLine());
            
            /*
             * El metodo ganarRonda() recibe el indice del participante
             * y el puntaje que obtuvo en la ronda
             * Devuelve el nuevo puntaje del participante. Util para saber
             * si alcanzó el tope para ganar.
            */
            if (dom.ganarRonda(choice-1, punt) >= 100) {
                vandera = true;
            }
            
            //Existe tambien un metodo ganarRonda() que recibe un objeto Participante
            //Puede ser usado si no se quiere utilizar el indice y se posee el objeto
            //del participante que ha ganado la ronda
        }
        
        /*
         * guardarPartida() guarda todo lo que se lleva hasta ahora en la partida
         * Guarda en cascada las rondas, los participantes y la partida como tal
        */
        System.out.println(dom.guardarPartida());
    }
    
    public static void aPruebas() {
        SessionManager sm = new SessionManager();
        sm.consulta1();
    }
}
