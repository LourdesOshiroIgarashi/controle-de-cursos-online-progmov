package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.trabalho2.R;
import com.example.trabalho2.database.LocalDatabase;
import com.example.trabalho2.entity.Aluno;
import com.example.trabalho2.entity.Curso;

public class EditarCurso extends AppCompatActivity {

    EditText input_nomeCurso, input_cargaHorariaCurso;

    Button btn_salvarCurso, buttonVoltarC;



    LocalDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_curso);


        //conectando o banco de dados
        db = LocalDatabase.getDataBase(getApplicationContext());

        //conectar os componentes com o codigo java
        input_cargaHorariaCurso = findViewById(R.id.input_cargaHorariaCurso);
        input_nomeCurso = findViewById(R.id.input_nomeCurso);
        btn_salvarCurso = findViewById(R.id.btn_salvarCurso);
        buttonVoltarC = findViewById(R.id.buttonVoltarC);





        Bundle bundle = getIntent().getExtras();
        String valor = bundle.getString("idCurso");
        int idB = Integer.parseInt(valor);
        Curso dao = db.cursoDao().instancia(idB);


        int horas = dao.getQtdHoras();
        String horasS = String.valueOf(horas);
        input_cargaHorariaCurso.setText(horasS);
        input_nomeCurso.setText(dao.getNomeCurso());








        buttonVoltarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_salvarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeC = input_nomeCurso.getText().toString();
                String ch = input_cargaHorariaCurso.getText().toString();
                int chInt = Integer.parseInt(ch);
                db.cursoDao().updateCurso(idB, nomeC, chInt);
                finish();



            }
        });





    }
}