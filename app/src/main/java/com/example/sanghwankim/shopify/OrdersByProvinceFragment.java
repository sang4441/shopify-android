package com.example.sanghwankim.shopify;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrdersByProvinceFragment extends Fragment {

    private RecyclerView orderByProvinceList;
    private OrderByProvinceAdapter adapterParent;
    private OrderByProvinceItemAdapter adapterChild;
    private List<Province> mProvinces;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_by_province, container, false);
        mProvinces = OrderLab.get(getActivity()).getmOrderByProvinces();
        orderByProvinceList = rootView.findViewById(R.id.orders_by_province_list_view);
        adapterParent = new OrderByProvinceAdapter(mProvinces);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        orderByProvinceList.setLayoutManager(mLayoutManager);
        orderByProvinceList.setAdapter(adapterParent);

        return rootView;
    }

    public class OrderByProvinceAdapter extends RecyclerView.Adapter<OrderByProvinceAdapter.MyViewHolder> {

        private View itemView;
        private List<Order> mOrders;
        public RecyclerView orderByProvinceItemList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mProvinceName;


            public MyViewHolder(View view) {
                super(view);
                mProvinceName = view.findViewById(R.id.order_province_name);
                orderByProvinceItemList = view.findViewById(R.id.orders_by_province_detail_list);

            }
        }

        public OrderByProvinceAdapter(List<Province> provinces) {
            mProvinces = provinces;
        }

        @Override
        public OrderByProvinceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order_by_province, parent, false);


            return new OrderByProvinceAdapter.MyViewHolder(itemView);
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }


        @Override
        public void onBindViewHolder(final OrderByProvinceAdapter.MyViewHolder holder, final int position) {
            Province province = mProvinces.get(position);
//            TextView friendName = holder.orderId;
            holder.mProvinceName.setText(province.getmProvince());

            mOrders = mProvinces.get(position).getmOrders();
            adapterChild = new OrderByProvinceItemAdapter(mOrders);

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

            orderByProvinceItemList.setLayoutManager(mLayoutManager);
            orderByProvinceItemList.setAdapter(adapterChild);
        }

        @Override
        public int getItemCount() {
            return mProvinces.size();
        }
    }


    public class OrderByProvinceItemAdapter extends RecyclerView.Adapter<OrderByProvinceItemAdapter.MyViewHolder> {
        private List<Order> mOrders;
        private View itemView;
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mOrderId;

            public MyViewHolder(View view) {
                super(view);
                mOrderId = view.findViewById(R.id.order_id);
            }
        }

        public OrderByProvinceItemAdapter(List<Order> orders) {
            mOrders = orders;
        }

        @Override
        public OrderByProvinceItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order_by_province_detail_item, parent, false);


            return new OrderByProvinceItemAdapter.MyViewHolder(itemView);
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }


        @Override
        public void onBindViewHolder(final OrderByProvinceItemAdapter.MyViewHolder holder, final int position) {
            Order order = mOrders.get(position);
//            TextView friendName = holder.orderId;
            holder.mOrderId.setText(order.getId()+"");
        }

        @Override
        public int getItemCount() {
            return mOrders.size();
        }
    }

}
