package com.example.guide.hotels;

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

public class HotelRepository {
    private ApiService apiService;
    private HotelDao hotelDao;
    private FirebaseFirestore firebaseFirestore;

    public HotelRepository(ApiService api, HotelDao hotelDao) {
        this.apiService = api;
        this.hotelDao = hotelDao;
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LiveData<List<Hotel>> getHotels(){
        MutableLiveData<List<Hotel>> result = new MutableLiveData<>();

        hotelDao.getAll().observeForever(hotels -> {
            if (hotels != null && !hotels.isEmpty()) {
                result.setValue(hotels); // Данные из Room
            } else {
                fetchFromNetwork(result);
            }
        });

        return  result;
    }

    private void fetchFromNetwork(MutableLiveData<List<Hotel>> result) {
        apiService.getHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hotel>> call, @NonNull Response<List<Hotel>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Hotel> hotels = response.body();

                    hotelDao.deleteAll(); // Удаляем старые данные

                    for (Hotel hotel : hotels) {
                        hotelDao.insert(hotel);
                    }

                    saveToFirebase(hotels);
                    result.postValue(hotels);
                } else {
                    Log.d("HotelRepository", "API вернул пустой ответ");
                    result.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hotel>> call, @NonNull Throwable t) {
                fetchFromFirebase(result);
            }
        });
    }

    private void saveToFirebase(List<Hotel> hotelList) {
        CollectionReference ref = firebaseFirestore.collection("hotels");
        for (Hotel hotel : hotelList) {
            ref.add(hotel);
        }
    }

    private void fetchFromFirebase(MutableLiveData<List<Hotel>> result) {
        firebaseFirestore.collection("hotels")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Hotel> remoteList = queryDocumentSnapshots.toObjects(Hotel.class);
                    result.postValue(remoteList);
                })
                .addOnFailureListener(e -> result.postValue(null));
    }

    public LiveData<List<Hotel>> getHotelsFromApi() {
        MutableLiveData<List<Hotel>> result = new MutableLiveData<>();

        apiService.getHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hotel>> call, @NonNull Response<List<Hotel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Hotel> hotelList = response.body();

                    hotelDao.deleteAll();

                    for (Hotel hotel : hotelList) {
                        hotelDao.insert(hotel);
                    }

                    saveToFirebase(hotelList);   // Сохраняем в Firebase
                    result.postValue(hotelList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hotel>> call, @NonNull Throwable t) {
                result.postValue(null);
            }
        });

        return result;
    }
}
