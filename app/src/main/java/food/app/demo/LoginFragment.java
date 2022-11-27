package food.app.demo;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    public FirebaseAuth mAuth;
    public EditText Email;
    public EditText Password;
    public TextView LoginTv;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        LoginTv = view.findViewById(R.id.login1_btn);


        Email = (EditText)view.findViewById(R.id.email1_txt);

        Password = (EditText)view.findViewById(R.id.password1_txt);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        view.findViewById(R.id.login1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Emails = Email.getText().toString().trim();
                String Passwords = Password.getText().toString().trim();

                if(!Emails.equals("")&& (!Passwords.equals(""))){
                    mAuth.signInWithEmailAndPassword(Emails,Passwords).addOnCompleteListener(( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getActivity(),"Text!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getActivity(),"Signed in",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity().getApplicationContext(),Dashboard.class);
                                startActivity(intent);
                            }
                        }
                    }));
                }
            }
        });
        return view;
    }
}