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
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarDao carDao;

    @PostMapping(value="/car")
    public CommonResp createNewCar(@Valid @RequestBody CarCreateReq req) throws Exception {
        CommonResp resp = new CommonResp();
        if (carDao.searchById(req.getId()) != null) {
            resp.setSuccess(false);
            resp.setMessage("car id already been used");
            return resp;
        };
        Car car = new Car();
        car.setId(req.getId());
        car.setMaker(req.getMaker());
        car.setModel(req.getModel());
        car.setPrice(req.getPrice());
        carDao.createNewCar(car);
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
        if (listCar.size() > 0) {
            resp.setMessage("OK");
        } else {
            resp.setSuccess(false);
            resp.setMessage("Nothing to display");
        }
        resp.setContent(listResp);
        return resp;
    }

    @PutMapping(value="/car")
    public CommonResp updateCar(@Valid @RequestBody CarSaveReq req){
        CommonResp resp = new CommonResp();
        if (carDao.searchById(req.getId()) == null) {
            resp.setSuccess(false);
            resp.setMessage("car doesn't exist");
            return resp;
        };
        Car car = new Car();
        car.setId(req.getId());
        car.setMaker(req.getMaker());
        car.setModel(req.getModel());
        car.setPrice(req.getPrice());
        carDao.updateCar(car);
        resp.setMessage("car info updated");
        return resp;
    }

    @DeleteMapping (value="/car/{id}")
    public CommonResp deleteCarById(@PathVariable Integer id){
        CommonResp resp = new CommonResp();
        if (carDao.searchById(id) == null) {
            resp.setSuccess(false);
            resp.setMessage("nothing to delete");
            return resp;
        };
        carDao.deleteCarById(id);
        resp.setMessage("car deleted");
        return resp;
    }
}