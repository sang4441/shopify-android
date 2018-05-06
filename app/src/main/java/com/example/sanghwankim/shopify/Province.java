package com.example.sanghwankim.shopify;

import java.util.List;

public class Province {

    Province(String province) {
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
    //first_name":"Napoleon","address1":"464 Dixie Mission","phone":"(573) 502-6949 x722","city":"Bessemer","zip":"35023","province":"Alabama","country":"United States","last_name":"Batz","address2":null,"company":null,"latitude":33.4704596,"longitude":-87.0424393,"name":"Napoleon Batz","country_code":"US","province_code":"AL"},

}
