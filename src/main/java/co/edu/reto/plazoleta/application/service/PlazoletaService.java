package co.edu.reto.plazoleta.application.service;

import co.edu.reto.plazoleta.domain.exception.*;
import co.edu.reto.plazoleta.domain.model.*;
import co.edu.reto.plazoleta.infrastructure.output.persistence.entity.*;
import co.edu.reto.plazoleta.infrastructure.output.persistence.repository.*;
import co.edu.reto.plazoleta.infrastructure.input.rest.dto.*;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PlazoletaService {
 private final RestauranteJpaRepository restaurantes; private final UsuarioJpaRepository usuarios;
 private final PlatoJpaRepository platos; private final PedidoJpaRepository pedidos; private final DetallePedidoJpaRepository detalles;
 private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
 public PlazoletaService(RestauranteJpaRepository r, UsuarioJpaRepository u, PlatoJpaRepository p, PedidoJpaRepository pe, DetallePedidoJpaRepository d) { restaurantes=r; usuarios=u; platos=p; pedidos=pe; detalles=d; }

 public RestauranteEntity crearRestaurante(RestauranteRequest q) {
  if(restaurantes.existsByNit(q.nit())) throw new ReglaNegocioException("Ya existe un restaurante con ese NIT");
  UsuarioEntity owner=usuarios.findById(q.idPropietario()).orElseThrow(()->new RecursoNoEncontradoException("Propietario no encontrado"));
  if(owner.rol!=Rol.PROPIETARIO) throw new ReglaNegocioException("El usuario indicado no tiene rol PROPIETARIO");
  RestauranteEntity e=new RestauranteEntity(); e.nombre=q.nombre(); e.nit=q.nit(); e.direccion=q.direccion(); e.telefono=q.telefono(); e.urlLogo=q.urlLogo(); e.idPropietario=q.idPropietario(); return restaurantes.save(e);
 }
 public UsuarioEntity crearUsuario(UsuarioRequest q, Rol rolPermitido) {
  if(usuarios.existsByCorreo(q.correo())) throw new ReglaNegocioException("El correo ya está registrado");
  if(usuarios.existsByDocumento(q.documento())) throw new ReglaNegocioException("El documento ya está registrado");
  if(q.rol()!=rolPermitido) throw new ReglaNegocioException("Rol inválido para esta operación");
  UsuarioEntity e=new UsuarioEntity(); e.nombre=q.nombre(); e.apellido=q.apellido(); e.correo=q.correo(); e.passwordHash=encoder.encode(q.password()); e.documento=q.documento(); e.rol=q.rol(); e.idRestaurante=q.idRestaurante();
  return usuarios.save(e);
 }
 public PlatoEntity crearPlato(PlatoRequest q, Long usuarioId) {
  RestauranteEntity r=restaurantes.findById(q.idRestaurante()).orElseThrow(()->new RecursoNoEncontradoException("Restaurante no encontrado"));
  validarPropietario(r, usuarioId);
  PlatoEntity e=new PlatoEntity(); e.nombre=q.nombre(); e.precio=q.precio(); e.descripcion=q.descripcion(); e.urlImagen=q.urlImagen(); e.categoria=q.categoria(); e.idRestaurante=q.idRestaurante(); return platos.save(e);
 }
 public PlatoEntity actualizarPlato(Long id, ActualizarPlatoRequest q, Long usuarioId) {
  PlatoEntity e=platos.findById(id).orElseThrow(()->new RecursoNoEncontradoException("Plato no encontrado"));
  RestauranteEntity r=restaurantes.findById(e.idRestaurante).orElseThrow(()->new RecursoNoEncontradoException("Restaurante no encontrado")); validarPropietario(r, usuarioId);
  e.precio=q.precio(); e.descripcion=q.descripcion(); return platos.save(e);
 }
 public PlatoEntity cambiarEstadoPlato(Long id, boolean activo, Long usuarioId) {
  PlatoEntity e=platos.findById(id).orElseThrow(()->new RecursoNoEncontradoException("Plato no encontrado"));
  RestauranteEntity r=restaurantes.findById(e.idRestaurante).orElseThrow(()->new RecursoNoEncontradoException("Restaurante no encontrado")); validarPropietario(r, usuarioId);
  e.activo=activo; return platos.save(e);
 }
 private void validarPropietario(RestauranteEntity r, Long usuarioId) { if(!Objects.equals(r.idPropietario, usuarioId)) throw new ReglaNegocioException("El propietario no administra este restaurante"); }
 public Page<RestauranteEntity> listarRestaurantes(int page, int size) { return restaurantes.findAll(PageRequest.of(Math.max(page,0), Math.min(Math.max(size,1),100), Sort.by("nombre").ascending())); }
 public Page<PlatoEntity> listarPlatos(Long idRestaurante, String categoria, int page, int size) {
  if(!restaurantes.existsById(idRestaurante)) throw new RecursoNoEncontradoException("Restaurante no encontrado");
  return platos.findByIdRestauranteAndCategoriaIgnoreCaseAndActivoTrue(idRestaurante,categoria,PageRequest.of(Math.max(page,0),Math.min(Math.max(size,1),100),Sort.by("nombre").ascending()));
 }
 @Transactional public PedidoEntity crearPedido(Long clienteId, PedidoRequest q) {
  UsuarioEntity cliente=usuarios.findById(clienteId).orElseThrow(()->new RecursoNoEncontradoException("Cliente no encontrado"));
  if(cliente.rol!=Rol.CLIENTE) throw new ReglaNegocioException("Solo un CLIENTE puede crear pedidos");
  if(pedidos.existsByIdClienteAndEstadoIn(clienteId,List.of(EstadoPedido.PENDIENTE,EstadoPedido.EN_PREPARACION,EstadoPedido.LISTO))) throw new ReglaNegocioException("El cliente ya tiene un pedido activo");
  if(!restaurantes.existsById(q.idRestaurante())) throw new RecursoNoEncontradoException("Restaurante no encontrado");
  PedidoEntity pedido=new PedidoEntity(); pedido.idCliente=clienteId; pedido.idRestaurante=q.idRestaurante(); pedido.estado=EstadoPedido.PENDIENTE;
  pedido=pedidos.save(pedido); BigDecimal total=BigDecimal.ZERO;
  Set<Long> vistos=new HashSet<>();
  for(ItemPedidoRequest item:q.items()) {
   if(!vistos.add(item.idPlato())) throw new ReglaNegocioException("No repita platos en la lista; ajuste la cantidad");
   PlatoEntity plato=platos.findById(item.idPlato()).orElseThrow(()->new RecursoNoEncontradoException("Plato no encontrado: "+item.idPlato()));
   if(!plato.activo) throw new ReglaNegocioException("El plato "+plato.id+" está inactivo");
   if(!Objects.equals(plato.idRestaurante,q.idRestaurante())) throw new ReglaNegocioException("Todos los platos deben pertenecer al mismo restaurante");
   DetallePedidoEntity d=new DetallePedidoEntity(); d.idPedido=pedido.id; d.idPlato=plato.id; d.nombrePlato=plato.nombre; d.cantidad=item.cantidad(); d.precioUnitario=plato.precio; detalles.save(d);
   total=total.add(plato.precio.multiply(BigDecimal.valueOf(item.cantidad())));
  }
  pedido.total=total; return pedidos.save(pedido);
 }
}
