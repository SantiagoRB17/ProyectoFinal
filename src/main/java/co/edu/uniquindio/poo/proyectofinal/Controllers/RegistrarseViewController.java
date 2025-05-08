package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class RegistrarseViewController {

    @FXML
    private Text btnRegistrarse;

    @FXML
    private Button btnRegistrase;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneFormularioRegistrase;

    @FXML
    private Hyperlink linkIniciarSesion;

    @FXML
    private PasswordField passFieldContrasenaRegistrarse;

    @FXML
    private TextField txtApellidosRegistrarse;

    @FXML
    private TextField txtEmailRegistrarse;

    @FXML
    private TextField txtNombreCompletoRegistrarse;

    @FXML
    private TextField txtNumeroIdentificacionRegistrarse;

    @FXML
    private TextField txtNumeroTelefonoRegistrarse;

    RepositorioPersonas repositorioPersonas= RepositorioPersonas.getInstancia();

    private static VentanasController ventanasController = VentanasController.getInstancia();


    @FXML
    void registrarse(ActionEvent event) {

    }

    @FXML
    void volverAInicio(ActionEvent event) {

    }

    @FXML
    public void registrarCuenta(ActionEvent event) {
        try{
        repositorioPersonas.agregarPersona(
                Persona.settxtNombreCompletoRegistrarse,
                txtApellidosRegistrarse,
                txtEmailRegistrarse,
                txtNumeroIdentificacionRegistrarse,
                txtNumeroTelefonoRegistrarse
        );

    }

}
