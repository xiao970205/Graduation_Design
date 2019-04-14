package com.znck.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author 肖舒翔
 * 2019-04-09
 * @version 1.0
 * 第三个版本的停车类
 */
public class ParkingEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private String carId;

	private String inSpaceId;

	private String nowSpaceId;

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

	private String nature;

	private String way;

	public int getOutTimeForSort() {
		return (int) this.getOutTime().getTime();
	}

	public int getInPlaceTimeForSort() {
		return (int) this.getInPlaceTime().getTime();
	}

	public int getVipAppGetTimeForSort() {
		return (int) this.vipAppGetTime.getTime();
	}

	public int getVipSendTimeForSort() {
		return (int) this.vipSendTime.getTime();
	}

	public ParkingEntity() {
		super();
	}

	public void addWay(String way) {
		setWay(this.getWay() + way);
	}

	public ParkingEntity(String id, String carId, String inSpaceId, String nowSpaceId, String saveSpaceId,
			String outSpaceId, Date vipSendTime, Date vipAppParkingTime, Date inPlaceTime, Date inTime, Date vipGetTime,
			Date vipAppGetTime, Date outTime, Date outPlaceTime, Date getTime, String nature, String way) {
		this.id = id;
		this.carId = carId;
		this.inSpaceId = inSpaceId;

		this.nowSpaceId = nowSpaceId;
		this.saveSpaceId = saveSpaceId;
		this.outSpaceId = outSpaceId;

		this.vipSendTime = vipSendTime;
		this.vipAppParkingTime = vipAppParkingTime;
		this.inPlaceTime = inPlaceTime;

		this.inTime = inTime;
		this.vipGetTime = vipGetTime;
		this.vipAppGetTime = vipAppGetTime;

		this.outTime = outTime;
		this.outPlaceTime = outPlaceTime;
		this.getTime = getTime;

		this.nature = nature;
		this.way = way;
	}

	public ParkingEntity(String id,String carId,String inSpaceId
				,String nowSpaceId,String saveSpaceId,Date vipAppParkingTime
				,Date vipSendTime,String nature,String way){
		this.id = id;
		this.carId = carId;
		this.inSpaceId = inSpaceId;
		this.nowSpaceId = nowSpaceId;
		this.saveSpaceId = saveSpaceId;
		this.vipAppParkingTime = vipAppParkingTime;
		this.vipSendTime = vipSendTime;
		this.nature = nature;
		this.way = way;
	}

	public ParkingEntity(String id,String carId,String inSpaceId
			,String nowSpaceId,String saveSpaceId,Date inPlaceTime
			,String nature,String way){
		this.id = id;
		this.carId = carId;
		this.inSpaceId = inSpaceId;
		this.nowSpaceId = nowSpaceId;
		this.saveSpaceId = saveSpaceId;
		this.inPlaceTime = inPlaceTime;
		this.nature = nature;
		this.way = way;
	}

	private String getNullDateOrDateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
		if (date != null) {
			return format.format(date);
		}else{
			return "null";
		}
	}

	@Override
	public String toString() {
		return "id: " + this.getId() + ", carId: " + this.getCarId() + ",inSpaceId: " + this.getInSpaceId()
				+ ",nowSpaceId: " + this.getNowSapceId() + ",saveSpaceId: " + this.saveSpaceId + ",outSpaceId: " + this.outSpaceId
				+ ",vipSendTime: " + getNullDateOrDateToString(this.vipSendTime)
				+ ",vipAppParkingTime: " + getNullDateOrDateToString(this.vipAppParkingTime)
				+ ",inPlaceTime: " + getNullDateOrDateToString(this.inPlaceTime)
				+ ",inTime: " + getNullDateOrDateToString(this.inTime)
				+ ",vipGetTime: " + getNullDateOrDateToString(this.vipGetTime)
				+ ",vipAppGetTime: " + getNullDateOrDateToString(this.vipAppGetTime)
				+ ",outTime: " + getNullDateOrDateToString(this.outTime)
				+ ",outPlaceTime: " + getNullDateOrDateToString(this.outPlaceTime)
				+ ",getTime: " + getNullDateOrDateToString(this.getTime)
				+ ",nature: " + this.nature + ",way: " + this.way;
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

	public String getNowSapceId() {
		return nowSpaceId;
	}

	public void setNowSapceId(String nowSapceId) {
		this.nowSpaceId = nowSapceId;
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
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