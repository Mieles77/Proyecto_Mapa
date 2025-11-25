package Interfaz;

import java.net.URL;
import java.util.ResourceBundle;

import Datos.BdRutas;
import Logica.GrafoRutas;
import Logica.Parada;
import Logica.PruebaRutas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
        GrafoRutas ruta = new GrafoRutas(getNombreRuta());
        String paradaAnterior = null;
        for (ParadaTabla paradaTabla : datos) {
            ruta.agregarParada(new Parada(paradaTabla.getNombre()));
            if(paradaTabla.getOrden()!= 1){
                ruta.conectar(paradaAnterior, paradaTabla.getNombre());
            }
            paradaAnterior = paradaTabla.getNombre();
        }
        bdRutas.adicionarRuta(ruta);
        bdRutas.guardarCambios();
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
}