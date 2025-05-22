package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Utils.CodigoTemporal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Controlador encargado de manejar la funcionalidad de la ventana de confirmación de código.
 */
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
            // Obtiene el modo (puede ser "registro" o "recuperación").
            String modo = CodigoTemporal.getModo();
            // Obtiene el correo asociado al código temporal.
            String correo = CodigoTemporal.getCorreo();

            // Si el modo es "recuperación", navega a la ventana para cambiar la contraseña.
            if ("recuperacion".equalsIgnoreCase(modo)) {
                ventanasController.mostrarAlerta("Código correcto", Alert.AlertType.INFORMATION);
                try {
                    ventanasController.navegarVentanas("/ConfirmarPassword.fxml", "Cambiar Contraseña", false, false);
                } catch (Exception e) {
                    e.printStackTrace();
                    ventanasController.mostrarAlerta("Error al cargar la ventana de cambio de contraseña:\n" + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
            // Si el modo es "registro", crea un nuevo usuario y activa su cuenta.
            else if ("registro".equalsIgnoreCase(modo)) {
                try {
                    // Crea un nuevo usuario en la plataforma utilizando los datos almacenados en CódigoTemporal.
                    ventanasController.getPlataforma().crearUsuario(
                            CodigoTemporal.getNombre(),
                            CodigoTemporal.getApellido(),
                            CodigoTemporal.getCedula(),
                            CodigoTemporal.getCorreo(),
                            CodigoTemporal.getTelefono(),
                            CodigoTemporal.getPassword(),
                            CodigoTemporal.getRol()
                    );

                    // Busca a la persona recién creada utilizando su correo.
                    Persona persona = ventanasController.getPlataforma().recuperarPersonaPorEmail(correo);
                    if (persona != null) {
                        // Activa la cuenta de la persona y actualiza sus datos.
                        persona.setCuentaActiva(true);
                        ventanasController.getPlataforma().editarPersona(persona.getNombre(), persona.getApellidos(), persona.getCedula(), persona.getEmail(), persona.getTelefono(), persona.getPassword());
                        // Muestra un mensaje de éxito e invita al usuario a iniciar sesión.
                        ventanasController.mostrarAlerta("Cuenta activada correctamente. Ya puedes iniciar sesión.", Alert.AlertType.INFORMATION);
                        ventanasController.navegarVentanas("/IniciarSesion.fxml", "Inicio de Sesión", true, true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ventanasController.mostrarAlerta("Error al activar la cuenta:\n" + e.getMessage(), Alert.AlertType.ERROR);
                }
            }

            // Limpia la información almacenada en CódigoTemporal.
            CodigoTemporal.setCodigo(null);
            CodigoTemporal.setModo(null);
        } else {
            ventanasController.mostrarAlerta("El código ingresado no es válido", Alert.AlertType.ERROR);
        }
    }

    /**
     * Método que se ejecuta al presionar el botón "Volver a Inicio".
     * Navega de vuelta a la ventana de inicio de sesión.
     *
     * @param event Evento que activa este método cuando se hace clic en el botón.
     * @throws Exception Si ocurre un error al intentar cargar la ventana de inicio de sesión.
     */
    /**
     * Metodo que permite volver al inicio
     * @param event
     * @throws Exception
     */
    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
            ventanasController.navegarVentanas("/IniciarSesion.fxml", "Inicio de Sesión", false, false);
    }
}
