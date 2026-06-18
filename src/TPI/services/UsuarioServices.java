/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.services;

import TPI.entities.Usuario;
import TPI.enums.Rol;
import TPI.exception.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicoyshansho
 */
public class UsuarioServices {

    private List<Usuario> usuarios = new ArrayList<>();

    public void crear(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) throws EntidadNoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(mail) && !u.isEliminado()) {
                throw new EntidadNoEncontradaException("Ya existe un usuario con ese email.");
            }
        }

        Usuario usuario = new Usuario(nombre, apellido, mail, celular, contrasenia, rol);
        usuarios.add(usuario);
        System.out.println("Usuario creado con id: " + usuario.getId());
    }

    public List<Usuario> listar() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                activos.add(u);
            }
        }
        return activos;
    }

    public Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new EntidadNoEncontradaException("No existe usuario con id " + id);
    }

    public void actualizar(Long id, String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(id);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setMail(mail);
        u.setCelular(celular);
        u.setContrasenia(contrasenia);
        u.setRol(rol);
        System.out.println("Usuarios actualizado.");
    }

    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(id);
        u.setEliminado(true);
        System.out.println("Usuario eliminado.");
    }
}
