/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.services;

import TPI.entities.Pedido;
import TPI.entities.Producto;
import TPI.entities.Usuario;
import TPI.enums.Estado;
import TPI.enums.FormaPago;
import TPI.exception.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoyshansho
 */
public class PedidoServices {

    private List<Pedido> pedidos = new ArrayList<>();
    private UsuarioServices usuarioServices;
    private ProductoServices productoServices;

    public PedidoServices(UsuarioServices usuarioServices, ProductoServices productoServices) {
        this.usuarioServices = usuarioServices;
        this.productoServices = productoServices;
    }

    public Pedido crear(Long id, Estado estado, FormaPago formaPago) throws EntidadNoEncontradaException {
        Usuario u = usuarioServices.buscarPorId(id);
        Pedido pedido = new Pedido(estado, formaPago, u);
        pedidos.add(pedido);
        u.agregarPedido(pedido);
        System.out.println("Pedido creado con id: " + pedido.getId());
        return pedido;
    }

    public void agregarDetalle(Long productoId, Pedido pedido, int cantidad) throws EntidadNoEncontradaException {
        Producto p = productoServices.buscarPorId(productoId);
        pedido.addDetallePedido(cantidad, p);
    }

    public List<Pedido> listar() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public Pedido buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        throw new EntidadNoEncontradaException("No existe un pedido con id " + id);

    }
    
    public void actualizar(Long id, Estado estado, FormaPago formaPago) throws EntidadNoEncontradaException{
        Pedido p = buscarPorId(id);
        p.setEstado(estado);
        p.setFormaPago(formaPago);
        System.out.println("Se actualizo el pedido con id: " + id);
    }
    
    public void eliminar(Long id) throws EntidadNoEncontradaException{
        Pedido p = buscarPorId(id);
        p.setEliminado(true);
        System.out.println("Pedido eliminado");
    }
}
