package models.cliente;

import models.cuenta.CuentaBancaria;
import repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Cliente {
    private int id;
    private String nombre;

    public Cliente(String nombre, int id){
        this.id = id;
        this. nombre = nombre;
    }
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id; // puedes agregar nombre si querés, pero ID es suficiente
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
