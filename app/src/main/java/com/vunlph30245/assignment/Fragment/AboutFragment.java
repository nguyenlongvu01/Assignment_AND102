package com.vunlph30245.assignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vunlph30245.assignment.R;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Initialize and set content for the TextView
        TextView textView = view.findViewById(R.id.textAbout);
        textView.setText("Đây là ứng dụng quản lý sản phẩm, giúp bạn dễ dàng tìm kiếm và quản lý các sản phẩm trong cửa hàng.");

        return view;
    }
}
