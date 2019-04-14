package com.znck.enums;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.mysql.cj.util.StringUtils;
import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;
import com.znck.service.ParkingServiceImpl;
import com.znck.service.SpaceServiceImpl;

/**
 * 
 * InitDataListener
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@Service
public class InitDataListener implements InitializingBean, ServletContextAware {

	public static String lockForParking;

	public static String lockForSpace;

	public static List<SpaceEntity> spaces;
	
	public static List<ParkingEntity> parkings;

	@Autowired
	private ParkingServiceImpl parkingServiceImpl;

	@Autowired
	private SpaceServiceImpl spaceServiceImpl;

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		System.out.println("项目启动中，静态变量上锁");
		try {
			this.onclock("0");
			spaces = new ArrayList<SpaceEntity>();
			spaceServiceImpl.getAll().forEach(space -> {
				space.setWeight(22, 9, 5);
				spaces.add(space);
			});
			parkings = parkingServiceImpl.getAll();
			if(parkings == null){
				parkings = new ArrayList<ParkingEntity>();
			}
			if(parkings.size()==0){
				parkings = new ArrayList<ParkingEntity>();
			}
			this.offclock("0");
			System.out.println("项目启动中，静态变量解锁");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onclock(String threadId) throws InterruptedException {
		do {
			if (StringUtils.isNullOrEmpty(lockForParking)) {
				lockForParking = threadId;
			}
			if (StringUtils.isNullOrEmpty(lockForSpace)) {
				lockForSpace = threadId;
			}
			Thread.sleep(100);
		} while (!(lockForParking.equals(threadId) & lockForSpace.equals(threadId)));
	}

	public void offclock(String threadId) {
		InitDataListener.lockForParking = null;
		InitDataListener.lockForSpace = null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}
}
