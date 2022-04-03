package com.yixin.aggrid.controller;

import com.yixin.aggrid.dao.CarDao;
import com.yixin.aggrid.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarDao carDao;

    @PostMapping(value="/car")
    public void saveTest(String maker, String model, Integer price) throws Exception {
        Car car = new Car();
        car.setMaker(maker);
        car.setModel(model);
        car.setPrice(price);
        carDao.createNewCar(car);
    }

    @GetMapping(value="/car")
    public List<Car> getAllCars(){
        List<Car> listCar= carDao.getAllCars();
        return listCar;
    }

//    @PutMapping(value="/person")
//    public void updateTest(String name){
//        MongoTest mgtest=new MongoTest();
//        mgtest.setId(11);
//        mgtest.setAge(44);
//        mgtest.setName(name);
//        mtdao.updateTest(mgtest);
//    }

    @DeleteMapping (value="/car/{id}")
    public void deleteCarById(@PathVariable String id){
        LOG.info(id);
        carDao.deleteCarById(id);
    }
}