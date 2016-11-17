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
import com.backendless.persistence.BackendlessDataQuery;

import java.util.List;

public class ApartmentsActivity extends AppCompatActivity {

    private static final String TAG = "ApartmentsActivity";
    private String city_id;
    private ListView listView;
    private List<Apartment> apartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartments);

        listView = (ListView) findViewById(R.id.listView);

        city_id = getIntent().getExtras().getString("city_id");

        downloadApartments(city_id);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openFlats(position);
            }
        });


    }

    private void openFlats(int position) {
        Intent intent = new Intent(this,FlatsActivity.class);
        intent.putExtra("apartment_id",apartments.get(position).getObjectId());
        startActivity(intent);
    }


    private void downloadApartments(String city_id) {
        String whereClause = "city.objectId = " + "'" + city_id + "'";

        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause(whereClause);

        Backendless.Persistence.of(Apartment.class).find(query, new AsyncCallback<BackendlessCollection<Apartment>>() {
            @Override
            public void handleResponse(BackendlessCollection<Apartment> response) {
                displayApartments(response.getData());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG,"some error " +fault.getMessage());
            }
        });

    }

    private void displayApartments(List<Apartment> apartments) {
        this.apartments=apartments;
        ApartmentAdapter adapter = new ApartmentAdapter(apartments,this);
        listView.setAdapter(adapter);

    }
}
