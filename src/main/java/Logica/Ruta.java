package Logica;

import java.util.ArrayList;
import java.util.List;

public class Ruta {
   private String nombre;
   private List<Parada> paradas;
   private final double frecuencia;

    public Ruta(String nombre, ArrayList<Parada> paradas, double frecuencia){
        this.paradas = paradas;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
    }
}
