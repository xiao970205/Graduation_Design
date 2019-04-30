package com.znck.service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.znck.entity.BaseBean;
import com.znck.entity.ParkingSaveEntity;
import com.znck.entity.PublicMethods;
import com.znck.entity.UserEntity;
import com.znck.enums.InitDataListener;

/**
 * @author 肖舒翔
 */
@Service
public class AllAdminService {
	private ContrastServiceImpl contrastService;

	private ParkingSaveServiceImpl parkingSaveServiceImpl;
	
	public JSONObject getPage1() {
		List<ParkingSaveEntity> parkingSaveEntities = parkingSaveServiceImpl.getAll();
		JSONObject data = new JSONObject();
		return data;
	}
	
	/**
	 * 采集数据总量，各车库数据采集量
	 * 
	 * @param parkingSaveEntities
	 * @param userEntities
	 * @return
	 */
	public int getAllDataNumbser(List<ParkingSaveEntity> parkingSaveEntities, List<UserEntity> userEntities) {
		return parkingSaveEntities.size() + userEntities.size();
	}

	/**
	 * 当月采集数据量，当月停车数
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public int getThisMonthData(List<ParkingSaveEntity> parkingSaveEntities) {
		return parkingSaveEntities.stream().filter(parkingSaveEntitiy -> {
			try {
				return pdsbsJn(parkingSaveEntitiy.getInPlaceTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}).collect(Collectors.toList()).size();
	}

	/**
	 * 总停车数
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public int getAllParkingNumber(List<ParkingSaveEntity> parkingSaveEntities) {
		return parkingSaveEntities.size();
	}

	/**
	 * 总用户数
	 * 
	 * @param userEntities
	 * @return
	 */
	public int getAllUserNumber(List<UserEntity> userEntities) {
		return userEntities.size();
	}

	/**
	 * 获得当前会员人数
	 * 
	 * @param userEntities
	 * @return
	 */
	public int getAllVipUserNumber(List<UserEntity> userEntities) {
		return userEntities.stream().filter(userEntity -> userEntity.getNature().equals("2"))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 各车库会员比例
	 * 
	 * @param userEntities
	 * @return
	 */
	public Map<String, Integer> getParkingUserOrVip(List<UserEntity> userEntities) {
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		returnMap.put("0", userEntities.stream().filter(userEntity -> userEntity.getNature().equals("0"))
				.collect(Collectors.toList()).size());
		returnMap.put("1", userEntities.stream().filter(userEntity -> userEntity.getNature().equals("1"))
				.collect(Collectors.toList()).size());
		returnMap.put("2", userEntities.stream().filter(userEntity -> userEntity.getNature().equals("2"))
				.collect(Collectors.toList()).size());
		return returnMap;
	}

	/**
	 * 获得数据采集条数(当日)/停车数量(当日)
	 * 
	 * @return
	 */
	public JSONObject getSjcjTs(List<ParkingSaveEntity> parkingSaveEntities) {
		List<Integer> parkingSaveToday = getBrClSdTcSl(parkingSaveEntities);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("8:00", parkingSaveToday.get(8));
		jsonObject.put("10:00", parkingSaveToday.get(10));
		jsonObject.put("12:00", parkingSaveToday.get(12));
		jsonObject.put("14:00", parkingSaveToday.get(14));
		jsonObject.put("16:00", parkingSaveToday.get(16));
		jsonObject.put("18:00", parkingSaveToday.get(18));
		jsonObject.put("20:00", parkingSaveToday.get(20));
		return jsonObject;
	}

	/**
	 * 获得全年停车数量
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public int getQnTcsl(List<ParkingSaveEntity> parkingSaveEntities) {
		return parkingSaveEntities.stream().filter(parkingSave -> {
			try {
				return pdJn(parkingSave.getInPlaceTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList()).size();
	}

	/**
	 * 获得当月停车数量
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public int getDyTcsl(List<ParkingSaveEntity> parkingSaveEntities) {
		return parkingSaveEntities.stream().filter(parkingSave -> {
			try {
				return pdsbsJn(parkingSave.getInPlaceTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList()).size();
	}

	/**
	 * 获得全年会员停车数量
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public int getQnHyTcSl(List<ParkingSaveEntity> parkingSaveEntities) {
		return parkingSaveEntities.stream().filter(parkingSave -> {
			try {
				return pdJn(parkingSave.getInPlaceTime())
						&& (parkingSave.getVipAppGetTime() != null || parkingSave.getVipAppParkingTime() != null
								|| parkingSave.getVipGetTime() != null || parkingSave.getVipSendTime() != null);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList()).size();
	}

	/**
	 * 获得当月会员停车数量
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public int getDyHyTcsl(List<ParkingSaveEntity> parkingSaveEntities) {
		return parkingSaveEntities.stream().filter(parkingSave -> {
			try {
				return pdsbsJn(parkingSave.getInPlaceTime())
						&& (parkingSave.getVipAppGetTime() != null || parkingSave.getVipAppParkingTime() != null
								|| parkingSave.getVipGetTime() != null || parkingSave.getVipSendTime() != null);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList()).size();
	}

	/**
	 * 获得会员存车占比
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public String getHyCcZb(List<ParkingSaveEntity> parkingSaveEntities) {
		int hYCc = parkingSaveEntities.stream().filter(
				parkingSave -> parkingSave.getVipAppParkingTime() != null || parkingSave.getVipSendTime() != null)
				.collect(Collectors.toList()).size();
		int zCc = parkingSaveEntities.size();
		return getBfb(hYCc, zCc);
	}

	/**
	 * 获得会员取车占比
	 * 
	 * @param parkingSaveEntities
	 * @return
	 */
	public String getHyQcZb(List<ParkingSaveEntity> parkingSaveEntities) {
		int hYQc = parkingSaveEntities.stream().filter(
				parkingSave -> parkingSave.getVipAppGetTime() != null || parkingSave.getVipGetTime() != null)
				.collect(Collectors.toList()).size();
		int zQc = parkingSaveEntities.size();
		return getBfb(hYQc, zQc);
	}

	/**
	 * 获得当前等待用户停车中的数量
	 * 
	 * @return
	 */
	private int getWaitForSaveCar() {
		return InitDataListener.parkings.stream()
				.filter(parking -> parking.getNature().equals(contrastService.getContrastByRealName("等待用户停车中")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 获得普通用户开往停车位的数据
	 * 
	 * @return
	 */
	private int getNoramalRunToSavePlace() {
		return InitDataListener.parkings.stream()
				.filter(parkingEntity -> parkingEntity.getNature().equals(contrastService.getContrastByRealName("停车中")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 获得vip用户开往停车位的数据
	 * 
	 * @return
	 */
	private int getVipRunToSavePlace() {
		return InitDataListener.parkings.stream().filter(
				parkingEntity -> parkingEntity.getNature().equals(contrastService.getContrastByRealName("vip停车中")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 车辆中存车的数量
	 * 
	 * @return
	 */
	private int getSaving() {
		return InitDataListener.parkings.stream()
				.filter(parkingEntity -> parkingEntity.getNature().equals(contrastService.getContrastByRealName("存车中")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 获得普通用户取车数量
	 * 
	 * @return
	 */
	private int getNormalGettingCar() {
		return InitDataListener.parkings.stream()
				.filter(parkingEntity -> parkingEntity.getNature().equals(contrastService.getContrastByRealName("取车中")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 获得vip用户取车数量
	 * 
	 * @return
	 */
	private int getVipGettingCar() {
		return InitDataListener.parkings.stream().filter(
				parkingEntity -> parkingEntity.getNature().equals(contrastService.getContrastByRealName("vip取车中")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 获得车库中的剩余
	 * 
	 * @return
	 */
	private int getSpaceCanSaveNumber() {
		return InitDataListener.spaces.stream()
				.filter(spaceEntity -> spaceEntity.getNature().equals(contrastService.getContrastByRealName("车库-空置")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 获得车库中的占用
	 * 
	 * @return
	 */
	private int getSpaceCanNotSaveNumber() {
		return InitDataListener.spaces.stream()
				.filter(spaceEntity -> spaceEntity.getNature().equals(contrastService.getContrastByRealName("车库-占用")))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 本日车辆时段停车数量,分区24
	 */
	private List<Integer> getBrClSdTcSl(List<ParkingSaveEntity> parkingSaveEntityList) {
		List<ParkingSaveEntity> toDaySave = parkingSaveEntityList.stream().filter(parkingSaveEntity -> {
			try {
				return pdJr(parkingSaveEntity.getInPlaceTime());
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}).collect(Collectors.toList());
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			data.add(0);
		}
		parkingSaveEntityList.forEach(parkingSaveEntity -> {
			String hours = getHour(parkingSaveEntity.getInPlaceTime());
			data.set(Integer.parseInt(hours), (data.get(Integer.parseInt(hours)) + 1));
		});

		return data;
	}

	/**
	 * 本月车辆时段停车数量,分区24
	 */
	private void getByClSdTcSl(List<ParkingSaveEntity> parkingSaveEntityList) {
		List<ParkingSaveEntity> toDaySave = parkingSaveEntityList.stream().filter(parkingSaveEntity -> {
			try {
				return pdsbsJn(parkingSaveEntity.getInPlaceTime());
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}).collect(Collectors.toList());
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			data.add(0);
		}
		parkingSaveEntityList.forEach(parkingSaveEntity -> {
			String hours = getHour(parkingSaveEntity.getInPlaceTime());
			data.set(Integer.parseInt(hours), (data.get(Integer.parseInt(hours)) + 1));
		});
	}

	/**
	 * 得到现在小时
	 */
	private String getHour(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 判断是不是今年
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	private boolean pdJn(Date date) throws ParseException {
		Date now = PublicMethods.getDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDay = sf.format(now);
		String day = sf.format(date);
		return day.split("-")[0].equals(nowDay.split("-")[0]);
	}

	/**
	 * 判断是不是今天
	 */
	private boolean pdJr(Date date) throws ParseException {
		Date now = PublicMethods.getDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String nowDay = sf.format(now);
		String day = sf.format(date);
		return day.equals(nowDay);
	}

	/**
	 * 判断是不是今年的同一月
	 * 
	 * @param date
	 * @return
	 */
	private boolean pdsbsJn(Date date) throws ParseException {
		Date now = PublicMethods.getDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String nowDay = sf.format(now).substring(0, 6);
		String day = sf.format(date).substring(0, 6);
		return day.equals(nowDay);
	}

	/**
	 * 获得两个数的比例（百分比）
	 * 
	 * @return
	 */
	private String getBfb(int num1, int num2) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) num1 / (float) num2 * 100);
		return result + "%";
	}
}
