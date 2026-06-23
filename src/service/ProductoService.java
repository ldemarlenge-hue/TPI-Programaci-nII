/*Esta clase gestionará las listas de memoria para la clase Producto.
El Main la llamará para mostrar los datos en consola*/
package service;


import entities.Categoria;
import entities.Producto;
import java.util.ArrayList;


public class ProductoService {
    //Creo la lista en memoria que almacena todos los productos del sistema
    private static ArrayList<Producto> listaProductos = new ArrayList<>();

    
    //Método agregarProducto
    public void agregarProducto(Producto p) {
        this.listaProductos.add(p);
    }
    
    
    //Método listarProductos
    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> activos = new ArrayList<>();
        for (Producto prod : listaProductos) {
            //Muestro solo productos no eliminados
            if (!prod.isEliminado()) {
                activos.add(prod);
            }
        }
        return activos;
    }
    
    
    //Método para crear Productos
    public Producto crearProducto(String nombre, double precio, String descripcion, int stock, Categoria categoria) {
        Producto nuevoProd = new Producto(nombre, precio, descripcion, stock, categoria);

        //Lo agrego al listado general del servicio
        listaProductos.add(nuevoProd);

        //Lo vinculo a la lista interna de la categoría correspondiente
        categoria.agregarProducto(nuevoProd);

        return nuevoProd;
    }
    
    
    //Método buscarProductoPorId
    public Producto buscarProductoPorId(Long id){
        int i = 0;
        Producto prodEncontrado = null;
        while(i < listaProductos.size() && !this.listaProductos.get(i).getId().equals(id)){
            i++;
        }
        
        if(i < listaProductos.size()){
            prodEncontrado = this.listaProductos.get(i);
        }
        
        return prodEncontrado;
    }
    
    
    //Método editar Producto
    public void editarProducto(Producto producto, String nombre, double precio, String descripcion, int stock, Categoria categoria){
        //Valido y modifico si el usuario ingresó un texto (No presionó ENTER)
        if (nombre != null && !nombre.trim().isEmpty()) {
            
            //Valido que no se utilice el nombre de otro producto
            for (Producto prod : listaProductos) {
                // Si el nombre ya existe en otro producto que no sea el que estoy editando y está activo
                if (!prod.getId().equals(producto.getId()) && 
                    prod.getNombre().trim().equalsIgnoreCase(nombre.trim()) && 
                    !prod.isEliminado()) {
                    throw new IllegalArgumentException("ERROR: Ya existe otro producto activo con el nombre '" + nombre + "'.");
                }
            }
            // Si el nombre es nuevo y válido, recién ahí lo actualizamos
            producto.setNombre(nombre);
        }
        
        //Actualizo condicional de Descripción
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            producto.setDescripcion(descripcion);
        }

        //Actualizo condicional de Precio (-1 significa que el usuario dio ENTER para mantenerlo)
        if (precio != -1) {
            producto.setPrecio(precio); 
        }

        //Actualizo condicional de Stock (-1 significa que el usuario dio ENTER)
        if (stock != -1) {
            producto.setStock(stock); 
        }

        //Actualizo condicional de Categoría
        if (categoria != null) {
            producto.setCategoria(categoria);
            categoria.agregarProducto(producto);
        }
    }
    
    
    //Método eliminarProducto
    public void eliminarProducto(Producto producto){
        producto.setEliminado(true);
    }
}
