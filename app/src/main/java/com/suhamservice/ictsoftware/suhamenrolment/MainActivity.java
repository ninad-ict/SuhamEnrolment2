package com.suhamservice.ictsoftware.suhamenrolment;

import android.app.Activity;
import android.content.IntentFilter;
//import android.support.v4.app.Fragment;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandListAdaptor;
    List<String> ListTitle;
    LinkedHashMap<String,List<String>> ListItems;

    LocalDate birthdate = null;



    BaseFragment baseFragment=new BaseFragment();
    //BaseFragment baseFragmentPreg=new BaseFragment();
    //BaseFragment baseFragmentChild=new BaseFragment();

    //------DECLARATION OBJECTS FOR UID_LENGTH--------
    static int UID_LENGTH=9;
    //------DECLARATION OBJECTS FOR UID_LENGTH--------


    //------DECLARATION OBJECTS FOR ANTENATAL--------

    static ApplicationName anteApplicant=new ApplicationName();
    static LocationFragment anteLocation=new LocationFragment();
    static PregPersonal antePersonal=new PregPersonal();
    static PreDelivery antePreDelivery=new PreDelivery();

    static EmployeeRegistration employeeRegistration=new EmployeeRegistration();




    //------DECLARATION OBJECTS FOR POST-DELIVERY--------
    //static ApplicationName pregnantApplicant=new ApplicationName();
    static ApplicationName deliveryApplicant=new ApplicationName();
   // static PreDelivery preDelivery=new PreDelivery();
    static Delivery delivery=new Delivery();
    static LocationFragment DeliveryLocation=new LocationFragment();
    static PregPersonal pregPersonal=new PregPersonal();

    static Boolean PERSONALPREGSAVED=false;
    static Boolean PREDELIVERYSAVED=false;
    static Boolean DELIVERYSAVED=false;

    //------DECLARATION OBJECTS FOR CHILD--------
    static ApplicationName childApplicant=new ApplicationName();
    Parents parents=new Parents();
    static ChildBirth childBirth=new ChildBirth();
    static ChildGrowth childGrowth=new ChildGrowth();
    static LocationFragment childLocation=new LocationFragment();

    static Boolean PARENTSSAVED=false;
    static Boolean CHILDBIRTHSAVED=false;
    static Boolean CHILDGROWTHSAVED=false;

    static ChildBirth childBirthS=new ChildBirth();
    static ChildGrowth childGrowthS=new ChildGrowth();

    static ChildBirth childBirth1=new ChildBirth();
    static ChildBirth childBirth2=new ChildBirth();
    static ChildGrowth childGrowth1=new ChildGrowth();
    static ChildGrowth childGrowth2=new ChildGrowth();


    //------DECLARATION OBJECTS FOR GIRL--------
    static LocationFragment locationFragment=new LocationFragment();
    PersonalFragment personalFragment=new PersonalFragment();
    HealthFragment healthFragment=new HealthFragment();
    static ApplicationName applicationName=new ApplicationName();
    EducationWorkFragment educationWorkFragment=new EducationWorkFragment();

    static Boolean PERSONALSAVED=false;
    static Boolean HEALTHSAVED=false;
    static Boolean EDUWORKSAVED=false;

    InternetChecker internetChecker=new InternetChecker();
    ProfileAssociate profileAssociate=new ProfileAssociate();

    Toolbar myToolbar;
    private DrawerLayout mydrawLayout;

    //ImageView PersonalImage=findViewById(R.id.imageViewPersonal);

    //  static Boolean LOCATIONSAVED=false;


    static Boolean RECEIVED=false;
    static String SERVER="http://192.168.1.126:80/SUHAM_Final/";
    //static String SERVER="http://ec2-13-126-88-91.ap-south-1.compute.amazonaws.com/SUHAM/";
    //static String SERVER="http://www.suhamhealth.org/";
    static String ID="";

    //------VALUES FOR IDENTIFYING WHICH MENU OPTION IS SELECTED--------
    static boolean ENROL_GIRL=false;
    static boolean ENROL_DELL=false;
    static boolean ENROL_CHILD=false;
    static boolean ENROL_ANTE=false;
    //------VALUES FOR IDENTIFYING WHICH MENU OPTION IS SELECTED--------


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // unregisterReceiver(internetChecker);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d("IN-onCreate-MA","First Line");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//-----LOGIC FOR ONE-TIME EMPLOYEE REGISTRATION-----------------
        //DataBaseHelper DB=new DataBaseHelper(getBaseContext(),null,null,1);
        DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
        String EmpRegState=DB.isEMPRegFormFilled();
        //DB.close();;
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.Frame1, employeeRegistration);
           fragmentTransaction.addToBackStack(null);
           fragmentTransaction.commit();


//Log.d("After AER->","BEFORE-REGISTERED->");
       if(!EmpRegState.contains("SUCCESS"))
       {
           //Log.d("AER","EMP-IS-NOT-REGISTERED");
           return;
       }
        //Log.d("After AER->","AFTER-REGISTERED->");
       //new AsyncCheckReg

//-----LOGIC FOR ONE-TIME EMPLOYEE REGISTRATION-----------------

        //START:----------LOGIC FOR EXPANDABLE SLIDING MENU BAR--------------
        expandableListView=(ExpandableListView)findViewById(R.id.ExpandView);

        ListItems=ExpandableListData.getData();

        ListTitle=new ArrayList<String>(ListItems.keySet());
        expandListAdaptor=new ExpandListAdaptor(this,ListTitle,ListItems);
        expandableListView.setAdapter(expandListAdaptor);
        View header=getLayoutInflater().inflate(R.layout.drawerheader, null);
        expandableListView.addHeaderView(header);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String mess="In-OnGroupLis";
                //Log.d(mess,""+groupPosition);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (groupPosition)
                {
                    case 0:
                        //Log.d(mess,"Pressed Home Menu");
                        fragmentTransaction.replace(R.id.Frame1, new HomePage());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        mydrawLayout.closeDrawers();

                        return true;
                    case 3:
                        //Log.d(mess,"Pressed Contact Menu");
                        fragmentTransaction.replace(R.id.Frame1, new ContactUs());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        mydrawLayout.closeDrawers();

                        return true;


                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String mess="IN-ChildClickList";
                //Log.d(mess,"groupPosition:"+groupPosition+" childPosition:"+childPosition);
                String option=String.valueOf(groupPosition)+String.valueOf(childPosition);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // TextView textViewApplName;
             //=(TextView)findViewById(R.id.TextViewApplName);
  /*
                if(MainActivity.ENROL_GIRL)
                    textViewApplName.setText("Enter Name of the Girl");
                else if (MainActivity.ENROL_DELL)
                    textViewApplName.setText("Enter Name of the Pregnant Woman");
                else if (MainActivity.ENROL_CHILD)
                    textViewApplName.setText("Enter Name of the Child");*/

                switch (option)
                {
                    case ("10"):
                        ENROL_GIRL=true;
                        ENROL_DELL=false;
                        ENROL_CHILD=false;
                        ENROL_ANTE=false;

                        //Log.d(mess,"ENROLMENT FOR GIRL");
                       // ApplicationName.ApplicantName.setText("Enter Name of the Girl");
                        if(!applicationName.ApplName.equals("")) {
                            baseFragment=new BaseFragment();
                            fragmentTransaction.replace(R.id.Frame1, baseFragment);
                        }
                        else {

                            applicationName = new ApplicationName();
                            fragmentTransaction.replace(R.id.Frame1, applicationName);

                        }

                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                      //  textViewApplName=(TextView)findViewById(R.id.TextViewApplName);
                       // textViewApplName.setText("Enter Name of the Girl");

                        mydrawLayout.closeDrawers();
                        //menuItem.setChecked(true);
                        return true;

                    case ("11"):
                        ENROL_GIRL=false;
                        ENROL_ANTE=true;
                        ENROL_DELL=false;
                        ENROL_CHILD=false;

                        //Log.d(mess,"ENROLMENT FOR Antenatal Woman");
                        // ApplicationName.ApplicantName.setText("Enter Name of the Girl");
                        if(!anteApplicant.ApplName.equals("")) {
                            baseFragment=new BaseFragment();
                            fragmentTransaction.replace(R.id.Frame1, baseFragment);
                        }
                        else {

                            anteApplicant = new ApplicationName();
                            fragmentTransaction.replace(R.id.Frame1, anteApplicant);

                        }

                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        //  textViewApplName=(TextView)findViewById(R.id.TextViewApplName);
                        // textViewApplName.setText("Enter Name of the Girl");

                        mydrawLayout.closeDrawers();
                        //menuItem.setChecked(true);

                        return true;

                    case ("12"):
                        ENROL_DELL=true;
                        ENROL_GIRL=false;
                        ENROL_CHILD=false;
                        ENROL_ANTE=false;
//                        ApplicationName.ApplicantName.setText("Enter Name of Pregnant Woman");

                        //Log.d(mess,"ENROLMENT FOR PREGNANT");

                        if(!deliveryApplicant.ApplName.equals("")) {
                           baseFragment=new BaseFragment();
                            fragmentTransaction.replace(R.id.Frame1, baseFragment);

                        }
                        else
                        {
                            deliveryApplicant = new ApplicationName();
                            fragmentTransaction.replace(R.id.Frame1, deliveryApplicant);
                       }

                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        mydrawLayout.closeDrawers();

                        return true;
                    case ("13"):
                        ENROL_CHILD=true;
                        ENROL_GIRL=false;
                        ENROL_DELL=false;
                        ENROL_ANTE=false;

                       // ApplicationName.ApplicantName.setText("Enter Name of Child");
                        //ApplicationName.ApplicantName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       // textViewApplName.setText("Enter Name of the Child");

                        //Log.d(mess,"ENROLMENT FOR CHILD");

                        if(!childApplicant.ApplName.equals("")) {
                            baseFragment=new BaseFragment();
                            fragmentTransaction.replace(R.id.Frame1, baseFragment);


                        }
                        else {
                            childApplicant = new ApplicationName();
                            fragmentTransaction.replace(R.id.Frame1, childApplicant);
                        }

                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        mydrawLayout.closeDrawers();

                        return true;
                    case ("14"):
                        //Log.d(mess,"Pressed Archive Menu");
                        // Toast.makeText(getApplicationContext(),"ARCHIVE!!!!1",Toast.LENGTH_LONG).show();

                        fragmentTransaction.replace(R.id.Frame1, new ArchivedData());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        mydrawLayout.closeDrawers();

                        return true;

                    case ("20"):
                        //Log.d(mess,"Pressed Profile Menu");
                        fragmentTransaction.replace(R.id.Frame1, profileAssociate);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        mydrawLayout.closeDrawers();

                        return true;
                }
                return false;
            }
        });


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
               // Toast.makeText(getApplicationContext(), ListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });


        mydrawLayout=findViewById(R.id.draw1);
        myToolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(myToolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mydrawLayout.closeDrawers();

        //END:----------LOGIC FOR EXPANDABLE SLIDING MENU BAR--------------

          //DB=new DataBaseHelper(getBaseContext(),null,null,1);
             DB=DataBaseHelper.getInstance(getBaseContext());
        if(DB.rowPresent())
        {//DB.close();;
            MainActivity.RECEIVED=false;
            IntentFilter intentFilter=new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            //intentFilter.addAction();
            registerReceiver(internetChecker,intentFilter);
        }
        //unregisterReceiver(internetChecker);
         fragmentManager= getSupportFragmentManager();
         fragmentTransaction=fragmentManager.beginTransaction();
       // fragmentTransaction.add(android.R.id.content,applicationName);

        fragmentTransaction.replace(R.id.Frame1,new HomePage());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //Log.d("VISIB","df");
        //((EditText)findViewById(R.id.editTextMarrYear)).setVisibility(View.INVISIBLE);
        // findViewById(R.id.textViewMarrYear).setVisibility(View.INVISIBLE);
        //Log.d("MESS","ggg");
//PersonalImage.setVisibility(View.INVISIBLE);
    //new getAsyncRegions().execute();
        new getAsyncState().execute();
        new getAsyncDistrict().execute();
        new getAsyncFed().execute();
    }


    public static void hideKeyboard(Activity activity) {
        String mess="HIDE-KEY";
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            //Log.d(mess,"inside IF");
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*public void getArchive(View view)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame1, new ArchivedData());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }*/

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(getBaseContext(),"INSIDE INTERFACE ONNav",Toast.LENGTH_LONG).show();
        return true ;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String mess="OnOptionsItemSelected";
        switch (item.getItemId())
        {
            case android.R.id.home:
                //Log.d(mess,"1st switch");
               // mydrawLayout.openDrawer(GravityCompat.START);
                mydrawLayout.openDrawer(GravityCompat.START);
                hideKeyboard(this);
                return true;

        }
        //Log.d(mess,"OUTside SWITCHes");
        return super.onOptionsItemSelected(item);
    }

    //---------CODE FOR GETTING STATE LIST FROM SERVER IN LOCAL DB----------

    private class getAsyncState extends AsyncTask<String,String,String>
    {
        URL url;
        HttpURLConnection conn;

        @Override
        protected String doInBackground(String... strings) {
            //Log.d("ASYNC","Entered BG of STATE");


            try {
                url = new URL(SERVER+"getState.php");
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
               // conn.setRequestMethod("GET");
                conn.setDoOutput(true);
               // conn.setDoInput(true);
               // conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                int response = conn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = br.readLine())!= null) {
                        result.append(line);
                    }
                    //Log.d("Inside asyncSTATE",result.toString());
                    return result.toString();
                } else
                    return "NOTEXT";
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.equals("NOTEXT")&&!s.contains("java.net."))
            {
                //DataBaseHelper DB=new DataBaseHelper(getApplicationContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.uploadState(s);
                //DB.close();;

            }
          //  else
           // {
                //Log.d("STATE:post execute","NO Data");
            //}

        }

        }

    //---------CODE FOR GETTING STATE LIST FROM SERVER IN LOCAL DB----------


    //---------CODE FOR GETTING District LIST FROM SERVER IN LOCAL DB----------

    private class getAsyncDistrict extends AsyncTask<String,String,String>
    {
        URL url;
        HttpURLConnection conn;

        @Override
        protected String doInBackground(String... strings) {
            //Log.d("ASYNC","Entered BG of DISTRICT");

            try {
                url = new URL(SERVER+"getDistrict.php");
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                // conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                // conn.setDoInput(true);
                // conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                int response = conn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = br.readLine())!= null) {
                        result.append(line);
                    }
                    //Log.d("Inside asyncDistict",result.toString());
                    return result.toString();
                } else
                    return "NOTEXT";
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.equals("NOTEXT")&&!s.contains("java.net."))
            {
                //DataBaseHelper DB=new DataBaseHelper(getApplicationContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.uploadDistrict(s);
                //DB.close();;

            }
         //   else
           // {
            //    //Log.d("District:post execute","NO Data");
          //  }

        }

    }

    //---------CODE FOR GETTING District LIST FROM SERVER IN LOCAL DB----------

    //---------CODE FOR GETTING Fed LIST FROM SERVER IN LOCAL DB----------

    private class getAsyncFed extends AsyncTask<String,String,String>
    {
        URL url;
        HttpURLConnection conn;

        @Override
        protected String doInBackground(String... strings) {
            //Log.d("ASYNC","Entered BG of FED");

            try {
                url = new URL(SERVER+"getFed.php");
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                // conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                // conn.setDoInput(true);
                // conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                int response = conn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = br.readLine())!= null) {
                        result.append(line);
                    }
                    //Log.d("Inside asyncFed",result.toString());
                    return result.toString();
                } else
                    return "NOTEXT";
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.equals("NOTEXT")&&!s.contains("java.net."))
            {
                //DataBaseHelper DB=new DataBaseHelper(getApplicationContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.uploadFed(s);
                //DB.close();;

            }
           // else
           // {
          //      //Log.d("FED:post execute","NO Data");
           // }

        }

    }

    //---------CODE FOR GETTING Fed LIST FROM SERVER IN LOCAL DB----------


    public  void clickOnProceed(View view)
    {

    //Log.d("MMMM","Inside clickOnPressed");

    if(!isEmpty(((EditText)findViewById(R.id.EditTextApplName))))
    {
        if(ENROL_GIRL)
        {
            //Log.d("OnclickProceedButton","ENROLGIRL");
            locationFragment.SAVELocation=false;
            PERSONALSAVED=false;
            HEALTHSAVED=false;
            EDUWORKSAVED=false;
            if (!applicationName.ID_ENTERED) {
                applicationName.ApplName=((EditText)findViewById(R.id.EditTextApplName)).getText().toString();
                //Log.d("ClickToProceed", "Entered IF");
            } else {
                //Log.d("ClickToProceed", "Entered ELSE");
                try {
                    applicationName.ApplName = applicationName.jsonObject.get("GNAME").toString();
                      //Log.d("ClickToProceed", "applicationName.ApplName->" + applicationName.ApplName);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

        }
        if(ENROL_DELL) {
            //Log.d("OnclickProceedButton","ENROL_DELL");

            DeliveryLocation.SAVELocation=false;
            MainActivity.DELIVERYSAVED=false;
            pregPersonal.PPSAVED=false;
            if (!deliveryApplicant.ID_ENTERED) {
                deliveryApplicant.ApplName = ((EditText) findViewById(R.id.EditTextApplName)).getText().toString();
                //Log.d("ClickToProceed", "Entered IF");
            } else {
                //Log.d("ClickToProceed", "Entered ELSE");
                try {
                    if(deliveryApplicant.ID_WHO=="GIRL")
                        deliveryApplicant.ApplName = deliveryApplicant.jsonObject.get("GNAME").toString();
                    else
                        deliveryApplicant.ApplName = deliveryApplicant.jsonObject.get("NAME").toString();
                    //Log.d("ClickToProceed", "deliveryApplicant.ApplName->" + deliveryApplicant.ApplName);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }
        }
        if(ENROL_CHILD) {
            //childApplicant.ApplName = ((EditText) findViewById(R.id.EditTextApplName)).getText().toString();
            //Log.d("OnclickProceedButton","ENROL_CHILD");
            childLocation.SAVELocation=false;
            PARENTSSAVED=false;
            CHILDBIRTHSAVED=false;
            CHILDGROWTHSAVED=false;

            if (!childApplicant.ID_ENTERED) {
                childApplicant.ApplName = ((EditText) findViewById(R.id.EditTextApplName)).getText().toString();
                //Log.d("ClickToProceed", "Entered IF");
            } else {

                //Log.d("ClickToProceed", "Entered ELSE");
                try {
                    if(childApplicant.ID_WHO.equals("MOTH")) {
                        Parents.MomId=((EditText)findViewById(R.id.EditTextApplName)).getText().toString();
                        childApplicant.ApplName = childApplicant.jsonObject.get("NAME").toString() + "'s Child";
                        //Log.d("RESEARCH1",childApplicant.ApplName);
                    }
                    if(childApplicant.ID_WHO.equals("CHILD")) {
                        childApplicant.ApplName = childApplicant.jsonObject.get("NAME").toString();
                        //Log.d("RESEARCH2", childApplicant.ApplName);
                    }

                    //Log.d("ClickToProceed", "childApplicant.ApplName->" + childApplicant.ApplName);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

        }

        if(ENROL_ANTE) {
            //Log.d("OnclickProceedButton","ENROL_ANTE");

            anteLocation.SAVELocation=false;
            antePersonal.PPSAVED=false;
            antePreDelivery.PD_SAVED=false;

            if (!anteApplicant.ID_ENTERED) {
                anteApplicant.ApplName = ((EditText) findViewById(R.id.EditTextApplName)).getText().toString();
                //Log.d("ClickToProceed", "Entered IF");
            } else {
                //Log.d("ClickToProceed", "Entered ELSE");
                try {
                    if(anteApplicant.ID_WHO=="GIRL")
                        anteApplicant.ApplName = anteApplicant.jsonObject.get("GNAME").toString();
                    else if(anteApplicant.ID_WHO=="PREG")
                        anteApplicant.ApplName = anteApplicant.jsonObject.get("NAME").toString();
                    //Log.d("ClickToProceed", "deliveryApplicant.ApplName->" + anteApplicant.ApplName);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame1, baseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    else
    {
        Toast.makeText(getBaseContext(),"Enter Applicant's Name",Toast.LENGTH_LONG).show();
    }
}
    public void clickOnBack(View view)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentManager.findFragmentByTag("PerFrag");
        fragmentTransaction.replace(R.id.Frame1, baseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void finalSubmit(View view)
    {
        String mess="In finalSubmit";
        if(ENROL_GIRL) {
            //Log.d(mess,"->IN Girl");

        if (!locationFragment.SAVELocation) {
            Toast.makeText(getBaseContext(), "Please Check Location Details!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!PERSONALSAVED) {
            Toast.makeText(getBaseContext(), "Please Check Personal Details!", Toast.LENGTH_LONG).show();
            return;
        }
       if (!EDUWORKSAVED) {
           Toast.makeText(getBaseContext(), "Please Check Education/Work Details!", Toast.LENGTH_LONG).show();
           return;
       }
        if (!HEALTHSAVED) {
            Toast.makeText(getBaseContext(), "Please Check Health Details!", Toast.LENGTH_LONG).show();
            return;
        }
            if ((locationFragment.SAVELocation) && (PERSONALSAVED) && (EDUWORKSAVED) && (HEALTHSAVED)) {
                //-------CODE FOR SAVING IN LOCAL SQLITE DB--------------

                //DataBaseHelper DB = new DataBaseHelper(this, null, null, 1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.addData(locationFragment);
                //DB.close();;


                //-----CODE for sending broadcast to InternetChecker RECEIVER--To send Data to SERVER

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                //intentFilter.addAction();
                registerReceiver(internetChecker, intentFilter);

                //-----CODE for sending broadcast to InternetChecker RECEIVER--To send Data to SERVER

                baseFragment = new BaseFragment();
                //locationFragment = new LocationFragment();
                personalFragment = new PersonalFragment();
                healthFragment = new HealthFragment();
                applicationName = new ApplicationName();
                educationWorkFragment = new EducationWorkFragment();

                Toast.makeText(getBaseContext(), "Details Submitted Successfully", Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame1, applicationName);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        }

        if(ENROL_DELL) {
            //Log.d(mess,"->IN DELL");
            //Log.d(mess,"first line");

             if (!DeliveryLocation.SAVELocation) {
                Toast.makeText(getBaseContext(), "Please Check Location Details!", Toast.LENGTH_LONG).show();
                return;
            } /*else if (!preDelivery.PD_SAVED) {
                Toast.makeText(getBaseContext(), "Please Check Pregnancy Details: ", Toast.LENGTH_LONG).show();
            } */
            if (!DELIVERYSAVED) {
                Toast.makeText(getBaseContext(), "Please Check Delivery Details!", Toast.LENGTH_LONG).show();
                return;
            }
            if (!pregPersonal.PPSAVED) {
                Toast.makeText(getBaseContext(), "Please Personal Details!", Toast.LENGTH_LONG).show();
                return;
            }

            if ((DeliveryLocation.SAVELocation) && (pregPersonal.PPSAVED ) && (DELIVERYSAVED)) {
                //Log.d(mess,"Second line");

                //DataBaseHelper DB = new DataBaseHelper(this, null, null, 1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.addData(DeliveryLocation); //----------ADDING DATA TO SQLLITE DATABASE------
              //  DB.setArchiveData(deliveryApplicant.ApplName, ID, "NO");
                //DB.close();;

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                //intentFilter.addAction();
                registerReceiver(internetChecker, intentFilter);

                deliveryApplicant=new ApplicationName();
                //DeliveryLocation=new LocationFragment();
                pregPersonal = new PregPersonal();
                //preDelivery = new PreDelivery();
                delivery = new Delivery();

                childBirthS=new ChildBirth();
                childBirth1=new ChildBirth();
                childBirth2=new ChildBirth();
                childGrowth1=new ChildGrowth();
                childGrowth2=new ChildGrowth();
                childGrowthS=new ChildGrowth();

                //Log.d(mess,"3rd line");


                Toast.makeText(getBaseContext(), "Details Submitted Successfully", Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame1, deliveryApplicant);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                //Log.d(mess,"4th line");

            }
        }

        if(ENROL_CHILD) {
            //Log.d(mess,"->IN Child");
            if((childLocation.SAVELocation)&&(PARENTSSAVED)&&(CHILDGROWTHSAVED)&&(CHILDBIRTHSAVED))
            {
                //Log.d(mess,"->IN Child->AllSAVED");
                //DataBaseHelper DB = new DataBaseHelper(this, null, null, 1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.addData(childLocation); //----------ADDING DATA TO SQLLITE DATABASE------
               // DB.setArchiveData(childApplicant.ApplName, ID, "NO");


                //DB.close();;

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                //intentFilter.addAction();
                registerReceiver(internetChecker, intentFilter);

                childApplicant=new ApplicationName();
                //childLocation=new LocationFragment();
                parents = new Parents();
                childBirth = new ChildBirth();
                childGrowth = new ChildGrowth();

                Toast.makeText(getBaseContext(), "Details Submitted Successfully", Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame1, childApplicant);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
            else if (!childLocation.SAVELocation) {
                Toast.makeText(getBaseContext(), "Please Check Location Details!", Toast.LENGTH_LONG).show();
            } else if (!PARENTSSAVED) {
                Toast.makeText(getBaseContext(), "Please Check Parents Details!", Toast.LENGTH_LONG).show();
            } else if (!CHILDBIRTHSAVED) {
                Toast.makeText(getBaseContext(), "Please Check Child Birth Details!", Toast.LENGTH_LONG).show();
            } else if (!CHILDGROWTHSAVED) {
                Toast.makeText(getBaseContext(), "Please Child Growth Details!", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getBaseContext(), "Please Check Entered Details!", Toast.LENGTH_LONG).show();

        }

        if(ENROL_ANTE)

        {
            //Log.d(mess,"->IN ANTE");
            if((anteLocation.SAVELocation)&&(antePreDelivery.PD_SAVED)&&(antePersonal.PPSAVED))
            {
                //DataBaseHelper DB = new DataBaseHelper(this, null, null, 1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getBaseContext());
                DB.addData(anteLocation); //----------ADDING DATA TO SQLLITE DATABASE------
               // DB.setArchiveData(anteApplicant.ApplName, ID, "NO");


                //DB.close();;

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                //intentFilter.addAction();
                registerReceiver(internetChecker, intentFilter);

                anteApplicant=new ApplicationName();
                //childLocation=new LocationFragment();
                antePersonal=new PregPersonal();
                antePreDelivery=new PreDelivery();

                Toast.makeText(getBaseContext(), "Details Submitted Successfully", Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame1, anteApplicant);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            else if (!anteLocation.SAVELocation) {
                //Log.d("CODE_REACHED_HERE","CODE_REACHED_HERE");
                Toast.makeText(getBaseContext(), "Please Check Location Details!", Toast.LENGTH_LONG).show();
            } else if (!antePersonal.PPSAVED) {
                Toast.makeText(getBaseContext(), "Please Check Personal Details!", Toast.LENGTH_LONG).show();
            } else if (!antePreDelivery.PD_SAVED) {
                Toast.makeText(getBaseContext(), "Please Check Delivery Details!", Toast.LENGTH_LONG).show();
            }  else
                Toast.makeText(getBaseContext(), "Please Check Entered Details!", Toast.LENGTH_LONG).show();

        }
    }

    public void reEnter(View view)

    {
        //Log.d("ENTERED reENTER","1st Line");
        baseFragment=new BaseFragment();

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        if(ENROL_GIRL) {
            applicationName=new ApplicationName();
            //locationFragment=new LocationFragment();
            personalFragment=new PersonalFragment();
            healthFragment=new HealthFragment();
            educationWorkFragment=new EducationWorkFragment();
            fragmentTransaction.replace(R.id.Frame1,applicationName);
        }
        if(ENROL_DELL) {
            deliveryApplicant=new ApplicationName();
           // DeliveryLocation=new LocationFragment();
           // preDelivery = new PreDelivery();
            pregPersonal=new PregPersonal();
            delivery=new Delivery();
            childBirthS=new ChildBirth();
            childBirth1=new ChildBirth();
            childBirth2=new ChildBirth();
            childGrowth1=new ChildGrowth();
            childGrowth2=new ChildGrowth();
            childGrowthS=new ChildGrowth();
            fragmentTransaction.replace(R.id.Frame1,deliveryApplicant);
        }
        if(ENROL_CHILD) {
            childApplicant=new ApplicationName();
          //  childLocation=new LocationFragment();
            parents=new Parents();
            childBirth=new ChildBirth();
            childGrowth=new ChildGrowth();
            fragmentTransaction.replace(R.id.Frame1,childApplicant);
        }

        if(ENROL_ANTE)
        {
            anteApplicant=new ApplicationName();
            antePersonal =new PregPersonal();
            anteLocation=new LocationFragment();
            antePreDelivery=new PreDelivery();
            fragmentTransaction.replace(R.id.Frame1,anteApplicant);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static boolean isEmpty(EditText editText)
    {
        if(editText.getText().toString().trim().length()>0)
            return false;
        else
            return true;
    }

    private String getText(Integer id)
    {
        return (String)((((EditText)findViewById(id)).getText()).toString());
    }

    public void checkLocation(View view)
    {
        String testL=((EditText)findViewById(R.id.editTextState)).getText().toString();
        //Log.d("Inside checkLocation","->"+testL);

        if(
                isEmpty((EditText)findViewById(R.id.editTextFedCode))||
                isEmpty((EditText)findViewById(R.id.editTextState))||
                isEmpty((EditText)findViewById(R.id.editTextDist))
                ||isEmpty((EditText)findViewById(R.id.editTextPanch))
                ||isEmpty((EditText)findViewById(R.id.editTextVillage))
                ||isEmpty((EditText)findViewById(R.id.editTextContact))
                ||isEmpty((EditText)findViewById(R.id.editTextFed))||((EditText)findViewById(R.id.editTextFedCode)).getError()!=null
                ||(((EditText)findViewById(R.id.editTextGirlGroup)).isShown()&&isEmpty((EditText)findViewById(R.id.editTextGirlGroup)))
        )
        {
            //Log.d("checkLocation","Something-empty");
            Toast.makeText(getBaseContext(),"Please Enter Correct Details",Toast.LENGTH_LONG).show();
            if(ENROL_GIRL)
            {  locationFragment.SAVELocation=false;return;}
            if(ENROL_DELL)
            {  DeliveryLocation.SAVELocation=false;return;}
            if(ENROL_CHILD)
            { childLocation.SAVELocation=false;return;}
            if(ENROL_ANTE)
            { anteLocation.SAVELocation=false;return;}
        }
        else {
            if(ENROL_GIRL)
            {
                locationFragment.SAVELocation = true;
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------

                locationFragment.STATE=((EditText)findViewById(R.id.editTextState)).getText().toString();
                locationFragment.DISTRICT=((EditText)findViewById(R.id.editTextDist)).getText().toString();
                locationFragment.FED=((EditText)findViewById(R.id.editTextFed)).getText().toString();
                locationFragment.FEDCODE=((EditText)findViewById(R.id.editTextFedCode)).getText().toString();
                locationFragment.PANCH=((EditText)findViewById(R.id.editTextPanch)).getText().toString();
                locationFragment.VILLAGE=((EditText)findViewById(R.id.editTextVillage)).getText().toString();
                locationFragment.Group=((EditText)findViewById(R.id.editTextGirlGroup)).getText().toString();
                locationFragment.Contact=((EditText)findViewById(R.id.editTextContact)).getText().toString();

                if(((RadioButton)findViewById(R.id.radioButtonSchool)).isChecked())
                {
                    locationFragment.GroupType="School";
                }
                else
                {
                    locationFragment.GroupType="AdlGroup";
                }
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------
            }

            if(ENROL_DELL)
            {
                DeliveryLocation.SAVELocation=true;
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------
                DeliveryLocation.STATE=((EditText)findViewById(R.id.editTextState)).getText().toString();
                DeliveryLocation.DISTRICT=((EditText)findViewById(R.id.editTextDist)).getText().toString();
                DeliveryLocation.FED=((EditText)findViewById(R.id.editTextFed)).getText().toString();
                DeliveryLocation.FEDCODE=((EditText)findViewById(R.id.editTextFedCode)).getText().toString();
                DeliveryLocation.PANCH=((EditText)findViewById(R.id.editTextPanch)).getText().toString();
                DeliveryLocation.VILLAGE=((EditText)findViewById(R.id.editTextVillage)).getText().toString();
                DeliveryLocation.Contact=((EditText)findViewById(R.id.editTextContact)).getText().toString();
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------


            }

            if(ENROL_CHILD)
            {
                childLocation.SAVELocation=true;
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------
                childLocation.STATE=((EditText)findViewById(R.id.editTextState)).getText().toString();
                childLocation.DISTRICT=((EditText)findViewById(R.id.editTextDist)).getText().toString();
                childLocation.FED=((EditText)findViewById(R.id.editTextFed)).getText().toString();
                childLocation.FEDCODE=((EditText)findViewById(R.id.editTextFedCode)).getText().toString();
                childLocation.PANCH=((EditText)findViewById(R.id.editTextPanch)).getText().toString();
                childLocation.VILLAGE=((EditText)findViewById(R.id.editTextVillage)).getText().toString();
                childLocation.Contact=((EditText)findViewById(R.id.editTextContact)).getText().toString();

                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------

            }

            if(ENROL_ANTE)
            {
                anteLocation.SAVELocation=true;
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------
                anteLocation.STATE=((EditText)findViewById(R.id.editTextState)).getText().toString();
                anteLocation.DISTRICT=((EditText)findViewById(R.id.editTextDist)).getText().toString();
                anteLocation.FED=((EditText)findViewById(R.id.editTextFed)).getText().toString();
                anteLocation.FEDCODE=((EditText)findViewById(R.id.editTextFedCode)).getText().toString();
                anteLocation.PANCH=((EditText)findViewById(R.id.editTextPanch)).getText().toString();
                anteLocation.VILLAGE=((EditText)findViewById(R.id.editTextVillage)).getText().toString();
                anteLocation.Contact=((EditText)findViewById(R.id.editTextContact)).getText().toString();
                //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------
            }


            Toast.makeText(getBaseContext(), "Location Details Saved!", Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.Frame1, baseFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }



    public void clearApplicationName(View view)
    {       //Log.d("MMMM","Inside clickOnPressed");
            ((EditText)findViewById(R.id.EditTextApplName)).setText("");

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
//        unregisterReceiver(new InternetChecker());
    }

    public void clearLocation(View view)
    {

    //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    if(ENROL_GIRL)
    {
        locationFragment.SAVELocation=false;
        locationFragment=new LocationFragment();
        fragmentTransaction.replace(R.id.Frame1, locationFragment);
    }
    if(ENROL_DELL)
    {
        DeliveryLocation.SAVELocation=false;
        DeliveryLocation=new LocationFragment();
        fragmentTransaction.replace(R.id.Frame1, DeliveryLocation);

    }
    if(ENROL_CHILD)
    {
        childLocation.SAVELocation=false;
        childLocation=new LocationFragment();
        fragmentTransaction.replace(R.id.Frame1, childLocation);


    }
    if(ENROL_ANTE)
    {
            anteLocation.SAVELocation=false;
            anteLocation=new LocationFragment();
            fragmentTransaction.replace(R.id.Frame1, anteLocation);
    }


        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void clearPersonal(View view)
    {   //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
        PERSONALSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(locationFragment);
        personalFragment=new PersonalFragment();
        fragmentTransaction.replace(R.id.Frame1, personalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void clearPreDelivery(View view)
    {   //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       /* if(ENROL_DELL)
        {
            preDelivery.PD_SAVED=false;
            preDelivery=new PreDelivery();
            fragmentTransaction.replace(R.id.Frame1, preDelivery);

        }*/

        if(ENROL_ANTE)
        {
            antePreDelivery.PD_SAVED=false;
            antePreDelivery=new PreDelivery();
            fragmentTransaction.replace(R.id.Frame1, antePreDelivery);

        }

        // fragmentTransaction.remove(locationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void clearDelivery(View view)
    {   //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
        DELIVERYSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(locationFragment);
        delivery=new Delivery();
        fragmentTransaction.replace(R.id.Frame1, delivery);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void clearParents(View view)
    {   //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
        PARENTSSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(locationFragment);
        parents=new Parents();
        fragmentTransaction.replace(R.id.Frame1, parents);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void clearChildBirth(View view)
    {
        CHILDBIRTHSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(locationFragment);
        childBirth=new ChildBirth();
        fragmentTransaction.replace(R.id.Frame1, childBirth);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void clearChildGrowth(View view)
    {
        CHILDGROWTHSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(locationFragment);
        childGrowth=new ChildGrowth();
        fragmentTransaction.replace(R.id.Frame1, childGrowth);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void clearPregPersonal(View view)
    {   //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(ENROL_DELL) {
            pregPersonal.PPSAVED = false;
            pregPersonal = new PregPersonal();
            fragmentTransaction.replace(R.id.Frame1, pregPersonal);
        }
        if(ENROL_ANTE) {

            antePersonal.PPSAVED = false;
            antePersonal = new PregPersonal();
            fragmentTransaction.replace(R.id.Frame1,antePersonal);
        }
        //PERSONALPREGSAVED=false;

        // fragmentTransaction.remove(locationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

public  void Refresh(View view)
{
    //Log.d("REFRESH","Inside it");
    // new MainActivity();
   // getActivity().recreate();
    recreate();
    //finish();

    // getActivity().finish();

}


    public void clearHealth(View view)
    {   //Toast.makeText(getBaseContext(),"Entered Clear Location",Toast.LENGTH_LONG).show();
        HEALTHSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(locationFragment);
        healthFragment=new HealthFragment();
        fragmentTransaction.replace(R.id.Frame1, healthFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void clearEduWork(View view)
    {
        EDUWORKSAVED=false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        educationWorkFragment=new EducationWorkFragment();
        fragmentTransaction.replace(R.id.Frame1, educationWorkFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void checkPersonal(View view)
    {
        int YOM=0;
        int yDOB=0;
        Calendar c= Calendar.getInstance();
        if(!checkdate((EditText)findViewById(R.id.editTextDob)))
            return;
        else
        {
            yDOB=Integer.valueOf(((EditText)findViewById(R.id.editTextDob)).getText().toString().split("-")[2]);
        }

        if (!isEmpty((EditText)findViewById(R.id.editTextMarrYear)))
        {
            YOM=Integer.parseInt(((EditText) findViewById(R.id.editTextMarrYear)).getText().toString());
        }

        else if(isEmpty((EditText)findViewById(R.id.editTextName))
                ||isEmpty((EditText)findViewById(R.id.editTextMother))
                ||isEmpty((EditText)findViewById(R.id.editTextFather)))
        {
            Toast.makeText(getBaseContext(),"Please Enter Correct Details!",Toast.LENGTH_LONG).show();
            PERSONALSAVED=false;
            return;
        }
        else if ((((RadioButton)findViewById(R.id.radioButtonYes)).isChecked()) &&
                (YOM<yDOB||YOM>c.get(Calendar.YEAR)||YOM==0))
        {
            Toast.makeText(getBaseContext(),"Enter Correct Year of Marriage!",Toast.LENGTH_LONG).show();
            PERSONALSAVED=false;
            return;

        }

        String text="Select Applicable Programme";
        if(
                ((Spinner)findViewById(R.id.spinnerMomMember)).getSelectedItem().toString().equals(text)||
                ((Spinner)findViewById(R.id.spinnerDadMember)).getSelectedItem().toString().equals(text)
        )
        {
            Toast.makeText(getBaseContext(),"Select Correct Option!",Toast.LENGTH_LONG).show();
            PERSONALSAVED=false;
            return;

        }



        else {

            if (HEALTHSAVED) {
                HealthFragment.YOP=Integer.parseInt(HealthFragment.EditPuberty.getText().toString());
               if(HealthFragment.YOP<=yDOB)
               {
                   HEALTHSAVED=false;
               }
            }
            PersonalFragment.YOB=yDOB;
            Toast.makeText(getBaseContext(), "Personal Details Saved!", Toast.LENGTH_LONG).show();
            PERSONALSAVED = true;

            //---------STORING EDITABLE TEXT INTO STATIC MEMBERS---------

            PersonalFragment.Name= ((EditText)findViewById(R.id.editTextName)).getText().toString();
            PersonalFragment.Mother= ((EditText)findViewById(R.id.editTextMother)).getText().toString();
            PersonalFragment.Father= ((EditText)findViewById(R.id.editTextFather)).getText().toString();
            PersonalFragment.DOB=((EditText)findViewById(R.id.editTextDob)).getText().toString();
            PersonalFragment.MotherMem=((Spinner)findViewById(R.id.spinnerMomMember)).getSelectedItem().toString();
            PersonalFragment.FatherMem=((Spinner)findViewById(R.id.spinnerDadMember)).getSelectedItem().toString();

            if(!PersonalFragment.ISMARRIED)
            PersonalFragment.MarrYear="NULL";
            else
            PersonalFragment.MarrYear=((EditText)findViewById(R.id.editTextMarrYear)).getText().toString();

            //---------STORING EDITABLE TEXT INTO STATIC MEMBERS---------

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.Frame1, baseFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public boolean checkdate (EditText editText)
    {
        Calendar c;String DOB="";int yDOB=0;int dDOB=0;int mDOB=0;Boolean DOBFORMAT=false;
        c= Calendar.getInstance();

        if(isEmpty(editText))
        {
            Toast.makeText(getBaseContext(),"Please Enter Date of Birth!",Toast.LENGTH_LONG).show();
            return false;
        }
       DOB = editText.getText().toString();
        //DD-MM-YYYY

        if(!(DOB.length()==10)||!(DOB.substring(2, 3).equals("-"))||!(DOB.substring(5, 6).equals("-"))) {
            Toast.makeText(getBaseContext(),"Please Enter Correct Date Format(DD-MM-YYYY)",Toast.LENGTH_LONG).show();
            return false;
        }
        yDOB = Integer.valueOf(DOB.split("-")[2]);
        dDOB = Integer.valueOf(DOB.split("-")[0]);
        mDOB = Integer.valueOf(DOB.split("-")[1]);

        if (yDOB<1900 || yDOB>c.get(Calendar.YEAR)) {
            Toast.makeText(getBaseContext(),"Please Check Date Year",Toast.LENGTH_LONG).show();
            return false;
        }

        if (mDOB<1 || mDOB>12){
            Toast.makeText(getBaseContext(),"Please Check Date Month",Toast.LENGTH_LONG).show();
            return false;
        }


        else if (dDOB<1 || dDOB>31 || (dDOB>30 && (mDOB==4||mDOB==6||mDOB==9||mDOB==11))||(dDOB>29&&mDOB==2)||(dDOB>28 &&mDOB==2 &&(yDOB%4!=0)))//2,4,6,9,11 and Leap Years
        {
            Toast.makeText(getBaseContext(),"Please Enter Correct Date",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    public void checkPregPersonal(View view)
    {

        if(isEmpty((EditText)findViewById(R.id.editTextNameP))
            ||isEmpty((EditText)findViewById(R.id.editTextHusband))
            ||isEmpty((EditText)findViewById(R.id.editTextDobP))
            ||(((Spinner)findViewById(R.id.spinnerEntryPregEdu)).getSelectedItem().toString().equals("Select Your Education Level:"))
            ||(((Spinner)findViewById(R.id.spinnerPregHusband)).getSelectedItem().toString().equals("Select Applicable Programme"))
         )
                {
                    Toast.makeText(getBaseContext(),"Please Enter Correct Details",Toast.LENGTH_LONG).show();
                    //PERSONALPREGSAVED=false;
                    if(ENROL_DELL)
                        pregPersonal.PPSAVED=false;
                    if(ENROL_ANTE)
                        antePersonal.PPSAVED=false;
                }
                else
         {
             if(!checkdate((EditText)findViewById(R.id.editTextDobP)))
                 return;

            // PERSONALPREGSAVED = true;

             if(ENROL_DELL) {
                 pregPersonal.PPSAVED = true;
                 pregPersonal.Name = ((EditText) findViewById(R.id.editTextNameP)).getText().toString();
                 pregPersonal.Husband = ((EditText) findViewById(R.id.editTextHusband)).getText().toString();
                 pregPersonal.DOB = ((EditText) findViewById(R.id.editTextDobP)).getText().toString();
                 pregPersonal.Education = ((Spinner) findViewById(R.id.spinnerEntryPregEdu)).getSelectedItem().toString();
                 pregPersonal.HusbandMem = ((Spinner) findViewById(R.id.spinnerPregHusband)).getSelectedItem().toString();
             }
             if(ENROL_ANTE)
             {
                 antePersonal.PPSAVED = true;

                 antePersonal.Name = ((EditText) findViewById(R.id.editTextNameP)).getText().toString();
                 antePersonal.Husband = ((EditText) findViewById(R.id.editTextHusband)).getText().toString();
                 antePersonal.DOB = ((EditText) findViewById(R.id.editTextDobP)).getText().toString();
                 antePersonal.Education = ((Spinner) findViewById(R.id.spinnerEntryPregEdu)).getSelectedItem().toString();
                 antePersonal.HusbandMem = ((Spinner) findViewById(R.id.spinnerPregHusband)).getSelectedItem().toString();


             }
             Toast.makeText(getBaseContext(), "Personal Details Saved!", Toast.LENGTH_LONG).show();
             FragmentManager fragmentManager = getSupportFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.Frame1, baseFragment);
             fragmentTransaction.addToBackStack(null);
             fragmentTransaction.commit();
         }
    }

    public void checkPreDelivery(View view)
    {
        if(isEmpty((EditText)findViewById(R.id.editTextLMP))
                ||isEmpty((EditText)findViewById(R.id.editTextEDD))
                ||isEmpty((EditText)findViewById(R.id.editTextGravida))
                ||isEmpty((EditText)findViewById(R.id.editTextPHC))
                ||isEmpty((EditText)findViewById(R.id.editTextMP)))
        {
            Toast.makeText(getBaseContext(),"Please Enter Correct Details",Toast.LENGTH_LONG).show();
          //  if(ENROL_DELL)
          //  preDelivery.PD_SAVED=false;
            if(ENROL_ANTE)
                antePreDelivery.PD_SAVED=false;
        }
        else
        {
           /* if(ENROL_DELL) {


                preDelivery.PD_SAVED = true;

                preDelivery.LMP = ((EditText) findViewById(R.id.editTextLMP)).getText().toString();
                preDelivery.EDD = ((EditText) findViewById(R.id.editTextEDD)).getText().toString();
                preDelivery.GRAVIDA = ((EditText) findViewById(R.id.editTextGravida)).getText().toString();
                preDelivery.PHC = ((EditText) findViewById(R.id.editTextPHC)).getText().toString();
                preDelivery.MONTHPREG = ((EditText) findViewById(R.id.editTextMP)).getText().toString();
            }*/

            if(ENROL_ANTE)
            {
                antePreDelivery.PD_SAVED = true;

                antePreDelivery.LMP = ((EditText) findViewById(R.id.editTextLMP)).getText().toString();
                antePreDelivery.EDD = ((EditText) findViewById(R.id.editTextEDD)).getText().toString();
                antePreDelivery.GRAVIDA = ((EditText) findViewById(R.id.editTextGravida)).getText().toString();
                antePreDelivery.PHC = ((EditText) findViewById(R.id.editTextPHC)).getText().toString();
                antePreDelivery.MONTHPREG = ((EditText) findViewById(R.id.editTextMP)).getText().toString();

            }

            Toast.makeText(getBaseContext(), "Pregnancy Details Saved!", Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.Frame1, baseFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

   public void checkDelivery(View view)
   {
       String TempOutcome="";
       DELIVERYSAVED=true;
       String mess="IN-checkDelivery";
       //Log.d(mess,"Start of funtion");

       if(isEmpty((EditText)findViewById(R.id.editTextDelGravida)))
       {
           Toast.makeText(getBaseContext(),"Enter Gravida",Toast.LENGTH_LONG).show();
           DELIVERYSAVED=false;
           return;
       }
       else
           delivery.Gravida=((EditText)findViewById(R.id.editTextDelGravida)).getText().toString();


      if(!((AppCompatCheckBox)findViewById(R.id.checkNormal)).isChecked()&&
              !((AppCompatCheckBox)findViewById(R.id.checkAbortion)).isChecked()&&
              !((AppCompatCheckBox)findViewById(R.id.checkMMR)).isChecked()&&
              !((AppCompatCheckBox)findViewById(R.id.checkStill)).isChecked())
       {
           Toast.makeText(getBaseContext(),"Please Tick a Box",Toast.LENGTH_LONG).show();
           DELIVERYSAVED=false;
           return;
       }
      if(((AppCompatCheckBox)findViewById(R.id.checkNormal)).isChecked())
      {
          TempOutcome+="CB";

          //Log.d(mess,"value of TempOutcome->"+TempOutcome);
      }
       if(((AppCompatCheckBox)findViewById(R.id.checkAbortion)).isChecked())
       {
           if(isEmpty((EditText)findViewById(R.id.editTextDelAbortion)))
           {
               Toast.makeText(getBaseContext(),"Enter Abortion Details",Toast.LENGTH_LONG).show();
               DELIVERYSAVED=false;
               return;
           }

           TempOutcome+="AB";
           delivery.aborMonth=((EditText)findViewById(R.id.editTextDelAbortion)).getText().toString();
       }
       //Log.d("Check Switch5",((SwitchCompat)findViewById(R.id.switchkids)).getText().toString());
       if(((AppCompatCheckBox)findViewById(R.id.checkStill)).isChecked())
       {
           TempOutcome+="SB";
       }
       if(((AppCompatCheckBox)findViewById(R.id.checkMMR)).isChecked())
       {
           if(!((AppCompatCheckBox)findViewById(R.id.checkNormal)).isChecked()&&
                   !((AppCompatCheckBox)findViewById(R.id.checkStill)).isChecked())
           {
               Toast.makeText(getBaseContext(),"Tick Right Choice",Toast.LENGTH_LONG).show();
               return;
           }

           TempOutcome+="|MD";
       }

       //Log.d("check box OP->",TempOutcome);
       delivery.Outcome = TempOutcome;

       //Log.d("Check Switch4",((SwitchCompat)findViewById(R.id.switchkids)).getText().toString());
       if(((AppCompatCheckBox)findViewById(R.id.checkNormal)).isChecked())
       {
           if((((RadioGroup)findViewById(R.id.radioGroupDelPlace)).getCheckedRadioButtonId()==-1 )||
                   (((RadioGroup)findViewById(R.id.radioGroupDelStatus)).getCheckedRadioButtonId()==-1 )||
                   (((RadioGroup)findViewById(R.id.radioGroupDelCol)).getCheckedRadioButtonId()==-1 ))
       {
           Toast.makeText(getBaseContext(), "Please select a Choice!", Toast.LENGTH_LONG).show();
           DELIVERYSAVED=false;
           return;
       }


           if(((RadioButton)findViewById(R.id.radioButtonHome)).isChecked())
               delivery.Place="HOME";
           else
               delivery.Place="HOSPITAL";

           if(((RadioButton)findViewById(R.id.radioButtonNor)).isChecked())
               delivery.Status="NORMAL";
           else
               delivery.Status="CAESARIAN";

           if(((RadioButton)findViewById(R.id.radioButtonCol_Y)).isChecked())
               delivery.Colostrum="YES";
           else
               delivery.Colostrum="NO";

           if(!checkdate((EditText)findViewById(R.id.editTextDelDob)))
           {
               DELIVERYSAVED=false;
               return;
           }

           delivery.EDD=((EditText)findViewById(R.id.editTextDelDob)).getText().toString();

           //String babyAge=delivery.EDD;

            if(!deliveryApplicant.ID_ENTERED) {
                //Log.d("Twins?->", (((SwitchCompat) findViewById(R.id.switchkids)).isChecked()) ? "yes" : "no");

                if (!((SwitchCompat) findViewById(R.id.switchkids)).isChecked()) {
                    //Log.d("SWITCH ", "->NO");
                    if (isEmpty((EditText) findViewById(R.id.editTextDelChildBWeightS)) ||
                            isEmpty((EditText) findViewById(R.id.editTextDelChildOrderS)) ||
                            isEmpty((EditText) findViewById(R.id.editTextDelChildheightS)) ||
                            isEmpty((EditText) findViewById(R.id.editTextDelChildWeightNowS))) {
                        Toast.makeText(getBaseContext(), "Enter Correct Details", Toast.LENGTH_LONG).show();
                        DELIVERYSAVED = false;
                        return;
                    }

                    if (((RadioGroup) findViewById(R.id.radioGroupDelSexS)).getCheckedRadioButtonId() == -1) {
                       // Toast.makeText(getBaseContext(), "Enter Choose Gender", Toast.LENGTH_LONG).show();
                        DELIVERYSAVED = false;
                        return;
                    }
                    delivery.twins = "NO";

                    childBirthS.ChildName = null;
                    childBirthS.ChildDOB = ((EditText) findViewById(R.id.editTextDelDob)).getText().toString();
                    childBirthS.ChildWeight = ((EditText) findViewById(R.id.editTextDelChildBWeightS)).getText().toString();
                    if (((RadioButton) findViewById(R.id.radioButtonCol_Y)).isChecked())
                        childBirthS.ChildCol = "YES";
                    else
                        childBirthS.ChildCol = "NO";

                    if (((RadioButton) findViewById(R.id.radioButtonMaleS)).isChecked()) {
                        childBirthS.ChildGender = "MALE";
                        //Log.d(mess, "Its Male");
                    } else {
                        childBirthS.ChildGender = "FEMALE";
                        //Log.d(mess, "Its FEmale");
                    }

                    childGrowthS.Height = ((EditText) findViewById(R.id.editTextDelChildheightS)).getText().toString();
                    childGrowthS.Order = ((EditText) findViewById(R.id.editTextDelChildOrderS)).getText().toString();
                    childGrowthS.Weight = ((EditText) findViewById(R.id.editTextDelChildWeightNowS)).getText().toString();

                } else {

                    //Log.d("IN SWITCH ss", "->YES");

                    if (
                            isEmpty((EditText) findViewById(R.id.editTextDelChildBWeight1)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildBWeight2)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildOrder1)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildOrder2)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildheight1)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildheight2)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildWeightNow1)) ||
                                    isEmpty((EditText) findViewById(R.id.editTextDelChildWeightNow2))) {
                        Toast.makeText(getBaseContext(), "Enter Correct Details", Toast.LENGTH_LONG).show();
                        DELIVERYSAVED = false;
                        return;
                    }

                    if
                    (((RadioGroup) findViewById(R.id.radioGroupDelSex1)).getCheckedRadioButtonId() == -1 ||
                            (((RadioGroup) findViewById(R.id.radioGroupDelSex2)).getCheckedRadioButtonId() == -1)) {
                       // Toast.makeText(getBaseContext(), "Enter Choose Gender", Toast.LENGTH_LONG).show();
                        DELIVERYSAVED = false;
                        return;
                    }

                    delivery.twins = "YES";

//--------DETAILS FOR TWIN 1-------------------

                    childBirth1.ChildName = null;
                    childBirth1.ChildDOB = ((EditText) findViewById(R.id.editTextDelDob)).getText().toString();
                    childBirth1.ChildWeight = ((EditText) findViewById(R.id.editTextDelChildBWeight1)).getText().toString();
                    if (((RadioButton) findViewById(R.id.radioButtonCol_Y)).isChecked())
                        childBirth1.ChildCol = "YES";
                    else
                        childBirth1.ChildCol = "NO";

                    if (((RadioButton) findViewById(R.id.radioButtonMale1)).isChecked())
                        childBirth1.ChildGender = "MALE";
                    else
                        childBirth1.ChildGender = "FEMALE";

                    childGrowth1.Height = ((EditText) findViewById(R.id.editTextDelChildheight1)).getText().toString();
                    childGrowth1.Order = ((EditText) findViewById(R.id.editTextDelChildOrder1)).getText().toString();
                    childGrowth1.Weight = ((EditText) findViewById(R.id.editTextDelChildWeightNow1)).getText().toString();


//--------DETAILS FOR TWIN 1-------------------


//--------DETAILS FOR TWIN 2-------------------


                    childBirth2.ChildName = null;
                    childBirth2.ChildDOB = ((EditText) findViewById(R.id.editTextDelDob)).getText().toString();
                    childBirth2.ChildWeight = ((EditText) findViewById(R.id.editTextDelChildBWeight2)).getText().toString();
                    if (((RadioButton) findViewById(R.id.radioButtonCol_Y)).isChecked())
                        childBirth2.ChildCol = "YES";
                    else
                        childBirth2.ChildCol = "NO";

                    if (((RadioButton) findViewById(R.id.radioButtonMale2)).isChecked())
                        childBirth2.ChildGender = "MALE";
                    else
                        childBirth2.ChildGender = "FEMALE";

                    childGrowth2.Height = ((EditText) findViewById(R.id.editTextDelChildheight2)).getText().toString();
                    childGrowth2.Order = ((EditText) findViewById(R.id.editTextDelChildOrder2)).getText().toString();
                    childGrowth2.Weight = ((EditText) findViewById(R.id.editTextDelChildWeightNow2)).getText().toString();


//--------DETAILS FOR TWIN 2-------------------


                }
            }
            else
            {
                try {
                    delivery.twins = deliveryApplicant.jsonObject.get("TWINS").toString();
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }

       }


           Toast.makeText(getBaseContext(), "Delivery Details Saved!", Toast.LENGTH_LONG).show();
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.Frame1, baseFragment);
           fragmentTransaction.addToBackStack(null);
           fragmentTransaction.commit();



   }


   public void checkParents(View view)
   {PARENTSSAVED=false;
       String mess="checkparents";
       //Log.d(mess,"FirstLine");
       String text="Select Applicable Programme";

       if(
               isEmpty((EditText)findViewById(R.id.editTextNameMom))||
               isEmpty((EditText)findViewById(R.id.editTextMomDoB))||
               isEmpty((EditText)findViewById(R.id.editTextFather))||
                       ((Spinner)findViewById(R.id.spinnerParMemberM)).getSelectedItem().toString().equals(text)||
                       ((Spinner)findViewById(R.id.spinnerParMemberF)).getSelectedItem().toString().equals(text)
       )
       {
           //Log.d(mess,"SecondLine");
           PARENTSSAVED=false;
           Toast.makeText(getBaseContext(),"Please Enter Correct Values",Toast.LENGTH_LONG).show();
           return;
       }
       else
       {
           //Log.d(mess,"3rd Line");
           if(!checkdate((EditText)findViewById(R.id.editTextMomDoB)))
               return;
           //Log.d(mess,"4th Line");
           PARENTSSAVED=true;
           Parents.MomName=((EditText)findViewById(R.id.editTextNameMom)).getText().toString();
           Parents.MomDoB=((EditText)findViewById(R.id.editTextMomDoB)).getText().toString();
           Parents.FatherName=((EditText)findViewById(R.id.editTextFather)).getText().toString();
           Parents.MomMem=((Spinner)findViewById(R.id.spinnerParMemberM)).getSelectedItem().toString();
           Parents.DadMem=((Spinner)findViewById(R.id.spinnerParMemberF)).getSelectedItem().toString();

               if (isEmpty((EditText)findViewById(R.id.editTextMomId))) {
                   Parents.MomId = null;
               } else {
                   Parents.MomId = ((EditText)findViewById(R.id.editTextMomId)).getText().toString();
               }

           //Log.d(mess,"5thLine");
           Toast.makeText(getBaseContext(), "Parent Details Saved!", Toast.LENGTH_LONG).show();
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.Frame1, baseFragment);
           fragmentTransaction.addToBackStack(null);
           fragmentTransaction.commit();
       }
   }

   public void checkChildBirth(View view)
   {CHILDBIRTHSAVED=false;
       if(
               isEmpty((EditText)findViewById(R.id.editTextChildName))||
                       isEmpty((EditText)findViewById(R.id.editTextChildBWeight))||
                       isEmpty((EditText)findViewById(R.id.editTextChildDob))||
                       (((RadioGroup)findViewById(R.id.radioGroupChildGender)).getCheckedRadioButtonId()==-1 )||
                       (((RadioGroup)findViewById(R.id.radioGroupChildCol)).getCheckedRadioButtonId()==-1 )
       )
       {
           CHILDBIRTHSAVED=false;
           Toast.makeText(getBaseContext(),"Please Enter or Choose Correct Values",Toast.LENGTH_LONG).show();
           return;
       }
       else
       {
           if(!checkdate((EditText)findViewById(R.id.editTextChildDob)))
               return;

           CHILDBIRTHSAVED=true;

           childBirth.ChildName=((EditText)findViewById(R.id.editTextChildName)).getText().toString();
           childBirth.ChildWeight=((EditText)findViewById(R.id.editTextChildBWeight)).getText().toString();
           childBirth.ChildDOB=((EditText)findViewById(R.id.editTextChildDob)).getText().toString();

           if(((RadioButton)findViewById(R.id.radioButtonChildMale)).isChecked())
               childBirth.ChildGender="MALE";
           else
               childBirth.ChildGender="FEMALE";

           if(((RadioButton)findViewById(R.id.radioButtonChildCol_Y)).isChecked())
               childBirth.ChildCol="YES";
           else
               childBirth.ChildCol="NO";


           Toast.makeText(getBaseContext(), "Child Birth Details Saved!", Toast.LENGTH_LONG).show();
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.Frame1, baseFragment);
           fragmentTransaction.addToBackStack(null);
           fragmentTransaction.commit();
       }

   }

   public void checkChildGrowth(View view)
   { CHILDGROWTHSAVED=false;
       if(
               isEmpty((EditText)findViewById(R.id.editTextChildOrder))||
                       isEmpty((EditText)findViewById(R.id.editTextChildHeight))||
                       isEmpty((EditText)findViewById(R.id.editTextChildWeight))
       )
       {
           CHILDGROWTHSAVED=false;
           Toast.makeText(getBaseContext(),"Please Enter Correct Values",Toast.LENGTH_LONG).show();
           return;
       }
       else {
           CHILDGROWTHSAVED = true;

           childGrowth.Order=((EditText) findViewById(R.id.editTextChildOrder)).getText().toString();
           childGrowth.Height=((EditText) findViewById(R.id.editTextChildHeight)).getText().toString();
           childGrowth.Weight=((EditText) findViewById(R.id.editTextChildWeight)).getText().toString();
           //childGrowth.Age=((EditText) findViewById(R.id.editTextChildAge)).getText().toString();

           Toast.makeText(getBaseContext(), "Child Growth Details Saved!", Toast.LENGTH_LONG).show();
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.Frame1, baseFragment);
           fragmentTransaction.addToBackStack(null);
           fragmentTransaction.commit();

       }



       }

    public void checkEducation(View view)
    {
        String SpinnerTextHE=((Spinner)findViewById(R.id.spinnerHE)).getSelectedItem().toString();
        String SpinnerTextOcc=((Spinner)findViewById(R.id.spinnerOcc)).getSelectedItem().toString();
        String[] EducationArray = getResources().getStringArray(R.array.spinnerEntryHE);
        String[] WorkArray = getResources().getStringArray(R.array.spinnerEntryOcc);

        if(SpinnerTextHE.equals(EducationArray[0])||SpinnerTextHE.equals("Select the Class Currently Studying:")||SpinnerTextOcc.equals(WorkArray[0]))
        {
            Toast.makeText(getBaseContext(),"Please Choose Correct Option",Toast.LENGTH_LONG).show();

        }
        else
        {EDUWORKSAVED=true;

            //---------STORING EDITABLE TEXT INTO STATIC MEMBERS---------

            EducationWorkFragment.WORK=((Spinner)findViewById(R.id.spinnerHE)).getSelectedItem().toString();
            EducationWorkFragment.EDUCATION=((Spinner)findViewById(R.id.spinnerOcc)).getSelectedItem().toString();

            if(((RadioButton)findViewById(R.id.radioButtonENo)).isChecked())
                EducationWorkFragment.STUDY="NO";
            else
                EducationWorkFragment.STUDY="YES";


            //---------STORING EDITABLE TEXT INTO STATIC MEMBERS---------



            Toast.makeText(getBaseContext(), "Education & Work Details Saved!", Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.Frame1,baseFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void checkHealth(View view) {
            int YOP=0;
            Calendar c=Calendar.getInstance();
            if(!isEmpty((EditText) findViewById(R.id.editTextPuberty)))
            YOP=Integer.parseInt(((EditText) findViewById(R.id.editTextPuberty)).getText().toString());

        if (isEmpty((EditText) findViewById(R.id.editTextWeight))
                || isEmpty((EditText) findViewById(R.id.editTextHeight))
                || isEmpty((EditText) findViewById(R.id.editTextHB)))
        {
            Toast.makeText(getBaseContext(), "Please Enter Correct Details!", Toast.LENGTH_LONG).show();
        }
        //Logic for PubertyCheck
        else if ((((RadioButton)findViewById(R.id.radioButtonPYes)).isChecked())&&(YOP<=personalFragment.YOB||YOP>c.get(Calendar.YEAR)||YOP==0))
        {
            Toast.makeText(getBaseContext(), "Enter Valid Year of Puberty!", Toast.LENGTH_LONG).show();
        }

        else
        {HealthFragment.YOP=YOP;
            HEALTHSAVED=true;
            //---------STORING EDITABLE TEXT INTO STATIC MEMBERS---------
            if((((RadioButton)findViewById(R.id.radioButtonPYes)).isChecked()))
            HealthFragment.PubertyYear=((EditText)findViewById(R.id.editTextPuberty)).getText().toString();
            else
                HealthFragment.PubertyYear=null;
            HealthFragment.HB=((EditText)findViewById(R.id.editTextHB)).getText().toString();
            HealthFragment.WEIGHT=((EditText)findViewById(R.id.editTextWeight)).getText().toString();
            HealthFragment.HEIGHT=((EditText)findViewById(R.id.editTextHeight)).getText().toString();


            //---------STORING EDITABLE TEXT INTO STATIC MEMBERS---------

            Toast.makeText(getBaseContext(), "Health Details Saved!", Toast.LENGTH_LONG).show();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.Frame1, baseFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

 public void switchFragment (View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {

           /* case R.id.imageViewPregnant:
                if(ENROL_DELL)
                    fragmentTransaction.replace(R.id.Frame1,preDelivery);

                break;*/

            case R.id.imageViewPreNatal:
                fragmentTransaction.replace(R.id.Frame1,antePreDelivery);
                break;

            case R.id.imageViewBabyDelivery:
                fragmentTransaction.replace(R.id.Frame1,delivery);
                break;

            case R.id.imageViewParents:
                fragmentTransaction.replace(R.id.Frame1,parents);
                break;

            case R.id.imageViewChildBirth:
                fragmentTransaction.replace(R.id.Frame1,childBirth);
                break;

            case R.id.imageViewBabyGrowth:
                fragmentTransaction.replace(R.id.Frame1,childGrowth);
                break;


            case R.id.imageViewPersonal:

                fragmentTransaction.replace(R.id.Frame1,personalFragment,"PerFrag");
                break;

            case R.id.imageViewPersonalPreg:

                if(ENROL_DELL)
                    fragmentTransaction.replace(R.id.Frame1,pregPersonal,"Preg-PerFrag");
                if(ENROL_ANTE)
                    fragmentTransaction.replace(R.id.Frame1,antePersonal);

                break;

            case R.id.imageViewEducation:

                fragmentTransaction.replace(R.id.Frame1,educationWorkFragment);
                break;

            case R.id.imageViewHealth:

                fragmentTransaction.replace(R.id.Frame1,healthFragment);
                break;

            case R.id.imageViewLocation:
                if(ENROL_GIRL)
                fragmentTransaction.replace(R.id.Frame1,locationFragment);
                if(ENROL_DELL)
                    fragmentTransaction.replace(R.id.Frame1,DeliveryLocation);
                if(ENROL_CHILD)
                    fragmentTransaction.replace(R.id.Frame1,childLocation);
                if(ENROL_ANTE)
                    fragmentTransaction.replace(R.id.Frame1,anteLocation);

                //fragmentTransaction.replace(R.id.Frame1,anLocation);

                break;

        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void clickonRadio(View view)
    {
        if(((RadioButton)findViewById(R.id.radioButtonNo)).isChecked())
        {personalFragment.ISMARRIED=false;
            ((EditText)findViewById(R.id.editTextMarrYear)).setText(null);
            findViewById(R.id.editTextMarrYear).setVisibility(View.INVISIBLE);
            //findViewById(R.id.textViewMarrYear).setVisibility(View.INVISIBLE);
        }
        if(((RadioButton)findViewById(R.id.radioButtonYes)).isChecked())
        {personalFragment.ISMARRIED=true;
            findViewById(R.id.editTextMarrYear).setVisibility(View.VISIBLE);
            //findViewById(R.id.textViewMarrYear).setVisibility(View.VISIBLE);
        }


    }

    public void clickonEduRadio(View view)
    {
        Spinner Sp=findViewById(R.id.spinnerHE);
/*ArrayAdapter<String> spinneradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,android.R.id.text1);
spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
Sp.setAdapter(spinneradapter);*/
String[] myEducationArray=getResources().getStringArray(R.array.spinnerEntryHE);
        if(((RadioButton)findViewById(R.id.radioButtonENo)).isChecked())
        {
            myEducationArray[0]="Select Highest Class Studied:";
        }
        if(((RadioButton)findViewById(R.id.radioButtonEYes)).isChecked())
        {
            myEducationArray[0]="Select the Class Currently Studying:";

        }

        ArrayAdapter<String>  arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,myEducationArray);
Sp.setAdapter(arrayAdapter);

    }

    public void  clickonHealthRadio(View view)
    {
        if(((RadioButton)findViewById(R.id.radioButtonPNo)).isChecked())
        {
            findViewById(R.id.textViewPubertyYear).setVisibility(View.INVISIBLE);
            findViewById(R.id.editTextPuberty).setVisibility(View.INVISIBLE);
            ((EditText)findViewById(R.id.editTextPuberty)).setText(null);

        }
        if(((RadioButton)findViewById(R.id.radioButtonPYes)).isChecked())
        {
            findViewById(R.id.textViewPubertyYear).setVisibility(View.VISIBLE);
            findViewById(R.id.editTextPuberty).setVisibility(View.VISIBLE);

        }

    }


}
