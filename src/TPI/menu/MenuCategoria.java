/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.menu;

import TPI.entities.Categoria;
import TPI.exception.EntidadNoEncontradaException;
import TPI.services.CategoriaServices;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nicoyshansho
 */
public class MenuCategoria {

    private CategoriaServices service;
    private Scanner sc = new Scanner(System.in);

    public MenuCategoria(CategoriaServices service) {
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- CATEGORÍAS ---");
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
        List<Categoria> categorias = service.listar();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias cargadas");
            return;
        }
        for (Categoria c : categorias) {
            System.out.println(c);
        }
    }

    private void crear() {
        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        if (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }
        System.out.print("Ingrese la descripción: ");
        String descripcion = sc.nextLine();
        if (descripcion.isBlank()) {
            System.out.println("La descipcion no puede estar vacia.");
            return;
        }

        try {
            service.crear(nombre, descripcion);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editar() {
        System.out.println("Ingrece el id de la categoria a modificar: ");
        Long categoriaId;
        try {
            categoriaId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.buscarPorId(categoriaId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Ingrese el nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese la nueva descripción: ");
        String descripcion = sc.nextLine();

        try {
            service.actualizar(categoriaId, nombre, descripcion);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }

    }

    private void eliminar() {
        System.out.println("Ingrece el id de la categoria a eliminar: ");
        Long categoriaId;
        try {
            categoriaId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.eliminar(categoriaId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }

    }

}
