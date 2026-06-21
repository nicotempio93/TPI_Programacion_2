/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPI.menu;

import TPI.entities.Pedido;
import TPI.entities.Producto;
import TPI.entities.Usuario;
import TPI.enums.Estado;
import TPI.enums.FormaPago;
import TPI.exception.EntidadNoEncontradaException;
import TPI.services.CategoriaServices;
import TPI.services.PedidoServices;
import TPI.services.ProductoServices;
import TPI.services.UsuarioServices;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nicoyshansho
 */
public class MenuPedido {

    private PedidoServices service;
    private UsuarioServices usuarioServices;
    private ProductoServices productoServices;
    private Scanner sc = new Scanner(System.in);

    public MenuPedido(PedidoServices service, UsuarioServices usuarioServices, ProductoServices productoServices) {
        this.service = service;
        this.usuarioServices = usuarioServices;
        this.productoServices = productoServices;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- PEDIDOS ---");
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
        List<Pedido> pedidos = service.listar();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos cargados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    private void crear() {
        System.out.println("Usuarios disponibles: ");
        List<Usuario> usuarios = usuarioServices.listar();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        System.out.println("Ingrese el id del cliente: ");
        Long usuarioId;
        try {
            usuarioId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            usuarioServices.buscarPorId(usuarioId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Forma de pago: 1-TARJETA 2-TRANSFERENCIA 3-EFECTIVO");
        int opcionPago;
        try {
            opcionPago = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida, ingresá un número.");
            return;
        }
        FormaPago formaPago = switch (opcionPago) {
            case 1 ->
                FormaPago.TARJETA;
            case 2 ->
                FormaPago.TRANSFERENCIA;
            case 3 ->
                FormaPago.EFECTIVO;
            default ->
                FormaPago.EFECTIVO;
        };

        try {
            Pedido pedido = service.crear(usuarioId, Estado.PENDIENTE, formaPago);

            boolean seguir = true;
            while (seguir) {
                System.out.println("Productos disponibles:");
                List<Producto> productos = productoServices.listar();
                for (Producto p : productos) {
                    System.out.println(p);
                }

                System.out.print("Ingrese el id del producto: ");
                Long productoId;
                try {
                    productoId = Long.parseLong(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido. Cancelando creación del pedido.");
                    service.eliminar(pedido.getId());
                    return;
                }

                System.out.print("Ingrese la cantidad: ");
                int cantidad;
                try {
                    cantidad = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida. Cancelando creación del pedido.");
                    service.eliminar(pedido.getId());
                    return;
                }

                try {
                    service.agregarDetalle(productoId, pedido, cantidad);
                } catch (EntidadNoEncontradaException e) {
                    System.out.println(e.getMessage() + " Cancelando creación del pedido.");
                    service.eliminar(pedido.getId());
                    return;
                }

                System.out.print("¿Agregar otro producto? (S/N): ");
                seguir = sc.nextLine().equalsIgnoreCase("S");
            }

            System.out.println("Pedido creado. Total: $" + pedido.getTotal());

        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editar() {
        System.out.print("Ingrese el id del pedido a modificar: ");
        Long pedidoId;
        try {
            pedidoId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.buscarPorId(pedidoId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Estado: 1-PENDIENTE 2-CONFIRMADO 3-TERMINADO 4-CANCELADO");
        int opcionEstado;
        try {
            opcionEstado = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida, ingresá un número.");
            return;
        }
        Estado estado = switch (opcionEstado) {
            case 1 ->
                Estado.PENDIENTE;
            case 2 ->
                Estado.CONFIRMADO;
            case 3 ->
                Estado.TERMINADO;
            case 4 ->
                Estado.CANCELADO;
            default ->
                Estado.PENDIENTE;
        };

        System.out.println("Forma de pago: 1-TARJETA 2-TRANSFERENCIA 3-EFECTIVO");
        int opcionPago;
        try {
            opcionPago = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida, ingresá un número.");
            return;
        }
        FormaPago formaPago = switch (opcionPago) {
            case 1 ->
                FormaPago.TARJETA;
            case 2 ->
                FormaPago.TRANSFERENCIA;
            case 3 ->
                FormaPago.EFECTIVO;
            default ->
                FormaPago.EFECTIVO;
        };

        try {
            service.actualizar(pedidoId, estado, formaPago);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar() {
        System.out.println("Ingrece el id del pedido a eliminar: ");
        Long pedidoId;
        try {
            pedidoId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido, ingresá un número.");
            return;
        }

        try {
            service.buscarPorId(pedidoId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("¿Confirma la eliminación? (S/N): ");
        if (!sc.nextLine().equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        try {
            service.eliminar(pedidoId);
        } catch (EntidadNoEncontradaException e) {
            System.out.println(e.getMessage());
        }

    }

}
