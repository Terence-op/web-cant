package food.app.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastViewHolder> {
    private Context context;
    private List<BreakfastDelux> brUpdate;
    public BreakfastAdapter(Context context,List<BreakfastDelux> brUpdate){
        this.context = context;
        this.brUpdate = brUpdate;
    }
    @NonNull
    @Override
    public BreakfastAdapter.BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_single_item,parent,false);
        return new BreakfastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastAdapter.BreakfastViewHolder holder, int position) {
        BreakfastDelux  uploadCurrent = brUpdate.get(position);
        holder.NameOfFood.setText(uploadCurrent.getName());
        holder.priceOfFood.setText(uploadCurrent.getPrice());
        Picasso.with(context)
                .load(uploadCurrent.getUri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageUri);
    }

    @Override
    public int getItemCount() {
        return brUpdate.size();
    }

    public class BreakfastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView NameOfFood;
        TextView priceOfFood;
        ImageView imageUri;
        public BreakfastViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            NameOfFood= itemView.findViewById(R.id.name);
            priceOfFood = itemView.findViewById(R.id.price);
            imageUri = itemView.findViewById(R.id.imageProduct);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            BreakfastDelux BR = brUpdate.get(position);
            Toast.makeText(context, BR.getPrice(), Toast.LENGTH_SHORT).show();
        }
    }
}
