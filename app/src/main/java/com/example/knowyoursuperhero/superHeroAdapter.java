package com.example.knowyoursuperhero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class superHeroAdapter extends RecyclerView.Adapter<superHeroAdapter.ViewHolder> {

    private List<HeroInfo> supData;

    public superHeroAdapter(List<HeroInfo> supData) {
        this.supData = supData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView supName_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            supName_id = itemView.findViewById(R.id.supName_id);
        }
    }

    @NonNull
    @Override
    public superHeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hero_base, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull superHeroAdapter.ViewHolder holder, int position) {
        holder.supName_id.setText(supData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return supData.size();
    }
}
