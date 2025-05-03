/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author zengjuncai
 */
public class Brand implements Serializable {
    private String name;

    public Brand(String name) {  this.name = name;   }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public Brand(){}
}
