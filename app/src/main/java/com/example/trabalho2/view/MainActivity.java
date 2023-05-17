package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trabalho2.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_crud_aluno, btn_crud_curso, btn_view_aluno, btn_view_curso, viewDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Conexão dos botões com o código JAVA
        btn_crud_aluno = findViewById(R.id.btn_crud_aluno);
        btn_crud_curso = findViewById(R.id.btn_crud_curso);
        btn_view_aluno = findViewById(R.id.btn_view_aluno);
        btn_view_curso = findViewById(R.id.btn_view_curso);
        viewDados = findViewById(R.id.viewDados);


        //Método ouvinte para ENTRAR na activity ALUNO_ACTIVITY >> CADASTRO
        btn_crud_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(getApplicationContext(), Aluno_Activity.class);
                startActivity(intentA);
            }
        });
        //Método ouvinte para ENTRAR na activity CURSO_ACTIVITY >> CADASTRO
        btn_crud_curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(getApplicationContext(), Curso_Activity.class);
                startActivity(intentA);
            }
        });

        //Método ouvinte para ENTRAR na activity LISTA_ALUNO_ACTIVITY >> VISUALIZAR TODOS OS ALUNOS
        //CADASTRADOS
        btn_view_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(getApplicationContext(), ListaAluno_Activity.class);
                startActivity(intentB);
            }
        });

        //Método ouvinte para ENTRAR na activity LISTA_CURSO_ACTIVITY >> VISUALIZAR TODOS OS CURSOS
        //CADASTRADOS
        btn_view_curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(getApplicationContext(), ListaCurso_Activity.class);
                startActivity(intentA);
            }
        });

        viewDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(getApplicationContext(), VisualizadorDados.class);
                startActivity(intentA);
            }
        });
    }
}