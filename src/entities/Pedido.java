
package entities;

import enums.Estado;
import enums.FormaPago;
import interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Pedido extends Base implements Calculable {
    //Atributos
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago; 
    private List<DetallePedido> detalles; //Relación 1..n con DetallePedido
    private Usuario usuario; // Relación n..1 con Usuario
    
    //Gettters y Setters
    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    //Constructor
    public Pedido(Estado estado, FormaPago formaPago, Usuario usuario) {
        this.detalles = new ArrayList<>();
        
        if (estado == null) {
            throw new IllegalArgumentException("El pedido debe nacer con un Estado válido.");
        }
        this.estado = estado;
        
        if (formaPago == null) {
            throw new IllegalArgumentException("Debe especificar una Forma de Pago válida.");
        }
        this.formaPago = formaPago;
        
        if (usuario == null) {
            throw new IllegalArgumentException("El pedido debe estar asociado a un Usuario.");
        }
        this.usuario = usuario;
        
        this.fecha = LocalDate.now();
        this.total = 0.0;
    }
    
    
    //Método calcularTotal para implementar la Interfaz Calculable
    @Override
    public void calcularTotal() {
        double sumaSuma = 0.0;
        for (DetallePedido detalle : detalles) {
            if (!detalle.isEliminado()) { //Solo sumo renglones no eliminados
                sumaSuma += detalle.getSubtotal();
            }
        }
        this.total = sumaSuma;
    }
    
    //Agregar detalle
    public void addDetallePedido(int cantidad, Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("No se puede agregar un detalle con un producto nulo.");
        }
        
        //Valido Stock
        if (cantidad > producto.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre() + 
                    " (Solicitado: " + cantidad + " | Disponible: " + producto.getStock() + ")");
        }
        
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
        this.detalles.add(nuevoDetalle);
        
        //Resto el stock del producto
        producto.setStock(producto.getStock() - cantidad);
        
        //Recalculo el total automáticamente
        calcularTotal();
    }
    
    //Buscar Detalle por Producto
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        if (producto == null) return null;
        
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                return detalle;
            }
        }
        return null; 
    }
    
    //Eliminar Detalle por Producto
    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalleEncontrado = findDetallePedidoByProducto(producto);
        
        if (detalleEncontrado == null) {
            throw new NoSuchElementException("El producto '" + producto.getNombre() + "' no se encuentra en el pedido.");
        }
        
        //Devuelvo el stock al producto antes de borrar el renglón
        Producto p = detalleEncontrado.getProducto();
        p.setStock(p.getStock() + detalleEncontrado.getCantidad());
        
        //Lo renuevo de la lista
        this.detalles.remove(detalleEncontrado);
        
        //Recalculo el total de la factura
        calcularTotal();
    }
    
    //Método toString
    @Override
    public String toString() {
        /*
        Uso StringBuilder en el toString() para poder concatenar todo el ticket 
        de compra porque un pedido puede tener muchos renglones de detalles
        */
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("   Pedido ID: %d | Fecha: %s | Pago: %s | Estado: %s\n", 
                getId(), fecha, formaPago, estado));
        
        //Recorro los renglones y sumo su toString()
        for (DetallePedido det : detalles) {
            sb.append(det.toString()).append("\n");
        }
        
        sb.append(String.format("   TOTAL DEL PEDIDO: $%.2f\n", total));
        return sb.toString();
    }

    
    
}
