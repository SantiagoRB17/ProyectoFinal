package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Servicios.ServicioBilleteras;
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

    private ServicioBilleteras serviciosBilletera = ServicioBilleteras.getInstancia();

    private final VentanasController ventanasController = VentanasController.getInstancia();

    @FXML
    void recargarSaldo(ActionEvent event) {

        try {
            String montoStr = txtSaldoMontoRecarga.getText();
            if(montoStr == null || montoStr.isEmpty()){
                ventanasController.mostrarAlerta("Debe ingresar un monto para recargar", Alert.AlertType.WARNING);
                return;
            }
            float monto = Float.parseFloat(montoStr);
            String numeroBilletera =
            serviciosBilletera.recargarBilletera(monto, numeroBilletera);

            VentanasController.getInstancia().mostrarAlerta("Se recargaron los saldos correctamente", Alert.AlertType.INFORMATION);
            txtSaldoMontoRecarga.clear();
        }catch (Exception e) {
            VentanasController.getInstancia().mostrarAlerta("Error al recargar el saldo", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void volverAInicio(ActionEvent event) throws Exception {
        ventanasController.navegarVentanas("/InicioView.fxml","Iniciar Sesión",false,false);

    }
}
