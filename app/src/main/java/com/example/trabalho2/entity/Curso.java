package com.example.trabalho2.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Curso {

    @PrimaryKey(autoGenerate = true)
    int cursoId;
    String nomeCurso;
    int qtdHoras;

    public Curso(){}


    public Curso(int cursoId, String nomeCurso, int qtdHoras) {
        this.cursoId = cursoId;
        this.nomeCurso = nomeCurso;
        this.qtdHoras = qtdHoras;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    @NonNull
    @Override
    public String toString() {
        return getNomeCurso();
    }
}
