package com.example.trabalho2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2.entity.Aluno;
import com.example.trabalho2.entity.Curso;

import java.util.List;

@Dao
public interface CursoDao {

    //buscar um curso com o ID
    @Query("SELECT * FROM Curso WHERE cursoId = :id LIMIT 1")
    Curso getId(int id);

    @Query("SELECT nomeCurso FROM Curso WHERE cursoId = :id LIMIT 1")
    String getNomeCurso(int id);

    //buscar todos os cursos
    @Query("SELECT * FROM Curso")
    List<Curso> getAll();

    @Query("SELECT nomeCurso, qtdHoras, cursoId FROM Curso")
    List<Curso> getCursoCompleto();


    //inserir uma lista de cursos
    @Insert
    void insertAll(Curso... cursos);


    //salvar um curso no banco de dados
    @Insert
    void salva(Curso curso);

    //atualizar um curso no banco de dados
    @Update
    void update(Curso cursos);


    //deletar um curso no banco de dados
    @Delete
    void delete(Curso cursos);

    @Query("DELETE FROM Curso WHERE cursoId = :id")
    void deleteCursoById(int id);

    @Query("SELECT * FROM Curso")
    List<Curso> todosCursos();

    @Query("SELECT * FROM Curso WHERE cursoId = :id LIMIT 1")
    Curso instancia(int id);

    @Query("UPDATE curso SET nomeCurso = :nome, qtdHoras = :horas WHERE cursoId = :id")
    void updateCurso(int id, String nome, int horas);



}
