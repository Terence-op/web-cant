package food.app.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class RestaurantFragment extends Fragment {


    private RecyclerView mRecyclerV;
    private RestaurantAdapter mAdapter;


    private List<Restuarant> mUploads;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerV = (RecyclerView) getView().findViewById(R.id.recyclerViewRestaurant);
        mRecyclerV.setHasFixedSize(true);

        mRecyclerV.setLayoutManager(new LinearLayoutManager(getContext()));
        mUploads = new ArrayList<>();


        db.collection("Users").document("mainMeal").collection("Restaurant").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mUploads.clear();
                for (DocumentSnapshot docs : value.getDocuments()) {
                    Restuarant food = docs.toObject(Restuarant.class);
                    mUploads.add(food);
                }

                mAdapter.notifyDataSetChanged();

            }
        });

        mAdapter = new RestaurantAdapter(getContext(), mUploads);

        mRecyclerV.setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }
}