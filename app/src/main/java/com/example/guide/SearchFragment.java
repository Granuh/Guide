package com.example.guide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.guide.api.RetrofitClient;
import com.example.guide.bases.Base;
import com.example.guide.bases.BaseAdapter;
import com.example.guide.bases.BaseRepository;
import com.example.guide.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private BaseAdapter baseAdapter;
    private List<Base> allBases = new ArrayList<>();
    private List<Base> filteredList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView_3);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        baseAdapter = new BaseAdapter();
        recyclerView.setAdapter(baseAdapter);

        // Загружаем
        loadAllPlaces();

        // Настройка поиска
        SearchView searchView = view.findViewById(R.id.searchView_3);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void loadAllPlaces() {
        BaseRepository baseRepository = new BaseRepository(
                RetrofitClient.getApi(),
                AppDatabase.getInstance(requireContext()).baseDao()
        );

        LiveData<List<Base>> bases = baseRepository.getBases();
        bases.observe(getViewLifecycleOwner(), baseList -> {
            if (baseList != null && !baseList.isEmpty()) {
                baseAdapter.submitList(baseList);
            }
        });

        bases.observe(getViewLifecycleOwner(), baseList -> {
            if (baseList != null && !baseList.isEmpty()) {
                allBases.clear();
                allBases.addAll(baseList);
                baseAdapter.submitList(allBases);
            }
        });
    }

    private void filter(String text) {
        filteredList.clear();
        for (Base base : allBases) {
            if (base.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(base);
            }
        }
        baseAdapter.submitList(filteredList);
    }
}