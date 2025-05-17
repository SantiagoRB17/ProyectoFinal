package co.edu.uniquindio.poo.proyectofinal;

import co.edu.uniquindio.poo.proyectofinal.Controllers.VentanasController;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PlataformaApp extends Application {

    VentanasController ventanasController = VentanasController.getInstancia();
    @Override
    public void start(Stage stage) throws Exception {
        VentanasController controladorVentanas = VentanasController.getInstancia();
        controladorVentanas.setPrimaryStage(stage);
        controladorVentanas.navegarVentanas("/InicioView.fxml", "Inicio de sesión", false, false);
    }



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

    public static void main(String[] args) {
        launch(args);
}
}
