package com.app.carsaathi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.carsaathi.CarDetailsActivity;
import com.app.carsaathi.Pojo.StoreItem;
import com.app.carsaathi.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
 ArrayList<StoreItem> item;
    Context context;
    public CarAdapter(Context context, ArrayList<StoreItem> item){
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carlist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
         StoreItem model = item.get(position);
        Glide.with(holder.itemView.getContext()).load(model.getImage()).error(R.drawable.error).placeholder(R.drawable.placeholder).into(holder.ivCarImage);
        holder.tvCarName.setText(model.getCarName());
        holder.tvPrice.setText("₹" + model.getPrice());
        holder.tvCarInfo.setText(model.getType());
        holder.tvMileage.setText(model.getKm());
        holder.tvDistance.setText(model.getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CarDetailsActivity.class);
                i.putExtra("id",model.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCarImage;
        TextView tvCarName,tvPrice,tvCarInfo,tvMileage,tvDistance;

        public ViewHolder(@NonNull View view) {
            super(view);
            ivCarImage = view.findViewById(R.id.ivCarImage);
            tvCarName = view.findViewById(R.id.tvCarName);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvCarInfo = view.findViewById(R.id.tvCarInfo);
            tvMileage = view.findViewById(R.id.tvMileage);
            tvDistance = view.findViewById(R.id.tvDistance);

        }
    }
}
