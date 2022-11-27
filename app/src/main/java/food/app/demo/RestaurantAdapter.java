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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private Context context;
    private List<Restuarant> ResUpdate;

    public RestaurantAdapter(Context context,List<Restuarant> ResUpdate){
        this.context = context;
        this.ResUpdate = ResUpdate;
    }
    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_restaurant,parent,false);
        return new RestaurantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        Restuarant  uploadCurrent = ResUpdate.get(position);
        holder.NameOfRestaurant.setText(uploadCurrent.getName());
        holder.Distance.setText(uploadCurrent.getDistance());
        holder.text.setText(uploadCurrent.getTexts());
        Picasso.with(context)
                .load(uploadCurrent.getImageUri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageUri);

    }

    @Override
    public int getItemCount() {
        return ResUpdate.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView NameOfRestaurant;
        TextView Distance;
        TextView text;
        ImageView imageUri;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            NameOfRestaurant = itemView.findViewById(R.id.name);
            Distance = itemView.findViewById(R.id.Distance);
            text = itemView.findViewById(R.id.text);
            imageUri = itemView.findViewById(R.id.RestaurantImage);

        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Restuarant RES = ResUpdate.get(position);
            switch (position){
                case 0:
                    Intent main = new Intent(context,MainBusinessActivity.class);
                    context.startActivity(main);
                    break;
                case 1:
                    Toast.makeText(context,RES.getName(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context,"Restaurant not opened", Toast.LENGTH_SHORT).show();
            }

          //  Toast.makeText(context,RES.getDistance(), Toast.LENGTH_SHORT).show();

        }
    }
}
