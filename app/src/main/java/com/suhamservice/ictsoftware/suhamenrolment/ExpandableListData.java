package com.suhamservice.ictsoftware.suhamenrolment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListData {

    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String,List<String>> ExpandedListDetail= new LinkedHashMap<String, List<String>>();

        List<String>Home=new ArrayList<String>();
        Home.add("MyHOME");
        Home.add("Your HOME");

        List<String>Application = new ArrayList<String>();
        Application.add("Adolescent Girl");
        Application.add("Pregnant Woman");
        Application.add("PostNatal Woman");
        Application.add("Small Child");
        Application.add("Past Records");

        List<String> Contact =new ArrayList<String>();
        Contact.add("MyContact");
        Contact.add("YourContact");

        List<String> Associate =new ArrayList<String>();
        Associate.add("Profile");



        ExpandedListDetail.put("HOME",new ArrayList<String>());
        ExpandedListDetail.put("ENROLMENT",Application);
        ExpandedListDetail.put("ASSOCIATE",Associate);
        ExpandedListDetail.put("CONTACT US",new ArrayList<String>());




        return ExpandedListDetail;
    }
}

