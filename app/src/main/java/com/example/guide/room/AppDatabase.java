package com.example.guide.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.guide.bases.Base;
import com.example.guide.bases.BaseDao;
import com.example.guide.hotels.Hotel;
import com.example.guide.hotels.HotelDao;

@Database(entities = {Base.class, Hotel.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BaseDao baseDao();
    public abstract HotelDao hotelDao();
    //public abstract Sanatorium sanatoriumDao();
    //public abstract User userDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "guide_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
