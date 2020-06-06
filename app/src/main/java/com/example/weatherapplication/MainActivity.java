package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapplication.Retrofit.ApiInterface;
import com.example.weatherapplication.Retrofit.ClientAPI;
import com.example.weatherapplication.Retrofit.Example;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView temp, descr, humid;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.enterQuery);
        temp=findViewById(R.id.temparature);
        descr=findViewById(R.id.description);
        humid=findViewById(R.id.humidity);
        imageView=findViewById(R.id.search);

        imageView.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                getWeatherData(editText.getText().toString().trim());
            }
        });
    }

    private void getWeatherData(String name) throws NullPointerException{

        ApiInterface apiInterface = ClientAPI.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>( ) {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if(response.body() != null) {
                    temp.setText(response.body( ).getMain( ).getTemp( ));
                    descr.setText(response.body( ).getMain( ).getFeels_like( ));
                    humid.setText(response.body( ).getMain( ).getHumidity( ));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter Valid City",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Enter Valid City",Toast.LENGTH_LONG).show();
            }
        });
    }
}
