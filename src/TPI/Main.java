/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TPI;

import TPI.menu.MenuCategoria;
import TPI.menu.MenuPedido;
import TPI.menu.MenuPrincipal;
import TPI.menu.MenuProducto;
import TPI.menu.MenuUsuario;
import TPI.services.CategoriaServices;
import TPI.services.PedidoServices;
import TPI.services.ProductoServices;
import TPI.services.UsuarioServices;

/**
 *
 * @author nicoyshansho
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CategoriaServices categoriaServices = new CategoriaServices();
        ProductoServices productoServices = new ProductoServices(categoriaServices);
        UsuarioServices usuarioServices = new UsuarioServices();
        PedidoServices pedidoServices = new PedidoServices(usuarioServices, productoServices);

        MenuCategoria menuCategoria = new MenuCategoria(categoriaServices);
        MenuProducto menuProducto = new MenuProducto(categoriaServices, productoServices);
        MenuUsuario menuUsuario = new MenuUsuario(usuarioServices);
        MenuPedido menuPedido = new MenuPedido(pedidoServices, usuarioServices, productoServices);

        MenuPrincipal menuPrincipal = new MenuPrincipal(menuCategoria, menuProducto, menuUsuario, menuPedido);

        menuPrincipal.mostrar();
    }
}
