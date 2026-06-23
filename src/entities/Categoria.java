
package entities;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    //Atributos
    private String nombre;
    private String descripcion;
    private List <Producto> productos; //Relación 1..n con Producto

    //Getters y Setters
     public String getNombre() {    
        return nombre;
    }

    public void setNombre(String nombre) {
        /*
        .trim() -> quita todos los espacios en blanco que haya al principio y 
        al final de una cadena de texto.
        .isEmpty() -> devuelve true si el texto no tiene absolutamente ningún 
        carácter (su longitud es 0).
        Juntos trabajan para obligar al usuario a ingresar sí o sí una cadena de caracteres
        No sólo verifico que no sea una entrada nula sino que tampoco sea vacía
        */
        if (nombre == null || nombre.trim().isEmpty()){
            //Si el dato es inválido, se lanza la excepción
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío o ser nulo.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()){
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía o ser nula.");
        }
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    
    //Constructor
    public Categoria(String nombre, String descripcion) {
        this.productos = new ArrayList<>();
        setNombre(nombre);
        setDescripcion(descripcion);
    }
    
    
    //Agregar producto
    public void agregarProducto(Producto producto){
        if (producto == null){
            throw new IllegalArgumentException("No se puede agregar un producto nulo.");
        }
        this.productos.add(producto);
    }
    
     
    //toStigng
    @Override
    public String toString() {
        return "Categoría [ID: " + getId() + " | Nombre: " + 
                nombre + " | Descripción: " + descripcion + 
                " | Cant. Productos: " + productos.size() + "]";
    }
    
}
