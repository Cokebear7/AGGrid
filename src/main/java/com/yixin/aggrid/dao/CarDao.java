package com.yixin.aggrid.dao;

import com.yixin.aggrid.config.AgGridApplication;
import com.yixin.aggrid.model.Car;
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
//        Query query = new Query();
//        query.with(new Sort(Sort.Direction.DESC, "_id"));
//
//        List<Car> listCar=  mongoTemplate.find(query, Car.class);
//        LOG.info(listCar.get(0).toString());
        return listCar;
    }

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