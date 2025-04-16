package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.PlataformaApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.*;

@Setter
public class VentanasController {
    private static VentanasController INSTANCE;

    private VentanasController() {
    }

    public static VentanasController getInstancia(){
        if(INSTANCE == null){
            INSTANCE = new VentanasController();
        }
        return INSTANCE;
    }
    public Stage primaryStage;
    /**
     * Metodo que permite navegar entre ventanas
     * @param nombreArchivoFxml nombre del archivo de la ventana
     * @param tituloVentana titulo de la ventana
     * @param resize parametro para establecer si la ventana se puede o no agrandar
     * @throws Exception
     */
    public void navegarVentanas(String nombreArchivoFxml, String tituloVentana, Boolean resize) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            primaryStage.resizableProperty().setValue(resize);
            primaryStage.setTitle(tituloVentana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
