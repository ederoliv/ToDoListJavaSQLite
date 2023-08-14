package com.example.todolistjavasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    public ListView listViewData;
    public Button buttonNewTask;
    public ArrayList<Integer> arrayIDs;
    public Integer idSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewData = (ListView) findViewById(R.id.listViewData);
        buttonNewTask = (Button) findViewById(R.id.buttonNewTask);

        buttonNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewTaskScreen();
            }
        });

    }

    // método que carrega a tela para criação de uma nova tarefa
    public void openNewTaskScreen(){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}