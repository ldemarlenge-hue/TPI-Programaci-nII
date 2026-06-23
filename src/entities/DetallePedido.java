
package entities;

public class DetallePedido extends Base {
    //Atributos
    private int cantidad;
    private double subtotal;
    private Producto producto; //Relación n..1 con Producto
    
    //Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0){
            throw new IllegalArgumentException("ERROR. La cantidad de productos en el detalle debe ser mayor a cero.");      
        } 
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    //No hago un "setSubtotal(double)" para evitar que manipulen el dinero a mano. El subtotal solo se altera mediante el método calcularSubtotal().

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        this.producto = producto;
        calcularSubtotal(); //Si cambia el producto, recalculamos
    }
    
    //Método calcularSubtotal
    public void calcularSubtotal() {
        if (this.producto != null) {
            this.subtotal = this.cantidad * this.producto.getPrecio();
        }
    }
    
    
    //Constructor
    public DetallePedido(int cantidad, Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El detalle del pedido debe contener un producto válido.");
        }
        this.producto = producto;
        setCantidad(cantidad);
    }

    
    //Método toString
    @Override
    public String toString() {
        //String.format ayudará a que se vea como un ticket, %.2f es para usar 2 decimales
        return String.format("   - %s x %d unidades | Precio Unitario: $%.2f | Subtotal: $%.2f", 
                producto.getNombre(), cantidad, producto.getPrecio(), subtotal);
    }
    
}
