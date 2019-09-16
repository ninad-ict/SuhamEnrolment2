package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteLocation;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childLocation;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.locationFragment;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.deliveryApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.DeliveryLocation;


public class LocationFragment extends Fragment {
    View locationView;
    static String getRegions[]=null;



    TextView heading;

    static EditText nameVillage;//=((EditText)(locationView.findViewById(R.id.editTextVillage)));
    static AutoCompleteTextView autoRegion;

    EditText FedCode;
    AutoCompleteTextView state;
    AutoCompleteTextView district;
    AutoCompleteTextView federation;
    EditText Panch;
    EditText Village;
    EditText editContact;
    RadioButton radioSchool;
    RadioButton radioAdlGrp;
    EditText EditGirlGroup;
    boolean OR1=false,OR2=false;

    String FEDCODE;
    String STATE;
    String DISTRICT;
    String FED;
    String PANCH;
    String VILLAGE;
    String Contact;
    String Group;
    String GroupType;

    static String commonPanch="";
    static String commonVillage="";

TextWatcher TWfedCode;
TextWatcher TWfedState;
TextWatcher TWfedDistrict;
TextWatcher TWfedFed;
    boolean SAVELocation=false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new getAsyncRegion().execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        locationView = inflater.inflate(R.layout.locationfragment, container, false);
        heading = ((TextView) locationView.findViewById(R.id.textViewHeadingL));

        FedCode=locationView.findViewById(R.id.editTextFedCode);
        state=locationView.findViewById(R.id.editTextState);
        district=locationView.findViewById(R.id.editTextDist);
        federation=locationView.findViewById(R.id.editTextFed);
        Panch=locationView.findViewById(R.id.editTextPanch);
        Village=locationView.findViewById(R.id.editTextVillage);
       // editContact=locationView.findViewById(R.id.editTextContact);
        new getAsyncState().execute();
        new getAsyncDistrict().execute();
        new getAsyncFed().execute();

        DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
        if(MainActivity.ENROL_EMP)
        {
           // editContact.setVisibility(View.GONE);
            heading.setText("Enter Your Location Details-" + DB.getEmpRegData()[1]);
        }


        String locDetails[]=DB.getEmpLocation(DB.getEmpRegData()[0]);
        if(!locDetails[0].equals("-1"))
        {
            FedCode.setText(locDetails[0]);
            state.setText(locDetails[1]);
            district.setText(locDetails[2]);
            federation.setText(locDetails[3]);
            Panch.setText(locDetails[4]);
            Village.setText(locDetails[5]);

        }


        if(MainActivity.ENROL_GIRL){

        locationFragment.SAVELocation = false;
        if (applicationName.ApplName!= null) {

            heading.setText("Enter Location Details for "+applicationName.ApplName);
        }

           // locationView.findViewById(R.id.LinearGirlGroup).setVisibility(View.VISIBLE);
           // locationView.findViewById(R.id.LinearGirlGroupBanner).setVisibility(View.VISIBLE);

           // radioSchool=locationView.findViewById(R.id.radioButtonSchool);
          //  radioAdlGrp=locationView.findViewById(R.id.radioButtonAdlGrp);

           /* EditGirlGroup=locationView.findViewById(R.id.editTextGirlGroup);
            radioSchool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditGirlGroup.setHint("Enter School Name");
                }
            });

            radioAdlGrp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditGirlGroup.setHint("Enter Group Name");
                }
            });*/



            if(applicationName.ID_ENTERED)
            {
                try {

                    //Log.d("IN GIRL Location","In try part");

                    FedCode.setText(applicationName.jsonObject.get("FED_CODE").toString());
                    district.setText(applicationName.jsonObject.get("DISTRICT").toString());
                    state.setText(applicationName.jsonObject.get("STATE").toString());
                    federation.setText(applicationName.jsonObject.get("FEDERATION").toString());
                    Panch.setText(applicationName.jsonObject.get("PANCHAYAT").toString());
                    Village.setText(applicationName.jsonObject.get("VILLAGE").toString());
                    //editContact.setText(applicationName.jsonObject.get("CONTACT").toString());
                   // EditGirlGroup.setText(applicationName.jsonObject.get("GROUPNAME").toString());

                  /*  if(applicationName.jsonObject.get("GROUPNAME").equals("AdlGroup"))
                        radioAdlGrp.setChecked(true);
                    else
                        radioSchool.setChecked(true);*/

                    //Log.d("IN DELL Location","FedCode.gettext->"+FedCode.getText());

                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
           // else
               // //Log.d("IN GIRL Location","Else part");


        }

        if(MainActivity.ENROL_ANTE){

        anteLocation.SAVELocation=false;
            if (anteApplicant.ApplName!= null) {
                heading.setText("Enter Location Details for " + anteApplicant.ApplName);
            }

            if(anteApplicant.ID_ENTERED)
            {
                try {

                    //Log.d("IN ANTE Location","In try part");
                    FedCode.setText(anteApplicant.jsonObject.get("FED_CODE").toString());
                    district.setText(anteApplicant.jsonObject.get("DISTRICT").toString());
                    state.setText(anteApplicant.jsonObject.get("STATE").toString());
                    federation.setText(anteApplicant.jsonObject.get("FEDERATION").toString());
                    Panch.setText(anteApplicant.jsonObject.get("PANCHAYAT").toString());
                    Village.setText(anteApplicant.jsonObject.get("VILLAGE").toString());
                  //  editContact.setText(anteApplicant.jsonObject.get("CONTACT").toString());
                    //Log.d("IN DELL Location","FedCode.gettext->"+FedCode.getText());

                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
            //else
               // //Log.d("IN ANTE Location","Else part");
        }

        if(MainActivity.ENROL_DELL){

            DeliveryLocation.SAVELocation=false;
            if (deliveryApplicant.ApplName!= null) {

                heading.setText("Enter Location Details for " + deliveryApplicant.ApplName);

            }
            if(deliveryApplicant.ID_ENTERED)
            {
                try {

                    //Log.d("IN DELL Location","In try part");
                    FedCode.setText(deliveryApplicant.jsonObject.get("FED_CODE").toString());
                    district.setText(deliveryApplicant.jsonObject.get("DISTRICT").toString());
                    state.setText(deliveryApplicant.jsonObject.get("STATE").toString());
                    federation.setText(deliveryApplicant.jsonObject.get("FEDERATION").toString());
                    Panch.setText(deliveryApplicant.jsonObject.get("PANCHAYAT").toString());
                    Village.setText(deliveryApplicant.jsonObject.get("VILLAGE").toString());
                   // editContact.setText(deliveryApplicant.jsonObject.get("CONTACT").toString());

                    //Log.d("IN DELL Location","FedCode.gettext->"+FedCode.getText());

                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }
            }
            //else
               // //Log.d("IN DELL Location","Else part");
        }

        if(MainActivity.ENROL_CHILD){

        childLocation.SAVELocation=false;

            if (childApplicant.ApplName != null) {

                heading.setText("Enter Location Details for " + childApplicant.ApplName);

               /* if(!childApplicant.ID_ENTERED)
                    heading.setText("Enter Location Details for " + childApplicant.ApplName);
                else {

                    if (childApplicant.ID_WHO == "MOTH")
                        heading.setText("Enter Location Details for Child of " + childApplicant.ApplName);
                    else
                        heading.setText("Enter Location Details for " + childApplicant.ApplName);
                }*/


                if(childApplicant.ID_ENTERED)
                {
                    try {

                        //Log.d("IN DELL Location","In try part");

                        FedCode.setText(childApplicant.jsonObject.get("FED_CODE").toString());
                        district.setText(childApplicant.jsonObject.get("DISTRICT").toString());
                        state.setText(childApplicant.jsonObject.get("STATE").toString());
                        federation.setText(childApplicant.jsonObject.get("FEDERATION").toString());
                        Panch.setText(childApplicant.jsonObject.get("PANCHAYAT").toString());
                        Village.setText(childApplicant.jsonObject.get("VILLAGE").toString());
                      //  editContact.setText(childApplicant.jsonObject.get("CONTACT").toString());

                        //Log.d("IN DELL Location","FedCode.gettext->"+FedCode.getText());

                    }
                    catch (JSONException j)
                    {
                        j.printStackTrace();
                    }
                }
               // else
                  //  //Log.d("IN DELL Location","Else part");


            }
        }
//--------Code for Reading From SERVER---------------
       // new getAsyncRegion().execute();
//--------Code for Reading From SERVER---------------

//---CODE for Checking FED CODE AND ITS CORRESPONDING STATE/DIST/FED NAME----




        FedCode.addTextChangedListener(TWfedCode=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //state.setText("");
                //district.setText("");
                //federation.setText("");
                if(FedCode.getText().toString().equals(""))
                {
                    state.setEnabled(true);
                    district.setEnabled(true);
                    federation.setEnabled(true);
                    federation.setText("");
                    state.setText("");
                    district.setText("");
                }

                if(!OR2) {

                    //DataBaseHelper DB = new DataBaseHelper(getContext(), null, null, 1);
                    DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                    String[] location = DB.getLocation(s.toString());
                    if (location[0] != "-1") {
                        OR1 = true;
                        state.setText(location[0]);
                        district.setText(location[1]);
                        federation.setText(location[2]);
                          state.setEnabled(false);
                          district.setEnabled(false);
                         federation.setEnabled(false);
                        FedCode.setError(null);

                    } else {

                        // state.setText("");
                        //  district.setText("");
                        //  federation.setText("");
                        // state.setEnabled(true);
                        // district.setEnabled(true);
                        // federation.setEnabled(true);
                        if(!FedCode.getText().toString().equals("9999"))
                        FedCode.setError("Please Enter a Valid Code");
                        OR1 = false;

                        /*FedCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                FedCode.setError("Please Enter a Valid Code");
                                locationFragment.SAVELocation = false;
                            }
                        });*/
                    }
               }
            }
        });


//---CODE for Checking FED CODE AND ITS CORRESPONDING STATE/DIST/FED NAME----


        //--------CODE for Setting CODE-------------


        //--------CODE for Setting CODE-------------

//-----------CODE FOR READING REGION LIST FROM LOCAL DATABASE-----------------

       /* final DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
        getRegions=DB.getRegions();

        ArrayAdapter<String> arrayAdapterRegion =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getRegions);

        autoRegion = (AutoCompleteTextView) locationView.findViewById(R.id.editTextState);
        autoRegion.setThreshold(1);
        autoRegion.setAdapter(arrayAdapterRegion);

        autoRegion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!Arrays.asList(getRegions).contains(autoRegion.getText().toString())) {
                    autoRegion.setError("Please Enter a Valid Region");
                    locationFragment.SAVELocation = false;
                }
            }
        });*/

//-----------CODE FOR READING REGION LIST FROM LOCAL DATABASE----------------

//-----------CODE FOR AUTOCOMPLETE STATE DETAILS----------------

        state.addTextChangedListener(TWfedState=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.d("InState ATC",s.toString());
                district.setText("");
                federation.setText("");
                if(!OR1) {
                    if (state.getText().toString().equals(""))
                        FedCode.setEnabled(true);
                    else
                        FedCode.setEnabled(false);
                }
                OR2=false;
                //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                  String getstate[]=DB.getState(s.toString());
                  if(!getstate[0].equals("-1")) {
                      ArrayAdapter<String> arrayAdapterState =
                              new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getstate);
                      state.setThreshold(1);
                      state.setAdapter(arrayAdapterState);

                  }
                  //DB.close();








            }
        });

//-----------CODE FOR AUTOCOMPLETE STATE DETAILS----------------


// -----------CODE FOR AUTOCOMPLETE DISTRICT DETAILS----------------

        district.addTextChangedListener(TWfedDistrict=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                federation.setText("");
                if(!OR1) {
                    if (state.getText().toString().equals("") && district.getText().toString().equals(""))
                        FedCode.setEnabled(true);
                    else
                        FedCode.setEnabled(false);
                }
                OR2=false;
                //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                String getdistrict[]=DB.getDistrict(s.toString(),state.getText().toString());
                if(!getdistrict[0].equals("-1")) {
                    //Log.d("In DIST ATC",getdistrict[0]);
                    ArrayAdapter<String> arrayAdapterState =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getdistrict);
                    district.setThreshold(0);
                    district.setAdapter(arrayAdapterState);

                }
                //DB.close();

            }
        });

//-----------CODE FOR AUTOCOMPLETE DISTRICT DETAILS----------------


// -----------CODE FOR AUTOCOMPLETE FED DETAILS----------------

        federation.addTextChangedListener(TWfedFed=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.d("IN-FED-ATC","JUST-Entered-FED-ATC");
                OR2=false;
                //FedCode.setText("");
                if(!OR1) {
                    //Log.d("IN-FED-ATC","2nd log");
                    if (state.getText().toString().equals("") && district.getText().toString().equals("") && federation.getText().toString().equals(""))
                        FedCode.setEnabled(true);
                    else
                        FedCode.setEnabled(false);
                }

                if(s.toString().equals("SUHAM"))
                {
                    FedCode.setText("9999");
                    OR2=true;
                    return;
                }


                //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                String getFed[]=DB.getFed(s.toString(),state.getText().toString(),district.getText().toString());
                if(!getFed[0].equals("-1")) {
                    //Log.d("IN-FED-ATC","getFed[0]->"+getFed[0]);
                    ArrayAdapter<String> arrayAdapterState =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getFed);
                    federation.setThreshold(0);
                    federation.setAdapter(arrayAdapterState);
                    federation.showDropDown();
                    federation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            federation.showDropDown();
                        }
                    });

                    //if(FedCode.getText().toString().equals("")) {
                    if(!OR1) {
                        int code=9999;
                        //Log.d("IN-FED-ATC",federation.getText().toString());
                        if(!federation.getText().toString().equals("SUHAM"))
                        {
                            //Log.d("IN-FED-ATC","fed not equals suham");
                            code = DB.setCode(state.getText().toString(), district.getText().toString(), federation.getText().toString());
                        }
                        if (code != 0) {
                            //Log.d("IN-FED-ATC", "code->" + code);
                            OR2=true;
                            FedCode.setEnabled(false);
                            FedCode.setText(Integer.toString(code));
                            FedCode.setError(null);
                           // FedCode.setEnabled(false);
                        }
                    }
                }
              //  else
              //  {
                    ////Log.d("IN-FED-ATC", "Did not go in IF");
              //  }
                //DB.close();

            }
        });

       /* FedCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FedCode.addTextChangedListener(TWfedCode);
                state.removeTextChangedListener(TWfedState);
                district.removeTextChangedListener(TWfedDistrict);
                federation.removeTextChangedListener(TWfedFed);
            }
        });

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FedCode.removeTextChangedListener(TWfedCode);
                state.addTextChangedListener(TWfedState);
            }
        });
        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FedCode.removeTextChangedListener(TWfedCode);
                district.addTextChangedListener(TWfedDistrict);
            }
        });*/
      /*  federation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FedCode.removeTextChangedListener(TWfedCode);
                federation.addTextChangedListener(TWfedFed);
            }
        });*/
//-----------CODE FOR AUTOCOMPLETE FED DETAILS----------------


        return locationView;


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
                url = new URL(MainActivity.SERVER+"getState.php");
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
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
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
                url = new URL(MainActivity.SERVER+"getDistrict.php");
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
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
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
                url = new URL(MainActivity.SERVER+"getFed.php");
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
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                Log.d("in OPE->","s->"+s);
                DB.uploadFed(s);
                //DB.close();;

            }
            // elseuploadFed
            // {
            //      //Log.d("FED:post execute","NO Data");
            // }

        }

    }

    //---------CODE FOR GETTING Fed LIST FROM SERVER IN LOCAL DB----------

}
