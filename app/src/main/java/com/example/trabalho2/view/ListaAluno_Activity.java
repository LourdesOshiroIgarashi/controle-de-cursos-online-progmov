package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.trabalho2.R;
import com.example.trabalho2.dao.AlunoDao;
import com.example.trabalho2.database.LocalDatabase;
import com.example.trabalho2.entity.Aluno;

import java.util.List;

public class ListaAluno_Activity extends AppCompatActivity {
    private LocalDatabase db;
    Button btnVoltar, btnExcluir, btn_editarCurso;
    Spinner spinner;

    int idAlunoSelecionadoParaExcluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);


        //Conexão com o banco de dados
        db = LocalDatabase.getDataBase(getApplicationContext());


        //recupera do banco de dados todos os alunos cadastrados e devolve em uma lista
        //List<Aluno> alunos = db.alunoDao().getAlunoCompleto();

        List<String> aluno = db.alunoDao().nomeAlunoL();


        /*
        List<String> alunosFormatados = new ArrayList<>();
         for (AlunoCursoDao alunoX : aluno) {
            AlunoCursoDao alunoFormatado = alunoX.getNomeAluno() + " - " + alunoX.getNomeCursoAluno();
            aluno.add(alunoFormatado);
        }*/

        //cria um adaptador para poder visualizar os alunos de uma forma mais amigável e personalizada
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, aluno);


        //Conecta o spinner com o código JAVA
        spinner = findViewById(R.id.spinnerAluno);

        //Adiciona no visualizador do spinner passando o adaptador para ser visualizado no spinner
        spinner.setAdapter(adapter);

        //Conecta o botão de voltar, que desempilha a activity na pilha
        btnVoltar = findViewById(R.id.btnVoltarVA);
        btnExcluir = findViewById(R.id.btn_excluirAluno);
        btn_editarCurso = findViewById(R.id.btn_editarCurso);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String alunoSelecionado = (String) parent.getItemAtPosition(position);
                System.out.println("ALUNOSELECIONADO"+ alunoSelecionado);
                AlunoDao dao = db.alunoDao();
                idAlunoSelecionadoParaExcluir = dao.getIdAluno(alunoSelecionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_editarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), EditarAluno.class);
                Bundle bundle = new Bundle();
                bundle.putString("idAluno", String.valueOf(idAlunoSelecionadoParaExcluir));
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
                AlunoDao dao = db.alunoDao();
                dao.deleteAlunoById(idAlunoSelecionadoParaExcluir);
                recreate();
            }
        });
    }
}