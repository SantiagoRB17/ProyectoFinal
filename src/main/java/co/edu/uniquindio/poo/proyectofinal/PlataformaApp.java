package co.edu.uniquindio.poo.proyectofinal;

import co.edu.uniquindio.poo.proyectofinal.Controllers.VentanaController;
import javafx.application.Application;
import javafx.stage.Stage;


public class PlataformaApp extends Application {

    VentanaController ventanaController = VentanaController.getInstancia();
    @Override
    public void start(Stage stage) throws Exception {
        ventanaController.setPrimaryStage(stage);
        ventanaController.navegarVentanas("/InicioView.fxml", "Inicio de sesión", true, true);
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
