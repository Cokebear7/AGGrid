package com.yixin.aggrid.controller;

import com.yixin.aggrid.dao.MongoTestDao;
import com.yixin.aggrid.model.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MongoTestController {

    @Autowired
    private MongoTestDao mtdao;

    @PostMapping(value="/person")
    public void saveTest(String name) throws Exception {
        MongoTest mgtest=new MongoTest();
        mgtest.setId(12);
        mgtest.setAge(33);
        mgtest.setName(name);
        mtdao.saveTest(mgtest);
    }

    @GetMapping(value="/person")
    public MongoTest findTestByName(){
        MongoTest mgtest= mtdao.findTestByName("Andrew");
        System.out.println("mgtest is "+mgtest);
        return mgtest;
    }

    @PutMapping(value="/person")
    public void updateTest(String name){
        MongoTest mgtest=new MongoTest();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName(name);
        mtdao.updateTest(mgtest);
    }

    @DeleteMapping (value="/person")
    public void deleteTestByName(String name){
//        mtdao.deleteTestById(11);
        mtdao.deleteTestByname(name);
    }
}