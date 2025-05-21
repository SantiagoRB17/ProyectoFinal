module co.edu.uniquindio.poo.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxtras.controls;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires com.dlsc.gemsfx;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.desktop;


    opens co.edu.uniquindio.poo.proyectofinal.Model.entidades to com.fasterxml.jackson.databind;
    opens co.edu.uniquindio.poo.proyectofinal.Controllers to javafx.fxml;
    exports co.edu.uniquindio.poo.proyectofinal;
    exports co.edu.uniquindio.poo.proyectofinal.Model.entidades;
    exports co.edu.uniquindio.poo.proyectofinal.Repositorios;
    exports co.edu.uniquindio.poo.proyectofinal.Servicios;
    exports co.edu.uniquindio.poo.proyectofinal.Enums;
    exports co.edu.uniquindio.poo.proyectofinal.Observers;
    exports co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator;
    opens co.edu.uniquindio.poo.proyectofinal.Enums to com.fasterxml.jackson.databind;
    exports co.edu.uniquindio.poo.proyectofinal.Model.enums;
    opens co.edu.uniquindio.poo.proyectofinal.Model.enums to com.fasterxml.jackson.databind;
    exports co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory;
    opens co.edu.uniquindio.poo.proyectofinal.Model.AlojamientosFactory to com.fasterxml.jackson.databind;
    opens co.edu.uniquindio.poo.proyectofinal.Servicios to com.fasterxml.jackson.databind;
    opens co.edu.uniquindio.poo.proyectofinal.Observers to javafx.fxml;
    opens co.edu.uniquindio.poo.proyectofinal.Model.AlojamientoDecorator to com.fasterxml.jackson.databind;

}