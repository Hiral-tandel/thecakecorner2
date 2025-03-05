package com.example.thecakecorner.adminactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.thecakecorner.R;
import com.example.thecakecorner.adapters.adapter;
import com.example.thecakecorner.databse.dbhelper;
import com.example.thecakecorner.models.modal;

import java.util.ArrayList;

public class seeuser extends AppCompatActivity {

    private ArrayList<modal> courseModalArrayList;
    private dbhelper dbHandler;
    private adapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeuser);

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new dbhelper(seeuser.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array list to our adapter class.
        courseRVAdapter = new adapter(courseModalArrayList,getApplicationContext());
        coursesRV = findViewById(R.id.rlte);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}
