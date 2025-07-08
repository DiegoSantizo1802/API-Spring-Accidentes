# API-Spring-Accidentes (Rama Master)

## Descripción

Esta es una API RESTful desarrollada con Spring Boot, diseñada para gestionar información relacionada con accidentes. Proporciona una interfaz robusta y escalable para realizar operaciones CRUD sobre datos de accidentes, facilitando la interacción con una base de datos relacional.
## Tecnologías Utilizadas


*   **Java**: Versión 21
*   **Spring Boot**: Versión 3.5.0
*   **Maven**: Herramienta para la gestión de dependencias y la construcción del proyecto.
*   **MySQL**: Base de datos relacional para el almacenamiento de los datos de accidentes.
*   **Spring Data JPA / Hibernate**: Para la capa de persistencia y mapeo objeto-relacional.


## Configuración y Ejecución

Sigue estos pasos para configurar y ejecutar el proyecto localmente:

1.  **Clonar el Repositorio:**
    ```bash
    git clone https://github.com/DiegoSantizo1802/API-Spring-Accidentes.git
    cd API-Spring-Accidentes
    ```

2.  **Configurar la Base de Datos:**
    *   Crea una base de datos en tu servidor MySQL.
    *   Navega a `src/main/resources` y abre el archivo `application.properties`.
    *   Actualiza las propiedades de conexión a la base de datos con tus credenciales:

        ```properties
        # Configuración de la base de datos MySQL
        spring.datasource.url=jdbc:mysql://localhost:3306/db_accidentes?createDatabaseIfNotExist=true
        spring.datasource.username=tu_usuario_mysql
        spring.datasource.password=tu_contraseña_mysql

        # Configuración de JPA/Hibernate
        spring.jpa.hibernate.ddl-auto=update 
        spring.jpa.show-sql=true             
        ```

3.  **Compilar y Ejecutar el Proyecto:**

    *   **Usando Maven desde la terminal en la raíz del proyecto:**
        ```bash
        mvn clean install  
        mvn spring-boot:run 
        ```
    *   **Desde tu IDE :**
        *   Importa el proyecto como un proyecto Maven.
        *   Busca la clase principal y ejecútala.

    La API estará disponible en `http://localhost:8080`

