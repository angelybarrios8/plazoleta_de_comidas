package co.edu.reto.plazoleta.application.service;
import co.edu.reto.plazoleta.infrastructure.output.persistence.repository.*;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.*;
import co.edu.reto.plazoleta.domain.model.*;
import co.edu.reto.plazoleta.domain.exception.*;
import co.edu.reto.plazoleta.infrastructure.input.rest.dto.*;
import org.junit.jupiter.api.*; import org.mockito.*; import static org.mockito.Mockito.*; import static org.junit.jupiter.api.Assertions.*;
import java.util.*; import java.math.BigDecimal;
class PlazoletaServiceTest {
 @Mock RestauranteJpaRepository restaurantes; @Mock UsuarioJpaRepository usuarios; @Mock PlatoJpaRepository platos; @Mock PedidoJpaRepository pedidos; @Mock DetallePedidoJpaRepository detalles;
 PlazoletaService service;
 @BeforeEach void setup(){MockitoAnnotations.openMocks(this); service=new PlazoletaService(restaurantes,usuarios,platos,pedidos,detalles);}
 @Test void crearRestauranteRechazaNitDuplicado(){when(restaurantes.existsByNit("900")).thenReturn(true); assertThrows(ReglaNegocioException.class,()->service.crearRestaurante(new RestauranteRequest("A","900","Calle 1","300",null,2L)));}
 @Test void crearRestauranteRequierePropietario(){when(restaurantes.existsByNit("900")).thenReturn(false); UsuarioEntity u=new UsuarioEntity();u.rol=Rol.CLIENTE;when(usuarios.findById(2L)).thenReturn(Optional.of(u));assertThrows(ReglaNegocioException.class,()->service.crearRestaurante(new RestauranteRequest("A","900","Calle 1","300",null,2L)));}
 @Test void listarRestaurantesLimitaTamano(){when(restaurantes.findAll(any(org.springframework.data.domain.Pageable.class))).thenReturn(org.springframework.data.domain.Page.<RestauranteEntity>empty());service.listarRestaurantes(0,1000);verify(restaurantes).findAll(argThat(p->p.getPageSize()==100));}
 @Test void listarPlatosFallaSiRestauranteNoExiste(){when(restaurantes.existsById(77L)).thenReturn(false);assertThrows(RecursoNoEncontradoException.class,()->service.listarPlatos(77L,"Bebidas",0,10));}
 @Test void crearPedidoRechazaClienteConPedidoActivo(){UsuarioEntity u=new UsuarioEntity();u.rol=Rol.CLIENTE;when(usuarios.findById(5L)).thenReturn(Optional.of(u));when(pedidos.existsByIdClienteAndEstadoIn(eq(5L),anyCollection())).thenReturn(true);assertThrows(ReglaNegocioException.class,()->service.crearPedido(5L,new PedidoRequest(1L,List.of(new ItemPedidoRequest(2L,1)))));}
}
