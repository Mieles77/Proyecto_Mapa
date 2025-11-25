package Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class bdUsuarios {
    private String nombreArchivo;

    public bdUsuarios(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    public void agregarUsuario(String correo, String clave){
        String usu = correo + " " + clave;

        File archivo = new File(this.nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(usu);
            salida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean buscarUsuario(String correo, String clave){
        String usu = correo + " " + clave;

        File archivo = new File(this.nombreArchivo);

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            do {
                lectura = entrada.readLine();
                if(lectura.equals(usu)){
                    return true;
                }
            } while (lectura != null);
            entrada.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
