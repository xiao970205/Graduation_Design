package com.znck.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.znck.entity.CarEntity;
import com.znck.entity.ContrastEntity;
import com.znck.entity.ParkingEntity;
import com.znck.entity.SpaceEntity;
import com.znck.service.CarServiceImpl;
import com.znck.service.ContrastServiceImpl;
import com.znck.service.ParkingServiceImpl;
import com.znck.service.RunService;
import com.znck.service.SpaceServiceImpl;

/**
 * 
 * TestController
 * 
 * @author 肖舒翔
 * @version 1.0
 *
 */
@RestController
public class TestController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private ContrastServiceImpl contrastService;

    @Autowired
    private ParkingServiceImpl parkingService;

    @Autowired
    private SpaceServiceImpl spaceService;

    @Autowired
    private RunService runService;

    @RequestMapping("/getAllCar")
    public String getAllCar() {

        String a = carService.getOne("6f0f4516ec04414f90c3cc72e25f6d47").toString();

        return a;
    }

    @RequestMapping("/getAllCars")
    public String getAllCars() {
        List<CarEntity> cars = carService.getAll();
        StringBuilder text = new StringBuilder("");
        String a = "";
        for (CarEntity car : cars) {
            text.append(car.toString() + "</br>");
//            a = a + car.toString() + "</br>";
        }
        return a;
    }

    @RequestMapping("/getAllContrast")
    public String getAllContrast() {
        List<ContrastEntity> contrasts = contrastService.getAll();
        String a = "";
        StringBuilder text = new StringBuilder("");
        for (ContrastEntity contrast : contrasts) {
            text.append(contrast.toString() + "</br>");
//            a = a + contrast.toString() + "</br>";
        }
        return a;
    }

    @RequestMapping("/getParkings")
    public String getParkingEntitys() {
        List<ParkingEntity> parkings
            = parkingService.getParkingsByNature("351cc2cdda6547528e20c6444e4a3bbd", "order by inTime");
        String a = "";
        StringBuilder text = new StringBuilder("");
        for (ParkingEntity parking : parkings) {
            text.append(parking.toString() + "</br>");
//            a = a + parking.toString() + "</br>";
        }
        return a;
    }

    @RequestMapping("/getSpaces")
    public String getSpaceEntitys() {
        List<SpaceEntity> spaces = spaceService.getAll();
        String a = "";
        StringBuilder text = new StringBuilder("");
        for (SpaceEntity space : spaces) {
            text.append(space.toString() + "</br>");
//            a = a + space.toString() + "</br>";
        }
        return a;
    }

    @RequestMapping("/saveCar")
    public void saveCar() {
        try {
            runService.saveCar("6f0f4516ec04414f90c3cc72e25f6d47", null);
            Thread.sleep(1000);
            runService.saveCar("7050f26ee0fa4e7b8571ad3f43a83884", null);
            Thread.sleep(1000);
            runService.saveCar("721799c190734042a0329c6ddacc1040", null);
            Thread.sleep(1000);
            runService.saveCar("9aafff25567b4a5b8f429ba2b6de5494", null);
            Thread.sleep(1000);
            runService.saveCar("a79f4d9a52ae4acda5d361306464f7af", null);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateParking")
    public void updateParking() {
        ParkingEntity parking = parkingService.getAll().get(0);
        parking.setCarId("54112345");
        parking.setNowSpaceId("45456456");
        parking.setFetureSpaceId("asdfdsf456");
        parking.setNature("asdfdsf");
        // yyyy/MM/dd-HH:mm:ss:SSS
        parkingService.update(parking);
    }
}
