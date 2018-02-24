/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.scores;

/**
 *
 * @author fmlia
 */
public class DominoScores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Principal p = new Principal();
        pruebas();
    }
    
    public static void pruebas() {
        SessionManager sm = new SessionManager();
        sm.getAndSaveJugador("dfdfdfdfdf");
    }
    
}
