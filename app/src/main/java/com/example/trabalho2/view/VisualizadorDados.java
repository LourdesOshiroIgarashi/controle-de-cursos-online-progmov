package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.trabalho2.R;
import com.example.trabalho2.database.LocalDatabase;
import com.example.trabalho2.entity.Aluno;
import com.example.trabalho2.entity.Curso;

import java.util.List;

public class VisualizadorDados extends AppCompatActivity {

    ListView listViewAluno, listViewCurso;
    Button btnVoltar;
    private LocalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador_dados);

        listViewAluno = findViewById(R.id.listViewAluno);
        listViewCurso = findViewById(R.id.listViewCurso);

        db = LocalDatabase.getDataBase(getApplicationContext());


        List<Aluno> alunos = db.alunoDao().todosAlunos();


        ArrayAdapter<Aluno> adapterAluno = new ArrayAdapter<Aluno>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                alunos
        );

        List<Curso> cursos = db.cursoDao().todosCursos();

        ArrayAdapter<Curso> adapterCurso = new ArrayAdapter<Curso>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                cursos
        );

        listViewAluno.setAdapter(adapterAluno);
        listViewCurso.setAdapter(adapterCurso);

        btnVoltar = findViewById(R.id.btnVoltarVA);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}