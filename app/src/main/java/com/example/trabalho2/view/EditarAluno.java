package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.trabalho2.R;
import com.example.trabalho2.dao.AlunoDao;
import com.example.trabalho2.database.LocalDatabase;
import com.example.trabalho2.entity.Aluno;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class EditarAluno extends AppCompatActivity {


    Button btnHome, btnSalvar;
    TextInputEditText nome, email, telefone;
    //Spinner sp;
    LocalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_aluno);


        //conectando o banco de dados
        db = LocalDatabase.getDataBase(getApplicationContext());

        //Conectando componentes com o código JAVA
        btnHome = findViewById(R.id.btn_home);
        btnSalvar = findViewById(R.id.btn_salvar);
        nome = findViewById(R.id.input_nomeAluno);
        email = findViewById(R.id.input_emailAluno);
        telefone = findViewById(R.id.input_telefoneAluno);
        //sp = findViewById(R.id.spinner);

        /*
        //recupera do banco de dados todos os alunos cadastrados e devolve em uma lista
        List<String> aluno = db.alunoDao().nomeAlunoL();

        //cria um adaptador para poder visualizar os alunos de uma forma mais amigável e personalizada
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, aluno);

        sp.setAdapter(adapter);

         */

        Bundle bundle = getIntent().getExtras();
        String valor = bundle.getString("idAluno");
        int idB = Integer.parseInt(valor);
        Aluno dao = db.alunoDao().instancia(idB);

        nome.setText(dao.getNomeAluno());
        email.setText(dao.getEmailAluno());
        telefone.setText(dao.getTelefoneAluno());




        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeA = nome.getText().toString();
                String emailA = email.getText().toString();
                String telA = telefone.getText().toString();

                int idA = Integer.parseInt(valor);
                db.alunoDao().updateAluno(idA, nomeA, emailA, telA);
                finish();
            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}