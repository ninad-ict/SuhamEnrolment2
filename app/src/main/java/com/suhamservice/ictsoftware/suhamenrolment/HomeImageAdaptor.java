package com.suhamservice.ictsoftware.suhamenrolment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeImageAdaptor extends PagerAdapter {

    Context myContext;

    HomeImageAdaptor(Context context)
    {
        this.myContext=context;
    }


    @Override
    public int getCount() {
        return sliderImageId.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((ImageView)o);

    }

    private int[] sliderImageId = new int[]{
            R.drawable.health1, R.drawable.health2,R.drawable.health3,R.drawable.health4
    };

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        //Log.d("Entered instantiateItem","Line1");
        ImageView imageView = new ImageView(myContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

}
