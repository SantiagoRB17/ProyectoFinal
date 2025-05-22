package co.edu.uniquindio.poo.proyectofinal.Utils;

public class RangoPrecio {
    private final Double min;
    private final Double max;
    private final String label;

    public RangoPrecio(Double min, Double max, String label) {
        this.min = min;
        this.max = max;
        this.label = label;
    }
    public Double getMin() { return min; }
    public Double getMax() { return max; }
    @Override
    public String toString() { return label; }

}
