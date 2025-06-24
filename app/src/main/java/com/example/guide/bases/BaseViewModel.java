package com.example.guide.bases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.guide.api.ApiService;
import com.example.guide.api.RetrofitClient;
import com.example.guide.room.AppDatabase;

import java.util.List;

public class BaseViewModel extends AndroidViewModel {
    private BaseRepository baseRepository;
    private LiveData<List<Base>> bases;

    public BaseViewModel(@NonNull Application application) {
        super(application);

        ApiService apiService = RetrofitClient.getApi();    // Инициализация Retrofit
        BaseDao baseDao = AppDatabase.getInstance(application).baseDao();
        baseRepository = new BaseRepository(apiService, baseDao);

        bases = baseRepository.getBasesFromApi();
    }

    public LiveData<List<Base>> getBases() {
        return bases;
    }
}
