package food.app.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ObjDoubleConsumer;

public class MainMealFragment extends Fragment implements OnFoofItemSelected {
    private RecyclerView mRecyclerV;
    private NewAdapter mAdapter;

    private FloatingActionButton mAddFab;

    private List<Food> mUploads;
    private ArrayList<Food> SelectedFoods = new ArrayList<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MainMealFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerV =(RecyclerView)getView().findViewById(R.id.recyclerViewID);
        mAddFab = (FloatingActionButton)getView().findViewById(R.id.fab);

        mRecyclerV.setHasFixedSize(true);

        mRecyclerV.setLayoutManager(new LinearLayoutManager(getContext()));


        mUploads = new ArrayList<>();



        db.collection("Users").document("mainMeal").collection("main").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mUploads.clear();
                for(DocumentSnapshot docs: value.getDocuments()){
                    Food food =  docs.toObject(Food.class);

                    mUploads.add(food);
                }

                mAdapter.notifyDataSetChanged();


            }
        });

        mAdapter = new NewAdapter(getContext(),mUploads,this);

        mRecyclerV.setAdapter(mAdapter);



//            @Override
//            public void onClick(View view, String name,String price) {
//
//
////                Intent intent = new Intent(getContext(),Oder.class);
////                Bundle args = new Bundle();
////                args.putSerializable("ARRAYLIST",(Serializable)SelectedFoods);
////                intent.putExtra("myList",SelectedFoods);
////                startActivity(intent);
//
//
//            }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_meal, container, false);


    }
    public void myData(Food food){
        String Name = food.getName();
        String Price = food.getPrice();
        Intent intent = new Intent(getContext(),Oder.class);
        intent.putExtra("name",Name);
        intent.putExtra("price",Price);
        startActivity(intent);



    }


    @Override
    public void OnOrder(Food food) {
        if (!SelectedFoods.contains(food)) {


        String nameFood = food.getName();
        String priceFood = food.getName();

            List<String> list = new ArrayList<String>();
            list.add(nameFood);
            list.add(priceFood);
           //try using database


            Toast.makeText(getContext(),list.get(0),Toast.LENGTH_SHORT).show();


        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myData(food);
            }
        });
        }
    }
}