package com.znck.service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ParkingEntity;
import com.znck.entity.ParkingSaveEntity;
import com.znck.entity.PublicMethods;
import com.znck.entity.SpaceEntity;
import com.znck.entity.UserEntity;
import com.znck.enums.InitDataListener;

/**
 * 所有关于停车取车的service AllparkingService
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class AllparkingService {
	@Autowired
	private ContrastServiceImpl contrastServiceImpl;

	@Autowired
	private ParkingSaveServiceImpl parkingSaveServiceImpl;

	private final static String ENTRANCE = "入口";

	private final static String PUTIN_CAR = "存车中";

	private final static String EXPORT = "出口";

	private final static String GARAGE_TAKE_UP = "车库-占用";

	private final static String GETIN_CAR = "取车中";

	private final static String CHANNEL_PASSABLE = "通道-可通过";

	private final static String CHANNEL_TAKE_UP = "通道-占用";

	private final static String GARAGE_VACANCY = "车库-空置";

	private final static String IN_THE_PARKING = "";
	
	private final static int SIZETWO = 2;
	
	private final static int SIZETHREE = 3;
	
	private final static int SIZETWENTYTWO = 22;

	public void onclock(String threadId) throws InterruptedException {
		do {
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForParking)) {
				InitDataListener.lockForParking = threadId;
			}
			if (StringUtils.isNullOrEmpty(InitDataListener.lockForSpace)) {
				InitDataListener.lockForSpace = threadId;
			}
			Thread.sleep(100);
		} while (!(InitDataListener.lockForParking.equals(threadId) & InitDataListener.lockForSpace.equals(threadId)));
	}

	public void offclock(String threadId) {
		InitDataListener.lockForParking = null;
		InitDataListener.lockForSpace = null;
	}

	/**
	 * 存车方法
	 * 
	 * @param data ContrastEntity类型数据，id是carid，realName为时间，Date类型
	 * @return
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	public String saveCarByStatic(UserEntity data) throws ParseException, InterruptedException {
		String threadId = PublicMethods.getId();
		onclock(threadId);

		List<ParkingEntity> parkings = InitDataListener.parkings;
		List<SpaceEntity> spaces = InitDataListener.spaces;
		// 处理日期，若日期不为空则格式化日期
		Date outTime = null;
		if (!StringUtils.isNullOrEmpty(data.getNickName())) {
			// vip
			if (!StringUtils.isNullOrEmpty(data.getRealName())) {
				Random r = new Random();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
				ParsePosition pos = new ParsePosition(0);
				outTime = formatter.parse(data.getRealName() + " " + (r.nextInt(899) + 100), pos);
			}
		}

		// 获得入口id
		SpaceEntity inSpaceId = spaces.stream().filter(a -> !StringUtils.isNullOrEmpty(a.getNature()))
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId()))
				.collect(Collectors.toList()).get(0);

		// 获得车位，若没有则返回字符串false
		List<SpaceEntity> spacesForFeture = spaces.stream()
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId()))
				.sorted(Comparator.comparing(SpaceEntity::getWeight)).collect(Collectors.toList());
		if (spacesForFeture.size() == 0) {
			offclock(threadId);
			return "false";
		}

		// 获得车位
		SpaceEntity fetureSpace = spacesForFeture.get(0);

		// 更新空间列表，锁定空间
		SpaceEntity oldSpace = spacesForFeture.get(0);
		fetureSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId());
		Collections.replaceAll(spaces, oldSpace, fetureSpace);

		// 向停车列表插入数据
		ParkingEntity parking = new ParkingEntity(PublicMethods.getId(), data.getId(), inSpaceId.getId(), fetureSpace.getId(),
				PublicMethods.getDate(), outTime, contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId(),
				inSpaceId.getX() + "," + inSpaceId.getY() + "," + inSpaceId.getZ(), fetureSpace.getId());
		parkings.add(parking);
		InitDataListener.parkings = parkings;
		InitDataListener.spaces = spaces;

		offclock(threadId);
		return "true";
	}

	/**
	 * 静态的取车方法，vip取车与正常取车，vip取车仅仅修改取车时间，正常取车直接修改性质
	 * 
	 * @param data
	 * @return
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	public String parkingGetCarByStatic(UserEntity data) throws ParseException, InterruptedException {
		String getOutTimeString = data.getRealName();
		String carId = data.getId();
		ParkingEntity parkingOld = InitDataListener.parkings.stream().filter(a -> a.getCarId().equals(carId))
				.collect(Collectors.toList()).get(0);
		ParkingEntity parking = parkingOld;
		String nature = parking.getNature();
		Date outTime = parking.getOutTime();
		String natureByGetVip = "0";
		if (!StringUtils.isNullOrEmpty(getOutTimeString)) {
			// vip预约取车，仅仅取车修改时间
			Random r = new Random();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
			ParsePosition pos = new ParsePosition(0);
			outTime = formatter.parse(getOutTimeString + " " + (r.nextInt(899) + 100), pos);
			natureByGetVip = "1";
		} else {
			// 正常取车
			nature = contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId();
			outTime = PublicMethods.getDate();
			natureByGetVip = "0";
			parking.setFetureSpaceId(InitDataListener.spaces.stream()
					.filter(space -> space.getNature()
							.equals(contrastServiceImpl.getContrastByRealName(EXPORT).getId()))
					.collect(Collectors.toList()).get(0).getId());
		}
		parking.setNature(nature);
		parking.setOutTime(outTime);
		parking.setNature2(natureByGetVip);

		String clockId = PublicMethods.getId();
		onclock(clockId);

		List<ParkingEntity> parkings = InitDataListener.parkings;

		Collections.replaceAll(parkings, parkingOld, parking);

		InitDataListener.parkings = parkings;

		offclock(clockId);

		return null;
	}

	public void getCar() throws ParseException {
		Date date = PublicMethods.getDate();
		List<ParkingEntity> inCar = InitDataListener.parkings.stream()
				.filter(a -> (a.getNature().equals(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId()))
						&& (a.getOutTime() != null) && (date.compareTo(a.getOutTime()) == 1))
				.collect(Collectors.toList());

		inCar.forEach(parking -> {
			UserEntity user = new UserEntity();
			user.setRealName(null);
			user.setId(parking.getCarId());
			try {
				parkingGetCarByStatic(user);
			} catch (ParseException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		List<ParkingEntity> saveCar = InitDataListener.parkings.parallelStream()
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId()))
				.sorted(Comparator.comparing(ParkingEntity::getInTimeSizeInt)).collect(Collectors.toList());

		saveCar.forEach(parking -> {
			try {
				saveCarNextTodo(parking);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		List<ParkingEntity> takeOutCar = InitDataListener.parkings.parallelStream()
				.filter(a -> a.getNature().equals(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId()))
				.sorted(Comparator.comparing(ParkingEntity::getOutTimeSizeInt)).collect(Collectors.toList());

		takeOutCar.forEach(parking -> {
			try {
				takeOutCarNextTodo(parking);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		systemOutParkingInfo();
	}

	public void systemOutParkingInfo() {
		List<ParkingEntity> parkings = InitDataListener.parkings;
		System.out.println("在车库中一共有：" + parkings.size() + "辆车。");
		parkings.forEach(parking -> {
			System.out.println("space:" + parking.getNowSpaceId() + ", fetureSpace:" + parking.getFetureSpaceId());
		});
		System.out.println("其中：");
		int toParkingSpace = parkings.stream()
				.filter(parking -> parking.getNature()
						.equals(contrastServiceImpl.getContrastByRealName(IN_THE_PARKING).getId()))
				.collect(Collectors.toList()).size();
		System.out.println("正在走向停车位置的车辆有" + toParkingSpace + "辆");
		int inParkingSpace = parkings.stream().filter(
				parking -> parking.getNature().equals(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId()))
				.collect(Collectors.toList()).size();
		System.out.println("正在停车的车辆有" + inParkingSpace + "辆");
		int outParkingSpace = parkings.stream().filter(
				parking -> parking.getNature().equals(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId()))
				.collect(Collectors.toList()).size();
		System.out.println("正在走向出口的车辆有" + outParkingSpace + "辆");
		System.out.println("入口：" + InitDataListener.spaces.stream()
				.filter(space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId()))
				.collect(Collectors.toList()).size());
		System.out.println("出口：" + InitDataListener.spaces.stream()
				.filter(space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(EXPORT).getId()))
				.collect(Collectors.toList()).size());
		System.out.println(GARAGE_TAKE_UP + InitDataListener.spaces.stream().filter(
				space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId()))
				.collect(Collectors.toList()).size());
		System.out.println(CHANNEL_TAKE_UP + InitDataListener.spaces.stream().filter(
				space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(CHANNEL_TAKE_UP).getId()))
				.collect(Collectors.toList()).size());
		System.out.println(GARAGE_VACANCY + InitDataListener.spaces.stream().filter(
				space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId()))
				.collect(Collectors.toList()).size());
		System.out.println(CHANNEL_PASSABLE + InitDataListener.spaces.stream().filter(
				space -> space.getNature().equals(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId()))
				.collect(Collectors.toList()).size());
	}

	public void takeOutCarNextTodo(ParkingEntity parking) throws ParseException, InterruptedException {
		ParkingEntity parkingOral = parking;
		SpaceEntity nowSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getNowSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity nowSpaceOral = nowSpace;
		SpaceEntity fetureSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getFetureSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity fetureSpaceOral = fetureSpace;
		final String x = "x";
		final String y = "y";
		final String z = "z";
		boolean deleteParking = false;
		final Map<String, Integer> nextSpaceMap = this.getNextSpaceWhenTakingOutCar(nowSpace.getX(), nowSpace.getY(),
				nowSpace.getZ(), fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
		SpaceEntity nextSpace = InitDataListener.spaces.stream().filter(space -> space.getX() == nextSpaceMap.get(x)
				&& space.getY() == nextSpaceMap.get(y) && space.getZ() == nextSpaceMap.get(z))
				.collect(Collectors.toList()).get(0);
		if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
			nowSpace.setCarId(null);
			if (nextSpaceMap.get(x) == fetureSpace.getX() && nextSpaceMap.get(y) == fetureSpace.getY()
					&& nextSpaceMap.get(z) == fetureSpace.getZ()) {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
				String way = parking.getWay() + "|" + fetureSpace.getX() + "," + fetureSpace.getY() + ","
						+ fetureSpace.getZ();
				parking.setOutInPlaceTime(PublicMethods.getDate());
				parking.setWay(way);
				ParkingSaveEntity parkingSave = new ParkingSaveEntity();
				parkingSave.setId(PublicMethods.getId());
				parkingSave.setCarId(parking.getCarId());
				parkingSave.setInTime(parking.getInTime());
				parkingSave.setSaveInPlaceTime(parking.getSaveInPlaceTime());
				parkingSave.setNature(parking.getNature2());
				parkingSave.setOutTime(parking.getOutTime());
				parkingSave.setOutInPlaceTime(parking.getOutInPlaceTime());
				parkingSave.setWay(parking.getWay());
				parkingSave.setSaveSpaceId(parking.getSaveSpaceId());
				parkingSaveServiceImpl.insert(parkingSave);
				deleteParking = true;
			} else {
				nextSpace.setCarId(parking.getCarId());
				nextSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_TAKE_UP).getId());
				if (nowSpace.getNature()
						.contentEquals(contrastServiceImpl.getContrastByRealName(GARAGE_TAKE_UP).getId())) {
					nowSpace.setNature(contrastServiceImpl.getContrastByRealName(GARAGE_VACANCY).getId());
				} else {
					nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
				}
				parking.setNature(contrastServiceImpl.getContrastByRealName(GETIN_CAR).getId());
				parking.setNowSpaceId(nextSpace.getId());
				parking.setWay(
						parking.getWay() + "|" + nextSpace.getX() + "," + nextSpace.getY() + "," + nextSpace.getZ());
			}
		} else {
			parking.setWay(parking.getWay() + "|wait");
		}
		String clockId = PublicMethods.getId();
		onclock(clockId);

		if (deleteParking) {
			InitDataListener.parkings.remove(parkingOral);
		} else {
			Collections.replaceAll(InitDataListener.parkings, parkingOral, parking);
		}
		Collections.replaceAll(InitDataListener.spaces, nowSpaceOral, nowSpace);
		Collections.replaceAll(InitDataListener.spaces, fetureSpaceOral, fetureSpace);
		offclock(clockId);
	}

	public void saveCarNextTodo(ParkingEntity parking) throws ParseException, InterruptedException {
		ParkingEntity parkingOral = parking;
		SpaceEntity nowSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getNowSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity nowSpaceOral = nowSpace;
		SpaceEntity fetureSpace = InitDataListener.spaces.stream()
				.filter(space -> space.getId().equals(parking.getFetureSpaceId())).collect(Collectors.toList()).get(0);
		SpaceEntity fetureSpaceOral = fetureSpace;
		final String x = "x";
		final String y = "y";
		final String z = "z";
		final Map<String, Integer> nextSpaceMap = this.getNextSpaceWhenSavingCar(nowSpace.getX(), nowSpace.getY(),
				nowSpace.getZ(), fetureSpace.getX(), fetureSpace.getY(), fetureSpace.getZ());
		SpaceEntity nextSpace = InitDataListener.spaces.stream().filter(space -> space.getX() == nextSpaceMap.get(x)
				&& space.getY() == nextSpaceMap.get(y) && space.getZ() == nextSpaceMap.get(z))
				.collect(Collectors.toList()).get(0);
		if (StringUtils.isNullOrEmpty(nextSpace.getCarId())) {
			nextSpace.setCarId(parking.getCarId());
			nowSpace.setCarId(null);
			parking.setNowSpaceId(nextSpace.getId());
			if (nowSpace.getNature().equals(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId())) {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(ENTRANCE).getId());
			} else {
				nowSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_PASSABLE).getId());
			}
			if (nextSpaceMap.get(x) == fetureSpace.getX() && nextSpaceMap.get(y) == fetureSpace.getY()
					&& nextSpaceMap.get(z) == fetureSpace.getZ()) {
				parking.setNature(contrastServiceImpl.getContrastByRealName(PUTIN_CAR).getId());
				parking.setSaveInPlaceTime(PublicMethods.getDate());
				parking.setSaveSpaceId(nextSpace.getId());
			} else {
				nextSpace.setNature(contrastServiceImpl.getContrastByRealName(CHANNEL_TAKE_UP).getId());
			}
			parking.setWay(parking.getWay() + "|" + nextSpace.getX() + "," + nextSpace.getY() + "," + nextSpace.getZ());
		} else {
			parking.setWay(parking.getWay() + "|wait");
		}
		String clockId = PublicMethods.getId();
		onclock(clockId);
		Collections.replaceAll(InitDataListener.spaces, nowSpaceOral, nowSpace);
		Collections.replaceAll(InitDataListener.spaces, fetureSpaceOral, fetureSpace);
		Collections.replaceAll(InitDataListener.parkings, parkingOral, parking);
		offclock(clockId);
	}

	/**
	 * 获得map类型映射的下一步xyz
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Map<String, Integer> getNextSpaceMapStringInteger(int x, int y, int z) {
		Map<String, Integer> zb = new HashMap<String, Integer>(3);
		zb.put("x", x);
		zb.put("y", y);
		zb.put("z", z);

		return zb;
	}

	/**
	 * 获得取车时候获得下一步路径算法
	 * 
	 * 取车的时候一共有几种情况
	 * 1.车辆没有入库在左侧通道被取走
	 * 	1-1车辆在上下电梯上 		nX = 1 & nY = 1 ;		nY++ （被包括了）
	 * 	1-2车辆在左侧通道上 		
	 * 	  1-2-1  可以通向横向通道	nX = 1 & (nY % 3) == 2 ;nX++
	 * 	  1-2-2  不可以通向横向通道	nX = 1 & (nY % 3) != 2 ;nY++ （用）
	 * 	1-3车辆在横向通道上 		nX != maxX & nX != 1 ;	nX++ （用）
	 * 2.车辆在车库中 			nY % 3 != 2
	 * 	2-1 在第一排，下挪		nY % 3 != 2 & nY == 1 ; nY++
	 * 	2-2不再第一排，上挪		nY % 3 != 2 & nY != 1 ; nY--
	 * 3.车辆在取车路上
	 * 	3-1在横向通道中，右挪		nX != maxX & nX == 1	; 	nX++ （重复）
	 * 	3-2在右侧通道中，上挪		nX == maxX & nY != 1; 	nY--
	 * 	3-3在升降电梯，上挪		nX == maxX & nY == 1 ;	nZ--
	 * 
	 * 
	 * @param nX
	 * @param nY
	 * @param nZ
	 * @param fX
	 * @param fY
	 * @param fZ
	 * @return
	 */
	public Map<String, Integer> getNextSpaceWhenTakingOutCar(int nX, int nY, int nZ, int fX, int fY, int fZ) {
		Map<String, Integer> zb = new HashMap<String, Integer>(3);
		if (nX == 1 & nY % SIZETHREE == SIZETWO) {
			// 在左侧通道并且可以往右移动
			zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
		} else if (nX == 1 & nY % SIZETHREE != SIZETWO) {
			// 在左侧通道但是不能往右移动，只能向下移动
			zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
		} else if ((nY % SIZETHREE != SIZETWO) & nY == 1) {
			// 在车位上，且在第一排，往下挪动。
			zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
		} else if ((nY % SIZETHREE != SIZETWO) & nY != 1) {
			// 在车位上，且不在第一排，可以上下移动。
			zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
		} else if (nX != SIZETWENTYTWO) {
			// 不在车位上，且不在最右侧通道，只能向右移动。
			zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
		} else if (nY != fY) {
			// 不在车位上，且不在同一排，所以只能在右侧通道,只能往前挪动
			zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
		} else {
			// 只能是楼层不同.
			zb = getNextSpaceMapStringInteger(nX, nY, nZ - 1);
		}
		return zb;
	}

	/**
	 * 获得存车时候获得下一步路径放法
	 * 
	 * @param nX
	 * @param nY
	 * @param nZ
	 * @param fX
	 * @param fY
	 * @param fZ
	 * @return
	 */
	public Map<String, Integer> getNextSpaceWhenSavingCar(int nX, int nY, int nZ, int fX, int fY, int fZ) {
		Map<String, Integer> zb = new HashMap<String, Integer>(3);
//		if (nZ == fZ) {
//			// 相同层
//			if (nY % numberThree == numberTwo) {
//				// 在横向通道中
//				if (Math.abs(nY - fY) == 1) {
//					// 在合适的横向通道中
//					if (nX != fX) {
//						// 未移动到合适的横向位置,只能往右走
//						zb = getNextSpaceMapStringInteger(nX + 1, nY, nZ);
//					} else {
//						// 仅差上下移动
//						if (nY - fY > 0) {
//							zb = getNextSpaceMapStringInteger(nX, nY - 1, nZ);
//						} else {
//							zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
//						}
//					}
//				} else {
//					zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
//				}
//			} else {
//				// 在纵向通道,只能往下走,y++
//				zb = getNextSpaceMapStringInteger(nX, nY + 1, nZ);
//
//			}
//		} else {
//			// 不同层,只能下,z++
//			zb = getNextSpaceMapStringInteger(nX, nY, nZ + 1);
//		}
		
		if (fZ - nZ != 0) {
			zb = getNextSpaceMapStringInteger(nX, nY, nZ + 1);
		} else if (nX == 1 & Math.abs(fY - nY) != 1) {
			zb = getNextSpaceMapStringInteger(nX, nY+1, nZ);
		} else if (nY % SIZETHREE == SIZETWO & Math.abs(fY - nY) == 1 & nX != fX) {
			zb = getNextSpaceMapStringInteger(nX+1, nY, nZ);
		} else if (nY > fY) {
			zb = getNextSpaceMapStringInteger(nX, nY-1, nZ);
		} else {
			zb = getNextSpaceMapStringInteger(nX, nY+1, nZ);
		}
		return zb;
	}
}
