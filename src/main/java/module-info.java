module co.edu.uniquindio.poo.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxtras.controls;
    requires com.jfoenix;


    opens co.edu.uniquindio.poo.proyectofinal.Controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectofinal;
}