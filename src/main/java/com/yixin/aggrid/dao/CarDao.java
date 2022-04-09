package com.yixin.aggrid.dao;

import com.yixin.aggrid.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CarDao {

    private static final Logger LOG = LoggerFactory.getLogger(CarDao.class);

    @Resource
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

    public Car searchById(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        //update first result
        return mongoTemplate.findOne(query,Car.class);
    }

    /**
     * Update
     */
    public void updateCar(Car car) {
        Query query = new Query(Criteria.where("_id").is(car.getId()));
        Update update= new Update().set("maker", car.getMaker()).set("model", car.getModel()).set("price", car.getPrice());
        //update first result
        mongoTemplate.updateFirst(query,update,Car.class);
    }

    /**
     * Delete
     */
    public void deleteCarById(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Car.class);
    }
}