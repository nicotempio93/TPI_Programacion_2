/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.services;

import examen.entities.Categoria;
import examen.exception.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoyshansho
 */
public class CategoriaServices {

    private List<Categoria> categorias = new ArrayList<>();

    public void Create(String nombre, String descripcion) throws EntidadNoEncontradaException {
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre) && !c.isEliminado()) {
                throw new EntidadNoEncontradaException("Ya existe una entidad con ese nombre.");
            }
        }
        Categoria categoria = new Categoria(nombre, descripcion);
        categorias.add(categoria);
        System.out.println("Categoria creada con ID: " + categoria.getId());
    }

    public List<Categoria> listar() {
        List<Categoria> activas = new ArrayList<>();
        for (Categoria c : categorias) {
            if (!c.isEliminado()) {
                activas.add(c);
            }
        }
        return activas;
    }

    public void actualizar(Long id, String nombre, String descripcion) throws EntidadNoEncontradaException {
        Categoria c = buscarPorId(id);
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        System.out.println("Categoría actualizada.");
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Categoria c = buscarPorId(id);
        c.setEliminado(true);
        System.out.println("Categoría eliminada.");
    }

    public Categoria buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Categoria c : categorias) {
            if (c.getId().equals(id) && !c.isEliminado()) {
                return c;
            }
        }
        throw new EntidadNoEncontradaException("No existe categoría con id " + id);
    }

}
