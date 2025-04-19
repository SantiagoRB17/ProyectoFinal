module co.edu.uniquindio.poo.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxtras.controls;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires static lombok;
    requires com.dlsc.gemsfx;


    opens co.edu.uniquindio.poo.proyectofinal.Controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectofinal;
    exports co.edu.uniquindio.poo.proyectofinal.Model;
}