package com.example.guide.bases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guide.R;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {
    private List<Base> bases = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Base base);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Base> baseList){
        if (baseList != null && !baseList.isEmpty()) {
            this.bases.clear();
            this.bases.addAll(baseList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Base base = bases.get(position);
        holder.textViewTitle.setText(base.getTitle());
        holder.textViewDescription.setText(base.getDescription());

        Glide.with(holder.imageView.getContext())
                .load(base.getImageUrl())
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(base);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bases.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        ImageView imageView;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
