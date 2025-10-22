package Interfaz;

import java.awt.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

       @Override
public void start(Stage primaryStage) {
    login(primaryStage); // Aquí sí se llama correctamente
}

    public void login(Stage primaryStage){
        try{
            
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
            Scene scene1 = new Scene(loader1.load(), 800, 600);
            primaryStage.setTitle("MiBus");
            
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (IOException e) {
    e.printStackTrace(); // Esto imprime el error en la consola
}

    }
    
    public void start2(Stage secondStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/Interfaz1.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            secondStage.setTitle("MiBus");
            

            secondStage.setScene(scene);
           secondStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
