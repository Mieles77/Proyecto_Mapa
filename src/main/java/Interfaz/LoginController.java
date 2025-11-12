/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Logica.Administrador;
import Logica.Usuario;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 

/**
 *
 * @author sebas
 */
public class LoginController {
    
        @FXML
        private TextField correo;
        @FXML
        private TextField contraseña;
        @FXML
        private TextField nombre;
        
        @FXML
        private AnchorPane mensajeError;
        @FXML
        private AnchorPane mensajeError1;
        @FXML
        private AnchorPane mensajeError2;
        
    public String getCorreo() {
        return correo.getText();
    }

    public String getContraseña() {
        return contraseña.getText();
    }
    
     public String getNombre() {
        return nombre.getText();
    }
    
  @FXML
    void irPrincipal(ActionEvent event) throws IOException{
        if(getCorreo().isEmpty()){
            mensajeError.setVisible(true);
            
            // 🔽 Detectar clic en cualquier parte de la ventana
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setOnMousePressed(e -> mensajeError.setVisible(false));
        
        
            return;
        }
        
        mensajeError.setVisible(false);
        
         if(getContraseña().isEmpty()){
            mensajeError1.setVisible(true);
            
            // 🔽 Detectar clic en cualquier parte de la ventana
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setOnMousePressed(e -> mensajeError1.setVisible(false));
        
            return;
        }
        
        
        else{
        
    Parent principalRoot = FXMLLoader.load(getClass().getResource("/Application/Interfaz1.fxml"));
    Scene principalScene = new Scene(principalRoot);

        // Obtener el Stage actual
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(principalScene);
        window.show();
        }
    }
    
    @FXML
    void irPrincipalAdmin(ActionEvent event) throws IOException{
        
        if(getCorreo().isEmpty()){
            mensajeError.setVisible(true);
            return;
        }
        
        if(getContraseña().isEmpty()){
            mensajeError1.setVisible(true);
            return;
        }
        
        Parent principalRoot = FXMLLoader.load(getClass().getResource("/Application/InterfazAdmin.fxml"));
        Scene principalScene = new Scene(principalRoot);

        // Obtener el Stage actual
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(principalScene);
        window.show();
    }
    
    @FXML
     void irAdminLogin(ActionEvent actionEvent) throws IOException{
        
        Parent LoginAdmin =  FXMLLoader.load(getClass().getResource("/application/LoginAdmin.fxml"));
        Scene AdminScene = new Scene(LoginAdmin);
        
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        
        window.setScene(AdminScene);
        window.show();
    }
     
     @FXML
     void crearCuenta(ActionEvent actionEvent) throws IOException{
         
         Parent irCrarCuenta = FXMLLoader.load(getClass().getResource("/application/CrearCuenta.fxml"));
         Scene escenaCrearCuenta = new Scene(irCrarCuenta);
         
         Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        
        window.setScene(escenaCrearCuenta);
        window.show();
     }
     
     @FXML
    void irPrincipalAdmin_Crearcuenta(ActionEvent event) throws IOException{
        
        if(getNombre().isEmpty()){
            mensajeError.setVisible(true);
             // 🔽 Detectar clic en cualquier parte de la ventana
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setOnMousePressed(e -> mensajeError1.setVisible(false));
            return;
        }
        mensajeError.setVisible(false);
        
        if(getCorreo().isEmpty()){
            mensajeError1.setVisible(true);
             // 🔽 Detectar clic en cualquier parte de la ventana
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setOnMousePressed(e -> mensajeError1.setVisible(false));
            return;
        }
        mensajeError1.setVisible(false);
        
        if(getContraseña().isEmpty()){
            mensajeError2.setVisible(true);
             // 🔽 Detectar clic en cualquier parte de la ventana
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setOnMousePressed(e -> mensajeError1.setVisible(false));
            return;
        }
        
        mensajeError2.setVisible(false);
        

        
        Parent principalRoot = FXMLLoader.load(getClass().getResource("/Application/Interfaz1.fxml"));
        Scene principalScene = new Scene(principalRoot);

        // Obtener el Stage actual
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(principalScene);
        window.show();
    }
    
 
    
    
}

