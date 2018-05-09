package com.example.sanghwankim.shopify.models;

import com.example.sanghwankim.shopify.models.Order;

import java.util.List;

public class OrderByProvince {

    OrderByProvince(String province) {
        this.mProvince = province;
    }

    private String mProvince;
    private List<Order> mOrders;

    public String getmProvince() {
        return mProvince;
    }

    public void setmProvince(String mProvince) {
        this.mProvince = mProvince;
    }

    public List<Order> getmOrders() {
        return mOrders;
    }

    public void setmOrders(List<Order> mOrders) {
        this.mOrders = mOrders;
    }

}
