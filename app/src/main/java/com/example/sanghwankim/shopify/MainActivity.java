package com.example.sanghwankim.shopify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.sanghwankim.shopify.models.Customer;
import com.example.sanghwankim.shopify.models.Order;
import com.example.sanghwankim.shopify.models.OrderLab;
import com.example.sanghwankim.shopify.models.OrderProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private LinearLayout mTextProvince, mTextYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextProvince = findViewById(R.id.order_by_province_txt);
        mTextProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextProvince.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mTextYear.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                replaceFragment(new OrdersByProvinceFragment());
            }
        });
        mTextYear = findViewById(R.id.order_by_year_txt);
        mTextYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextProvince.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mTextYear.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                replaceFragment(new OrdersByYearFragment());
            }
        });

        getOrders();
    }

    private void setDefaultFragment(Fragment defaultFragment)
    {
        this.replaceFragment(defaultFragment);
    }

    public void replaceFragment(Fragment destFragment)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ordersFragment, destFragment);
        fragmentTransaction.commit();
    }


    public void getOrders() {
        String url = "https://shopicruit.myshopify.com/admin/orders.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Cannot connect to server, please try again.", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Cannot connect to server, please try again.", Toast.LENGTH_LONG).show();
                        }
                    });
                    throw new IOException("Unexpected code " + response);
                }

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                try {
                    JSONObject result = new JSONObject(response.body().string());
                    JSONArray orderList = result.getJSONArray("orders");
                    List<Order> orders = new ArrayList<>();

                    if (orderList != null) {
                        for (int i=0;i<orderList.length();i++){
                            JSONObject orderObject = orderList.getJSONObject(i);
                            Order order = new Order();
                            order.setId(orderObject.getLong("id"));
                            Date date = convertStringToDate(orderObject.getString("created_at"));
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            int year = cal.get(Calendar.YEAR);
                            order.setYear(year);
                            String dateString = convertDateToString(date);
                            order.setCreatedAt(dateString);
                            order.setTotalPrice(orderObject.getDouble("total_price"));
                            order.setCurrency(orderObject.getString("currency"));

                            if(orderObject.has("shipping_address")) {
                                JSONObject shippingAddress =  orderObject.getJSONObject("shipping_address");
                                order.setProvince(shippingAddress.getString("province"));
                                order.setAddress(shippingAddress.getString("address1"));
                                order.setPhoneNumber(shippingAddress.getString("phone"));
                                order.setCity(shippingAddress.getString("city"));
                                order.setCountry(shippingAddress.getString("country"));
                            } else {
                                order.setProvince(null);
                            }

                            List<OrderProduct> orderProducts = new ArrayList<>();
                            if(orderObject.has("line_items")) {
                                JSONArray productList = orderObject.getJSONArray("line_items");
                                if (productList != null) {
                                    for (int k = 0; k < productList.length(); k++) {
                                        JSONObject product =  productList.getJSONObject(k);
                                        OrderProduct orderProduct = new OrderProduct();
                                        orderProduct.setId(product.getLong("id"));
                                        orderProduct.setName(product.getString("name"));
                                        orderProduct.setPrice(product.getDouble("price"));
                                        orderProduct.setQuantity(product.getInt("quantity"));
                                        orderProduct.setTotalDiscount(product.getDouble("quantity"));
                                        orderProduct.setVendor(product.getString("vendor"));
                                        orderProducts.add(orderProduct);
                                    }
                                }
                            }

                            order.setOrderProduct(orderProducts);
                            Customer orderCustomer = new Customer();
                            if(orderObject.has("customer")) {
                                JSONObject customer =  orderObject.getJSONObject("customer");
                                orderCustomer.setId(customer.getLong("id"));
                                orderCustomer.setEmail(customer.getString("email"));
                                orderCustomer.setFirstName(customer.getString("first_name"));
                                orderCustomer.setLastName(customer.getString("last_name"));
                            }
                            order.setCustomer(orderCustomer);
                            orders.add(order);
                        }
                    }

                    OrderLab.get(getApplicationContext()).setOrders(orders);
                    Fragment fragment = new OrdersByYearFragment();
                    setDefaultFragment(fragment);

                } catch (JSONException ex) {
                    //jons not!!
                }
            }
        });
    }

    public Date convertStringToDate(String stringDate) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            Date date = inputDateFormat.parse(stringDate);
            return date;
        } catch (ParseException e) {
            Log.d("tag", e.toString());
            return new Date();
        }
    }

    public String convertDateToString(Date date) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputDateFormat.format(date);
    }
}
