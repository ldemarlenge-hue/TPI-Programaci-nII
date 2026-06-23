/*Esta clase gestionará las listas de memoria para la clase Pedido.
El Main la llamará para mostrar los datos en consola*/
package service;

import entities.DetallePedido;
import entities.Pedido;
import entities.Producto;
import enums.Estado;
import enums.FormaPago;
import java.util.ArrayList;

public class PedidoService {
    //Creo la lista en memoria que almacena todos los pedidos del sistema
    private static ArrayList<Pedido> listaPedidos = new ArrayList<>();

    
    //Método agregarPedido
    public void agregarPedido(Pedido p) {
        this.listaPedidos.add(p);
    }
    
    
    //Método listarPedido
    public ArrayList<Pedido> listarPedido() {
        ArrayList<Pedido> activos = new ArrayList<>();
        for (Pedido ped : listaPedidos) {
            //Muestro solo pedidos no eliminados
            if (!ped.isEliminado()) {
                activos.add(ped);
            }
        }
        return activos;
    }
    
    
    //Método para listar pedidos filtrados por un usuario específico
    public ArrayList<Pedido> listarPedidosPorUsuario(Long idUsuario) {
        ArrayList<Pedido> filtrados = new ArrayList<>();
        for (Pedido ped : listaPedidos) {
            // Filtramos que no esté eliminado y que el ID del usuario asociado coincida
            if (!ped.isEliminado() && ped.getUsuario() != null && ped.getUsuario().getId().equals(idUsuario)) {
                filtrados.add(ped);
            }
        }
        return filtrados;
    }
    
    
    //Método buscarPedidoPorId
    public Pedido buscarPedidoPorId(Long id){
        int i = 0;
        Pedido pedEncontrado = null;
        while(i < listaPedidos.size() && !this.listaPedidos.get(i).getId().equals(id)){
            i++;
        }
        
        if(i < listaPedidos.size()){
            pedEncontrado = this.listaPedidos.get(i);
        }
        
        return pedEncontrado;
    }
    
    
    //Actualizar pedido
    public void actualizarPedido(Pedido pedido, Estado nuevoEstado, FormaPago nuevaFormaPago) {
        //Actualización condicional de Estado
        if (nuevoEstado != null) {
            pedido.setEstado(nuevoEstado);
        }
    
        //Actualización condicional de Forma de Pago
        if (nuevaFormaPago != null) {
            pedido.setFormaPago(nuevaFormaPago);
        }
    }
    
    //Método eleminarPedido
    public void eliminarPedido(Pedido pedido) {
        //Devolver stock para no dejar colecciones inconsistentes
        //Solo devolvemos stock si el pedido NO estaba ya CANCELADO o ENTREGADO
        for (DetallePedido detalle : pedido.getDetalles()) {
            if (!detalle.isEliminado()) {
                Producto prod = detalle.getProducto();
                //Le restituimos al producto la cantidad que tenía el detalle
                prod.setStock(prod.getStock() + detalle.getCantidad());
            
                //Marcar eliminado = true en sus detalles si corresponde
                detalle.setEliminado(true);
            }
        }

        //Se marca eliminado = true en el pedido
        pedido.setEliminado(true);
    }
}
