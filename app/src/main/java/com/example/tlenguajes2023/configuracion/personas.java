package com.example.tlenguajes2023.configuracion;
import java.io.Serializable;

public class personas implements Serializable
{
    private Integer id;
    private String nombres;
    private String apellidos;
   // private String genero;
    private String direccion;
    private String edad;
    private String correo;

    private String foto;

    public personas()
    {
    }

    public personas(Integer id, String nombres, String apellidos, String direccion, String edad, String correo, String foto) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
       // this.genero = genero;
        this.correo = correo;
        this.direccion = direccion;


        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
