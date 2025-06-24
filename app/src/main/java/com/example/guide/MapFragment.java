package com.example.guide;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {

    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Инициализируем конфигурацию OSMDroid
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());

        // Inflate разметки
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Настраиваем MapView
        mapView = view.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK); // бесплатный тайловый сервер
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        // Перемещаем камеру на Алтайский край
        IMapController mapController = mapView.getController();
        mapController.setZoom(8.0);
        GeoPoint startPoint = new GeoPoint(53.3558, 83.7522); // координаты Алтая
        mapController.setCenter(startPoint);

        addBaseMarkers();
    }

    private void addBaseMarkers() {
        List<GeoPoint> points = getAltaicLocations();

        for (GeoPoint point : points) {
            Marker marker = new Marker(mapView);
            marker.setPosition(point);
            marker.setTitle("Достопримечательность");
            marker.setSnippet("Нажмите, чтобы узнать больше");
            marker.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.icon));

            mapView.getOverlays().add(marker);
        }

        mapView.invalidate(); // обновляем карту
    }

    private List<GeoPoint> getAltaicLocations() {
        List<GeoPoint> locations = new ArrayList<>();

        // Etnica, отель
        locations.add(new GeoPoint( 51.9738, 84.6022));

        // Грин-парк «Сосна»
        locations.add(new GeoPoint(51.9725, 84.6030));

        // Загородный комплекс «Гостиный двор»
        locations.add(new GeoPoint(51.887542, 85.838772));

        // Гостиница «Алтай»
        locations.add(new GeoPoint(53.337038, 83.78963));

        // База отдыха «Медная сова»
        locations.add(new GeoPoint(51.304929413486, 82.559066253359));

        return locations;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}