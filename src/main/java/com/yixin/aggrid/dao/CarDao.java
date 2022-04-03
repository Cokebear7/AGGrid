package com.yixin.aggrid.dao;

import com.yixin.aggrid.config.AgGridApplication;
import com.yixin.aggrid.model.Car;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

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
//    public CarModel findCarByName(String name) {
//        Query query=new Query(Criteria.where("name").is(name));
//        MongoTest mgt =  mongoTemplate.findOne(query , MongoTest.class);
//        return mgt;
//    }
    public List<Car> getAllCars() {
        List<Car> listCar = mongoTemplate.findAll(Car.class);
        return listCar;
    }

    /**
     * Update
     */
//    public void updateTest(MongoTest test) {
//        Query query=new Query(Criteria.where("id").is(test.getId()));
//        Update update= new Update().set("age", test.getAge()).set("name", test.getName());
//        //update first result
//        mongoTemplate.updateFirst(query,update,MongoTest.class);
//        //update all result
//        // mongoTemplate.updateMulti(query,update,TestEntity.class);
//    }

    /**
     * Delete
     */
    public void deleteCarById(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.remove(query,Car.class);
    }
}