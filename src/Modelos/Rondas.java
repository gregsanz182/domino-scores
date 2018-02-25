package Modelos;
// Generated 24/02/2018 07:05:41 PM by Hibernate Tools 4.3.1



/**
 * Rondas generated by hbm2java
 */
public class Rondas  implements java.io.Serializable {


     private int id;
     private Participantes participantes;
     private Partidas partidas;
     private int numeroRonda;
     private int puntaje;

    public Rondas() {
    }

    public Rondas(Participantes participantes, Partidas partidas, int numeroRonda, int puntaje) {
       this.participantes = participantes;
       this.partidas = partidas;
       this.numeroRonda = numeroRonda;
       this.puntaje = puntaje;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Participantes getParticipantes() {
        return this.participantes;
    }
    
    public void setParticipantes(Participantes participantes) {
        this.participantes = participantes;
    }
    public Partidas getPartidas() {
        return this.partidas;
    }
    
    public void setPartidas(Partidas partidas) {
        this.partidas = partidas;
    }
    public int getNumeroRonda() {
        return this.numeroRonda;
    }
    
    public void setNumeroRonda(int numeroRonda) {
        this.numeroRonda = numeroRonda;
    }
    public int getPuntaje() {
        return this.puntaje;
    }
    
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }




}

