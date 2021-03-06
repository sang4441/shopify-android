package com.example.sanghwankim.shopify.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderLab {

    private List<Order> mOrders;
    private List<OrderByProvince> mOrderByProvinces;

    private static OrderLab sOrderLab;
    private Context mAppContext;

    private OrderLab(Context appContext) {
        mAppContext = appContext;
        mOrders = new ArrayList<>();
    }

    public static OrderLab get(Context c) {
        if (sOrderLab == null) {
            sOrderLab = new OrderLab(c.getApplicationContext());
        }
        return sOrderLab;
    }

    public void setOrders (List<Order> orders) {

        this.mOrders = orders;
        HashMap<String, List<Order>> map = new HashMap<>();
        for (Order order: orders) {
            String province = order.getProvince();
            if (province != null) {
                if (map.containsKey(province)) {
                    List<Order> oldOrder = map.get(province);
                    oldOrder.add(order);
                    map.put(province, oldOrder);
                } else {
                    List<Order> newOrder = new ArrayList<>();
                    newOrder.add(order);
                    map.put(province, newOrder);
                }
            }
        }
        List<OrderByProvince> ordersByProvinces = new ArrayList<>();
        Map<String, List<Order>> treeMap = new TreeMap<>(map);
        for (String key : treeMap.keySet()) {
            OrderByProvince province = new OrderByProvince(key);
            province.setmOrders(treeMap.get(key));
            ordersByProvinces.add(province);
        }
        this.mOrderByProvinces = ordersByProvinces;
    }

    public List<Order> getOrders() {
        return this.mOrders;
    }

    public List<Order> getOrdersByYear(int year) {
        List<Order> ordersByYear = new ArrayList<>();
        for (Order order : this.mOrders) {
            if (order.getYear() == year) {
                ordersByYear.add(order);
                if (ordersByYear.size() == 10) {
                    break;
                }
            }
        }
        return ordersByYear;
    }

    public List<OrderByProvince> getmOrderByProvinces() {
        return mOrderByProvinces;
    }

    public void setmOrderByProvinces(List<OrderByProvince> mOrderByProvinces) {
        this.mOrderByProvinces = mOrderByProvinces;
    }

}
