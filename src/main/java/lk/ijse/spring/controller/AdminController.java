package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.service.UserService;
import lk.ijse.spring.service.User_CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("v1/admin")
public class AdminController {


    @Autowired
    CarService service;

    @Autowired
    UserService user;

    @Autowired
    User_CarService userCarService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCar(@ModelAttribute CarDTO car) {
        service.saveCar(car);
        return new ResponseUtil(200,"Save",null);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCar(@RequestBody CarDTO car) {
        service.updateCar(car);
        return new ResponseUtil(200,"Updated",null);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCar(@RequestParam String id) {
        service.deleteCar(id);
        return new ResponseUtil(200,"Deleted",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil viewDriverSchedule(@RequestParam String id){
        return new ResponseUtil(200,"ok",userCarService.getdriverstatus(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil viewUsers(){
       return new ResponseUtil(200,"ok",user.getAllUsers());
    }

    @GetMapping(path = "/{nic},/{regno},/{damage}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil calculatebill(@RequestParam String nic,String regno,Boolean damage){
        return new ResponseUtil(200,"ok",userCarService.calculatebill(nic,regno,damage));
    }
    
}
