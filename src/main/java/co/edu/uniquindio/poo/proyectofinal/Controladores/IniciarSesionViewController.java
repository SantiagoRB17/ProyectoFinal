package co.edu.uniquindio.poo.proyectofinal.Controladores;

import co.edu.uniquindio.poo.proyectofinal.App.ReservaApp;
import co.edu.uniquindio.poo.proyectofinal.modelo.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.modelo.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.modelo.entidades.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class IniciarSesionViewController {

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneFormularioIniciarSesion;

    @FXML
    private PasswordField passFieldContrasenaInicioSesion;

    @FXML
    private TextField txtNumeroIdentificacionInicioSesion;

    private  final ReservaApp reservaApp=ReservaApp.getInstancia();

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {
        String cedula = txtNumeroIdentificacionInicioSesion.getText();
        String password = passFieldContrasenaInicioSesion.getText();

        if (cedula.isEmpty() || password.isEmpty()) {
            reservaApp.crearAlerta("Debe llenar todos los campos", Alert.AlertType.ERROR);
            return;
        }

        Persona personaEncontrada = buscarPersona(cedula, password);

        if (personaEncontrada == null) {
            reservaApp.crearAlerta("Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
        } else {
            Sesion.getInstancia().setPersona(personaEncontrada);
            reservaApp.crearAlerta("Bienvenido " + personaEncontrada.getNombres(), Alert.AlertType.INFORMATION);

            // Redirección basada en el tipo de persona
            if (personaEncontrada.getRol().equals("Administrador")) {
                reservaApp.navegarVentanas("/resources/EditarPerfil.fxml","panelEditar",false); // Debes implementar este método
            } else if (personaEncontrada.getRol().equals("Usuario")) {
                reservaApp.navegarVentanas(zi); // Debes implementar este método
            }
        }
    }


    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    void volverAInicio(ActionEvent event) {

    }







}