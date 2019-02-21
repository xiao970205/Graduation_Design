 package com.znck.enums;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.znck.entity.ParkingEntity;
import com.znck.service.ParkingServiceImpl;

@Service
public class InitDataListener implements InitializingBean, ServletContextAware {

    @Override
    public void setServletContext(ServletContext servletContext) {
        // TODO Auto-generated method stub
        System.out.println("项目启动设置参数");
        textInfo = "1";
        parkings = parkingServiceImpl.getAll();
    }
    
    public static String textInfo;
    
    public static List<ParkingEntity> parkings;
    
    @Autowired
    private ParkingServiceImpl parkingServiceImpl;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
    }

}
