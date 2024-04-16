package com.example.desafio2_mh181960.models

public class Medicine {
    var nombre:String? =null
    var id:String? =null
    var precio:Double? = 0.0

    constructor(){}

    constructor(nombre:String?,precio:Double?){
        this.nombre = nombre
        this.precio = precio
    }

    constructor(id:String?,nombre:String?,precio:Double?){
        this.id = id
        this.nombre = nombre
        this.precio = precio
    }
}