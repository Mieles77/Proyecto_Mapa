package Interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import Datos.BdRutas;
import Logica.GrafoRutas;
import Logica.Parada;
import Logica.PruebaRutas;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminController implements Initializable {
    
    @FXML
    private TableView<ParadaTabla> tabla;
    
    @FXML
    private TableColumn<ParadaTabla, Integer> casillaOrden;

    @FXML
    private TableColumn<ParadaTabla, String> casillaParada;
    
    @FXML
    private TableColumn<ParadaTabla, Void> casillaAccion;

    @FXML
    private ListView<String> listViewRutas;

    @FXML
    private TextField nombreParada;

    @FXML
    private TextField nombreRuta;

    @FXML
    private AnchorPane ventanaRuta;

    private ObservableList<ParadaTabla> datos = FXCollections.observableArrayList();
    private ObservableList<String> rutas;
    private int contadorOrden = 1;
    private PruebaRutas bdRutas;
        

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        casillaOrden.setCellValueFactory(new PropertyValueFactory<>("orden"));
        casillaParada.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        configurarBotonEliminar();
        
        
        tabla.setItems(datos);

        bdRutas = PruebaRutas.getinstancia();
        rutas = FXCollections.observableArrayList();
        
        for(GrafoRutas rut: bdRutas.getRutas()){
            rutas.add(rut.getId());
        }
        
        listViewRutas.setItems(rutas);
    }

    public String getNombreRuta() {
        return nombreRuta.getText();
    }

    public String getNombreParada() {
        return nombreParada.getText();
    }

    @FXML
    void crearParadas(ActionEvent event) {
        String nombre = getNombreParada();
        
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            
            ParadaTabla nuevaParada = new ParadaTabla(contadorOrden, nombre);
            datos.add(nuevaParada);
            contadorOrden++;
            
            nombreParada.clear();
        }
    }
    
    private void configurarBotonEliminar() {
        Callback<TableColumn<ParadaTabla, Void>, TableCell<ParadaTabla, Void>> cellFactory = 
            new Callback<TableColumn<ParadaTabla, Void>, TableCell<ParadaTabla, Void>>() {
                @Override
                public TableCell<ParadaTabla, Void> call(final TableColumn<ParadaTabla, Void> param) {
                    final TableCell<ParadaTabla, Void> cell = new TableCell<ParadaTabla, Void>() {

                        private final Button btnEliminar = new Button("Eliminar");

                        {
                            btnEliminar.setOnAction((ActionEvent event) -> {
                                ParadaTabla parada = getTableView().getItems().get(getIndex());
                                datos.remove(parada);
                                
                                
                                actualizarOrden();
                            });
                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(btnEliminar);
                            }
                        }
                    };
                    return cell;
                }
            };

        casillaAccion.setCellFactory(cellFactory);
    }
    
    private void actualizarOrden() {
        for (int i = 0; i < datos.size(); i++) {
            datos.get(i).setOrden(i + 1);
        }
        contadorOrden = datos.size() + 1;
        tabla.refresh();
    }

    
    public static class ParadaTabla {
        private int orden;
        private String nombre;

        public ParadaTabla(int orden, String nombre) {
            this.orden = orden;
            this.nombre = nombre;
        }

        public int getOrden() {
            return orden;
        }

        public void setOrden(int orden) {
            this.orden = orden;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    @FXML
    void guardarRuta(ActionEvent event) {
        if(nombreRuta.getText().isEmpty()){
            System.out.println("Nombre de la ruta vacio");
            return;
        }

        boolean encontrado = false;
        for(GrafoRutas ruta: bdRutas.getRutas()){
            if(nombreRuta.getText().equals(ruta.getId())){
                encontrado = true;
            }
        }

        if(encontrado){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ruta duplicada");
            alert.setHeaderText(null);
            alert.setContentText("Ya existe una ruta con el nombre: " + nombreRuta.getText());
            alert.showAndWait();
            return;
        }

        GrafoRutas ruta = new GrafoRutas(getNombreRuta());
        String paradaAnterior = null;
        for (ParadaTabla paradaTabla : datos) {
            ruta.agregarParada(new Parada(paradaTabla.getNombre().toLowerCase()));
            if(paradaTabla.getOrden()!= 1){
                ruta.conectar(paradaAnterior, paradaTabla.getNombre().toLowerCase());
            }
            paradaAnterior = paradaTabla.getNombre().toLowerCase();
        }
        bdRutas.adicionarRuta(ruta);
        bdRutas.guardarCambios();
        listViewRutas.getItems().clear();
        for(GrafoRutas rut: bdRutas.getRutas()){
            rutas.add(rut.getId());
        }
        nombreRuta.clear();
        limpiarTabla();
    }

    public void limpiarTabla(){
        if(tabla.getItems() != null){
            tabla.getItems().clear();
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
     void irAdminLogin(ActionEvent actionEvent) throws IOException{
        
        Parent LoginAdmin =  FXMLLoader.load(getClass().getResource("/Application/Interfaz1.fxml"));
        Scene AdminScene = new Scene(LoginAdmin);
        
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        
        window.setScene(AdminScene);
        window.show();
    }

}
