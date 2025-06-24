package com.example.guide.hotels;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HotelDao {
    @Insert
    void insert(Hotel hotel);

    @Query("SELECT * FROM hotels")
    LiveData<List<Hotel>> getAll();

    @Query("DELETE FROM hotels")
    void deleteAll();
}
