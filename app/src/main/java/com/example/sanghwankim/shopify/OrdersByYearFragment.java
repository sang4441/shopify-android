package com.example.sanghwankim.shopify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanghwankim.shopify.models.Order;
import com.example.sanghwankim.shopify.models.OrderLab;

import java.util.List;

public class OrdersByYearFragment extends Fragment {

    private final static int  TARGET_YEAR = 2017;
    private RecyclerView orderByYearList;
    private OrderItemAdapter adapter;
    private List<Order> mOrders;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_by_year, container, false);
        mOrders = OrderLab.get(getActivity()).getOrdersByYear(TARGET_YEAR);
        orderByYearList = rootView.findViewById(R.id.orders_by_year_list_view);
        adapter = new OrderItemAdapter(mOrders);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(orderByYearList.getContext(),
                mLayoutManager.getOrientation());
        orderByYearList.addItemDecoration(dividerItemDecoration);
        orderByYearList.setLayoutManager(mLayoutManager);
        orderByYearList.setAdapter(adapter);

        return rootView;
    }
}
