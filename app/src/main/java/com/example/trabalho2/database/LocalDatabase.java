package com.example.trabalho2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trabalho2.dao.AlunoDao;
import com.example.trabalho2.dao.CursoDao;
import com.example.trabalho2.entity.Aluno;
import com.example.trabalho2.entity.Curso;

@Database(entities = {Aluno.class, Curso.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    //private static LocalDatabase INSTANCE;

    /*public static LocalDatabase getDataBase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class,
                    "CursosOnline").allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
     */
    private static LocalDatabase INSTANCE;
    public static LocalDatabase getDataBase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "CursosOnline").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract AlunoDao alunoDao();

    public abstract CursoDao cursoDao();


}
