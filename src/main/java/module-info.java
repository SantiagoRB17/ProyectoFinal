module co.edu.uniquindio.poo.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires javafx.graphics;


    opens co.edu.uniquindio.poo.proyectofinal to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectofinal;
}