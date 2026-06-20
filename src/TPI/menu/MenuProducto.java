/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.menu;

import TPI.entities.Categoria;
import TPI.entities.Producto;
import TPI.exception.EntidadNoEncontradaException;
import TPI.exception.StockInvalidoException;
import TPI.services.CategoriaServices;
import TPI.services.ProductoServices;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nicoyshansho
 */
public class MenuProducto {

    private CategoriaServices categoriaService;
    private ProductoServices service;
    private Scanner sc = new Scanner(System.in);

    public MenuProducto(CategoriaServices categoriaService, ProductoServices service) {
        this.categoriaService = categoriaService;
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- PRODUCTOS ---");
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
                opcion = -1; // ← hace que el do-while siga mostrando el menú
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
        List<Producto> productos = service.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados");
            return;
        }
        for (Producto c : productos) {
            System.out.println(c);
        }
    }

    private void crear() {

        System.out.println("Ingrese el nombre del nuevo producto: ");
        String nombre = sc.nextLine();
        if (nombre.isBlank()){
            System.out.println("El nombre no puede estar vacio.");
            return;
        }    
        

        System.out.print("Ingrese la descripción del nuevo porducto: ");
        String descripcion = sc.nextLine();

        System.out.print("Ingrese nomnbre de imagen del nuevo producto: ");
        String imagen = sc.nextLine();

        System.out.print("Ingrese el precio del nuevo porducto: ");
        double precio;
        try {
            precio = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido, ingresá un número.");
            return;
        }

        System.out.print("Ingrese el stock del nuevo producto: ");
        int stock;
        try {
            stock = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Stock inválido, ingresá un número entero.");
            return;
        }

        System.out.println("Lista de categorias:");
        imprimirIdCategorias();
        System.out.println("Ingrese el id de la categoria correspondiente: ");
        Long categoriaId;
        try {
            categoriaId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.crear(nombre, descripcion, imagen, precio, stock, true, categoriaId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (StockInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void imprimirIdCategorias() {
        System.out.println("Categorias disponibles: ");
        List<Categoria> categorias = categoriaService.listar();

        for (Categoria c : categorias) {
            System.out.println(c);
        }

    }

    private void editar() {
        System.out.println("Ingrese el id del producto a modificar: ");
        Long productoId;
        try {
            productoId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.buscarPorId(productoId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Ingrese nombre modificado: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese descripción modificada: ");
        String descripcion = sc.nextLine();

        System.out.print("Ingrese nombre de imagen modificado: ");
        String imagen = sc.nextLine();

        System.out.print("Ingrese el precio modificado: ");
        double precio;
        try {
            precio = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido, ingresá un número.");
            return;
        }

        System.out.print("Ingrese el stock modificado: ");
        int stock;
        try {
            stock = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Stock inválido, ingresá un número entero.");
            return;
        }

        System.out.println("Lista de categorias:");
        imprimirIdCategorias();
        System.out.println("Ingrese el id de la categoria correspondiente: ");
        Long categoriaId;
        try {
            categoriaId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.actualizar(productoId, nombre, descripcion, imagen, precio, stock, true, categoriaId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }

    }

    private void eliminar() {
        System.out.println("Ingrece el id del producto a eliminar: ");
        Long productoId;
        try {
            productoId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }
        try {
            service.eliminar(productoId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }

    }
}
