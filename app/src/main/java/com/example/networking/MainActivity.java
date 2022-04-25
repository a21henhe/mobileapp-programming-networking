package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";
    private ArrayList<Mountain> listOfMountains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfMountains = new ArrayList<>(Arrays.asList(
                new Mountain("berg1", "sverige", 100),
                new Mountain("berg2", "sverige", 100),
                new Mountain("berg3", "sverige", 100)
        ));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, listOfMountains, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain item) {
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        new JsonFile(this, this).execute(JSON_FILE);

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }

}
