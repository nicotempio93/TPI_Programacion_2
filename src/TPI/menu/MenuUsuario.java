/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.menu;

import TPI.entities.Usuario;
import TPI.enums.Rol;
import TPI.exception.EntidadNoEncontradaException;
import TPI.services.UsuarioServices;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nicoyshansho
 */
public class MenuUsuario {

    private UsuarioServices service;
    private Scanner sc = new Scanner(System.in);

    public MenuUsuario(UsuarioServices service) {
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- USUARIOS ---");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, ingresá un número.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 ->
                    listar();
                case 2 ->
                    crear();
                case 3 ->
                    editar();
                case 4 ->
                    eliminar();
                case 0 ->
                    System.out.println("Volviendo al menú principal...");
                default ->
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void listar() {
        List<Usuario> usuarios = service.listar();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios cargados.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    private void crear() {

        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la apellido: ");
        String apellido = sc.nextLine();

        System.out.println("Ingrese el mail: ");
        String mail = sc.nextLine();

        System.out.print("Ingrese el celular: ");
        String celular = sc.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contrasenia = sc.nextLine();

        System.out.println("Ingrese el rol: ");
        System.out.println("Rol: 1-ADMIN 2-USUARIO");

        int opcionRol;
        try {
            opcionRol = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida, ingresá un número.");
            return;
        }

        Rol rol = switch (opcionRol) {
            case 1 ->
                Rol.ADMIN;
            case 2 ->
                Rol.USUARIO;
            default ->
                Rol.USUARIO;
        };

        try {
            service.crear(nombre, apellido, mail, celular, contrasenia, rol);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editar() {

        System.out.println("Ingrece el id del usuarios a modificar: ");

        Long usuarioId;
        try {
            usuarioId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.buscarPorId(usuarioId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la apellido: ");
        String apellido = sc.nextLine();

        System.out.println("Ingrese el mail: ");
        String mail = sc.nextLine();

        System.out.print("Ingrese el celular: ");
        String celular = sc.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contrasenia = sc.nextLine();

        System.out.println("Ingrese el rol: ");
        System.out.println("Rol: 1-ADMIN 2-USUARIO");
        int opcionRol;
        try {
            opcionRol = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida, ingresá un número.");
            return;
        }
        Rol rol = switch (opcionRol) {
            case 1 ->
                Rol.ADMIN;
            case 2 ->
                Rol.USUARIO;
            default ->
                Rol.USUARIO;
        };

        try {
            service.actualizar(usuarioId, nombre, apellido, mail, celular, contrasenia, rol);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar() {
        System.out.println("Ingrece el id del usuario a eliminar: ");
        Long usuarioId;
        try {
            usuarioId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.eliminar(usuarioId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }
}
