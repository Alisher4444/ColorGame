package kzasd.kz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

public class CitiesActivity extends AppCompatActivity {

    private static final String TAG = "CitiesActivity";
    private ListView listView;
    private List<City> cities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        listView = (ListView) findViewById(R.id.listView);

        Backendless.initApp(this,Const.APP_ID, Const.ANDROID_KEY, "v1");
        downloadCities();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openCity(position);
            }
        });


    }

    private void openCity(int position) {
        Intent intent = new Intent(this,ApartmentsActivity.class);
        intent.putExtra("city_id",cities.get(position).getObjectId());
        startActivity(intent);

    }


    private void downloadCities(){



        Backendless.Persistence.of(City.class).find(new AsyncCallback<BackendlessCollection<City>>() {
            @Override
            public void handleResponse(BackendlessCollection<City> response) {
                Log.d(TAG,response.getData().toString());

                displayCities(response.getData());

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG,"some error"+ fault.getMessage());

            }
        });

    }

    private void displayCities(List<City> cities) {
        this.cities=cities;
        CityAdapter adapter  = new CityAdapter(cities,this);
        listView.setAdapter(adapter);

    }
}
