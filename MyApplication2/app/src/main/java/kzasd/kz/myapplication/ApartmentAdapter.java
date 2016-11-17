package kzasd.kz.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Алишер on 14.06.2016.
 */
public class ApartmentAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Apartment> apartments;
    private Context context;

    public ApartmentAdapter(List<Apartment> apartments, Context context) {
        this.apartments = apartments;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return apartments.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView  == null){
            convertView = inflater.inflate(R.layout.row_apartment_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.apartmentTitleTextView.setText(apartments.get(position).getTitle());
        viewHolder.apartmentPriceTextView.setText(apartments.get(position).getPrice() + "тг");

        Glide.with(context).load(apartments.get(position).getImage())
                .centerCrop().into(viewHolder.apartmentImageView);

        return convertView;
    }

    private class ViewHolder{
        TextView apartmentPriceTextView;
        ImageView apartmentImageView;
        TextView apartmentTitleTextView;


        public ViewHolder(View v){
            apartmentImageView = (ImageView) v.findViewById(R.id.apartmentImageView);
            apartmentPriceTextView = (TextView) v.findViewById(R.id.apartmentPriceTextView);
            apartmentTitleTextView = (TextView) v.findViewById(R.id.apartmentTitleTextView);
        }

    }
}
