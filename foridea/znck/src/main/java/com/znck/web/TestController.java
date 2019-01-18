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
import com.znck.entity.UserEntity;
import com.znck.service.CarService;
import com.znck.service.ContrastService;
import com.znck.service.ParkingService;
import com.znck.service.RunService;
import com.znck.service.SpaceService;
import com.znck.service.UserService;

@RestController
public class TestController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ContrastService contrastService;
    
    @Autowired
    private ParkingService parkingService;
    
    @Autowired
    private SpaceService spaceService;
    
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
        String a = "";
        for (CarEntity car : cars) {
            a = a + car.toString() + "</br>";
        }
        return a;
    }
    
    @RequestMapping("/getAllUser")
    public String getAllUser() {
        List<UserEntity> users = userService.getAll();
        String a = "";
        for (UserEntity user : users) {
            a = a + user.toString() + "</br>";
        }
        return a;
    }
    
    @RequestMapping("/getAllContrast")
    public String getAllContrast(){
        List<ContrastEntity> contrasts = contrastService.getAll();
        String a = "";
        for(ContrastEntity contrast:contrasts){
            a = a + contrast.toString() + "</br>";
        }
        return a;
    }
    
    @RequestMapping("/getParkings")
    public String getParkingEntitys(){
        List<ParkingEntity> parkings = parkingService.getParkingsByNature("351cc2cdda6547528e20c6444e4a3bbd", "order by inTime");
        String a = "";
        for(ParkingEntity parking:parkings){
            a = a + parking.toString() + "</br>";
        }
        return a;
    }
    
    @RequestMapping("/getSpaces")
    public String getSpaceEntitys(){
        List<SpaceEntity> spaces = spaceService.getAll();
        String a = "";
        for(SpaceEntity space:spaces){
            a = a + space.toString() + "</br>";
        }
        return a;
    }
    
    @RequestMapping("/saveCar")
    public void saveCar(){
        try {
            runService.saveCar("642880079c2b4c7eb9b2a58780e2a957", "6f0f4516ec04414f90c3cc72e25f6d47", null);
            Thread.sleep(1000);
            runService.saveCar("642880079c2b4c7eb9b2a58780e2a957", "7050f26ee0fa4e7b8571ad3f43a83884", null);
            Thread.sleep(1000);
            runService.saveCar("642880079c2b4c7eb9b2a58780e2a957", "721799c190734042a0329c6ddacc1040", null);
            Thread.sleep(1000);
            runService.saveCar("642880079c2b4c7eb9b2a58780e2a957", "9aafff25567b4a5b8f429ba2b6de5494", null);
            Thread.sleep(1000);
            runService.saveCar("642880079c2b4c7eb9b2a58780e2a957", "a79f4d9a52ae4acda5d361306464f7af", null);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
    }
   
    @RequestMapping("/updateParking")
    public void updateParking(){
        ParkingEntity parking = parkingService.getAll().get(0);
        parking.setCarId("54112345");
        parking.setOrginalSpaceId("asdfdsa");
        parking.setNowSpaceId("45456456");
        parking.setFetureSpaceId("asdfdsf456");
        parking.setNature("asdfdsf");
        parkingService.update(parking);
    }
}
