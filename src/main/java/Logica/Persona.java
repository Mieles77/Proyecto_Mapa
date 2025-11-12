/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import javafx.scene.control.TextField;

/**
 *
 * @author sebas
 */
    
    
   public abstract class Persona {
       
    protected String correo;
    protected String contrasena;

    // Constructor
    public Persona(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Métodos comunes
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}