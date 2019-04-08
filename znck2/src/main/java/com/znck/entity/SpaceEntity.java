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

    public void setWeight(int maxX, int maxY, int maxZ) {
        int weightX = 1;
        int weightY = weightX * maxX;
        int weightZ = weightY * maxY + weightY;
        this.weight = weightX * (maxX-x) + weightY * y + weightZ * z;
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
