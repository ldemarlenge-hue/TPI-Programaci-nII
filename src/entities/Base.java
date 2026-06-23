
package entities;

import java.time.LocalDateTime;

public abstract class Base {
    //Atributos
    private final Long id; //Lo marco como final porque no se podrá modificar luego
    private boolean eliminado;
    private LocalDateTime createdAt;
    private static Long totalId = 0L; //Genero este atributo static para poder generar Id
    
    
    //Getters y Setters
    public Long getId() {    
        return id;
    }

    //No genero un set de id para que no se pueda modificar una vez otorgado

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //No genero un set para createdAt porque lo genera el LocalDateTime

    //Constructor
    /*
    Lo genero vacío porque por defecto eliminado será false y la hora la 
    genero en el construcor.
    Respecto al id, decido también generarlo de manera automática. De esta manera,
    evito que se generen duplicados o de manera errónea ya que al ser una variable 
    final luego no se podrá modificar, por lo que debe estar sí o sí bien 
    ingresada desde el principio.
    */
    public Base() {
        totalId ++; //Incremento el contador
        this.id = totalId; //Y asigno ese valor a la instancia actual
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }
    
    //Método toString abstracto, cada clase deberá sobreescribirlo
    @Override
    public abstract String toString();
   
}
