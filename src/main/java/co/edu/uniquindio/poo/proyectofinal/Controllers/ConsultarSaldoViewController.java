package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Billetera;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Persona;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import co.edu.uniquindio.poo.proyectofinal.Repositorios.RepositorioPersonas;
import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioPersonas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;



public class ConsultarSaldoViewController {

    @FXML
    private Button btnIrRecargar;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneConsultarSaldo;

    @FXML
    private TextField txtSaldo;

    private final VentanaController ventanasController = VentanaController.getInstancia();
    private final RepositorioPersonas repositorioPersonas= new RepositorioPersonas();
    private final Sesion sesion = Sesion.getInstancia();

    @FXML
    void initialize() {
        Persona persona = sesion.getPersona();
        Billetera billetera=ventanasController.getPlataforma().buscarPorNumero(persona.getNumeroBilletera());
        if (persona != null) {
            try {
                System.out.println("Email actual: " + persona.getEmail());
                double saldo = ventanasController.getPlataforma().consultarSaldo(persona.getEmail(), billetera);
                txtSaldo.setText("$" + String.format("%,.2f", saldo));
            } catch (Exception e) {
                txtSaldo.setText("Error al consultar el saldo: " + e.getMessage());
            }
            txtSaldo.setEditable(false);
        }
    }
    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/InicioView.fxml","Inicio",true,true);
    }

    @FXML
    void irRecargar(ActionEvent event) {
        try{
            ventanasController.navegarVentanas("/RecargarBilletera.fxml","Billetera",true, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
