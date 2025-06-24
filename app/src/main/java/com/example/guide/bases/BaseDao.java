package com.example.guide.bases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BaseDao {
    @Insert
    void insert(Base base);

    @Query("SELECT * FROM bases")
    LiveData<List<Base>> getAll();

    @Query("DELETE FROM bases")
    void deleteAll();
}
