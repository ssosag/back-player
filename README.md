# Backend - Music Player API

Este proyecto es un backend para una API de gestión de canciones desarrollado con Spring Boot y MongoDB.

## Características
- Creación y búsqueda de canciones.
- Subida y almacenamiento de archivos de audio.
- Exposición de endpoints REST para interactuar con el frontend.

## Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.4.3**
- **MongoDB** como base de datos NoSQL.
- **Spring Security Crypto** para manejo de contraseñas.
- **Lombok** para reducir el código boilerplate.

## Instalación y Configuración

### Prerrequisitos
- Tener instalado [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
- Tener [Maven](https://maven.apache.org/) instalado.
- Tener un servidor de **MongoDB** corriendo.

### Pasos para ejecutar

1. Clona este repositorio:
   ```sh
   git clone https://github.com/tu_usuario/back-player.git
   cd back-player
   ```
2. Configura MongoDB en el archivo `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/tu_base_de_datos
   ```
3. Compila y ejecuta el backend:
   ```sh
   mvn spring-boot:run
   ```

El servidor se ejecutará en `http://localhost:8080`.

## Endpoints Disponibles

### Canciones

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `/api/song` | Obtiene todas las canciones con filtros opcionales |
| `POST` | `/api/song` | Crea una nueva canción |
| `POST` | `/api/song/upload` | Sube un archivo de audio |
| `GET` | `/api/song/{filename}` | Obtiene un archivo de audio específico |

## Notas Adicionales
- Se ha habilitado **CORS** para permitir solicitudes desde cualquier origen.
- La subida de archivos almacena las canciones en la carpeta `songs/` dentro del proyecto.


