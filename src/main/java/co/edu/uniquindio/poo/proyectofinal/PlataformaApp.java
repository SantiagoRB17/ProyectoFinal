package co.edu.uniquindio.poo.proyectofinal;

import co.edu.uniquindio.poo.proyectofinal.Controllers.VentanasController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PlataformaApp extends Application {

    VentanasController ventanasController = VentanasController.getInstancia();
    @Override
    public void start(Stage primaryStage) throws Exception {
        ventanasController.setPrimaryStage(primaryStage);
        //ventanasController.navegarVentanas("/InicioView.fxml","Inicio",true,true);
        ventanasController.navegarVentanas("/servicioAlojamientosView.fxml","Inicio",true,true);
    }
import co.edu.uniquindio.poo.proyectofinal.Controllers.VentanasController;
import javafx.application.Application;
import javafx.stage.Stage;


public class PlataformaApp extends Application {
    public static PlataformaApp INSTANCE;

    public PlataformaApp() {
        INSTANCE = this;
    }

    public static PlataformaApp getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlataformaApp();
        }
        return INSTANCE;
    }

    VentanasController ventanasController = VentanasController.getInstancia();
    @Override
    public void start(Stage primaryStage) throws Exception {
        ventanasController.setPrimaryStage(primaryStage);
        ventanasController.navegarVentanas("/IniciarSesion.fxml","Inicio",true,true);
    }

    public static void main(String[] args) {
        launch(args);
}
}
    public static void main(String[] args) {
        launch(args);
    }
}