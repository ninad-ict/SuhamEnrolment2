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

        //LinkedHashMap<String,List<String>> ExpandedListDetail= new LinkedHashMap<String, List<String>>();
        LinkedHashMap<String,List<String>> ExpandedListDetail= new LinkedHashMap<String, List<String>>();

        String mission=mycontext.getString(R.string.mission);
        String mission2="Hii Ninad";
        //Log.d("Inside getData()","mission->"+mission);
        String vision=mycontext.getString(R.string.vision);
        String values=mycontext.getString(R.string.values);


List<TextView> MissionText=new ArrayList<TextView>();

List<LocationFragment> MyLocation=new ArrayList<LocationFragment>();

MyLocation.add(EmployeeLogin.EmployeeLocation);

        List<String>Mission=new ArrayList<String>();
       // String mission=Integer.toString(R.string.address);
        Mission.add(mission);
        Mission.add(mission2);


        List<String>Vision = new ArrayList<String>();
        Vision.add(vision);

        List<String> Values =new ArrayList<String>();
        Values.add(values);


        List<String> Associate =new ArrayList<String>();
        Associate.add("Profile");



       // ExpandedListDetail.put("Location ",MyLocation);
        ExpandedListDetail.put("Our Mission",Mission);
        ExpandedListDetail.put("Vision",Vision);
        ExpandedListDetail.put("Values",Values);





        return ExpandedListDetail;
    }
}