package com.znck;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import com.znck.service.ParkingServiceImpl;

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

    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("<<<<<<<<<<<我被销毁了......................>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<我被销毁了......................>>>>>>>>>>>>>>>");
    }

}
