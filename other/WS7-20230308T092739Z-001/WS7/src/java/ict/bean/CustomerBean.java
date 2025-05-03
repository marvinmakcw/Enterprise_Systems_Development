/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;
import java.io.Serializable;

public class CustomerBean implements Serializable{
    private String custId, name, tel;
    private int age;
    
    public CustomerBean(){}
    public CustomerBean(String id, String name, String tel, int age){
        this.custId = id;
        this.name = name;
        this.tel = tel;
        this.age = age;
    }

    public String getCustId(){ return custId; }
    public String getName(){ return name; }
    public String getTel(){ return tel; }
    public int getAge(){ return age; }
    
    public void setCustId(String _custId){ custId = _custId; }
    public void setName(String _name){ name = _name; }
    public void setTel(String _tel){ tel = _tel; }
    public void setAge(int _age){ age = _age; }
    
    public String toString(){
        return "CustomerBean{custid=" + getCustId() + ", name=" + getName() + ", tel=" + getTel() + ", age=" + getAge() + "}";
    }
}