package com.example.sanghwankim.shopify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanghwankim.shopify.models.Order;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.MyViewHolder> {
    private List<Order> mOrders;
    private View itemView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mOrderId, mOrderDate, mFirstName, mLastName, mPhoneNumber, mEmail, mAddress, mTotalPrice, mCurrency, mOrderProducts;

        public MyViewHolder(View view) {
            super(view);
            mOrderId = view.findViewById(R.id.order_id);
            mOrderDate = view.findViewById(R.id.order_created_at);
            mFirstName  = view.findViewById(R.id.customer_first_name);
            mLastName = view.findViewById(R.id.customer_last_name);
            mPhoneNumber = view.findViewById(R.id.customer_phoneNumber);
            mEmail = view.findViewById(R.id.customer_email);
            mAddress = view.findViewById(R.id.address);
            mTotalPrice = view.findViewById(R.id.total_price);
            mCurrency = view.findViewById(R.id.currency);
            mOrderProducts = view.findViewById(R.id.order_item_products);
        }
    }

    public OrderItemAdapter(List<Order> orders) {
        mOrders = orders;
    }

    @Override
    public OrderItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_detail, parent, false);
        return new OrderItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(final OrderItemAdapter.MyViewHolder holder, final int position) {
        Order order = mOrders.get(position);
        holder.mOrderId.setText(order.getId()+"");
        holder.mOrderDate.setText(order.getCreatedAt());
        holder.mFirstName.setText(order.getCustomer().getFirstName());
        holder.mLastName.setText(order.getCustomer().getLastName());
        holder.mPhoneNumber.setText(order.getPhoneNumber());
        holder.mEmail.setText(order.getCustomer().getEmail());

        StringBuilder addressString = new StringBuilder();
        if (order.getAddress() != null) {
            addressString.append(order.getAddress());
            addressString.append(", ");
            addressString.append(order.getCity());
            addressString.append(", ");
            addressString.append(order.getProvince());
            addressString.append(", ");
            addressString.append(order.getCountry());
        }
        holder.mAddress.setText(addressString.toString());
        holder.mTotalPrice.setText(order.getTotalPrice()+"");
        holder.mCurrency.setText(order.getCurrency());
        StringBuilder productString = new StringBuilder();
        int sizeOfProducts = order.getOrderProduct().size();
        if (sizeOfProducts > 0) {
            productString.append(order.getOrderProduct().get(0).getName());
        }
        if (sizeOfProducts > 1) {
            for (int i = 1; i < sizeOfProducts; i++) {
                productString.append(", ");
                productString.append(order.getOrderProduct().get(i).getName());
            }
        }
        holder.mOrderProducts.setText(productString.toString());
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }
}