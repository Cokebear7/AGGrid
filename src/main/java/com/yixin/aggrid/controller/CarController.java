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
    public CommonResp createNewCar(@RequestBody CarCreateReq req) throws Exception {
        CommonResp resp = new CommonResp();
        Car car = new Car();
        car.setId(req.getId());
        car.setMaker(req.getMaker());
        car.setModel(req.getModel());
        car.setPrice(req.getPrice());
        carDao.createNewCar(car);
        resp.setStatusCode(201);
        resp.setMessage("new car created");
        return resp;
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
    public CommonResp updateCar(@RequestBody CarSaveReq req){
        CommonResp resp = new CommonResp();
        Car car = new Car();
        car.setId(req.getId());
        car.setMaker(req.getMaker());
        car.setModel(req.getModel());
        car.setPrice(req.getPrice());
        carDao.updateCar(car);
        resp.setStatusCode(200);
        resp.setMessage("car info updated");
        return resp;
    }

    @DeleteMapping (value="/car/{id}")
    public CommonResp deleteCarById(@PathVariable Integer id){
        CommonResp resp = new CommonResp();
        LOG.info(id.toString());
        carDao.deleteCarById(id);
        resp.setStatusCode(200);
        resp.setMessage("car deleted");
        return resp;
    }
}