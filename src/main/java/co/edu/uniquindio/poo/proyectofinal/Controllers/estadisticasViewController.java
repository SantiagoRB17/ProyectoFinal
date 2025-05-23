package co.edu.uniquindio.poo.proyectofinal.Controllers;

import co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory.Alojamiento;
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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class estadisticasViewController implements Initializable {

    @FXML
    private BarChart<String, Number> chartOcupacion;

    @FXML
    private BarChart<String, Number> chartGanancias;

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

    @FXML
    private BarChart<String, Number> chartTiposRentables;

    @FXML
    private PieChart chartDistribucionTipos;

    private final VentanaController ventanaController=VentanaController.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colNombreAlojamiento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colNumReservas.setCellValueFactory(new PropertyValueFactory<>("numReservas"));

        cargarDatosOcupacion();
        cargarDatosGanancias();
        cargarDatosPopularidad();
        cargarDatosTiposRentables();

    }


    private void cargarDatosOcupacion() {
        chartOcupacion.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ocupación (%)");
            Map<Alojamiento, Double> ocupacion =ventanaController.getPlataforma().calcularOcupacionPorAlojamiento();
            for (Map.Entry<Alojamiento, Double> entry : ocupacion.entrySet()) {
                String nombre = entry.getKey().getNombre();
                Double porcentaje = entry.getValue();
                series.getData().add(new XYChart.Data<>(nombre, porcentaje));
            }
        chartOcupacion.getData().add(series);
        aplicarEstiloBarras(chartOcupacion, "#3b82f6");
    }


    private void cargarDatosGanancias() {
        chartGanancias.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias (€)");
        Map<Alojamiento, Double> ganancias = ventanaController.getPlataforma().obtenerGananciasPorAlojamiento();
        for (Map.Entry<Alojamiento, Double> entry : ganancias.entrySet()) {
            String nombre = entry.getKey().getNombre();
            Double ganancia = entry.getValue();
            series.getData().add(new XYChart.Data<>(nombre, ganancia));
        }
        chartGanancias.getData().add(series);
        aplicarEstiloBarras(chartGanancias, "#10b981");

    }


        private void cargarDatosPopularidad() {
            tablaPopularidad.getItems().clear();
            ObservableList<AlojamientoPopular> datos = FXCollections.observableArrayList();
                Map<Alojamiento, Long> reservaciones = ventanaController.getPlataforma().obtenerNumeroReservasPorAlojamiento();
                List<Map.Entry<Alojamiento, Long>> ranking = reservaciones.entrySet().stream()
                        .sorted(Map.Entry.<Alojamiento, Long>comparingByValue().reversed())
                        .collect(Collectors.toList());
                int pos = 1;
                for (Map.Entry<Alojamiento, Long> entry : ranking) {
                    Alojamiento a = entry.getKey();
                    datos.add(new AlojamientoPopular(pos++, a.getNombre(), a.getCiudad(), entry.getValue().intValue()));
                }
            tablaPopularidad.setItems(datos);
        }


    private void cargarDatosTiposRentables() {
        chartTiposRentables.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias Totales (€)");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            Map<String, Double> gananciasPorTipo = ventanaController.getPlataforma().obtenerTiposAlojamientoMasRentables();
            for (Map.Entry<String, Double> entry : gananciasPorTipo.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        chartTiposRentables.getData().add(series);
        aplicarEstiloBarras(chartTiposRentables, "#8b5cf6");
        chartDistribucionTipos.setData(pieChartData);
    }


    private void aplicarEstiloBarras(BarChart<String, Number> chart, String colorHex) {
        for (XYChart.Series<String, Number> series : chart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                if (data.getNode() != null)
                    data.getNode().setStyle("-fx-bar-fill: " + colorHex + ";");
            }
        }

    }

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
