package com.example.sanghwankim.shopify;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanghwankim.shopify.models.Order;
import com.example.sanghwankim.shopify.models.OrderByProvince;
import com.example.sanghwankim.shopify.models.OrderLab;

import java.util.List;

public class OrdersByProvinceFragment extends Fragment {

    private RecyclerView orderByProvinceList;
    private OrderByProvinceAdapter adapterParent;
    private OrderItemAdapter adapterChild;
    private List<OrderByProvince> mProvinces;

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

        public OrderByProvinceAdapter(List<OrderByProvince> provinces) {
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
            OrderByProvince province = mProvinces.get(position);
            mOrders = mProvinces.get(position).getmOrders();
            StringBuilder subHeader = new StringBuilder();
            subHeader.append(mOrders.size());
            subHeader.append(" number of orders from ");
            subHeader.append(province.getmProvince());
            holder.mProvinceName.setText(subHeader.toString());

            adapterChild = new OrderItemAdapter(mOrders);

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(orderByProvinceItemList.getContext(),
                    mLayoutManager.getOrientation());
            orderByProvinceItemList.addItemDecoration(dividerItemDecoration);
            orderByProvinceItemList.setLayoutManager(mLayoutManager);
            orderByProvinceItemList.setAdapter(adapterChild);
        }

        @Override
        public int getItemCount() {
            return mProvinces.size();
        }
    }
}
