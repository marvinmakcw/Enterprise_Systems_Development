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
public class Phone implements Serializable {
    private String name,img;
    private double price;

    public Phone(String name, String img, double price) {
        this.name = name;
        this.img = img;
        this.price = price;
    } 

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return img;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }

    public Phone(){}

}
