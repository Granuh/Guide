package com.example.guide.hotels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guide.R;
import com.example.guide.bases.Base;
import com.example.guide.bases.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotels = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Hotel hotel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Hotel> hotelList){
        if (hotelList != null && !hotelList.isEmpty()) {
            this.hotels.clear();
            this.hotels.addAll(hotelList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelAdapter.HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.textViewTitle.setText(hotel.getTitle());
        holder.textViewDescription.setText(hotel.getDescription());

        Glide.with(holder.imageView.getContext())
                .load(hotel.getImageUrl())
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(hotel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        ImageView imageView;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle_2);
            textViewDescription = itemView.findViewById(R.id.textViewDescription_2);
            imageView = itemView.findViewById(R.id.imageView_2);
        }
    }
}
