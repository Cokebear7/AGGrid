package com.yixin.aggrid.controller;

import com.yixin.aggrid.dao.CarDao;
import com.yixin.aggrid.model.Car;
import com.yixin.aggrid.req.CarSaveReq;
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
    public void saveTest(Integer id, String maker, String model, Integer price) throws Exception {
        Car car = new Car();
        car.setId(id);
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

    @PutMapping(value="/car")
    public void updateTest(@RequestBody CarSaveReq req){
        Car car = new Car();
        car.setId(req.getId());
        car.setMaker(req.getMaker());
        car.setModel(req.getModel());
        car.setPrice(req.getPrice());
        carDao.updateCar(car);
    }

    @DeleteMapping (value="/car/{id}")
    public void deleteCarById(@PathVariable Integer id){
        LOG.info(id.toString());
        carDao.deleteCarById(id);
    }
}