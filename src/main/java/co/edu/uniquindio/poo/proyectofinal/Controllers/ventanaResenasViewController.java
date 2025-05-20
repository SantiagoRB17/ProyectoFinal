package co.edu.uniquindio.poo.proyectofinal.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
        // Determinar qué estrella se ha seleccionado
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
        estrella1.setImage(valoracion >= 1 ? estrellaLlena : estrellaVacia);
        estrella2.setImage(valoracion >= 2 ? estrellaLlena : estrellaVacia);
        estrella3.setImage(valoracion >= 3 ? estrellaLlena : estrellaVacia);
        estrella4.setImage(valoracion >= 4 ? estrellaLlena : estrellaVacia);
        estrella5.setImage(valoracion >= 5 ? estrellaLlena : estrellaVacia);
    }

    /**
     * Maneja el evento del botón Cancelar
     */
    @FXML
    private void cancelar() {
        // Cerrar la ventana sin confirmar
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento del botón Enviar
     */
    @FXML
    private void enviar() {
        if (valoracion > 0) {
            // Guardar el comentario
            comentario = txtComentario.getText();
            confirmado = true;

            // Cerrar la ventana
            Stage stage = (Stage) btnEnviar.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Obtiene la valoración seleccionada por el usuario
     * @return Valoración (1-5)
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * Obtiene el comentario escrito por el usuario
     * @return Texto del comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Indica si el usuario confirmó la reseña
     * @return true si el usuario envió la reseña, false si canceló
     */
    public boolean isConfirmado() {
        return confirmado;
    }
}
