package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<City> dataList;
    private ListView cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        City[] cities = {
                new City("Edmonton", "Alberta"),
                new City("Vancouver", "British Columbia"),
                new City("Moscow", "Moscow Oblast"),
                new City("Sydney", "New South Wales"),
                new City("Berlin", "Berlin"),
                new City("Vienna", "Vienna"),
                new City("Tokyo", "Tokyo"),
                new City("Beijing", "Beijing"),
                new City("Osaka", "Osaka"),
                new City("New Delhi", "Delhi")
        };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityList = findViewById(R.id.city_list);

        ArrayAdapter<City> cityAdapter = new ArrayAdapter<City>(this, android.R.layout.simple_list_item_1, dataList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                City city = getItem(position);
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                }

                TextView textView = convertView.findViewById(android.R.id.text1);
                textView.setText(city.getName() + ", " + city.getProvince());

                return convertView;
            }
        };

        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            EditCityFragment fragment = EditCityFragment.newInstance(selectedCity);
            fragment.show(getSupportFragmentManager(), "editCity");
        });
    }

    public void updateCity(City updatedCity) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getName().equals(updatedCity.getName())) {
                dataList.set(i, updatedCity);
                ((ArrayAdapter) cityList.getAdapter()).notifyDataSetChanged();
                return;
            }
        }
    }
}