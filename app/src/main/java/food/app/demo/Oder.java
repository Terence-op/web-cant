package food.app.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Oder extends AppCompatActivity {
    private TextView Price;
    private ArrayList<Food> foods;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        Price = findViewById(R.id.pri);

        bundle = getIntent().getExtras();

        foods = (ArrayList<Food>) bundle.getSerializable("selectedFoods");


        double total = 0;

        StringBuilder stringBuilder = new StringBuilder();

        for (Food food : foods) {
            total += Double.parseDouble(food.getPrice());

            stringBuilder.append(food.getName() + " ----> " + "K" + food.getPrice() +"\n");
        }

        Price.setText(stringBuilder);


    }
}
