package com.rmanrique.notes.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmanrique.notes.R;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    public int [] slide_images = {
            R.drawable.ic_bookmark,
            R.drawable.ic_cloud_done,
            R.drawable.ic_refresh
    };

    public String [] slide_titles = {
            "Easy to use",
            "Secure transactions",
            "Convenient"
    };

    public String [] slide_description = {
            "Booking your flight in a convinient way",
            "Your data is one of our main priorities",
            "We take you from point A to B"
    };


    @Override
    public int getCount() {
        return slide_titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        TextView slideTitleView = (TextView) view.findViewById(R.id.slide_title);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideDescriptionView = (TextView) view.findViewById(R.id.image_description);


        slideImageView.setImageResource(slide_images[position]);
        slideDescriptionView.setText(slide_description[position]);
        slideTitleView.setText(slide_titles[position]);

        container.addView(view);
        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
