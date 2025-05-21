package co.edu.uniquindio.poo.proyectofinal.Controllers;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class estadisticasViewController implements Initializable {

    @FXML
    private BarChart<String, Number> chartOcupacion;

    @FXML
    private BarChart<String, Number> chartGanancias;

    // Tabla de alojamientos populares
    @FXML
    private TableView<AlojamientoPopular> tablaPopularidad;

    @FXML
    private TableColumn<AlojamientoPopular, Integer> colPosicion;

    @FXML
    private TableColumn<AlojamientoPopular, String> colNombreAlojamiento;

    @FXML
    private TableColumn<AlojamientoPopular, String> colCiudad;

    @FXML
    private TableColumn<AlojamientoPopular, Integer> colNumReservas;

    // Gráficos de tipos de alojamiento rentables
    @FXML
    private BarChart<String, Number> chartTiposRentables;

    @FXML
    private PieChart chartDistribucionTipos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar tabla
        configurarTabla();

        // Cargar datos de ejemplo
        cargarDatosOcupacion();
        cargarDatosGanancias();
        cargarDatosPopularidad();
        cargarDatosTiposRentables();
    }

    private void configurarTabla() {
        // Configurar columnas de la tabla de popularidad
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colNombreAlojamiento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colNumReservas.setCellValueFactory(new PropertyValueFactory<>("numReservas"));
    }

    private void cargarDatosOcupacion() {
        // Limpiar datos existentes
        chartOcupacion.getData().clear();

        // Crear serie de datos
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ocupación (%)");

        // Añadir datos de ejemplo
        series.getData().add(new XYChart.Data<>("Villa con vista al mar", 78));
        series.getData().add(new XYChart.Data<>("Apartamento céntrico", 85));
        series.getData().add(new XYChart.Data<>("Cabaña en el bosque", 63));
        series.getData().add(new XYChart.Data<>("Loft urbano", 73));
        series.getData().add(new XYChart.Data<>("Casa rural", 68));
        series.getData().add(new XYChart.Data<>("Ático con terraza", 82));
        series.getData().add(new XYChart.Data<>("Bungalow en la playa", 89));

        // Añadir serie al gráfico
        chartOcupacion.getData().add(series);

        // Aplicar estilo a las barras
        aplicarEstiloBarras(chartOcupacion, "#3b82f6");
    }

    private void cargarDatosGanancias() {
        // Limpiar datos existentes
        chartGanancias.getData().clear();

        // Crear serie de datos
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias (€)");

        // Añadir datos de ejemplo
        series.getData().add(new XYChart.Data<>("Villa con vista al mar", 24500));
        series.getData().add(new XYChart.Data<>("Apartamento céntrico", 18700));
        series.getData().add(new XYChart.Data<>("Cabaña en el bosque", 15200));
        series.getData().add(new XYChart.Data<>("Loft urbano", 19800));
        series.getData().add(new XYChart.Data<>("Casa rural", 16500));
        series.getData().add(new XYChart.Data<>("Ático con terraza", 22300));
        series.getData().add(new XYChart.Data<>("Bungalow en la playa", 28600));

        // Añadir serie al gráfico
        chartGanancias.getData().add(series);

        // Aplicar estilo a las barras
        aplicarEstiloBarras(chartGanancias, "#10b981");
    }

    private void cargarDatosPopularidad() {
        // Crear datos de ejemplo para la tabla
        ObservableList<AlojamientoPopular> datos = FXCollections.observableArrayList(
                new AlojamientoPopular(1, "Bungalow en la playa", "Málaga", 87),
                new AlojamientoPopular(2, "Apartamento céntrico", "Madrid", 82),
                new AlojamientoPopular(3, "Ático con terraza", "Barcelona", 76),
                new AlojamientoPopular(4, "Piso histórico", "Valencia", 68),
                new AlojamientoPopular(5, "Villa con vista al mar", "Alicante", 65),
                new AlojamientoPopular(6, "Estudio moderno", "Madrid", 61),
                new AlojamientoPopular(7, "Loft urbano", "Barcelona", 58),
                new AlojamientoPopular(8, "Chalet con piscina", "Sevilla", 52),
                new AlojamientoPopular(9, "Casa rural", "Granada", 49),
                new AlojamientoPopular(10, "Cabaña en el bosque", "Asturias", 45)
        );

        // Asignar datos a la tabla
        tablaPopularidad.setItems(datos);
    }

    private void cargarDatosTiposRentables() {
        // Limpiar datos existentes
        chartTiposRentables.getData().clear();

        // Crear serie de datos para el gráfico de barras
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias Totales (€)");

        // Añadir datos de ejemplo
        series.getData().add(new XYChart.Data<>("Apartamentos", 45000));
        series.getData().add(new XYChart.Data<>("Casas", 38000));
        series.getData().add(new XYChart.Data<>("Villas", 62000));
        series.getData().add(new XYChart.Data<>("Hostales", 28000));
        series.getData().add(new XYChart.Data<>("Cabañas", 32000));

        // Añadir serie al gráfico
        chartTiposRentables.getData().add(series);

        // Aplicar estilo a las barras
        aplicarEstiloBarras(chartTiposRentables, "#8b5cf6");

        // Crear datos para el gráfico de pastel
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Apartamentos", 45000),
                new PieChart.Data("Casas", 38000),
                new PieChart.Data("Villas", 62000),
                new PieChart.Data("Hostales", 28000),
                new PieChart.Data("Cabañas", 32000)
        );

        // Asignar datos al gráfico de pastel
        chartDistribucionTipos.setData(pieChartData);
    }

    private void aplicarEstiloBarras(BarChart<String, Number> chart, String colorHex) {
        // Aplicar estilos personalizados a las barras del gráfico
        for (XYChart.Series<String, Number> series : chart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                data.getNode().setStyle("-fx-bar-fill: " + colorHex + ";");
            }
        }
    }

    // Clase modelo para la tabla de alojamientos populares
    public static class AlojamientoPopular {
        private final int posicion;
        private final String nombre;
        private final String ciudad;
        private final int numReservas;

        public AlojamientoPopular(int posicion, String nombre, String ciudad, int numReservas) {
            this.posicion = posicion;
            this.nombre = nombre;
            this.ciudad = ciudad;
            this.numReservas = numReservas;
        }

        public int getPosicion() { return posicion; }
        public String getNombre() { return nombre; }
        public String getCiudad() { return ciudad; }
        public int getNumReservas() { return numReservas; }
    }
}
