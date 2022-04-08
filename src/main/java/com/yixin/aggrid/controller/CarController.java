package com.yixin.aggrid.controller;

import com.yixin.aggrid.dao.CarDao;
import com.yixin.aggrid.model.Car;
import com.yixin.aggrid.req.CarCreateReq;
import com.yixin.aggrid.req.CarSaveReq;
import com.yixin.aggrid.resp.CarQueryResp;
import com.yixin.aggrid.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarDao carDao;

    @PostMapping(value="/car")
    public void createNewCar(@RequestBody CarCreateReq req) throws Exception {
        Car car = new Car();
        car.setId(req.getId());
        car.setMaker(req.getMaker());
        car.setModel(req.getModel());
        car.setPrice(req.getPrice());
        carDao.createNewCar(car);
    }

//    @GetMapping(value="/car")
//    public List<Car> getAllCars(){
//        List<Car> listCar= carDao.getAllCars();
//        return listCar;
//    }

    @GetMapping(value="/car")
    public CommonResp getAllCars(){
        CommonResp resp = new CommonResp();
        List<CarQueryResp> listResp = new ArrayList<>();
        List<Car> listCar = carDao.getAllCars();
        for (Car car : listCar) {
            CarQueryResp temp = new CarQueryResp();
            temp.setId(car.getId());
            temp.setMaker(car.getMaker());
            temp.setModel(car.getModel());
            temp.setPrice(car.getPrice());
            LOG.info(temp.toString());
            listResp.add(temp);
        }
//        if (listCar.size() >= 0) {
            resp.setStatusCode(200);
            resp.setMessage("OK");
            resp.setContent(listResp);
//        }
        return resp;
    }

    @PutMapping(value="/car")
    public void updateCar(@RequestBody CarSaveReq req){
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