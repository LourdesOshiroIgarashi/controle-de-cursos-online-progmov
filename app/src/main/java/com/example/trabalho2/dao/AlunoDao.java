package com.example.trabalho2.dao;

import android.icu.text.SymbolTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2.entity.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {

    @Query("SELECT * FROM Aluno WHERE alunoId = :id LIMIT 1")
    Aluno getMarca(int id);

    ///obter todos os alunos da tabela em forma de lista
    @Query("SELECT * FROM Aluno")
    List<Aluno> getAll();



    @Query("SELECT nomeAluno FROM Aluno")
    List<String> nomeAlunoL();

    //inserir no banco de dados todos os alunos que foram passados por forma de lista/array
    @Insert
    void insertAll(Aluno... alunos);

    @Query("SELECT * FROM Aluno")
    List<Aluno> getAlunoCompleto();


    //inserir no banco de dados um único aluno
    @Insert
    void salva(Aluno aluno);


    //atualizar um aluno no banco de dados
    @Update
    void update(Aluno alunos);


    //deletar uma tupla do banco de dados
    @Delete
    void delete(Aluno alunos);

    @Query("DELETE FROM Aluno WHERE alunoId = :id")
    void deleteAlunoById(int id);

    @Query("SELECT AlunoId FROM Aluno WHERE nomeAluno = :nome LIMIT 1")
    int getIdAluno(String nome);

    @Query("UPDATE aluno SET nomeAluno = :nome, emailAluno = :email, telefoneAluno = :tel WHERE alunoId = :id")
    void updateAluno(int id, String nome, String email, String tel);


    @Query("SELECT * FROM Aluno WHERE alunoId = :id LIMIT 1")
    Aluno instancia(int id);
}
