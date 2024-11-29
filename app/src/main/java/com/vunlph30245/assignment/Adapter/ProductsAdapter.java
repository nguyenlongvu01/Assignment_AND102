package com.vunlph30245.assignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vunlph30245.assignment.DAO.ProductsDAO;
import com.vunlph30245.assignment.Model.Products;
import com.vunlph30245.assignment.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Products> list;
    private ProductsDAO productsDAO;

    public ProductsAdapter(Context context, ArrayList<Products> list, ProductsDAO productsDAO) {
        this.context = context;
        this.list = list;
        this.productsDAO = productsDAO;
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

        if (list.get(position).getImg() != null) {
            Glide.with(context).load(list.get(position).getImg()).into(holder.ivImgProd);
        }

        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(list.get(holder.getAdapterPosition()));
            }
        });

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialogDelete(list.get(holder.getAdapterPosition()).getTensp(), list.get(holder.getAdapterPosition()).getMasp());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtQuantity, txtEdit, txtDelete;
        ImageView ivImgProd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        txtQuantity = itemView.findViewById(R.id.txtQuantity);
        txtEdit = itemView.findViewById(R.id.txtEdit);
        txtDelete = itemView.findViewById(R.id.txtDelete);
        ivImgProd = itemView.findViewById(R.id.ivImgProd);

        }
    }

    private void showDialogUpdate(Products products){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        EditText edtTenSP = view.findViewById(R.id.edtTenSP);
        EditText edtGiaSP = view.findViewById(R.id.edtGiaSP);
        EditText edtSoLuongSP = view.findViewById(R.id.edtSoLuongSP);
        Button btnUpdateSP = view.findViewById(R.id.btnUpdateSP);
        Button btnQuayLai = view.findViewById(R.id.btnQuayLai);

        edtTenSP.setText(products.getTensp());
        edtGiaSP.setText(String.valueOf(products.getGia()));
        edtSoLuongSP.setText(String.valueOf(products.getSoluong()));

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnUpdateSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = products.getMasp();
                String tensp = edtTenSP.getText().toString().trim();
                String gia = edtGiaSP.getText().toString().trim();
                String soluong = edtSoLuongSP.getText().toString().trim();

                if (tensp.isEmpty() || gia.isEmpty() || soluong.isEmpty()) {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {

                    Products productsUpdate = new Products(masp, tensp, Integer.parseInt(gia), Integer.parseInt(soluong), products.getImg());


                    boolean check = productsDAO.insertSP(productsUpdate);

                    if (check) {
                        Toast.makeText(context, "Sửa sản phẩm thành công", Toast.LENGTH_SHORT).show();

                        // Cập nhật danh sách và RecyclerView
                        list.clear();
                        list = productsDAO.getAll();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Sửa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    private void showDialogDelete(String tensp, int masp){
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("Thông báo");
    builder.setMessage("Bạn có muốn xóa sản phẩm " + tensp + " không?");
    builder.setIcon(R.drawable.ic_warning);

    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            boolean check = productsDAO.deleteSP(masp);
            if (check) {
                Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list = productsDAO.getAll();
                notifyDataSetChanged();
            }else {
                Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    });
        builder.setNegativeButton("Quay lại", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
