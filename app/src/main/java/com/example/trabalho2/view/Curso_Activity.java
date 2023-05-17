package com.example.trabalho2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.trabalho2.R;
import com.example.trabalho2.dao.AlunoDao;
import com.example.trabalho2.dao.CursoDao;
import com.example.trabalho2.database.LocalDatabase;

import com.example.trabalho2.entity.Curso;
import com.google.android.material.textfield.TextInputEditText;

public class Curso_Activity extends AppCompatActivity {


    Curso curso;
    Button btn_salvar, btn_voltar;
    EditText input_nomeCurso, input_cargaHorariaCurso, codCurso;
    private LocalDatabase db;

    ToggleButton toggleButton;
    boolean codId = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);


        //Conectando os componentes com o código JAVA
        btn_salvar = findViewById(R.id.btn_salvarCurso);
        btn_voltar = findViewById(R.id.buttonVoltarC);
        codCurso = findViewById(R.id.codigoCurso);
        toggleButton = findViewById(R.id.toggleButton);

        //conecta as caixas de texto para recuperar os valores digitados
        input_nomeCurso = findViewById(R.id.input_nomeCurso);
        input_cargaHorariaCurso = findViewById(R.id.input_cargaHorariaCurso);


        //Conectando o Banco De Dados
        db = LocalDatabase.getDataBase(getApplicationContext());


        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    codCurso.setVisibility(View.VISIBLE);
                    codId = true;
                } else {
                    codCurso.setVisibility(View.INVISIBLE);
                    codId = false;
                }
            }
        });
        curso = new Curso();

        //função que será executada quando clicar em SALVAR
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verificaPreenchimento()) {
                    String dadosDigitados = input_nomeCurso.getText().toString().trim();
                    curso.setNomeCurso(dadosDigitados);
                    dadosDigitados = input_cargaHorariaCurso.getText().toString().trim();
                    curso.setQtdHoras(Integer.parseInt(dadosDigitados));
                    if (codId) {
                        int codDigitado = Integer.parseInt(codCurso.getText().toString());
                        curso.setCursoId(codDigitado);
                        db.cursoDao().salva(curso);
                        finish();
                    } else {
                        db.cursoDao().insertAll(curso);
                        Toast.makeText(getApplicationContext(), "Dados Salvos com Sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }



                } else {
                    //Mensagem para o usuário completar corretamente todos os campos necessários
                    //para conseguir se cadastrar no app
                    Toast.makeText(getApplicationContext(), "Erro no preenchimento" +
                            "dos campos", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //função que será executada quando clicar no botão Voltar
        //Desempilha a activity da pilha
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Função que verifica se as caixas de textos foram preenchidas corretamente pelo usuário
    //Essa função verifica
    // 1° if -> Se a caixa de texto Nome do Curso foi preenchido
    // 2° if -> Se a caixa de texto Carga Horária do Curso foi preenchido
    public boolean verificaPreenchimento() {
        String texto = input_nomeCurso.getText().toString().trim();
        if (texto.equals("") || texto.isEmpty()) {
            return false;
        }
        texto = input_cargaHorariaCurso.getText().toString().trim();
        if (texto.equals("") || texto.isEmpty()) {
            return false;
        }
        return true;
    }


}