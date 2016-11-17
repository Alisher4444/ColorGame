package kzasd.kz.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class FlatsActivity extends AppCompatActivity {

    private static final String TAG = ".FlatsActivity";
    private String apartments_id;
    private ViewPager viewPager;
    private SwipeAdapter swipeAdapter;
    private List<Flats> flatsList;
    private Context context;
    private ImageView slideImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats);
        apartments_id = getIntent().getExtras().getString("apartments_id");

        String url1 = "https://api.backendless.com/E86ED4F2-BC00-05DA-FF55-536343756D00/v1/files/Images/Almaty/Additional_image/flat2New.jpg";
        String url2 = "https://api.backendless.com/E86ED4F2-BC00-05DA-FF55-536343756D00/v1/files/Images/Almaty/Additional_image/flatNewApartment1.jpg";


        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();

        View page= inflater.inflate(R.layout.swipe_layout,null);
        TextView textView = (TextView) page.findViewById(R.id.swipeTextView);
        Target<GlideDrawable> as = Glide.with(slideImageView.getContext()).load(url1).into(slideImageView);
        textView.setText("1");
        pages.add(page);

        page= inflater.inflate(R.layout.swipe_layout,null);
        textView = (TextView) page.findViewById(R.id.swipeTextView);
        as = Glide.with(slideImageView.getContext()).load(url1).into(slideImageView);
        textView.setText("2");
        pages.add(page);



        page= inflater.inflate(R.layout.swipe_layout,null);
        textView = (TextView) page.findViewById(R.id.swipeTextView);
        as = Glide.with(slideImageView.getContext()).load(url2).into(slideImageView);
        textView.setText("3");
        pages.add(page);


        page= inflater.inflate(R.layout.swipe_layout,null);
        textView = (TextView) page.findViewById(R.id.swipeTextView);
        textView.setText("4");
        pages.add(page);


        SwipeAdapter swipeAdapter = new SwipeAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(1);

        setContentView(viewPager);

    }



}
