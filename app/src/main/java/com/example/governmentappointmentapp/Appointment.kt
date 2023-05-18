package com.king.mysimplefirebasedatabaseapp

class Appointment {
    var name:String = ""
    var email:String = ""
    var reason:String = ""

    constructor(name:String, email:String, age:String){
        this.name = name
        this.email = email
        this.reason = age
    }

    constructor(){}
}