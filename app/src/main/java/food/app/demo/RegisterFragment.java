package food.app.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;

public class RegisterFragment extends Fragment {
    public EditText Email;
    public EditText Password;

    public EditText Name,Phone;
    public FirebaseAuth mAuth;
    public TextView RegisterTV;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        RegisterTV = view.findViewById(R.id.register_btn);

        Name = (EditText)view.findViewById(R.id.name_txt);
        Phone = (EditText)view.findViewById(R.id.phone_txt);

        Email = (EditText)view.findViewById(R.id.email_txt);

        Password = (EditText)view.findViewById(R.id.password_txt);

        RegisterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Emails = Email.getText().toString().trim();
                String Passwords = Password.getText().toString().trim();
                String names = Name.getText().toString().trim();
                String phones = Phone.getText().toString().trim();
                String theID ="";


            if(!Emails.equals("")&& (!Passwords.equals(""))){
                mAuth.createUserWithEmailAndPassword(Emails,Passwords).addOnCompleteListener(( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getActivity(),"Account created",Toast.LENGTH_SHORT).show();


                            // creating a collection for a user
                            FirebaseUser user = task.getResult().getUser();
                            String uid = user.getUid();
//
                            registrations registers = new registrations(names,phones,theID);
                            db.collection("Users").document(uid).set(registers).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"welcome",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getActivity(),"user"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                                }
                            });

                        }
                    }
                }));


            }

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;

    }
}