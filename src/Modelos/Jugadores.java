package Modelos;
// Generated 24/02/2018 01:40:43 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Jugadores generated by hbm2java
 */
public class Jugadores  implements java.io.Serializable {


     private int id;
     private String apodo;
     private Set participantesesForJugadorDosId = new HashSet(0);
     private Set participantesesForJugadorUnoId = new HashSet(0);

    public Jugadores() {
    }

	
    public Jugadores(int id, String apodo) {
        this.id = id;
        this.apodo = apodo;
    }
    public Jugadores(int id, String apodo, Set participantesesForJugadorDosId, Set participantesesForJugadorUnoId) {
       this.id = id;
       this.apodo = apodo;
       this.participantesesForJugadorDosId = participantesesForJugadorDosId;
       this.participantesesForJugadorUnoId = participantesesForJugadorUnoId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getApodo() {
        return this.apodo;
    }
    
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    public Set getParticipantesesForJugadorDosId() {
        return this.participantesesForJugadorDosId;
    }
    
    public void setParticipantesesForJugadorDosId(Set participantesesForJugadorDosId) {
        this.participantesesForJugadorDosId = participantesesForJugadorDosId;
    }
    public Set getParticipantesesForJugadorUnoId() {
        return this.participantesesForJugadorUnoId;
    }
    
    public void setParticipantesesForJugadorUnoId(Set participantesesForJugadorUnoId) {
        this.participantesesForJugadorUnoId = participantesesForJugadorUnoId;
    }




}

