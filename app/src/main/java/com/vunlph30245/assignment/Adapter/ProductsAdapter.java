package com.vunlph30245.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vunlph30245.assignment.Model.Products;
import com.vunlph30245.assignment.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Products> list;

    public ProductsAdapter(Context context, ArrayList<Products> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_prod, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getTensp());

        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = list.get(position).getGia();
        String formattedNumber = formatter.format(myNumber);

        holder.txtPrice.setText(formattedNumber + " VND");
        holder.txtQuantity.setText("SL: " + list.get(position).getSoluong());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtQuantity, txtEdit, txtDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        txtQuantity = itemView.findViewById(R.id.txtQuantity);

        }
    }
}
