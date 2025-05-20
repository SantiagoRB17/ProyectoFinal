package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoTemporal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class confirmarCodigoController {

    @FXML
    private Button btnConfirmarCodigo;

    @FXML
    private Text btnRegistrarse;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneFormularioIniciarSesion;

    @FXML
    private TextField txtCodigo;

    private final VentanaController ventanasController = VentanaController.getInstancia();


    /**
     * Gestiona la confirmación de un código ingresado por el usuario.
     * Valida el código y navega a la ventana de cambio de contraseña si es válido
     * @param event el evento que se activa al hacer clic en el botón de confirmación
     */
    @FXML
    void confirmarCodigo(ActionEvent event) {
        String codigoIngresado = txtCodigo.getText();

        if (codigoIngresado == null || codigoIngresado.isEmpty()) {
            ventanasController.mostrarAlerta("Debes ingresar un código", Alert.AlertType.ERROR);
            return;
        }


        if (CodigoTemporal.esCodigoValido(codigoIngresado)) {
            ventanasController.mostrarAlerta("Código correcto", Alert.AlertType.INFORMATION);
            try {
                ventanasController.navegarVentanas("/ConfirmarPassword.fxml", "Cambiar Contraseña", false, false);
            } catch (Exception e) {
                e.printStackTrace();
                ventanasController.mostrarAlerta("Error al cargar la ventana de cambio de contraseña:\n" + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            ventanasController.mostrarAlerta("El código ingresado no es válido", Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo que permite volver al inicio
     * @param event
     * @throws Exception
     */
    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
            ventanasController.navegarVentanas("IniciarSesion.fxml", "Inicio de Sesión", false, false);

    }
}
