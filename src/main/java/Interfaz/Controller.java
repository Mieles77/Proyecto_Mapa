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
import javafx.stage.Stage;

// Archivo: Controller.java (MODIFICADO)

public class Controller {
    private int menor = 999999;
    private String mejorBus = "Ruta no disponible"; // Inicializar con mensaje por defecto

    @FXML private ListView<String> resultados;
    private ObservableList<String> datosRuta;

    @FXML
    private TextField origen;
    @FXML
    private TextField destino;
    
    // 1. Inicializa la lista Observable solo una vez (al cargar el FXML)
    @FXML 
    public void initialize() {
        datosRuta = FXCollections.observableArrayList();
        resultados.setItems(datosRuta);
    }
    
    @FXML
    void BuscarRuta(ActionEvent event) {
        String pOrigen = origen.getText().trim();
        String pDestino = destino.getText().trim();

        // Limpiar y resetear el estado en cada búsqueda
        datosRuta.clear();
        menor = 999999;
        mejorBus = "Ruta no disponible";
        
        // 2. Ineficiencia: Se recomienda crear 'prueba' una sola vez en el constructor/initialize
        PruebaRutas prueba = new PruebaRutas(); 
        
        for (GrafoRutas bus: prueba.getBuses()){
            // 3. Solo llama a numeroParadas una vez por eficiencia
            int pasos = bus.numeroParadas(pOrigen, pDestino);
            
            // 4. Lógica de comparación: Solo si pasos es válido (>= 0)
            if (pasos >= 0 && pasos < menor){
                menor = pasos;
                mejorBus = bus.getId(); // Usas getId() del GrafoRutas
            }
        }
        
        // 5. Mostrar resultados
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
}
