package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
import co.edu.uniquindio.poo.proyectofinal.Model.entidades.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.Objects;

/**
 * Controlador para la ventana de reseñas
 */
public class ventanaResenasViewController {

    @FXML
    private Label lblTitulo;

    @FXML
    private HBox contenedorEstrellas;

    @FXML
    private ImageView estrella1;

    @FXML
    private ImageView estrella2;

    @FXML
    private ImageView estrella3;

    @FXML
    private ImageView estrella4;

    @FXML
    private ImageView estrella5;

    @FXML
    private TextArea txtComentario;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEnviar;

    private int valoracion = 0;
    private String comentario = "";
    private boolean confirmado = false;
    private Image estrellaVacia;
    private Image estrellaLlena;

    private VentanaController ventanasController = VentanaController.getInstancia();
    @Setter
    private Reserva reservaObservable;
    @Setter
    private Alojamiento alojamientoObservable;

    /**
     * Inicializa el controlador
     */
    public void initialize() {
        // Cargar imágenes
        estrellaVacia = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/estrellaVacia.png")));
        estrellaLlena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/estrellaLlena.png")));

        // Configurar estilo de cursor para las estrellas
        estrella1.setStyle("-fx-cursor: hand;");
        estrella2.setStyle("-fx-cursor: hand;");
        estrella3.setStyle("-fx-cursor: hand;");
        estrella4.setStyle("-fx-cursor: hand;");
        estrella5.setStyle("-fx-cursor: hand;");
    }

    /**
     * Establece el nombre del alojamiento en el título
     *
     * @param nombreAlojamiento Nombre del alojamiento a reseñar
     */
    public void setNombreAlojamiento(String nombreAlojamiento) {
        lblTitulo.setText("Valorar " + nombreAlojamiento);
    }

    /**
     * Maneja el evento de selección de estrellas
     */
    @FXML
    private void seleccionarEstrella(javafx.scene.input.MouseEvent event) {
        Object source = event.getSource();
        
        if (source == estrella1) {
            valoracion = 1;
        } else if (source == estrella2) {
            valoracion = 2;
        } else if (source == estrella3) {
            valoracion = 3;
        } else if (source == estrella4) {
            valoracion = 4;
        } else if (source == estrella5) {
            valoracion = 5;
        }

        // Actualizar la visualización de las estrellas
        actualizarEstrellas();
    }

    /**
     * Actualiza la visualización de las estrellas según la valoración seleccionada
     */
    private void actualizarEstrellas() {
        if (valoracion >= 1) {
            estrella1.setImage(estrellaLlena);
        } else {
            estrella1.setImage(estrellaVacia);
        }

        if (valoracion >= 2) {
            estrella2.setImage(estrellaLlena);
        } else {
            estrella2.setImage(estrellaVacia);
        }

        if (valoracion >= 3) {
            estrella3.setImage(estrellaLlena);
        } else {
            estrella3.setImage(estrellaVacia);
        }

        if (valoracion >= 4) {
            estrella4.setImage(estrellaLlena);
        } else {
            estrella4.setImage(estrellaVacia);
        }

        if (valoracion >= 5) {
            estrella5.setImage(estrellaLlena);
        } else {
            estrella5.setImage(estrellaVacia);
        }

    }

    /**
     * Maneja el evento del botón Cancelar
     */
    @FXML
    private void cancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento del botón Enviar
     */
    @FXML
    private void enviar() {
        if (valoracion > 0 || !comentario.isEmpty()) {
            comentario = txtComentario.getText();
            confirmado = true;
            try{
                ventanasController.getPlataforma().anadirResena(reservaObservable.getIdReserva(),valoracion,comentario
                        ,alojamientoObservable.getId());
            }catch (Exception e){
                e.printStackTrace();
            }

            // Cerrar la ventana
            Stage stage = (Stage) btnEnviar.getScene().getWindow();
            stage.close();
        }else{
            ventanasController.mostrarAlerta("Debe seleccionar una estrella y agregar un comentario", Alert.AlertType.ERROR);
        }
    }

}
