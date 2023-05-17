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

import java.util.ArrayList;
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


        //Conecta o banco de dados
        db = LocalDatabase.getDataBase(getApplicationContext());


        //Recupera todos os alunos no banco de dados
        List<Aluno> alunos = db.alunoDao().getAlunoCompleto();


        //Cria uma Lista de String para Formatar dados
        List<String> alunosFormatados = new ArrayList<String>();

        //Passa pela lista de alunos
        for (Aluno alunoX : alunos) {

            //pega o cursoId de cada aluno
            int idCurso = alunoX.getCursiId();

            //Recupera o nome do curso passando como argumento o cursoId recuperado no passo acima
            String nomeDoCurso = db.cursoDao().getNomeCurso(idCurso);

            //adiciona em uma String o nome do aluno e o nome do curso
            String alunoFormatado = alunoX.getNomeAluno() + " - " + nomeDoCurso;

            //adiciona na lista a nova String Formatada
            alunosFormatados.add(alunoFormatado);
        }


        //Mostra no List View
        ArrayAdapter<String> adapterAluno = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                alunosFormatados
        );

        listViewAluno.setAdapter(adapterAluno);


        //Recupera todos os cursos no banco de dados
        List<Curso> cursos = db.cursoDao().todosCursos();




        //Cria uma Lista de String para Formatar dados
        List<String> cursosFormatados = new ArrayList<String>();

        //Passa pela lista de curso
        for (Curso cursoX : cursos) {

            //pega o cursoId de cada curso
            int idCurso = cursoX.getCursoId();

            //
            int qtdHoras = cursoX.getQtdHoras();
            String cursoFormatado = cursoX.getNomeCurso() + " - " + qtdHoras + " horas";
            cursosFormatados.add(cursoFormatado);
        }
        ArrayAdapter<String> adapterCurso = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                cursosFormatados
        );




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