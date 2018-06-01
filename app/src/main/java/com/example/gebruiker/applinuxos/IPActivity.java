package com.example.gebruiker.applinuxos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gebruiker.applinuxos.data.model.ServiceProvider;

public class IPActivity extends AppCompatActivity {

    private EditText IPAdress;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        IPAdress = findViewById(R.id.IPEditText);
        button = findViewById(R.id.connect_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IPAdress.getText().toString() != ""){
                    ServiceProvider.instance().BASE_URL = "http://"+ IPAdress.getText().toString() + ":8081";
                    ServiceProvider.instance().createServiceProvider();
                    Log.d("BASEURL", IPAdress.getText().toString());
                    goToMainActivity();
                }
            }
        });
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
