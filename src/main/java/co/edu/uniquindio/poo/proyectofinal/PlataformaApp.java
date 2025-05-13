package co.edu.uniquindio.poo.proyectofinal;

import co.edu.uniquindio.poo.proyectofinal.Controllers.VentanaController;
import javafx.application.Application;
import javafx.stage.Stage;


public class PlataformaApp extends Application {

    VentanaController ventanasController = VentanaController.getInstancia();
    @Override
    public void start(Stage primaryStage) throws Exception {
        ventanasController.setPrimaryStage(primaryStage);
        //ventanasController.navegarVentanas("/InicioView.fxml","Inicio",true,true);
        ventanasController.navegarVentanas("/servicioAlojamientosView.fxml","Inicio",true,true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}