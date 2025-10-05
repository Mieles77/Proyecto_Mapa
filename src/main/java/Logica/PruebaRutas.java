package Logica;

import java.util.ArrayList;
import java.util.List;

public class PruebaRutas {

    private List<GrafoRutas> buses = new ArrayList<GrafoRutas>();

    public PruebaRutas() {
        GrafoRutas bus101 = new GrafoRutas("Bus101");
        bus101.agregarParada(new Parada("Nevada"));
        bus101.agregarParada(new Parada("Fundadores"));
        bus101.agregarParada(new Parada("25 diciembre"));
        bus101.agregarParada(new Parada("Novalito"));
        bus101.agregarParada(new Parada("Mayales"));
        bus101.agregarParada(new Parada("UNAD"));
        bus101.conectar("Nevada","Fundadores");
        bus101.conectar("Fundadores","25 diciembre");
        bus101.conectar("25 diciembre","Novalito");
        bus101.conectar("Novalito","Mayales");
        bus101.conectar("Mayales","UNAD");
        GrafoRutas bus102 = new GrafoRutas("Bus102");
        bus102.agregarParada(new Parada("Amaneceres del valle"));
        bus102.agregarParada(new Parada("Guataputi"));
        bus102.agregarParada(new Parada("UPC hurtado"));
        bus102.agregarParada(new Parada("UPC Sabanas"));
        bus102.agregarParada(new Parada("Nevada"));
        bus102.agregarParada(new Parada("UNAD"));
        bus102.agregarParada(new Parada("Area Andina"));
        bus102.conectar("Amaneceres del valle", "Guatapuri");
        bus102.conectar("Guatapuri","UPC hurtado");
        bus102.conectar("UPC hurtado","UPC Sabanas");
        bus102.conectar("UPC Sabanas","Nevada");
        bus102.conectar("Nevada","UNAD");
        bus102.conectar("UNAD","Area Andina");
        
        buses.add(bus101);
        buses.add(bus102);
    }

    public List<GrafoRutas> getBuses(){
        return this.buses;
    }
}
