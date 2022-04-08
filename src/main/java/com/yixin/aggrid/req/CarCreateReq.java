package com.yixin.aggrid.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarCreateReq {
    @NotNull(message = "id is empty")
    private Integer id;

    @NotEmpty(message = "maker is empty")
    private String maker;

    @NotEmpty(message = "model is empty")
    private String model;

    @NotNull(message = "price is empty")
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}


