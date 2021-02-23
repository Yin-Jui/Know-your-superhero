package com.example.knowyoursuperhero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;
import com.squareup.picasso.Picasso;

import java.util.List;

public class superHeroAdapter extends RecyclerView.Adapter<superHeroAdapter.ViewHolder> {

    private List<HeroInfo> supData;

    public superHeroAdapter(List<HeroInfo> supData) {
        this.supData = supData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView supImage;
        TextView info;
        TextView supName;
        TextView supGender;
        TextView supRace;
        TextView supHeight;
        TextView supWeight;
        TextView supEyeColor;
        TextView supHairColor;
        TextView powerstat;
        TextView intelli;
        TextView strength;
        TextView speed;
        TextView durability;
        TextView power;
        TextView combat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.info_id);
            supName = itemView.findViewById(R.id.name_id);
            supGender = itemView.findViewById(R.id.supGender_id);
            supRace = itemView.findViewById(R.id.race_id);
            supImage = itemView.findViewById(R.id.supImage);
            supHeight = itemView.findViewById(R.id.height_id);
            supWeight = itemView.findViewById(R.id.weight_id);
            supEyeColor = itemView.findViewById(R.id.eyeColor_id);
            supHairColor = itemView.findViewById(R.id.hairColor_id);

            powerstat = itemView.findViewById(R.id.powerstats_id);
            intelli = itemView.findViewById(R.id.intelli_id);
            strength = itemView.findViewById(R.id.strength_id);
            speed = itemView.findViewById(R.id.speed_id);
            durability = itemView.findViewById(R.id.durability_id);
            power = itemView.findViewById(R.id.power_id);
            combat = itemView.findViewById(R.id.combat_id);
        }
    }

    @NonNull
    @Override
    public superHeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hero_base, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(supData.get(position).getValueImageURL()).into(holder.supImage);
        holder.info.setText("Info");
        holder.supName.setText(supData.get(position).getName());
        holder.supGender.setText("Gender: " + supData.get(position).getValueAppearnace("gender"));
        holder.supRace.setText("Race: " + supData.get(position).getValueAppearnace("race"));
        holder.supHeight.setText("Height: " + supData.get(position).getHeightAppearnace("height"));
        holder.supWeight.setText("Weight: " + supData.get(position).getHeightAppearnace("weight"));
        holder.supEyeColor.setText("Eye color: " +supData.get(position).getValueAppearnace("eye-color"));
        holder.supHairColor.setText("Hair color: " + supData.get(position).getValueAppearnace("hair-color"));
        holder.powerstat.setText("Power Stats");

        holder.intelli.setText("intelligence: " + supData.get(position).getValuePowerstat("intelligence"));
        holder.strength.setText("strength: " + supData.get(position).getValuePowerstat("strength"));
        holder.speed.setText("speed: " + supData.get(position).getValuePowerstat("speed"));
        holder.durability.setText("durability: " + supData.get(position).getValuePowerstat("durability"));
        holder.power.setText("power: " + supData.get(position).getValuePowerstat("power"));
        holder.combat.setText("combat: " + supData.get(position).getValuePowerstat("combat"));

    }

    @Override
    public int getItemCount() {
        return supData.size();
    }
}
