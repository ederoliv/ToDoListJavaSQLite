package com.example.todolistjavasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateTaskActivity extends AppCompatActivity {

    EditText editTextName;
    Button button;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        editTextName = findViewById(R.id.editTextName); // alternativa ao editTextName = (EditText) findViewById(R.id.editTextName); deixando o código menos verboso.
        button = findViewById(R.id.buttonNewTask);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { register(); }
        });

    }

    public void register(){

        if(!TextUtils.isEmpty(editTextName.getText().toString())){
            try {
                database = openOrCreateDatabase("dbTodoList", MODE_PRIVATE, null);
                String query = "INSERT INTO taskTable (name) VALUES (?)"; // Insere na tabela taskTable, na coluna name os valores (?) do bind
                SQLiteStatement statement = database.compileStatement(query);
                statement.bindString(1, editTextName.getText().toString()); //faz o bind do texto contido da editText com a query do sql
                statement.executeInsert(); //executa o insert

                database.close(); // fecha a transação
                finish(); // fecha a activity

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}