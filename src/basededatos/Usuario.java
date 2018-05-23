package basededatos;

import java.sql.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String fechaNaciemiento;
    private int telefono;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidos, String fechaNaciemiento, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNaciemiento = fechaNaciemiento;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "id: " + id + " -- nombre: " + nombre + " -- apellidos:" + apellidos + 
                " --  fechaNaciemiento: " + fechaNaciemiento + " -- telefono:" + telefono;
    }
}
