package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.trabalho2.R;
import com.example.trabalho2.database.LocalDatabase;
import com.example.trabalho2.entity.Aluno;
import com.example.trabalho2.entity.Curso;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class Aluno_Activity extends AppCompatActivity {

    Aluno aluno;
    Button btn_salvar, btn_voltar, btn_cadastrarCurso;
    private LocalDatabase db;
    Spinner spinner;
    TextInputEditText input_nomeAluno, input_emailAluno, input_telefoneAluno;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        //Conectando os componentes com o código JAVA
        input_nomeAluno = findViewById(R.id.input_nomeAluno);
        input_emailAluno = findViewById(R.id.input_emailAluno);
        input_telefoneAluno = findViewById(R.id.input_telefoneAluno);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_voltar = findViewById(R.id.btn_home);
        btn_cadastrarCurso = findViewById(R.id.btn_cadastrarCurso);




        //Conexão com o Banco De Dados
        db = LocalDatabase.getDataBase(getApplicationContext());


        //Recuperar todos os cursos disponíveis e passar para uma lista
        List<Curso> cursos = db.cursoDao().getAll();

        //Cria um adaptador passando a lista de cursos para ser visualizada no spinner
        ArrayAdapter<Curso> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,cursos);


        //SPINNER mostrar todos os cursos disponíveis para ser escolhido
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);



        //Adicionar o clique na lista
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String valorSelecionado = spinner.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), valorSelecionado, Toast.LENGTH_LONG).show();
                Curso cursoSelecionado = (Curso) parent.getItemAtPosition(position);
                aluno.setCursiId(cursoSelecionado.getCursoId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Selecione um CURSO disponível", Toast.LENGTH_LONG).show();
                //NENHUM ITEM SELECIONADO
            }
        });



        aluno = new Aluno();
        //Essa função quando clicada Salva os dados no BANCO DE DADOS
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner.getSelectedItemPosition() != -1){
                    if (verificaPreenchimento()) {
                        String dadosDigitados = input_emailAluno.getText().toString().trim();
                        aluno.setEmailAluno(dadosDigitados);
                        dadosDigitados = input_nomeAluno.getText().toString().trim();
                        aluno.setNomeAluno(dadosDigitados);
                        dadosDigitados = input_telefoneAluno.getText().toString().trim();
                        aluno.setTelefoneAluno(dadosDigitados);
                        db.alunoDao().salva(aluno);
                        Toast.makeText(getApplicationContext(), "Dados Salvos com Sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        //Mensagem para o usuário completar corretamente todos os campos necessários
                        //para conseguir se cadastrar no app
                        Toast.makeText(getApplicationContext(), "Erro no preenchimento dos campos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Selecione um CURSO disponível", Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_cadastrarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Curso_Activity.class);
                startActivity(intent);
                finish();
            }
        });




    }



    public boolean verificaPreenchimento() {
        String texto = input_nomeAluno.getText().toString().trim();

        if (texto.equals("") || texto.isEmpty()) {
            return false;
        }
        texto = input_emailAluno.getText().toString().trim();
        if (texto.equals("") || texto.isEmpty()) {
            return false;
        }
        texto = input_telefoneAluno.getText().toString().trim();
        if (texto.equals("") || texto.isEmpty()) {
            return false;
        }
        return true;
    }
}