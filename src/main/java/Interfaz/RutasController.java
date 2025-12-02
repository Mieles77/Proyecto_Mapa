/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.io.IOException;

import Logica.GrafoRutas;
import Logica.Parada;
import Logica.PruebaRutas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author sebas
 */
public class RutasController  implements Initializable{

    @FXML
    private VBox rutasGuardadas;
    private PruebaRutas bdRutas;

    @FXML
    private ListView<String> vistaRuta;
    private ObservableList<String> datos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bdRutas = PruebaRutas.getinstancia();
        cargarRutasEnInterfaz();
        datos = FXCollections.observableArrayList();
        vistaRuta.setItems(datos);
    }

    public void cargarRutasEnInterfaz(){
            
        rutasGuardadas.getChildren().clear(); 

        
        List<GrafoRutas> rutas = bdRutas.getRutas();

        
        for (GrafoRutas ruta : rutas) {
            
            Pane panelRuta = crearPanelRuta(ruta);
            
            
            rutasGuardadas.getChildren().add(panelRuta);
        }
    }

    private Pane crearPanelRuta(GrafoRutas ruta) {
        
        HBox hBoxRuta = new HBox(150); 
        hBoxRuta.setAlignment(Pos.CENTER_LEFT);
        hBoxRuta.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: #ccc;");
        
        
        Label lblCodigo = new Label(ruta.getId()); // Usamos el nombre como código/título
        lblCodigo.setStyle("-fx-font-weight: bold; -fx-text-fill: #1976D2;"); 

        
        Button btnVerMas = new Button("Ver más");
        btnVerMas.setOnAction(event -> {
           
            System.out.println("Detalles de la ruta: " + ruta.getId());
            cargarDetallesDeRuta(ruta);
        });

        hBoxRuta.getChildren().addAll(lblCodigo, new Region(), btnVerMas); 
        HBox.setHgrow(hBoxRuta.getChildren().get(1), Priority.ALWAYS);

        return hBoxRuta;
}

private void cargarDetallesDeRuta(GrafoRutas ruta) {
    vistaRuta.getItems().clear();
    List<Parada> parada = ruta.paradasTotales();
    for(Parada par: parada){
        datos.add(par.getId());
    }
}

    
      @FXML
    void buscarRutas(ActionEvent event) throws IOException{
               
        Parent principalRoot = FXMLLoader.load(getClass().getResource("/Application/Interfaz1.fxml"));
        Scene principalScene = new Scene(principalRoot);

        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        
        window.setScene(principalScene);
        window.show();
    }

    @FXML
    void irLogin(ActionEvent event) throws IOException{
        Parent Login = FXMLLoader.load(getClass().getResource("/Application/Login.fxml"));
        Scene LoginScene = new Scene(Login);
       
         // Obtener el Stage actual
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(LoginScene);
        window.show();
    }
    
}
