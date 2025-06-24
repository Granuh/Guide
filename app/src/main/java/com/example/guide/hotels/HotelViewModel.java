package com.example.guide.hotels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.guide.api.ApiService;
import com.example.guide.api.RetrofitClient;
import com.example.guide.room.AppDatabase;

import java.util.List;

public class HotelViewModel extends AndroidViewModel {
    private HotelRepository hotelRepository;
    private LiveData<List<Hotel>> hotels;

    public HotelViewModel(@NonNull Application application) {
        super(application);

        ApiService apiService = RetrofitClient.getApi();    // Инициализация Retrofit
        HotelDao hotelDao = AppDatabase.getInstance(application).hotelDao();
        hotelRepository = new HotelRepository(apiService, hotelDao);

        hotels = hotelRepository.getHotelsFromApi();
    }

    public LiveData<List<Hotel>> getHotel() {
        return hotels;
    }
}
