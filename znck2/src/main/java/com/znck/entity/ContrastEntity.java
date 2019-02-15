package com.znck.entity;

/**
 * 
 * ContrastEntity
 * @author 肖舒翔
 * @version 1.0
 *
 */
public class ContrastEntity {
    private static final long serialVersionUID = 1L;
    private String id;
    private String realName;

    public ContrastEntity() {
        super();
    }

    public ContrastEntity(String id, String realName) {
        super();
        this.id = id;
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " ,RealName:" + this.realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
