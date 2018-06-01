package com.example.gebruiker.applinuxos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gebruiker.applinuxos.data.DataManager;
import com.example.gebruiker.applinuxos.data.model.Light;
import com.example.gebruiker.applinuxos.data.model.LightsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView OFFONMF;
    private SwitchCompat onOffSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OFFONMF = findViewById(R.id.light_status);
        onOffSwitch = findViewById(R.id.light_switch_toggle);
        onOffSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
        refresh();
    }

    private void refresh() {
        DataManager.instance(this).getLight("" + 1).enqueue(new Callback<Light>() {
            @Override
            public void onResponse(Call<Light> call, Response<Light> response) {
                update(response.body());
            }

            @Override
            public void onFailure(Call<Light> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void setLightState(String id, boolean state) {
        DataManager.instance(this).setLightStatus(new Light(id, state)).enqueue(new Callback<LightsResponse>() {
            @Override
            public void onResponse(Call<LightsResponse> call, Response<LightsResponse> response) {
                update(response.body().getLights().get(0));
            }

            @Override
            public void onFailure(Call<LightsResponse> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void showError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void update(Light light) {
        Log.d("aiuds", light.getId() + " " + light.isStatus());
        OFFONMF.setText(light.isStatus() ? "on" : "off");
        onOffSwitch.setOnCheckedChangeListener(null);
        onOffSwitch.setChecked(light.isStatus());
        onOffSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            setLightState("" + 1, isChecked);
        }
    };

}
