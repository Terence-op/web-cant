package food.app.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Food> mUploads;
    private OnFoofItemSelected mFoodSelected;


    public NewAdapter(Context context, List<Food> uploads, OnFoofItemSelected foodSelected) {
        mContext = context;
        mUploads = uploads;
        mFoodSelected = foodSelected;
    }

    @NonNull
    @Override
    public NewAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.food_single_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.ImageViewHolder holder, int position) {
        Food uploadCurrent = mUploads.get(position);
        holder.NameOfFood.setText(uploadCurrent.getName());
        holder.priceOfFood.setText(uploadCurrent.getPrice());
        Picasso.with(mContext)
                .load(uploadCurrent.getUri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageUri);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView NameOfFood;
        TextView priceOfFood;
        ImageView imageUri;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            //registering the onClick
            itemView.setOnClickListener(this);

            NameOfFood = itemView.findViewById(R.id.name);
            priceOfFood = itemView.findViewById(R.id.price);
            imageUri = itemView.findViewById(R.id.imageProduct);
        }


        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Food food = mUploads.get(position);
            food.setSelected(!food.isSelected());
            itemView.setBackgroundColor(food.isSelected() ? itemView.getResources().getColor(R.color.colorPrimary) : 0x00000000);
            mFoodSelected.OnOrder(food);

        }
    }
}


