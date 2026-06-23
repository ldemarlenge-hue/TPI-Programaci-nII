
package entities;

import enums.Rol;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Base {
    //Atributos
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol;
    private List <Pedido> pedidos; //Relación 1..n con Pedido
    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()){
            //Si el dato es inválido, se lanza la excepción
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío o ser nulo.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()){
            //Si el dato es inválido, se lanza la excepción
            throw new IllegalArgumentException("El apellido del usuario no puede estar vacío o ser nulo.");
        }
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        //Primero protejo contra nulos o vacíos para evitar que rompa el programa
        if (mail == null || mail.trim().isEmpty()){
            throw new IllegalArgumentException("El mail del usuario no puede estar vacío o ser nulo.");
        }
        //Luego aplico reglas básicas de negocio: debe contener '@' y no tener espacios sino se lanza excepción
        if (!mail.contains("@") || mail.contains(" ")) {
            throw new IllegalArgumentException("ERROR. Formato de correo inválido. Asegúrese de incluir '@' y no usar espacios.");
        }
    
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        //Primero protejo contra nulos o vacíos para evitar que rompa el programa
        if (celular == null || celular.trim().isEmpty()){
            throw new IllegalArgumentException("El celular del usuario no puede estar vacío o ser nulo.");
        }
        //Luego aplico reglas básicas de negocio:
        String patron = "^\\+54 9 \\d{10}$";
        if (!celular.matches(patron)) {
            throw new IllegalArgumentException("Error: Por favor, respete el formato internacional (+54 9 XXXXXXXXXX).");
        }
        this.celular = celular;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        if (contraseña == null || contraseña.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía o ser nula.");
        }
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El usuario debe tener un Rol asignado obligatoriamente.");
        }
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    
    //Constructor
    public Usuario(String nombre, String apellido, String mail, String celular, String contraseña, Rol rol) {
        this.pedidos = new ArrayList<>();
        setNombre(nombre);
        setApellido(apellido);
        setMail(mail);
        setCelular(celular);
        setContraseña(contraseña);
        setRol(rol);
    }
    
    //Agregar pedido
    public void agregarPedido(Pedido pedido){
        if (pedido == null) {
            throw new IllegalArgumentException("No se puede agregar un pedido nulo al usuario.");
        }
        this.pedidos.add(pedido);
    }

    //Método toString
    @Override
    public String toString() {
        return "Usuario [ID: " + getId() + " | " + apellido + ", " + nombre + " | Mail: " + mail + " | Rol: " + rol + " | Cant. Pedidos: " + pedidos.size() + "]";
    }
    
}
