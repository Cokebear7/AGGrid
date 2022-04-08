package com.yixin.aggrid.dao;

import com.yixin.aggrid.config.AgGridApplication;
import com.yixin.aggrid.model.Car;
import com.yixin.aggrid.resp.CarQueryResp;
import com.yixin.aggrid.resp.CommonResp;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarDao {

    private static final Logger LOG = LoggerFactory.getLogger(CarDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Create
     */
    public void createNewCar(Car car) {
        mongoTemplate.save(car);
    }

    /**
     * Read
     */
    public List<Car> getAllCars() {
        List<Car> listCar = mongoTemplate.findAll(Car.class);
        return listCar;
    }
//    public List<CarQueryResp> getAllCars() {
//        List<CarQueryResp> listCar = new ArrayList<>();
//        List<Car> findResult = mongoTemplate.findAll(Car.class);
//        for (Car car : findResult) {
//            CarQueryResp resp = new CarQueryResp();
//            resp.setId(car.getId());
//            resp.setMaker(car.getMaker());
//            resp.setModel(car.getModel());
//            resp.setPrice(car.getPrice());
//            listCar.add(resp);
//        }
//        return listCar;
//    }

    /**
     * Update
     */
    public void updateCar(Car car) {
        Query query = new Query(Criteria.where("_id").is(car.getId()));
        Update update= new Update().set("maker", car.getMaker()).set("model", car.getModel()).set("price", car.getPrice());
        //update first result
        mongoTemplate.updateFirst(query,update,Car.class);
        //update all result
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * Delete
     */
    public void deleteCarById(Integer id) {
//        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Car.class);
    }
}