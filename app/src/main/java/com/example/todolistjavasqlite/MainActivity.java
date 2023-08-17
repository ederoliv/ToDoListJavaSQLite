package com.example.todolistjavasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

        listViewData = findViewById(R.id.listViewData); // alternativa ao uso de listViewData = (ListView) findViewById(R.id.listViewData); para um código menos verboso
        buttonNewTask = findViewById(R.id.buttonNewTask);

        buttonNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewTaskScreen();
            }
        });

        createDatabase();
        listData();

    }

    public void createDatabase(){

        try {
            database = openOrCreateDatabase("dbTodoList", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS taskTable(" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT" + // cria uma coluna ID que seria a chave primaria que se autoincrementa
                    ", name VARCHAR" +                        // coluna com o nome da task
                    ", description VARCHAR)");                // coluna com a descrição da task

            database.close(); // fecha a transação

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listData(){

        try {
            arrayIDs = new ArrayList<>();
            database = openOrCreateDatabase("dbTodoList", MODE_PRIVATE, null);

            Cursor cursor =  database.rawQuery("SELECT * FROM taskTable", null);

            ArrayList<String> rows = new ArrayList<>();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    rows
            );

            listViewData.setAdapter(adapter);
            cursor.moveToFirst();

            while (cursor!=null){
                rows.add(cursor.getString(1)); //array com as strings da coluna: name
                arrayIDs.add(cursor.getInt(0));//array com as chaves primarias, coluna:id

                cursor.moveToNext();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // método que carrega a tela para criação de uma nova tarefa
    public void openNewTaskScreen(){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}