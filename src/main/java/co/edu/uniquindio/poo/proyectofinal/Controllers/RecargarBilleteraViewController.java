package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RecargarBilleteraViewController {

    @FXML
    private Button btnRecargarSaldo;

    @FXML
    private Button btnVolverAInicio;

    @FXML
    private GridPane gridPaneRecargarBilletera;

    @FXML
    private TextField txtSaldoMontoRecarga;

    private final VentanaController ventanasController = VentanaController.getInstancia();
    private final Sesion sesion = Sesion.getInstancia();

    @FXML
    void recargarSaldo(ActionEvent event) {

        try {
            String montoStr = txtSaldoMontoRecarga.getText();
            if(montoStr == null || montoStr.isEmpty()){
                ventanasController.mostrarAlerta("Debe ingresar un monto para recargar", Alert.AlertType.WARNING);
                return;
            }
            float monto = Float.parseFloat(montoStr);
            String numeroBilletera=sesion.getPersona().getNumeroBilletera();
            ventanasController.getPlataforma().recargarBilletera(monto, numeroBilletera);

            VentanaController.getInstancia().mostrarAlerta("Saldo recargado correctamente", Alert.AlertType.INFORMATION);
            txtSaldoMontoRecarga.clear();

            ventanasController.navegarVentanas("/ConsultarSaldoView.fxml","Consultar Saldo",true,true);
        }catch (Exception e) {
            e.printStackTrace();
            VentanaController.getInstancia().mostrarAlerta("Error al recargar el saldo", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/InicioView.fxml","Iniciar Sesión",true,true);
    }
}
