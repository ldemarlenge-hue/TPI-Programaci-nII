/*Esta clase gestionará las listas de memoria para la clase Usuario.
El Main la llamará para mostrar los datos en consola*/
package service;

import entities.Usuario;
import enums.Rol;
import java.util.ArrayList;

public class UsuarioService {
    //Creo la lista en memoria que almacena todos los productos del sistema
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    
    //Método agregarUsuario
    public void agregarUsuario(Usuario u) {
        this.listaUsuarios.add(u);
    }
    
    
    //Método listarUsuario
    public ArrayList<Usuario> listarUsuario() {
        ArrayList<Usuario> activos = new ArrayList<>();
        for (Usuario user : listaUsuarios) {
            //Muestro solo usuarios no eliminados
            if (!user.isEliminado()) {
                activos.add(user);
            }
        }
        return activos;
    }
    
    
    //Método para crear Usuarios
    public Usuario crearUsuario(String nombre, String apellido, String mail, String celular, String contraseña, Rol rol) {
        //El mail debe ser único entre los usuarios activos
        for (Usuario user : listaUsuarios) {
            if (user.getMail().trim().equalsIgnoreCase(mail.trim()) && !user.isEliminado()) {
                throw new IllegalArgumentException("ERROR: Ya existe un usuario activo registrado con el email '" + mail + "'.");
            }
        }

        //Si pasa la validación, se crea y persiste en memoria
        Usuario nuevoUser = new Usuario(nombre, apellido, mail, celular, contraseña, rol);
        listaUsuarios.add(nuevoUser);

        return nuevoUser;
    }
    
    
    //Método buscarUsuarioPorId
    public Usuario buscarUsuarioPorId(Long id){
        int i = 0;
        Usuario userEncontrado = null;
        while(i < listaUsuarios.size() && !this.listaUsuarios.get(i).getId().equals(id)){
            i++;
        }
        
        if(i < listaUsuarios.size()){
            userEncontrado = this.listaUsuarios.get(i);
        }
        
        return userEncontrado;
    }
    
    
    //Método editarUsuario
    public void editarUsuario(Usuario usuario, String nombre, String apellido, String mail, String celular, String contraseña, Rol rol) {
        //Si presiona Enter mantiene los datos guardados
    
        //Actualización condicional de Nombre
        if (nombre != null && !nombre.trim().isEmpty()) {
            usuario.setNombre(nombre);
        }
    
        //Actualización condicional de Apellido
        if (apellido != null && !apellido.trim().isEmpty()) {
            usuario.setApellido(apellido);
        }
    
        //Si se modifica el mail, se valida unicidad
        if (mail != null && !mail.trim().isEmpty() && !usuario.getMail().trim().equalsIgnoreCase(mail.trim())) {
            for (Usuario user : listaUsuarios) {
                if (!user.getId().equals(usuario.getId()) && 
                    user.getMail().trim().equalsIgnoreCase(mail.trim()) && 
                    !user.isEliminado()) {
                    throw new IllegalArgumentException("ERROR: Ya existe otro usuario activo registrado con el email '" + mail + "'.");
                }
            }
            usuario.setMail(mail);
        }
    
        //Actualización condicional de Celular
        if (celular != null && !celular.trim().isEmpty()) {
            usuario.setCelular(celular);
        }
    
        //Actualización condicional de Contraseña
        if (contraseña != null && !contraseña.isEmpty()) {
            usuario.setContraseña(contraseña);
        }
    
        //Actualización condicional de Rol
        if (rol != null) {
            usuario.setRol(rol);
        }
    }
    
    //Método eliminarUsuario
    public void eliminarUsuario(Usuario usuario) {
    usuario.setEliminado(true);
}
}
