package com.example.guide.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guide.R;
import com.example.guide.api.RetrofitClient;
import com.example.guide.bases.Base;
import com.example.guide.bases.BaseAdapter;
import com.example.guide.bases.BaseRepository;
import com.example.guide.bases.BaseViewModel;
import com.example.guide.hotels.Hotel;
import com.example.guide.hotels.HotelAdapter;
import com.example.guide.hotels.HotelRepository;
import com.example.guide.hotels.HotelViewModel;
import com.example.guide.room.AppDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private BaseAdapter baseAdapter;
    private HotelAdapter hotelAdapter;
    private BaseViewModel baseViewModel;
    private HotelViewModel hotelViewModel;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView_2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        baseAdapter = new BaseAdapter();

        recyclerView_2 = view.findViewById(R.id.recyclerView_2);
        recyclerView_2.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        hotelAdapter = new HotelAdapter();

        BaseRepository baseRepository = new BaseRepository(
                RetrofitClient.getApi(),
                AppDatabase.getInstance(requireContext()).baseDao()
        );

        LiveData<List<Base>> bases = baseRepository.getBases();
        bases.observe(getViewLifecycleOwner(), baseList -> {
            Log.d("HomeFragment", "Размер списка: " + baseList.size());
            Log.d("BaseAdapter", "Количество элементов в адаптере: " + baseAdapter.getItemCount());
            if (baseList != null && !baseList.isEmpty()) {
                baseAdapter.submitList(baseList);
                Log.d("HomeFragment", "Размер списка: " + baseList.size());
                Log.d("BaseAdapter", "Количество элементов в адаптере: " + baseAdapter.getItemCount());
            } else {
                Toast.makeText(requireContext(), "Нет данных", Toast.LENGTH_SHORT).show();
            }
        });

        HotelRepository hotelRepository = new HotelRepository(
                RetrofitClient.getApi(),
                AppDatabase.getInstance(requireContext()).hotelDao()
        );

        LiveData<List<Hotel>> hotels = hotelRepository.getHotels();
        hotels.observe(getViewLifecycleOwner(), hotelsList -> {
            if (hotelsList != null && !hotelsList.isEmpty()) {
                hotelAdapter.submitList(hotelsList);
            } else {
                Toast.makeText(requireContext(), "Нет данных", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(baseAdapter);

        recyclerView_2.setAdapter(hotelAdapter);
    }
}