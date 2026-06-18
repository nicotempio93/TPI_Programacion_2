/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.menu;

import java.util.Scanner;

/**
 *
 * @author nicoyshansho
 */
public class MenuPrincipal {

    private MenuCategoria menuCategoria;
    private MenuProducto menuProducto;
    private MenuUsuario menuUsuario;
    private MenuPedido menuPedido;
    private Scanner sc = new Scanner(System.in);

    public MenuPrincipal(MenuCategoria menuCategoria, MenuProducto menuProducto, MenuUsuario menuUsuario, MenuPedido menuPedido) {
        this.menuCategoria = menuCategoria;
        this.menuProducto = menuProducto;
        this.menuUsuario = menuUsuario;
        this.menuPedido = menuPedido;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, ingresá un número.");
                opcion = -1; // ← hace que el do-while siga mostrando el menú
            }

            switch (opcion) {
                case 1 ->
                    menuCategoria.mostrar();
                case 2 ->
                    menuProducto.mostrar();
                case 3 ->
                    menuUsuario.mostrar();
                case 4 ->
                    menuPedido.mostrar();
                case 0 ->
                    System.out.println("Saliendo del programa...");
                default ->
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

}
