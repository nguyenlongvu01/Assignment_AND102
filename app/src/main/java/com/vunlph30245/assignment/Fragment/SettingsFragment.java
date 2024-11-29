package com.vunlph30245.assignment.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.vunlph30245.assignment.R;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        Switch switchNotification = view.findViewById(R.id.switchNotification);
        CheckBox checkBoxDarkMode = view.findViewById(R.id.checkBoxDarkMode);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SettingsPrefs", getActivity().MODE_PRIVATE);
        boolean isNotificationEnabled = sharedPreferences.getBoolean("notificationEnabled", false);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("darkModeEnabled", false);


        switchNotification.setChecked(isNotificationEnabled);
        checkBoxDarkMode.setChecked(isDarkModeEnabled);


        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notificationEnabled", isChecked);
                editor.apply();  // Lưu thay đổi
                Toast.makeText(getActivity(), isChecked ? "Thông báo bật" : "Thông báo tắt", Toast.LENGTH_SHORT).show();
            }
        });


        checkBoxDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("darkModeEnabled", isChecked);
                editor.apply();
                Toast.makeText(getActivity(), isChecked ? "Chế độ tối bật" : "Chế độ tối tắt", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
