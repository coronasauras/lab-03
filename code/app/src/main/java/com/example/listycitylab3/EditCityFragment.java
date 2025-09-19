package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

public class EditCityFragment extends DialogFragment {

    private static final String CITY_KEY = "city";

    public static EditCityFragment newInstance(City city) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putSerializable(CITY_KEY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final City city = (City) getArguments().getSerializable(CITY_KEY);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText cityNameEditText = new EditText(getActivity());
        EditText provinceEditText = new EditText(getActivity());

        cityNameEditText.setText(city.getName());
        provinceEditText.setText(city.getProvince());

        layout.addView(cityNameEditText);
        layout.addView(provinceEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit City");
        builder.setView(layout);

        builder.setPositiveButton("OK", (dialog, id) -> {
            city.setName(cityNameEditText.getText().toString());
            city.setProvince(provinceEditText.getText().toString());
            ((MainActivity) getActivity()).updateCity(city);
        });

        builder.setNegativeButton("Cancel", (dialog, id) -> {});

        return builder.create();
    }
}