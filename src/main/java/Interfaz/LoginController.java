/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 

/**
 *
 * @author sebas
 */
public class LoginController {
    
 
    
      @FXML
    void irPrincipal(ActionEvent event) throws IOException{
        Parent principalRoot = FXMLLoader.load(getClass().getResource("/Application/Interfaz1.fxml"));
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
    
 
    
    
}

