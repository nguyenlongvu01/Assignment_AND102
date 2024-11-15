package com.vunlph30245.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vunlph30245.assignment.Adapter.ProductsAdapter;
import com.vunlph30245.assignment.DAO.ProductsDAO;
import com.vunlph30245.assignment.Model.Products;
import com.vunlph30245.assignment.R;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {
    private RecyclerView recyclerProd;
    private FloatingActionButton floatAdd;
    private ProductsDAO productsDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
       //
        recyclerProd = view.findViewById(R.id.recyclerProd);
        floatAdd = view.findViewById(R.id.floatAdd);

        productsDAO = new ProductsDAO(getContext());
        ArrayList<Products> list = productsDAO.getAll();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerProd.setLayoutManager(linearLayoutManager);
        ProductsAdapter productsAdapter = new ProductsAdapter(getContext(), list);
        recyclerProd.setAdapter(productsAdapter);




        return view;
    }
}
