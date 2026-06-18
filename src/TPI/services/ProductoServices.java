/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.services;

import TPI.entities.Categoria;
import TPI.entities.Producto;
import TPI.services.CategoriaServices;
import TPI.exception.EntidadNoEncontradaException;
import TPI.exception.StockInvalidoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoyshansho
 */
public class ProductoServices {

    private List<Producto> productos = new ArrayList<>();
    private CategoriaServices categoriaServices;

    public ProductoServices(CategoriaServices categoriaServices) {
        this.categoriaServices = categoriaServices;
    }

    public void crear(String nombre, String descripcion, String imagen,
            double precio, int stock, boolean disponible, Long categoriaId)
            throws EntidadNoEncontradaException, StockInvalidoException {

        if (precio < 0) {
            throw new StockInvalidoException("El precio no puede ser negativo.");
        }

        if (stock < 0) {
            throw new StockInvalidoException("El stock no puede ser negativo.");
        }

        Categoria categoria = categoriaServices.buscarPorId(categoriaId);

        Producto producto = new Producto(nombre, descripcion, imagen, precio, stock, disponible, categoria);
        productos.add(producto);
        System.out.println("Producto creado con ID: " + producto.getId());

    }

    public List<Producto> listar() {
        List<Producto> activas = new ArrayList<>();
        for (Producto p : productos) {
            if (!p.isEliminado()) {
                activas.add(p);
            }
        }
        return activas;
    }

    public Producto buscarPorId(Long id) 
        throws EntidadNoEncontradaException {
            for (Producto p : productos) {
                if (p.getId().equals(id) && !p.isEliminado()) {
                    return p;
                }
            }
            throw new EntidadNoEncontradaException("No existe un porducto con id " + id);
        } 
    
    

    public void actualizar(Long id, String nombre, String descripcion, String imagen, double precio, int stock, boolean disponible, Long categoriaId) throws EntidadNoEncontradaException {

        Producto p = buscarPorId(id);
        Categoria categoria = categoriaServices.buscarPorId(categoriaId);

        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setImagen(imagen);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setDisponible(disponible);
        p.setCategoria(categoria);
        System.out.println("Producto actualizado.");
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Producto p = buscarPorId(id);
        p.setEliminado(true);
        System.out.println("Producto eliminado.");

    }

}
