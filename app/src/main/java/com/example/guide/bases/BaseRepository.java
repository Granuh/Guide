package com.example.guide.bases;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.guide.api.ApiService;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseRepository {
    private ApiService apiService;
    private BaseDao baseDao;
    private FirebaseFirestore firebaseFirestore;

    public BaseRepository(ApiService api, BaseDao baseDao) {
        this.apiService = api;
        this.baseDao = baseDao;
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LiveData<List<Base>> getBases(){
        MutableLiveData<List<Base>> result = new MutableLiveData<>();

        baseDao.getAll().observeForever(bases -> {
            if (bases != null && !bases.isEmpty()) {
                result.setValue(bases); // Данные из Room
            } else {
                fetchFromNetwork(result);
            }
        });

        return  result;
    }

    private void fetchFromNetwork(MutableLiveData<List<Base>> result) {
        apiService.getBases().enqueue(new Callback<List<Base>>() {
            @Override
            public void onResponse(@NonNull Call<List<Base>> call, @NonNull Response<List<Base>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Base> bases = response.body();

                    baseDao.deleteAll(); // Удаляем старые данные

                    for (Base base : bases) {
                        baseDao.insert(base);
                    }

                    saveToFirebase(bases);
                    result.postValue(bases);
                } else {
                    Log.d("BaseRepository", "API вернул пустой ответ");
                    result.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Base>> call, @NonNull Throwable t) {
                fetchFromFirebase(result);
            }
        });
    }

    private void saveToFirebase(List<Base> baseList) {
        CollectionReference ref = firebaseFirestore.collection("bases");
        for (Base base : baseList) {
            ref.add(base);
        }
    }

    private void fetchFromFirebase(MutableLiveData<List<Base>> result) {
        firebaseFirestore.collection("bases")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Base> remoteList = queryDocumentSnapshots.toObjects(Base.class);
                    result.postValue(remoteList);
                })
                .addOnFailureListener(e -> result.postValue(null));
    }

    public LiveData<List<Base>> getBasesFromApi() {
        MutableLiveData<List<Base>> result = new MutableLiveData<>();

        apiService.getBases().enqueue(new Callback<List<Base>>() {
            @Override
            public void onResponse(@NonNull Call<List<Base>> call, @NonNull Response<List<Base>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Base> baseList = response.body();

                    baseDao.deleteAll();

                    for (Base base : baseList) {
                        baseDao.insert(base);
                    }

                    saveToFirebase(baseList);   // Сохраняем в Firebase
                    result.postValue(baseList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Base>> call, @NonNull Throwable t) {
                result.postValue(null);
            }
        });

        return result;
    }
}
