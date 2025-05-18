package co.edu.uniquindio.poo.proyectofinal.Controllers;

import javafx.event.ActionEvent;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditarPerfilController {

    @FXML
    private Button btnConfirmarEdicion;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneFormularioEditarPerfil;

    @FXML
    private PasswordField passwordEditar;

    @FXML
    private TextField txtApellidosEditar;

    @FXML
    private TextField txtEmailEditar;

    @FXML
    private TextField txtNombreCompletoEditar;

    @FXML
    private TextField txtNumeroTelefonoEditar;


    private final VentanasController ventanasController = VentanasController.getInstancia();
    RepositorioPersonas repositorioPersonas = RepositorioPersonas.getInstancia();
    ServicioPersonas servicioPersonas = ServicioPersonas.getInstancia();
    @FXML
    void editar(ActionEvent event) {
        String apellidos = txtApellidosEditar.getText();
        String email = txtEmailEditar.getText();
        String nombreCompleto = txtNombreCompletoEditar.getText();
        String numeroTelefono = txtNumeroTelefonoEditar.getText();

        Persona persona = Sesion.getInstancia().getPersona();

    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("InicioView","Inicio",false,false);

    }

}
