package com.znck.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 停车存储类
 * 
 * @author 肖舒翔
 * 2019-04-09
 * @version 1.0
 */
public class ParkingSaveEntity {
	private static final long serialVersionUID = 1L;

	private String id;

	private String carId;

	private String inSpaceId;

	private String saveSpaceId;

	private String outSpaceId;

	private Date vipSendTime;

	private Date vipAppParkingTime;

	private Date inPlaceTime;

	private Date inTime;

	private Date vipGetTime;

	private Date vipAppGetTime;

	private Date outTime;

	private Date outPlaceTime;

	private Date getTime;

	private String way;

	public ParkingSaveEntity() {
		super();
	}

	public ParkingSaveEntity(ParkingEntity parkingEntity) {
		this.id = parkingEntity.getId();
		this.carId = parkingEntity.getCarId();
		this.inSpaceId = parkingEntity.getInSpaceId();
		this.saveSpaceId = parkingEntity.getSaveSpaceId();
		this.outSpaceId = parkingEntity.getOutSpaceId();
		this.vipAppParkingTime = parkingEntity.getVipSendTime();
		this.vipAppGetTime = parkingEntity.getVipAppGetTime();
		this.inPlaceTime = parkingEntity.getInPlaceTime();
		this.inTime = parkingEntity.getInTime();
		this.vipGetTime = parkingEntity.getVipGetTime();
		this.vipAppGetTime = parkingEntity.getVipAppGetTime();
		this.outTime = parkingEntity.getOutTime();
		this.outPlaceTime = parkingEntity.getOutPlaceTime();
		this.getTime = parkingEntity.getGetTime();
		this.way = parkingEntity.getWay();
	}
	
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
		String vipSendTime = "null";
		if(this.vipSendTime != null) {
			vipSendTime = format.format(this.vipSendTime);
		}
		String vipAppParkingTime = "null";
		if(this.vipAppParkingTime != null) {
			vipAppParkingTime = format.format(this.vipAppParkingTime);
		}
		String inPlaceTime = "null";
		if(this.inPlaceTime != null) {
			inPlaceTime = format.format(this.inPlaceTime);
		}
		String inTime = "null";
		if(this.inTime != null) {
			inTime = format.format(this.inTime);
		}
		String vipGetTime = "null";
		if(this.vipGetTime != null) {
			vipGetTime = format.format(this.vipGetTime);
		}
		String vipAppGetTime = "null";
		if(this.vipAppGetTime != null) {
			vipAppGetTime = format.format(this.vipAppGetTime);
		}
		String outTime = "null";
		if(this.outTime != null) {
			outTime = format.format(this.outTime);
		}
		String outPlaceTime = "null";
		if(this.outPlaceTime != null) {
			outPlaceTime = format.format(this.outPlaceTime);
		}
		String getTime = "null";
		if(this.getTime == null) {
			getTime = format.format(this.getTime);
		}
		return "id: "+this.getId()+", carId: "+this.getCarId()+",inSpaceId: "+this.getInSpaceId()
				+ ",saveSpaceId: "+this.saveSpaceId+",outSpaceId: "+this.outSpaceId
				+ ",vipSendTime: "+vipSendTime+",vipAppParkingTime: "+vipAppParkingTime+",inPlaceTime: "+inPlaceTime
				+ ",inTime: "+inTime+",vipGetTime: "+vipGetTime+",vipAppGetTime: "+vipAppGetTime
				+ ",outTime: "+outTime+",outPlaceTime: "+outPlaceTime+",getTime: "+getTime
				+ ",way: "+this.way;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getInSpaceId() {
		return inSpaceId;
	}

	public void setInSpaceId(String inSpaceId) {
		this.inSpaceId = inSpaceId;
	}

	public String getSaveSpaceId() {
		return saveSpaceId;
	}

	public void setSaveSpaceId(String saveSpaceId) {
		this.saveSpaceId = saveSpaceId;
	}

	public String getOutSpaceId() {
		return outSpaceId;
	}

	public void setOutSpaceId(String outSpaceId) {
		this.outSpaceId = outSpaceId;
	}

	public Date getVipSendTime() {
		return vipSendTime;
	}

	public void setVipSendTime(Date vipSendTime) {
		this.vipSendTime = vipSendTime;
	}

	public Date getVipAppParkingTime() {
		return vipAppParkingTime;
	}

	public void setVipAppParkingTime(Date vipAppParkingTime) {
		this.vipAppParkingTime = vipAppParkingTime;
	}

	public Date getInPlaceTime() {
		return inPlaceTime;
	}

	public void setInPlaceTime(Date inPlaceTime) {
		this.inPlaceTime = inPlaceTime;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getVipGetTime() {
		return vipGetTime;
	}

	public void setVipGetTime(Date vipGetTime) {
		this.vipGetTime = vipGetTime;
	}

	public Date getVipAppGetTime() {
		return vipAppGetTime;
	}

	public void setVipAppGetTime(Date vipAppGetTime) {
		this.vipAppGetTime = vipAppGetTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Date getOutPlaceTime() {
		return outPlaceTime;
	}

	public void setOutPlaceTime(Date outPlaceTime) {
		this.outPlaceTime = outPlaceTime;
	}

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
