package com.suhamservice.ictsoftware.suhamenrolment;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class HomeExpandableListData {

 Context mycontext;

HomeExpandableListData(Context context)
{
    this.mycontext=context;
}
    public LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String,List<String>> ExpandedListDetail= new LinkedHashMap<String, List<String>>();

        String mission=mycontext.getString(R.string.mission);
        //Log.d("Inside getData()","mission->"+mission);
        String vision=mycontext.getString(R.string.vision);
        String values=mycontext.getString(R.string.values);


List<TextView> MissionText=new ArrayList<TextView>();

        List<String>Mission=new ArrayList<String>();
       // String mission=Integer.toString(R.string.address);
        Mission.add(mission);

        List<String>Vision = new ArrayList<String>();
        Vision.add(vision);

        List<String> Values =new ArrayList<String>();
        Values.add(values);


        List<String> Associate =new ArrayList<String>();
        Associate.add("Profile");



        ExpandedListDetail.put("Our Mission",Mission);
        ExpandedListDetail.put("Vision",Vision);
        ExpandedListDetail.put("Values",Values);





        return ExpandedListDetail;
    }
}