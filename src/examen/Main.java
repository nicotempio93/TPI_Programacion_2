/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen;

import examen.entities.Categoria;
import examen.entities.DetallePedido;
import examen.entities.Pedido;
import examen.entities.Producto;
import examen.entities.Usuario;
import examen.enums.Estado;
import examen.enums.FormaPago;
import examen.enums.Rol;

/**
 *
 * @author nicoyshansho
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Instanciamos 3 categorias
        Categoria telefonos = new Categoria("Telefonos", "Telefonos celulares", 1L);
        Categoria heladeras = new Categoria("Helaredas", "Heladeras y freezers", 2L);
        Categoria muebles = new Categoria("Muebles", "Muebles para el hogar", 3L);

        System.out.println("Mostramos Categorias");

        System.out.println(telefonos.toString());
        System.out.println(heladeras.toString());
        System.out.println(muebles.toString());

        System.out.println("---------------------");

        //Instanciamos 6 prodcutos
        Producto iphone17PM = new Producto("Iphone 17 Pro Max", "Ultimo Iphone, el mas potente", "img_1.jpg", 1000, 25, true, telefonos, 4L);
        Producto samsungS26U = new Producto("Samsung S 26 Ultra", "Ultimo Samsung, el mas potente", "img_2.jpg", 950, 13, true, telefonos, 5L);
        Producto hss1 = new Producto("Heladera Samsung Super 1 ", "Heladera doble puerta, con dispenser de hielo", "img_3.jpg", 1100, 22, true, heladeras, 6L);
        Producto lg26 = new Producto("Heladera LG 2026", "Heladera LG 2026, touch screen", "img_5.jpg", 1270, 19, true, heladeras, 7L);
        Producto deskOffice = new Producto("Desk Office M-3", "Escritorio de oficina con variacion de altura", "img_6.jpg", 310, 16, true, muebles, 8L);
        Producto rackTV = new Producto("Reck TV", "Rack de TV con retroiluminacion", "img_7.jpg", 120, 28, true, muebles, 9L);

        System.out.println("Mostramos Productos");
        System.out.println(iphone17PM.toString());
        System.out.println(samsungS26U.toString());
        System.out.println(hss1.toString());
        System.out.println(lg26.toString());
        System.out.println(deskOffice.toString());
        System.out.println(rackTV.toString());

        System.out.println("---------------------");

        //Agregamos los productos a su categoria
        telefonos.agregarProducto(iphone17PM);
        telefonos.agregarProducto(samsungS26U);
        heladeras.agregarProducto(hss1);
        heladeras.agregarProducto(lg26);
        muebles.agregarProducto(deskOffice);
        muebles.agregarProducto(rackTV);

        System.out.println("Verificamos si se agregaron");

        System.out.println(telefonos.toString());
        System.out.println(heladeras.toString());
        System.out.println(muebles.toString());

        System.out.println("---------------------");

        //Instanciamos usuarios
        Usuario administrador = new Usuario("Nicolas", "Tempio", "nico@gmail.com", "1550505050", "abc123", Rol.ADMIN, 10L);
        Usuario usuario = new Usuario("Juan", "Peña", "jp@gmail.com", "1560606060", "abc111", Rol.USUARIO, 11L);

        System.out.println("Verificamos usuarios");

        System.out.println(administrador.toString());
        System.out.println(usuario.toString());

        System.out.println("---------------------");

        // Instanciamos pedidos
        Pedido pedido1 = new Pedido(Estado.PENDIENTE, 0, FormaPago.TARJETA, administrador, 12L);
        Pedido pedido2 = new Pedido(Estado.CONFIRMADO, 0, FormaPago.EFECTIVO, administrador, 13L);
        Pedido pedido3 = new Pedido(Estado.PENDIENTE, 0, FormaPago.TRANSFERENCIA, usuario, 14L);
        Pedido pedido4 = new Pedido(Estado.TERMINADO, 0, FormaPago.TARJETA, usuario, 15L);

        System.out.println("Verificamos pedidos");

        System.out.println(pedido1.toString());
        System.out.println(pedido2.toString());
        System.out.println(pedido3.toString());
        System.out.println(pedido4.toString());

        System.out.println("---------------------");

        //Agregamos productos a pedidos.
        pedido1.addDetallePedido(2, iphone17PM);
        pedido1.addDetallePedido(1, samsungS26U);
        pedido1.addDetallePedido(3, deskOffice);

        pedido2.addDetallePedido(1, lg26);
        pedido2.addDetallePedido(2, rackTV);
        pedido2.addDetallePedido(3, samsungS26U);

        pedido3.addDetallePedido(5, iphone17PM);
        pedido3.addDetallePedido(2, lg26);
        pedido3.addDetallePedido(7, deskOffice);

        pedido4.addDetallePedido(4, iphone17PM);
        pedido4.addDetallePedido(3, hss1);
        pedido4.addDetallePedido(5, rackTV);

        //Verificamos
        System.out.println("Verificamos si se agregaron correctamente");
        System.out.println(pedido1.getDetalles().toString());
        System.out.println(pedido2.getDetalles().toString());
        System.out.println(pedido3.getDetalles().toString());
        System.out.println(pedido4.getDetalles().toString());

        System.out.println("---------------------");

        //Asignamos pedidos a usuarios
        administrador.agregarPedido(pedido1);
        administrador.agregarPedido(pedido2);
        usuario.agregarPedido(pedido3);
        usuario.agregarPedido(pedido4);

        System.out.println("---------------------");

        //Llamo a la funcion para imprimir los pedidos y detalles de cada usuario.
        imprimirResumenPorUsuario(administrador);
        imprimirResumenPorUsuario(usuario);

    }

    //Creo funcion para imprimir en consola pasando usuario como parametro.
    public static void imprimirResumenPorUsuario(Usuario usuario) {
        System.out.println("==============================================================================");
        System.out.println(usuario.toString());
        System.out.println("==============================================================================");

        double totalAcumulado = 0;

        for (Pedido pedido : usuario.getPedidos()) {
            System.out.println(pedido.toString());
            System.out.println("-------------------------------------");

            for (DetallePedido detalle : pedido.getDetalles()) {
                System.out.println(detalle.toString());
            }

            System.out.println("TOTAL DEL PEDIDO: $" + String.format("%.2f", pedido.getTotal()));
            System.out.println("-------------------------------------\n");

            totalAcumulado += pedido.getTotal();

        }
        System.out.println("TOTAL ACUMULADO DEL USUARIO: $" + String.format("%.2f", totalAcumulado));
        System.out.println("==============================================================================\n");

    }
}
