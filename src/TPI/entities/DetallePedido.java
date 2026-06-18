/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.entities;

/**
 *
 * @author nicoyshansho
 */
public class DetallePedido extends Base {

    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad = cantidad;
            this.subtotal = calcularSubtotal();
        }
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto != null) {
            this.producto = producto;
        }
    }

    private double calcularSubtotal() {
        if (this.producto == null) {
            return 0.0;
        }
        return (this.producto.getPrecio() * this.cantidad);
    }

    @Override
    public String toString() {
        return String.format("- DetallePedido ID: #%d: %s x %d => Subtotal: $%.2f\n",
                getId(), producto.getNombre(), cantidad, subtotal);
    }

}
