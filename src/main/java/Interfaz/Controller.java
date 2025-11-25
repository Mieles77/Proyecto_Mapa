package Interfaz;

import Logica.GrafoRutas;
import Logica.GrafoRutas;
import Logica.PruebaRutas;
import Logica.PruebaRutas;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    private int menor = 999999;
    private String mejorBus = "Ruta no disponible"; 

    @FXML private ListView<String> resultados;
    private ObservableList<String> datosRuta;

    @FXML
    private TextField origen;
    @FXML
    private TextField destino;
    @FXML
    private AnchorPane mensajeError;
    @FXML
    private AnchorPane mensajeError1;
    
    public String getOrigen(){
        return origen.getText();
    }
    
    public String getDestino(){
        return destino.getText();
    }
    
    
    @FXML 
    public void initialize() {
        datosRuta = FXCollections.observableArrayList();
        resultados.setItems(datosRuta);
    }
    
    @FXML
    void BuscarRuta(ActionEvent event) {
        
         if(getOrigen().isEmpty()){
            mensajeError.setVisible(true);
            return;
        }
         mensajeError.setVisible(false);
        
        if(getDestino().isEmpty()){
            mensajeError1.setVisible(true);
            return;
        }
        mensajeError1.setVisible(false);
        
        String pOrigen = origen.getText().trim();
        String pDestino = destino.getText().trim();

        
        datosRuta.clear();
        menor = 999999;
        mejorBus = "Ruta no disponible";
        
        PruebaRutas bdRutas = PruebaRutas.getinstancia(); 
        
        for (GrafoRutas ruta: bdRutas.getRutas()){

            int pasos = ruta.numeroParadas(pOrigen, pDestino);
            
            if (pasos >= 0 && pasos < menor){
                menor = pasos;
                mejorBus = ruta.getId(); 
            }
        }
        
        if (menor != 999999) {
            datosRuta.add("¡Mejor Ruta Encontrada!");
            datosRuta.add("Bus: " + mejorBus);
            datosRuta.add("Tramos (paradas intermedias): " + menor);
        } else {
            datosRuta.add(mejorBus);
        }
    }
    
    
        @FXML
    void irLogin(ActionEvent actionEvent) throws IOException{
        
       Parent Login = FXMLLoader.load(getClass().getResource("/Application/Login.fxml"));
       Scene LoginScene = new Scene(Login);
       
         // Obtener el Stage actual
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(LoginScene);
        window.show();
    }
    
        @FXML
    void buscarRutas(ActionEvent event) throws IOException{
        
       
        Parent principalRoot = FXMLLoader.load(getClass().getResource("/Application/Interfaz1.fxml"));
        Scene principalScene = new Scene(principalRoot);

        // Obtener el Stage actual
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(principalScene);
        window.show();
    }
    
     @FXML
    void verRutas(ActionEvent Event) throws IOException{
        
        Parent Rutas = FXMLLoader.load(getClass().getResource("/Application/verRutas1.fxml"));
        Scene Escena = new Scene(Rutas);
        
         // Obtener el Stage actual
        Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();

        // Cambiar la escena
        window.setScene(Escena);
        window.show();
    }
       
}
