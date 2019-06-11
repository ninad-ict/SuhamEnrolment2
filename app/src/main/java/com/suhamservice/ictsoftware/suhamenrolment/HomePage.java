package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePage extends Fragment {

    View Home;
int currentpage=0;
Timer timer;
final long DELAY_MS=600;
final long PERIOD_MS = 3000;


    ExpandableListView expandableListView;
    ExpandableListAdapter expandListAdaptor;
    List<String> ListTitle;
    LinkedHashMap<String,List<String>> ListItems;
    LinkedHashMap<String,List<TextView>> TextListItems;
    final Handler handler=new Handler();
Runnable changeSlide;
TimerTask timerTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Home=inflater.inflate(R.layout.homefragment,container,false);

        final ViewPager myViewPager=(ViewPager)Home.findViewById(R.id.HomeViewPager);
        HomeImageAdaptor homeImageAdaptor=new HomeImageAdaptor(getContext());
myViewPager.setAdapter(homeImageAdaptor);
//myViewPager.setCurrentItem(3);


     changeSlide= new Runnable() {
    @Override
    public void run() {
        //Log.d("SLIDERUNNING","slide running at "+currentpage);
        if(currentpage==4)
        {
            currentpage=0;
        }
        myViewPager.setCurrentItem(currentpage++,true);
    }
};

timer=new Timer();
timer.schedule(timerTask=new TimerTask() {
    @Override
    public void run() {
        handler.post(changeSlide);
    }
},DELAY_MS,PERIOD_MS);

HomeExpandableListData homeExpandableListData=new HomeExpandableListData(getContext());
expandableListView=(ExpandableListView)Home.findViewById(R.id.HomeExpandView);
ListItems=homeExpandableListData.getData();
ListTitle=new ArrayList<String>(ListItems.keySet());
expandListAdaptor=new HomeExpandListAdapter(getContext(),ListTitle,ListItems);
expandableListView.setAdapter(expandListAdaptor);
expandableListView.setChildDivider(getResources().getDrawable(R.color.white));

        return Home;
    }

    @Override
    public void onPause() {
        super.onPause();
        timerTask.cancel();

        Log.d("Inside Pause","Reached");
        /*try {
          //  timer.wait();
            //changeSlide.wait();


        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            //Log.d("Inside Catch of onPause","e->"+e.toString());
        }*/

    }
}
