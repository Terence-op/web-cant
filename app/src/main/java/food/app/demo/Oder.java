package food.app.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Oder extends AppCompatActivity {
    private TextView Price;
 Bundle bundle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        Price = findViewById(R.id.pri);

        bundle = getIntent().getExtras();

        String nn = bundle.getString("name");
        String pp = bundle.getString("price");


        Toast.makeText(this, pp, Toast.LENGTH_LONG).show();
        Toast.makeText(this, nn, Toast.LENGTH_LONG).show();

        Price.setText(nn +"   "+"K" +pp);


    }
}