package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Datos.BdRutas;


public class PruebaRutas implements Serializable{

    private List<GrafoRutas> rutas = new ArrayList<GrafoRutas>();
    private static PruebaRutas instancia = null;
    private String archivo = "Rutas.dat";
    private BdRutas persistencia = new BdRutas(archivo);
    

    public PruebaRutas() {
        ArrayList<GrafoRutas> rutasRegistradas = persistencia.leerBd();
        if(rutasRegistradas.isEmpty()){
            rutasPorDefecto();
        }
        else{
            this.rutas = rutasRegistradas;
        }
    }

    public void rutasPorDefecto(){
        GrafoRutas bus101 = new GrafoRutas("Bus101");
        bus101.agregarParada(new Parada("nevada"));
        bus101.agregarParada(new Parada("fundadores"));
        bus101.agregarParada(new Parada("25 diciembre"));
        bus101.agregarParada(new Parada("novalito"));
        bus101.agregarParada(new Parada("mayales"));
        bus101.agregarParada(new Parada("unad"));
        bus101.conectar("nevada","fundadores");
        bus101.conectar("fundadores","25 diciembre");
        bus101.conectar("25 diciembre","novalito");
        bus101.conectar("novalito","mayales");
        bus101.conectar("mayales","unad");
        GrafoRutas bus102 = new GrafoRutas("Bus102");
        bus102.agregarParada(new Parada("amaneceres del valle"));
        bus102.agregarParada(new Parada("guataputi"));
        bus102.agregarParada(new Parada("upc hurtado"));
        bus102.agregarParada(new Parada("upc sabanas"));
        bus102.agregarParada(new Parada("nevada"));
        bus102.agregarParada(new Parada("unad"));
        bus102.agregarParada(new Parada("area andina"));
        bus102.conectar("amaneceres del valle", "guatapuri");
        bus102.conectar("guatapuri","upc hurtado");
        bus102.conectar("upc hurtado","upc sabanas");
        bus102.conectar("upc sabanas","nevada");
        bus102.conectar("nevada","unad");
        bus102.conectar("unad","area andina");
        
        rutas.add(bus101);
        rutas.add(bus102);
    }

    public List<GrafoRutas> getRutas(){
        return this.rutas;
    }

    public void adicionarRuta(GrafoRutas ruta){
        this.rutas.add(ruta);
    }

    public static PruebaRutas getinstancia(){
        if(instancia == null){
            instancia = new PruebaRutas();
        }
        return instancia;
    }

    public void guardarCambios(){
        persistencia.agregarBd((ArrayList<GrafoRutas>)this.rutas);
    }
}
