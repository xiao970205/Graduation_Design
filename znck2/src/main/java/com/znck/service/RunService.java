package com.znck.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znck.entity.ContrastEntity;
import com.znck.entity.ParkingEntity;
import com.znck.entity.ParkingSaveEntity;
import com.znck.entity.SpaceEntity;

/**
 * 
 * RunService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class RunService {

	/**
	 * id:729352f0e3b74fee91bf15baa7187e58 realName:出口
	 */
	private ContrastEntity ck;

	/**
	 * id:7bd42e7aad7645e4ad586349691a87e6 realName:车库-占用
	 */
	private ContrastEntity ckZy;

	/**
	 * id:1e95f2208c7f4dd9b584889bba7e3164 realName:入口
	 */
	private ContrastEntity rk;

	/**
	 * id:85f8320ee6004d5eb7f21a470fadb366 realName:取车中
	 */
	private ContrastEntity qCz;

	/**
	 * id:351cc2cdda6547528e20c6444e4a3bbd realName:存车中
	 */
	private ContrastEntity cCc;

	@Autowired
	private ContrastServiceImpl contrastService;

	@Autowired
	private ParkingServiceImpl parkingService;

	@Autowired
	private SpaceServiceImpl spaceService;

	@Autowired
	private ParkingSaveServiceImpl parkingSaveService;

	public void setAllContrast() {
		this.setCkZy(contrastService.getContrastByRealName("车库-占用"));
		this.setRk(contrastService.getContrastByRealName("入口"));
		this.setqCz(contrastService.getContrastByRealName("取车中"));
		this.setcCc(contrastService.getContrastByRealName("存车中"));
		this.setCk(contrastService.getContrastByRealName("出口"));
	}

	/**
	 * 存车方法
	 * 
	 * @param userId  用户id，字符串
	 * @param carId   车辆id，字符串
	 * @param outTime 取车时间，Date类型
	 * @throws ParseException
	 */
	public void saveCar(String carId, Date outTime) throws ParseException {

		this.setAllContrast();

		// 获得存车数据
		ParkingEntity parking = new ParkingEntity();

		// 获得入口id
		SpaceEntity inSpaceId = spaceService.getCrk(this.getRk().getRealName());
		Date inTime = getDate();

		String nature = this.getcCc().getId();

		SpaceEntity fetureSpace = spaceService.getSaveSpace().get(0);
		String fetureSpaceId = fetureSpace.getId();

		fetureSpace.setNature(this.getCkZy().getId());
		// 锁定空间
		spaceService.update(fetureSpace);

		parking.setId(getId());
		parking.setCarId(carId);
		parking.setNowSpaceId(inSpaceId.getId());
		parking.setFetureSpaceId(fetureSpaceId);
		parking.setInTime(inTime);
		parking.setOutTime(outTime);
		parking.setNature(nature);
		parking.setWay(parking.getWay() + "|" + inSpaceId.getX() + "," + inSpaceId.getY() + "," + inSpaceId.getZ());

		ParkingSaveEntity parkingSave = new ParkingSaveEntity();
		parkingSave.setId(parking.getId());
		parkingSave.setCarId(parking.getCarId());
		parkingSave.setInTime(parking.getInTime());
		parkingSave.setOutTime(parking.getOutTime());
		parkingSave.setSaveSpaceId(parking.getFetureSpaceId());
		parkingSaveService.insert(parkingSave);

		parkingService.insert(parking);
	}

	public Date getDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
		Date date = new Date();
		String dateStr = format.format(date);
		Date date2 = format.parse(dateStr);
		return date2;
	}

	/**
	 * 分两种情况 正常取车 用户强制取车
	 * 
	 * @param carId
	 * @return
	 * @throws ParseException
	 */
	public ParkingEntity takeOutCar(String carId) throws ParseException {
		this.setAllContrast();

		ParkingEntity parking = parkingService.getParkingByCarid(carId);

		String fetureSpaceId = spaceService.getCrk(this.getCk().getRealName()).getId();

		Date outTime = parking.getOutTime();
		if (outTime == null) {
			outTime = getDate();
		}
		parking.setOutTime(outTime);
		parking.setFetureSpaceId(fetureSpaceId);

		parking.setNature(this.getqCz().getId());

		parkingService.update(parking);

		ParkingSaveEntity parkingSave = parkingSaveService.getOne(parking.getId());
		parkingSave.setOutTime(parking.getOutTime());
		parkingSaveService.update(parkingSave);
		return parking;
	}

	/**
	 * 获得自生成id
	 * 
	 * @return
	 */
	public String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	public ContrastEntity getCkZy() {
		return ckZy;
	}

	public void setCkZy(ContrastEntity ckZy) {
		this.ckZy = ckZy;
	}

	public ContrastEntity getRk() {
		return rk;
	}

	public void setRk(ContrastEntity rk) {
		this.rk = rk;
	}

	public ContrastEntity getqCz() {
		return qCz;
	}

	public void setqCz(ContrastEntity qCz) {
		this.qCz = qCz;
	}

	public ContrastEntity getcCc() {
		return cCc;
	}

	public void setcCc(ContrastEntity cCc) {
		this.cCc = cCc;
	}

	public ContrastEntity getCk() {
		return ck;
	}

	public void setCk(ContrastEntity ck) {
		this.ck = ck;
	}

}
