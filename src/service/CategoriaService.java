/*Esta clase gestionará las listas de memoria para la clase Categoria.
El Main la llamará para mostrar los datos en consola*/
package service;

    import entities.Categoria;
import java.util.ArrayList;

public class CategoriaService {
    //Creo la lista en memoria que almacena todas las categorías del sistema
    private static ArrayList<Categoria> listaCategorias = new ArrayList<>();

    
    //Método agregarCategoria
    public void agregarCategoria(Categoria c) {
        this.listaCategorias.add(c);
    }
    
    //Método listarCategorias
    public ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> activas = new ArrayList<>();
        for (Categoria cat : listaCategorias) {
            //Muestro solo categorías no eliminadas
            if (!cat.isEliminado()) {
                activas.add(cat);
            }
        }
        return activas;
    }
    
    
    //Método para crear Categorias
    public Categoria crearCategoria(String nombre, String descripcion) {
        //Valido que no exista otra categoría con el mismo nombre
        for (Categoria cat : listaCategorias) {
            //Comparo ignorando mayúsculas/minúsculas y espacios de más
            if (cat.getNombre().trim().equalsIgnoreCase(nombre.trim()) && !cat.isEliminado()) {
                throw new IllegalArgumentException("ERROR: Ya existe una categoría activa con el nombre '" + nombre + "'.");
            }
        }

        //Genero la nueva Categoria y la agrego a la lista
        Categoria nuevaCategoria = new Categoria(nombre, descripcion);
        listaCategorias.add(nuevaCategoria);
        
        //Retorno la entidad generada
        return nuevaCategoria;
    }
    
    
    //Método buscarCategoriaPorId
    public Categoria buscarCategoriaPorId(Long id){
        int i = 0;
        Categoria catEncontrada = null;
        while(i < listaCategorias.size() && !this.listaCategorias.get(i).getId().equals(id)){
            i++;
        }
        
        if(i < listaCategorias.size()){
            catEncontrada = this.listaCategorias.get(i);
        }
        
        return catEncontrada;
    }
    
    //Método editar Categoria
    public void editarCategoria(Categoria categoria, String nombre, String descripcion){
        //Valido que no se utilice el nombre de otra categoría
        for (Categoria cat : listaCategorias) {
        //Si el nombre ya existe en otra categoría que no sea la que estoy editando y está activa
        if (!cat.getId().equals(categoria.getId()) && 
            cat.getNombre().trim().equalsIgnoreCase(nombre.trim()) && 
            !cat.isEliminado()) {
            throw new IllegalArgumentException("ERROR: Ya existe otra categoría activa con el nombre '" + nombre + "'.");
        }
    }
        //Actualizo datos
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }
    
    //Método eliminarCategoria
    public void eliminarCategoria(Categoria categoria){
        if (categoria.getProductos() != null){
            //Cuento los productos que están activos
            long productosActivos = categoria.getProductos().stream()
                .filter(p -> !p.isEliminado())
                .count();
            
            if (productosActivos > 0) {
            throw new IllegalStateException("ERROR: No se puede eliminar la categoría porque tiene " 
                    + productosActivos + " productos activos asociados. Primero elimine los productos.");
        }
        }
        
        //Marco eliminado = true (sin hacer .remove de la lista)
        categoria.setEliminado(true);
    }
}
