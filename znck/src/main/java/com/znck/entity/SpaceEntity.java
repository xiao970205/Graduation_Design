 package com.znck.entity;

 public class SpaceEntity {
     private static final long serialVersionUID = 1L;
     
     private String id;
     private int x;
     private int y;
     private int z;
     private String nature;
     private String carId;
     
     public SpaceEntity(){
         super();
     }
     
     public SpaceEntity(String id,int x,int y,int z,String nature,String carId){
         this.id = id;
         this.x = x;
         this.y = y;
         this.z = z;
         this.nature = nature;
         this.carId = carId;
     }
     
     @Override
     public String toString(){
         return "id: " + this.id + " ,x: "+ this.x + " ,y: " +this.y + " ,z: " +this.z + " ,nature: " +this.nature + " ,carid: " +this.carId;
     }
}
