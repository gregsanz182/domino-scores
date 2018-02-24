package Modelos;
// Generated 24/02/2018 07:05:41 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Participantes generated by hbm2java
 */
public class Participantes  implements java.io.Serializable {


     private int id;
     private Jugadores jugadoresByJugadorDosId;
     private Jugadores jugadoresByJugadorUnoId;
     private Partidas partidas;
     private String nombreGrupo;
     private Set rondases = new HashSet(0);

    public Participantes() {
    }

	
    public Participantes(Jugadores jugadoresByJugadorUnoId) {
        this.jugadoresByJugadorUnoId = jugadoresByJugadorUnoId;
    }
    public Participantes(Jugadores jugadoresByJugadorDosId, Jugadores jugadoresByJugadorUnoId, Partidas partidas, String nombreGrupo, Set rondases) {
       this.jugadoresByJugadorDosId = jugadoresByJugadorDosId;
       this.jugadoresByJugadorUnoId = jugadoresByJugadorUnoId;
       this.partidas = partidas;
       this.nombreGrupo = nombreGrupo;
       this.rondases = rondases;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Jugadores getJugadoresByJugadorDosId() {
        return this.jugadoresByJugadorDosId;
    }
    
    public void setJugadoresByJugadorDosId(Jugadores jugadoresByJugadorDosId) {
        this.jugadoresByJugadorDosId = jugadoresByJugadorDosId;
    }
    public Jugadores getJugadoresByJugadorUnoId() {
        return this.jugadoresByJugadorUnoId;
    }
    
    public void setJugadoresByJugadorUnoId(Jugadores jugadoresByJugadorUnoId) {
        this.jugadoresByJugadorUnoId = jugadoresByJugadorUnoId;
    }
    public Partidas getPartidas() {
        return this.partidas;
    }
    
    public void setPartidas(Partidas partidas) {
        this.partidas = partidas;
    }
    public String getNombreGrupo() {
        return this.nombreGrupo;
    }
    
    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    public Set getRondases() {
        return this.rondases;
    }
    
    public void setRondases(Set rondases) {
        this.rondases = rondases;
    }




}


