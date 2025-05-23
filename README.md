🧠 Autores
Santiago Ramirez Bernal, Santiago Guevara, Santiago Fuentes
Proyecto para el curso Programación II – Universidad del Quindío.

# BookYourStay

**BookYourStay** es una plataforma de reservas de alojamientos en destinos turísticos de Colombia. Ofrece funcionalidades tanto para clientes como para un administrador único, permitiendo desde la gestión de reservas y alojamientos hasta la visualización de estadísticas por medio de gráficos interactivos.

## 📌 Descripción

Este proyecto fue desarrollado como trabajo final del curso **Programación II** de la Universidad del Quindío. Su objetivo es simular una plataforma completa de reservas en línea donde los usuarios pueden:

- Buscar alojamientos por ciudad, nombre, tipo o precio.
- Realizar reservas con validaciones de disponibilidad y capacidad.
- Pagar mediante una **billetera virtual** recargable.
- Dejar reseñas y calificaciones después de su estancia.
- Recibir facturas con código QR al completar reservas.

El administrador puede:

- Gestionar alojamientos y ofertas especiales.
- Ver estadísticas de ocupación y rentabilidad.
- Visualizar gráficas de alojamientos más populares y tipos más rentables.

## 🛠️ Tecnologías utilizadas

- **Java 21**
- **JavaFX** (interfaz gráfica)
- **Maven** (gestión de dependencias)
- **JUnit 5** (pruebas unitarias)
- **JSON** (persistencia)
- **Correo electrónico y códigos de activación**
- **Código QR** (para facturas)

## 🧱 Arquitectura

El sistema sigue una **arquitectura por capas**:
- **Repositorio:** Manejo de persistencia de datos en archivos JSON.
- **Servicio:** Lógica de negocio.
- **Controlador:** Manejo de eventos y lógica de interfaz.
  
Se aplicaron los principios **SOLID** y los patrones de diseño Observer, Singleton, Facade, Factory Method, Builder.

📊 Estadísticas
Gráficos de ocupación por alojamiento.

Tipos de alojamiento más rentables.

Alojamiento más reservado por ciudad.

✅ Pruebas
El sistema incluye pruebas unitarias usando JUnit para verificar el correcto funcionamiento de los métodos clave.

📦 Persistencia
Se utiliza persistencia con archivos JSON para almacenar:

Clientes

Alojamientos

Reservas

Facturas

Ofertas

Reseñas

## ✨ Funcionalidades destacadas

### Cliente
- Registro con verificación por correo.
- Inicio de sesión y edición de cuenta.
- Encriptacion de contraseñas
- Reserva con validación de fechas, capacidad y saldo.
- Billetera virtual recargable.
- Cancelación y listado de reservas.
- Reseñas y calificaciones post-estancia.
- Recuperación de contraseña por código.

### Administrador
- Gestión total de alojamientos y ofertas.
- Visualización de métricas (ocupación, ganancias).
- Gráficos de popularidad y rentabilidad.
- Recuperación de contraseña por código.

## 🚀 Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/SantiagoRB17/ProyectoFinal.git
   cd ProyectoFinal
