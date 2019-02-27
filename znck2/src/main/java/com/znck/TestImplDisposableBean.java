package com.znck;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import com.mysql.cj.util.StringUtils;
import com.znck.enums.InitDataListener;
import com.znck.service.ParkingServiceImpl;
import com.znck.service.SpaceServiceImpl;

/**
 * 
 * TestImplDisposableBean
 * 
 * @author 肖舒翔
 * @version 1.0 项目关闭操作，将数据保存进数据库。（等待编写）
 */
@Component
public class TestImplDisposableBean implements DisposableBean, ExitCodeGenerator {

	@Override
	public int getExitCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Autowired
	private ParkingServiceImpl parkingServiceImpl;

	@Autowired
	private SpaceServiceImpl spaceServiceImpl;

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("<<<<<<<<<<<我被销毁了......................>>>>>>>>>>>>>>>");
		System.out.println("正在更新数据。。。");
		upDateData();
		System.out.println("更新数据完成");
		System.out.println("<<<<<<<<<<<我被销毁了......................>>>>>>>>>>>>>>>");
	}

	public void upDateData() throws InterruptedException {
		parkingServiceImpl.truncateTable();
		onclock("clear");
		InitDataListener.parkings.forEach(parking -> {
			parkingServiceImpl.insert(parking);
		});
		InitDataListener.spaces.forEach(space -> {
			spaceServiceImpl.update(space);
		});
		offclock("clear");
	}

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
}
