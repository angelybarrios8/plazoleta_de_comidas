package co.edu.reto.plazoleta.infrastructure.config;
import io.swagger.v3.oas.models.OpenAPI; import io.swagger.v3.oas.models.info.Info; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
@Configuration public class OpenApiConfig {
 @Bean public OpenAPI plazoletaOpenAPI(){return new OpenAPI().info(new Info().title("API Plazoleta de Comidas").version("1.0.0").description("Backend académico para HU01-HU09. La identidad por cabeceras es solo para pruebas locales."));}
}
