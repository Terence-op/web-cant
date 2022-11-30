package food.app.demo;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

public class MainMealFragment extends Fragment implements OnFoofItemSelected {
    private RecyclerView mRecyclerV;
    private TextView mBucket;
    private NewAdapter mAdapter;
    private FloatingActionButton mAddFab;

    private List<Food> mUploads;
    private final ArrayList<Food> mSelectedFoods = new ArrayList<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MainMealFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerV = (RecyclerView) getView().findViewById(R.id.recyclerViewID);
        mAddFab = (FloatingActionButton) getView().findViewById(R.id.fab);

        mBucket = getView().findViewById(R.id.bucket);

        mRecyclerV.setHasFixedSize(true);

        mRecyclerV.setLayoutManager(new LinearLayoutManager(getContext()));


        mUploads = new ArrayList<>();


        db.collection("Users").document("mainMeal").collection("main").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mUploads.clear();
                for (DocumentSnapshot docs : value.getDocuments()) {
                    Food food = docs.toObject(Food.class);

                    mUploads.add(food);
                }

                mAdapter.notifyDataSetChanged();


            }
        });

        mAdapter = new NewAdapter(getContext(), mUploads, this);

        mRecyclerV.setAdapter(mAdapter);

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Oder.class);
                intent.putExtra("selectedFoods", mSelectedFoods);
                startActivity(intent);

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_meal, container, false);

    }


    @Override
    public void OnOrder(Food food) {

        if (!mSelectedFoods.contains(food)) {

            mSelectedFoods.add(food);

            Toast.makeText(getContext(), "Added " + food.getName(), Toast.LENGTH_SHORT).show();

        } else {

            mSelectedFoods.remove(food);

            Toast.makeText(getContext(), "Removed " + food.getName(), Toast.LENGTH_SHORT).show();

        }

        mBucket.setText(String.valueOf(mSelectedFoods.size()));
    }
}
