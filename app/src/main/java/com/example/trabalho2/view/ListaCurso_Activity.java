package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabalho2.R;
import com.example.trabalho2.dao.CursoDao;
import com.example.trabalho2.database.LocalDatabase;
import com.example.trabalho2.entity.Curso;

import java.util.ArrayList;
import java.util.List;

public class ListaCurso_Activity extends AppCompatActivity {


    private LocalDatabase db;
    Button btnVoltar, btnExcluir, btn_editarCurso;
    Spinner spinner;

    int idCursoSelecionadoParaExcluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_curso);


        //conecta o banco de dados
        db = LocalDatabase.getDataBase(getApplicationContext());

        //recupera todos os cursos cadastrados em forma de lista
        List<Curso> cursos = db.cursoDao().getAll();

        //cria um adaptador para poder visualizar os cursos de uma forma mais amigável e personalizada
        ArrayAdapter<Curso> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, cursos);

        //conecta o spinner com o código JAVA
        spinner = findViewById(R.id.spinnerCurso);

        //Adiciona no visualizador do spinner passando o adaptador para ser visualizado os cursos no spinner
        spinner.setAdapter(adapter);

        //conecta o botão de voltar com o código JAVA, que desempilha a activity na pilha
        btnVoltar = findViewById(R.id.btnVoltarLC);
        btnExcluir = findViewById(R.id.btn_excluirCurso);
        btn_editarCurso = findViewById(R.id.btn_editarCurso);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Curso cursoSelecionado = (Curso) parent.getItemAtPosition(position);

                idCursoSelecionadoParaExcluir = cursoSelecionado.getCursoId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Escolha um curso para alguma ação!", Toast.LENGTH_LONG).show();
                //Nenhum Item selecionado
            }
        });
        btn_editarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditarCurso.class);
                Bundle bundle = new Bundle();
                bundle.putString("idCurso", String.valueOf(idCursoSelecionadoParaExcluir));
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CursoDao dao = db.cursoDao();
                dao.deleteCursoById(idCursoSelecionadoParaExcluir);
                recreate();
            }
        });
    }
}