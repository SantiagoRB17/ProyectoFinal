package co.edu.uniquindio.poo.proyectofinal.App;

import co.edu.uniquindio.poo.proyectofinal.modelo.entidades.Persona;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ReservaApp extends Application {

    public static ReservaApp INSTANCE;

    public ReservaApp() {
        INSTANCE = this;
    }

    public static ReservaApp getInstancia() {
        if (INSTANCE == null) {
            INSTANCE = new ReservaApp();
        }
        return INSTANCE;
    }
    private Stage primary;

    @Override
    public void start(Stage primary) throws Exception {
        this.primary = primary;
        navegarVentanas("/IniciarSesion.fxml","Inicio",false);
    }

    /**
     *
     * @param nombrreArchivoFxml
     * @param tituloVentana
     * @param resize
     * @throws Exception
     */
    public void navegarVentanas(String nombrreArchivoFxml,String tituloVentana,Boolean resize) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombrreArchivoFxml));

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            primary.resizableProperty().setValue(resize);
            primary.setTitle(tituloVentana);
            primary.setScene(scene);
            primary.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void crearAlerta(String mensaje,Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Persona buscarPersona(String cedula, String password) {
        // Suponiendo que tienes una lista de personas (debes implementarla o cargarla)
        return getListaPersonas().stream()
                .filter(persona -> persona.getCedula().equals(cedula) && persona.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        launch(ReservaApp.class,args);
    }
}
