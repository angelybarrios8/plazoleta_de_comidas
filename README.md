# API REST — Plazoleta de Comidas

Proyecto académico de backend para gestionar restaurantes, usuarios, platos y pedidos. Desarrollado con Java 21, Spring Boot, Maven, Spring Data JPA y H2 en memoria.

## Requisitos

- JDK 21 instalado.
- Apache Maven 3.9 o superior.
- Visual Studio Code (opcional) con Extension Pack for Java.
- Puerto `8080` disponible.

Comprueba las instalaciones desde la terminal:

```bash
java -version
mvn -version
```

## Ejecutar el proyecto

Abre esta carpeta en VS Code y, desde la terminal integrada, ejecuta:

```bash
mvn clean test
mvn spring-boot:run
```

Cuando la aplicación esté iniciada:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- Consola H2: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:plazoleta`
  - Usuario: `sa`
  - Contraseña: vacía

La base H2 es temporal: los datos se borran al reiniciar la aplicación.

## Funcionalidades incluidas

| Historia | Operación | Endpoint |
|---|---|---|
| HU01 | Crear restaurante | `POST /api/restaurantes` |
| HU02 | Registrar propietario | `POST /api/usuarios/propietarios` |
| HU03 | Crear plato | `POST /api/platos` |
| HU04 | Actualizar precio y descripción | `PUT /api/platos/{id}` |
| HU05 | Activar/desactivar plato | `PATCH /api/platos/{id}/estado?activo=true` |
| HU06 | Listar restaurantes paginados | `GET /api/restaurantes?page=0&size=10` |
| HU07 | Listar platos activos por restaurante/categoría | `GET /api/restaurantes/{idRestaurante}/platos?categoria=Bebidas&page=0&size=10` |
| HU08 | Registrar empleado | `POST /api/usuarios/empleados` |
| HU09 | Crear pedido | `POST /api/pedidos` |

Los endpoints de creación de usuarios y los formatos de las solicitudes deben revisarse con los DTO y controladores del código. Swagger permite explorar los contratos expuestos por la aplicación.

## Arquitectura del código

- `domain/`: roles, estados y excepciones de negocio.
- `application/service/`: lógica de aplicación y reglas de negocio.
- `infrastructure/input/rest/`: controladores REST, DTO y respuestas de error.
- `infrastructure/output/persistence/`: entidades JPA y repositorios.
- `infrastructure/config/`: configuración de OpenAPI.

La organización está inspirada en arquitectura hexagonal, pero no es una implementación estricta: el servicio de aplicación todavía depende directamente de entidades, repositorios y DTO de infraestructura.

## Identidad de prueba y seguridad

Algunas operaciones reciben los encabezados `X-User-Id` y `X-User-Role` para simular la identidad durante pruebas académicas. Estos encabezados **no son autenticación segura** y no deben usarse como mecanismo de seguridad en producción. Antes de desplegar el sistema, implementar autenticación y autorización reales, por ejemplo con Spring Security y JWT, y proteger los endpoints administrativos.

## Pruebas

Ejecuta `mvn clean test` para compilar y ejecutar las pruebas automatizadas. No publiques una entrega como verificada hasta confirmar que el comando termina correctamente en tu equipo o en CI.

## Subir a GitHub desde VS Code

1. Crea un repositorio nuevo en GitHub. Para este proyecto puedes llamarlo `plazoleta-comidas-backend`.
2. Si vas a subir esta carpeta tal como está, crea el repositorio **sin** añadir README, `.gitignore` ni licencia desde GitHub, porque esos archivos ya están en el proyecto.
3. Abre esta carpeta en VS Code (`Archivo > Abrir carpeta`).
4. Abre la terminal integrada (`Terminal > Nueva terminal`) y ejecuta, una línea a la vez:

   ```bash
   git init
   git add .
   git commit -m "Primer commit: API Plazoleta de Comidas"
   git branch -M main
   git remote add origin https://github.com/TU-USUARIO/plazoleta-comidas-backend.git
   git push -u origin main
   ```

5. Sustituye `TU-USUARIO` por tu nombre de usuario real de GitHub. Si Git no está instalado, instálalo desde https://git-scm.com/downloads y vuelve a abrir VS Code.
6. Si Git solicita identidad, configúrala con tu nombre y correo usando `git config --global user.name "Tu nombre"` y `git config --global user.email "tu-correo"`.

No compartas contraseñas ni tokens en el código ni los subas al repositorio.
