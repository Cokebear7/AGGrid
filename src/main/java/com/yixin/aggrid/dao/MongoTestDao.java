package com.yixin.aggrid.dao;

import com.yixin.aggrid.model.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MongoTestDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Create
     */
    public void saveTest(MongoTest test) {
        mongoTemplate.save(test);
    }

    /**
     * Read
     *
     */
    public MongoTest findTestByName(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        MongoTest mgt =  mongoTemplate.findOne(query , MongoTest.class);
        return mgt;
    }

    /**
     * Update
     */
    public void updateTest(MongoTest test) {
        Query query=new Query(Criteria.where("id").is(test.getId()));
        Update update= new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,MongoTest.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * Delete
     *
     */
    public void deleteTestById(Integer id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,MongoTest.class);
    }

    public void deleteTestByname(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        mongoTemplate.remove(query,MongoTest.class);
    }
}