package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Person> arrayList = new ArrayList<>();
    PersonAdapter personAdapter;
    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler=new DatabaseHandler(this);
        initView();
        EditText editText= findViewById(R.id.editName);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnDel = findViewById(R.id.btnRemove);
        Button btnCan = findViewById(R.id.btnCancle);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ArrayList<Person> filteredList = new ArrayList<>();
                databaseHandler.addPersonName(text);
                filteredList = databaseHandler.getPersonsName();
                personAdapter.filterList(filteredList);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ArrayList<Person> filteredList = new ArrayList<>();
                databaseHandler.deletePeson(text);
                filteredList = databaseHandler.getPersonsName();
                personAdapter.filterList(filteredList);
            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }
    private void createListPre(){
        databaseHandler.addPerson(new Person("Hoai Phong"));
        databaseHandler.addPerson(new Person("Hoai Phong 1"));
        databaseHandler.addPerson(new Person("Hoai Phong 2"));



    }
    private void getList(){
        arrayList=databaseHandler.getPersonsName();
    }
    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        createListPre();
        getList();
        personAdapter = new PersonAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(personAdapter);
    }
}