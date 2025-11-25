package Datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Logica.GrafoRutas;

public class BdRutas {
    private String archivo;

    public BdRutas(String archivo){
        this.archivo = archivo;
    }

    public void agregarBd(ArrayList<GrafoRutas> rutas){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(rutas);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GrafoRutas> leerBd(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            return (ArrayList<GrafoRutas>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
