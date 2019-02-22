package com.znck.entity;

/**
 * 
 * SpaceEntity
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
public class SpaceEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private int x;
    private int y;
    private int z;
    private String nature;
    private String carId;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int max_x, int max_y, int max_z) {
        int weight_x = 1;
        int weight_y = weight_x * max_x;
        int weight_z = weight_y * max_y + weight_y;
        this.weight = weight_x * (22-x) + weight_y * y + weight_z * z;
    }

    public SpaceEntity() {
        super();
    }

    public SpaceEntity(String id, int x, int y, int z, String nature, String carId) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.nature = nature;
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " ,x: " + this.x + " ,y: " + this.y + " ,z: " + this.z + " ,nature: " + this.nature
            + " ,carid: " + this.carId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
