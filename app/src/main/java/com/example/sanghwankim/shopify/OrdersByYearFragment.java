package com.example.sanghwankim.shopify;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class OrdersByYearFragment extends Fragment {

    private RecyclerView orderByYearList;
    private OrderByYearAdapter adapter;
    private List<Order> mOrders;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_by_year, container, false);
        mOrders = OrderLab.get(getActivity()).getOrders();
        orderByYearList = rootView.findViewById(R.id.orders_by_year_list_view);
        adapter = new OrderByYearAdapter(mOrders);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        orderByYearList.setLayoutManager(mLayoutManager);
        orderByYearList.setAdapter(adapter);

        return rootView;


    }


    public class OrderByYearAdapter extends RecyclerView.Adapter<OrderByYearAdapter.MyViewHolder> {

        private View itemView;
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mOrderId;

            public MyViewHolder(View view) {
                super(view);
                mOrderId = view.findViewById(R.id.order_id);
            }
        }

        public OrderByYearAdapter(List<Order> orders) {
            mOrders = orders;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order_by_year, parent, false);


            return new MyViewHolder(itemView);
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
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
