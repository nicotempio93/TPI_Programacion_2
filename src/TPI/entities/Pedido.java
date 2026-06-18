/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.entities;

import TPI.enums.Estado;
import TPI.enums.FormaPago;
import TPI.interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoyshansho
 */
public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private List<DetallePedido> detalles;
    private Usuario usuario;

    public Pedido(Estado estado, FormaPago formaPago, Usuario usuario) {
        super();
        this.fecha = LocalDate.now();
        this.estado = estado;
        this.formaPago = formaPago;
        this.detalles = new ArrayList<>();
        this.usuario = usuario;
        this.total = 0.0;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        if (formaPago != null) {
            this.formaPago = formaPago;
        }
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario != null) {
            this.usuario = usuario;
        }
    }

    @Override
    public void calcularTotal() {
        double suma = 0;
        for (DetallePedido detalle : detalles) {
            suma += detalle.getSubtotal();
        }
        this.total = suma;
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        if (cantidad > 0 && producto != null && !producto.isEliminado()) {
            DetallePedido detalle = new DetallePedido(cantidad, producto);
            this.detalles.add(detalle);
            this.calcularTotal();
        }
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        if (producto == null) {
            return null;
        }

        Long idBuscado = producto.getId();

        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(idBuscado)) {
                return detalle;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalle = findDetallePedidoByProducto(producto);
        if (detalle != null) {
            detalles.remove(detalle);
            calcularTotal();
        }
    }

    @Override
    public String toString() {
        return String.format("> Pedido ID: #%d | Fecha: %s | Estado: %s | FormaPago: %s | Total: $%.2f",
                getId(), fecha, estado, formaPago, total);
    }
}
