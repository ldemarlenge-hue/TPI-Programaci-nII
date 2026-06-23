
package entities;

public class Producto extends Base {
    //Atributos
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria; //Relación n..1 con Categoría
    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()){
            //Si el dato es inválido, se lanza la excepción
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío o ser nulo.");
        }
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0){
            throw new IllegalArgumentException("ERROR. El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()){
            throw new IllegalArgumentException("La descripción del producto no puede estar vacía o ser nula.");
        }
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0){
            throw new IllegalArgumentException("ERROR. El stock no puede ser negativo");
        }
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("El producto debe estar asociado a una categoría válida.");
        }
        this.categoria = categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    //Constructor
    public Producto(String nombre, double precio, String descripcion, int stock, Categoria categoria) {
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setStock(stock);
        this.imagen = "";
        this.disponible = true;
        setCategoria(categoria); //Vinculo una categoría al nacer
    }

    //Método toString
    @Override
    public String toString() {
        return "Producto [ID: " + getId() + " | Nombre: " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Cat: " + categoria.getNombre() + "]";
    }
    
}
