/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author willi
 */
public class Retornomainmentu {
    
   private String id;
   private long suma;

    public Retornomainmentu() {
    }

    public Retornomainmentu(String id, long suma) {
        this.id = id;
        this.suma = suma;
    }

    public String getId() {
        return id;
    }

    public long getSuma() {
        return suma;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSuma(long suma) {
        this.suma = suma;
    }
    

    @Override
    public String toString() {
        return "Retornomainmentu{" + "id=" + id + ", suma=" + suma + '}';
    }
    
    
   
   
   
   
    
}
