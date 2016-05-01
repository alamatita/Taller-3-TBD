package com.citiaps.jefferson.database;

/**
 * Created by alamatita on 30-04-16.
 */
public class Actor {
    private String nombre;
    private String apellido;
    private String lastUpdate;
    private String actorId;

    public Actor(String nom, String ape, String last, String id){
        nombre=nom;
        apellido=ape;
        lastUpdate=last;
        actorId=id;
    }

    public Actor(String nom, String ape){
        nombre=nom;
        apellido=ape;
        lastUpdate="";
        actorId="";
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getActorId() {
        return actorId;
    }
}
