package com.example.aluno.appbruno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button login,register;
    EditText usuario;
    EditText password;
    TextView retorno;
    TextView registrar;
    User us;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        usuario = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        retorno = (TextView) findViewById(R.id.retorno);
        registrar = (TextView) findViewById(R.id.textView5);
        register = (Button) findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login1 = usuario.getText().toString();
                String senha = password.getText().toString();
                mAuth.signInWithEmailAndPassword(login1,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginIn();
                        }else{
                            Toast.makeText(MainActivity.this, "Log failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login1 = usuario.getText().toString();
                String senha = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(login1,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginIn();
                        }else{
                            Toast.makeText(MainActivity.this, "Reg failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
    public void loginIn(){
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }
}
