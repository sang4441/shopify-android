package com.example.sanghwankim.shopify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private TextView mTextProvince, mTextYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getOrders();



        mTextProvince = findViewById(R.id.order_by_province_txt);

        mTextProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new OrdersByProvinceFragment());
            }
        });

        mTextYear = findViewById(R.id.order_by_year_txt);

        mTextYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                OrdersByYearFragment fragment = new OrdersByYearFragment();
//                fragment.setArguments();
                replaceFragment(new OrdersByYearFragment());
            }
        });

    }

    private void setDefaultFragment(Fragment defaultFragment)
    {
        this.replaceFragment(defaultFragment);
    }

    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment)
    {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.ordersFragment, destFragment);

        // Commit the Fragment replace action.
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
//                        mProgressBar.setVisibility(View.INVISIBLE);
//                        Toast.makeText(AlbumActivity.this, "서버에 연결 할 수 없습니다. 다시 시도 해 주세요.", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mProgressBar.setVisibility(View.INVISIBLE);
//                            Toast.makeText(AlbumActivity.this, "서버에 문제가 생겼습니다. 다시 시도 해 주세요.", Toast.LENGTH_LONG).show();
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
//                    Log.d("exmple", response.body().string());

                    JSONArray jArrayOrders = result.getJSONArray("orders");
                    List<Order> orders = new ArrayList<>();
//                    adapter = new AlbumsAdapter(getActivity(), albums);

                    if (jArrayOrders != null) {
                        for (int i=0;i<jArrayOrders.length();i++){
                            JSONObject object = jArrayOrders.getJSONObject(i);
                            Order order = new Order();
                            order.setId(object.getLong("id"));
                            order.setCreatedAt(object.getString("created_at"));
                            if(object.has("shipping_address")) {
                                JSONObject shippingAddress =  object.getJSONObject("shipping_address");
                                order.setProvince(shippingAddress.getString("province"));
                                orders.add(order);
                            }
                        }
                    }
                    Log.d("t", "T");


                    OrderLab.get(getApplicationContext()).setOrders(orders);
                    Fragment fragment = new OrdersByYearFragment();
                    setDefaultFragment(fragment);


//                    String autoUploadAlbumId = Sharedpreference.getAutoUploadAlbumId(AlbumActivity.this);
//                    Log.d("auto upload album Id::", autoUploadAlbumId);
//                    if(!autoUploadAlbumId.equals("0")) {
//
//                        AlbumLab.get(AlbumActivity.this).getAlbumById(autoUploadAlbumId).setAutoUpload(true);
//                    }
////                    AlbumLab.get(getActivity()).setAlbums(albums);
//                    runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            adapter.notifyDataSetChanged();
//                            mProgressBar.setVisibility(View.INVISIBLE);
//                        }
//                    });


                } catch (JSONException ex) {
                    //jons not!!
                }


            }
        });
    }
}
