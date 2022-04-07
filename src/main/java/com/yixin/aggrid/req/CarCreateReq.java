package com.yixin.aggrid.req;

public class CarCreateReq {
    private Integer id;

    private String maker;

//    @NotNull(message = "【名称】不能为空")
    private String model;

//    @NotNull(message = "【排序】不能为空")
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


