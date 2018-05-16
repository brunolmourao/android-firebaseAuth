package com.example.aluno.appbruno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    EditText id;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = findViewById(R.id.editText);
        logout = findViewById(R.id.button3);

        final FirebaseAuth mauth = FirebaseAuth.getInstance();

        FirebaseUser user = mauth.getCurrentUser();

        id.setText(user.getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth.signOut();
                Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                Main2Activity.this.startActivity(myIntent);
            }
        });
    }
}
