package com.example.guide.sanatoriums;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SanatoriumDao {
    @Insert
    void insert(Sanatorium sanatorium);

    @Query("SELECT * FROM sanatoriums")
    LiveData<List<Sanatorium>> getAll();

    @Query("DELETE FROM sanatoriums")
    void deleteAll();
}
