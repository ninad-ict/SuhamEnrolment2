package com.suhamservice.ictsoftware.suhamenrolment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_ANTE;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_CHILD;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_DELL;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_GIRL;

public class ApplicationName extends Fragment {

    SwitchCompat switchCompat;

    //    static int APP_ID=1;
    String ApplName="";
   // static String PregApplName="";
   // static String ChildApplName="";
    static TextView ApplicantName;
    View appView;
    TextView Heading;
    Button ProceedButton;
    TextWatcher textWatcher=null;
    TextWatcher TWproceed=null;
    JSONObject jsonObject=null;
    boolean ID_ENTERED=false;
    String ID_WHO="";

    EditText editIDName;
    EditText editIDDob;
    EditText editIDFedCode;

    static final int UPDATE_DAYS=7;

    EditText editPanch;
    EditText editVillage;

    Spinner spinPanch;
    Spinner spinVillage;

    TextView textviewForget;

    ProgressBar id_progress;
    ImageButton ArrowProceed;
    getAsyncInternetCheck asyncInternetCheck;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("IN onCreate()","Inside onCreate() iof ApplName");
        //ApplicantName=appView.findViewById(R.id.TextViewApplName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appView= inflater.inflate(R.layout.applicantname,container,false);
        //Log.d("IN onCreateView()","Inside onCreateView() iof ApplName");
        ApplicantName=appView.findViewById(R.id.EditTextApplName);
        ProceedButton=appView.findViewById(R.id.ButtonApplProceed);
        ArrowProceed=appView.findViewById(R.id.buttonArrowProceed);

        Heading=appView.findViewById(R.id.textViewApplHeading);

         editIDName=appView.findViewById(R.id.EditTextIdName);
         editIDDob=appView.findViewById(R.id.EditTextIdDob);
         editIDFedCode=appView.findViewById(R.id.EditTextIdFedCode);

         editPanch=appView.findViewById(R.id.editTextPanch);
         editVillage=appView.findViewById(R.id.editTextVillage);
         spinPanch=appView.findViewById(R.id.spinPanch);
         spinVillage=appView.findViewById(R.id.spinVillage);

         id_progress=appView.findViewById(R.id.progress_circular);


         /*if(!LocationFragment.commonPanch.equals(""))
             spinPanch.setSelection(get).setText(LocationFragment.commonPanch);
        if(!LocationFragment.commonVillage.equals(""))
            editVillage.setText(LocationFragment.commonVillage);*/


        textviewForget=appView.findViewById(R.id.TextViewForgetID);
        asyncInternetCheck=new getAsyncInternetCheck();
        asyncInternetCheck.execute("Hi");

        DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
        String locDetails[]=DB.getEmpLocation(DB.getEmpRegData()[0]);
        String[] PANCH=DB.getPanch(locDetails[0]);
            ArrayAdapter<String> arrayPanch =
                    new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,PANCH);
            //spinPanch.setBackgroundColor(getResources().getColor(R.color.Lightskyblue));
            spinPanch.setAdapter(arrayPanch);
        if(!LocationFragment.commonPanch.equals(""))
            spinPanch.setSelection(arrayPanch.getPosition(LocationFragment.commonPanch));


        spinPanch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("IN-SPIN-PANCH","current selection->"+spinPanch.getItemAtPosition(position));
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                String[] VILLAGE=DB.getVillage(spinPanch.getItemAtPosition(position).toString());
                ArrayAdapter<String> arrayVillage =
                        new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,VILLAGE);
                spinVillage.setAdapter(arrayVillage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(!LocationFragment.commonVillage.equals(""))
                {
                    ArrayAdapter<String> arrayVillage =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,new String[]{LocationFragment.commonVillage,"AAA"});
                    spinVillage.setAdapter(arrayVillage);
                    spinVillage.setSelection(arrayVillage.getPosition(LocationFragment.commonVillage));
                }

            }
        });

        if(!LocationFragment.commonVillage.equals(""))
        {
            ArrayAdapter<String> arrayVillage =
                    new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,new String[]{LocationFragment.commonVillage,"AAA"});
            spinVillage.setAdapter(arrayVillage);
            spinVillage.setSelection(arrayVillage.getPosition(LocationFragment.commonVillage));
        }






        if(MainActivity.ENROL_GIRL) {
            ApplicantName.setHint("Enter Name of the Girl");
            //((TableRow)appView.findViewById(R.id.rowAskMember)).setVisibility(View.INVISIBLE);
            Heading.setText("Enrolment of Adolscent Girl");
        }
        else if (MainActivity.ENROL_DELL|| ENROL_ANTE) {
            ApplicantName.setHint("Enter Name of the Woman");
            ((TableRow)appView.findViewById(R.id.rowAskMember)).setVisibility(View.VISIBLE);
            if(ENROL_ANTE)
            Heading.setText("Enrolment of Pregnant Woman");
            if(MainActivity.ENROL_DELL)
                Heading.setText("Enrolment of New Mother");
        }
        else if (MainActivity.ENROL_CHILD) {
            ApplicantName.setHint("Enter Name of the Child");
            ((TextView)appView.findViewById(R.id.textViewAskMember)).setText("Is The Applicant or his Mother Member of SUHAM?");
            ((TableRow)appView.findViewById(R.id.rowAskMember)).setVisibility(View.VISIBLE);
            Heading.setText("Child Enrolment");

        }

        MainActivity.RECEIVED=false;

    switchCompat = appView.findViewById(R.id.switchAskMember);

    final TextWatcher TWproceed=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (!s.toString().equals(""))
            {
                ((ImageButton)appView.findViewById(R.id.buttonArrowProceed)).setVisibility(View.VISIBLE);
            }
            else
            {
                ((ImageButton)appView.findViewById(R.id.buttonArrowProceed)).setVisibility(View.INVISIBLE);
            }

        }
    };
        ApplicantName.addTextChangedListener(TWproceed);
    final TextWatcher TWgetId=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String name=editIDName.getText().toString();
            String dob=editIDDob.getText().toString();
            String fedcode=editIDFedCode.getText().toString();

            String who="";

            if(ENROL_GIRL)
                who="GIRL";
            if(ENROL_ANTE)
                who="PREG";
            if(ENROL_DELL)
                who="MOTH";
            if(ENROL_CHILD)
                who="CHILD";

            String[] array = new String[] {who,name,dob,fedcode};

            new getAsyncIDD().execute(array);

        }
    };

    switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

            if (isChecked) {

                textviewForget.setVisibility(View.VISIBLE);
                textviewForget.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    appView.findViewById(R.id.LinearForgetIdBanner).setVisibility(View.VISIBLE);
                    appView.findViewById(R.id.LinearForgetId).setVisibility(View.VISIBLE);
                    }
                });
                ApplicantName.removeTextChangedListener(TWproceed);
                ApplicantName.setText("");
                ArrowProceed.setVisibility(View.INVISIBLE);
                editIDName.setVisibility(View.VISIBLE);
                editIDDob.setVisibility(View.VISIBLE);
                editIDFedCode.setVisibility(View.VISIBLE);
                ID_ENTERED=true;
                //Toast.makeText(getContext(), "YES MEMBER", Toast.LENGTH_LONG).show();
                if (MainActivity.ENROL_GIRL)
                    ApplicantName.setHint("Enter ID of the Applicant");

                if (MainActivity.ENROL_DELL || ENROL_ANTE) {
                    ApplicantName.setHint("Enter ID of the Applicant");
                }
                else if (MainActivity.ENROL_CHILD) {
                    ApplicantName.setHint("Enter ID of the Applicant or his Mother");
                }
                editIDDob.addTextChangedListener(TWgetId);
                editIDDob.addTextChangedListener(new GenericTextWatcher(editIDDob));
                editIDName.addTextChangedListener(TWgetId);
                editIDFedCode.addTextChangedListener(TWgetId);
                    ApplicantName.addTextChangedListener(textWatcher=new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(ENROL_GIRL)
                            {
                                if(!s.toString().contains("A"))
                                {
                                   // Log.d("In AFT","GIRL with wrong ID P M");
                                    ApplicantName.setError("Please Enter a Valid ID");
                                    ProceedButton.setEnabled(false);
                                    ArrowProceed.setVisibility(View.INVISIBLE);
                                    return;
                                }
                            }
                            if(ENROL_ANTE)
                            {
                                if(!s.toString().contains("A")&&!s.toString().contains("P"))
                                {
                                   // Log.d("In AFT","PREG with wrong ID M C");
                                    ApplicantName.setError("Please Enter a Valid ID");
                                    ProceedButton.setEnabled(false);
                                    ArrowProceed.setVisibility(View.INVISIBLE);
                                    return;
                                }
                            }

                            if(ENROL_DELL)
                            {
                                if(s.toString().contains("C"))
                                {
                                    //Log.d("In AFT","PREG with wrong ID C");
                                    ApplicantName.setError("Please Enter a Valid ID");
                                    ProceedButton.setEnabled(false);
                                    ArrowProceed.setVisibility(View.INVISIBLE);
                                    return;
                                }
                            }

                            if(ENROL_CHILD)
                            {
                                if(!s.toString().contains("M")&&!s.toString().contains("C"))
                                {
                                    //Log.d("In AFT","PREG with wrong ID A P");
                                    ApplicantName.setError("Please Enter a Valid ID");
                                    ProceedButton.setEnabled(false);
                                    ArrowProceed.setVisibility(View.INVISIBLE);
                                    return;
                                }
                            }

                            if(s.length()==MainActivity.UID_LENGTH&&isChecked)
                            {
                                //Log.d("In AFT","IF Part");
                                //new getAsyncInternetCheck().execute("hi");
                                new getAsyncUID().execute(s.toString());
                            }
                            else
                            {
                               // Log.d("In AFT","ELSE: UID is Absent");
                                ApplicantName.setError("Please Enter a Valid ID");
                                // Toast.makeText(getApplicationContext(), "NET IS ABSENT", Toast.LENGTH_LONG).show();
                                ProceedButton.setEnabled(false);
                                ArrowProceed.setVisibility(View.INVISIBLE);

                            }
                        }
                    });


            }
            if (!isChecked) {
                ID_ENTERED=false;
                editIDName.setVisibility(View.INVISIBLE);
                editIDDob.setVisibility(View.INVISIBLE);
                editIDFedCode.setVisibility(View.INVISIBLE);
                ApplicantName.removeTextChangedListener(textWatcher);
                ApplicantName.addTextChangedListener(TWproceed);
                textviewForget.setVisibility(View.INVISIBLE);
                appView.findViewById(R.id.LinearForgetIdBanner).setVisibility(View.INVISIBLE);
                appView.findViewById(R.id.LinearForgetId).setVisibility(View.INVISIBLE);

                editIDName.removeTextChangedListener(textWatcher);
                editIDDob.removeTextChangedListener(textWatcher);
                editIDFedCode.removeTextChangedListener(textWatcher);

                ApplicantName.setText("");
                ApplicantName.setError(null);
                ProceedButton.setEnabled(true);
               // Toast.makeText(getContext(), "NO MEMBER", Toast.LENGTH_LONG).show();
                if (MainActivity.ENROL_GIRL)
                    ApplicantName.setHint("Enter Name of the Girl");
                if (MainActivity.ENROL_DELL || ENROL_ANTE)
                    ApplicantName.setHint("Enter Name of the Woman");
                else if (MainActivity.ENROL_CHILD)
                    ApplicantName.setHint("Enter Name of the Child");
            }
        }
    });

//}

    return appView;
    }






    //-----------Async Task for checking Internet Connection---------------
    private class getAsyncInternetCheck extends AsyncTask<String,String,String> {
        URL url;
        HttpURLConnection conn;

        URL uri;
        HttpURLConnection connection;

        @Override
        protected String doInBackground(String... params) {
            String mess = "UID BACKGROUND";
           // Log.d(mess, "Inside doinBackground");
            Socket socket = new Socket();
            try {
                //socket=new Socket();
                socket.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
                //socket.close();
                if (socket.isConnected()) {
                  switchCompat.setEnabled(true);
                } else
                    switchCompat.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
                switchCompat.setEnabled(false);
                return "FALSE";
            } finally {
                // socket.close();
            }
            return "FALSE";
        }

        @Override
        protected void onPostExecute(String message) {

        }
    }
    //-----------Async Task for checking Internet Connection---------------

    //---------Async Task for Checking if Entered UID Exists in Server----------

    private class getAsyncUID extends AsyncTask<String,String,String> {
        URL url;
        HttpURLConnection conn;

        URL uri;
        HttpURLConnection connection;

        @Override
        protected String doInBackground(String... params) {
            String mess = "UID BACKGROUND";
           // Log.d(mess, "Inside doinBackground");
            Socket socket = new Socket();
            try {
                //socket=new Socket();
                socket.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
                //socket.close();
                if (socket.isConnected()) {
                    socket.close();

                   // Log.d(mess, "Inside IF: for sock is connected");
                    try {

                        uri = new URL(MainActivity.SERVER + "checkUID.php");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                      //  Log.d("inside catch", "33");
                        //return e.toString();
                    }


                    try {
                        connection = (HttpURLConnection) uri.openConnection();
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);
                    //    Log.d(mess, "88");
                        // Log.d(mess, params[0]);

                        //-----------SENDING UID for CHECKING TO SERVER--------------------


                        Uri.Builder builder =new Uri.Builder().appendQueryParameter("UID", params[0]);

                        String query = builder.build().getEncodedQuery();
                        // Log.d(mess, "QUERY->" + query);
                        OutputStream os = connection.getOutputStream();
                        // Log.d(mess, "99");
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        // Log.d(mess, "100");
                        bw.write(query);
                        bw.flush();
                        bw.close();
                        os.close();
                        connection.connect();
                        // Log.d(mess, "101");
                        //  connection.disconnect();

                        //-----------SENDING UID for CHECKING TO SERVER--------------------

                        ///--------RECEVING ALL DATA FOR THAT UID FROM SERVER---------------------------
                        try {
                            int responseCode = connection.getResponseCode();
                            //  Log.d(mess, "102");
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                //  Log.d(mess, "103");
                                InputStream inputStream = connection.getInputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder sb = new StringBuilder();
                                String line;
                               // publishProgress(null);
                                while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                }
                                // Log.d("ACKNOWLEDGE->", sb.toString());
                                connection.disconnect();
                                return sb.toString();
                            } else {
                                // Log.d("ACKNOWLEDGE", "Nothing!!");
                                connection.disconnect();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            // Log.d(mess, "104");
                            return "FALSE";
                        }
                        ///--------RECEVING ALL DATA FOR THAT UID FROM SERVER---------------------------
                    } catch (IOException e) {
                        e.printStackTrace();
                        //Log.d(mess, "44");
                        return "FALSE";
                    }

                } else
                    return "FALSE";
            } catch (IOException e) {
                e.printStackTrace();
                return "FALSE";
            } finally {
                // socket.close();
            }
            return "FALSE";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.d("Reached-OPU","First-Line");
            id_progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(String message) {
            //mConsumer.accept(internet);
            String mess="POST-EXECUTE-checkUID";
            id_progress.setVisibility(View.GONE);
            Boolean BADTIME=false;
            Boolean BADEMP=false;
            //   Log.d(mess,"Entered onPostExecute");

            if(!message.equals("FALSE"))
            {
                //   Log.d(mess,"UID is Present");
                try {
                    jsonObject = new JSONObject(message);
                    Log.d(mess,jsonObject.getString("TIME_STAMP"));
                    //LocalDate THEN=LocalDate.parse(jsonObject.getString("TIME_STAMP").toString());
                    DateTime THEN=DateTime.parse(jsonObject.getString("TIME_STAMP").toString());
                    DateTime NOW=new DateTime();
                    Days daysGap= Days.daysBetween(THEN,NOW);
                    Log.d(mess,"Days->"+daysGap.getDays());

                    switch (jsonObject.get("ID").toString().charAt(4))
                    {
                        case 'A':
                            ID_WHO="GIRL";
                            break;
                        case 'M':
                            ID_WHO="MOTH";
                            break;
                        case 'P':
                            ID_WHO="PREG";
                            break;
                        case 'C':
                            ID_WHO="CHILD";
                            break;
                    }
                    DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                    String EMPID=DB.getEmpRegData()[0];

                    if(daysGap.getDays()>UPDATE_DAYS||(!jsonObject.get("EMPID").toString().equals(EMPID)))
                    {
                        if(ENROL_GIRL&&ID_WHO.equals("GIRL"))
                            BADTIME=true;
                        if(ENROL_ANTE&&ID_WHO.equals("PREG"))
                            BADTIME=true;
                        if(ENROL_DELL&&ID_WHO.equals("MOTH"))
                            BADTIME=true;
                        if(ENROL_CHILD&&ID_WHO.equals("CHILD"))
                            BADTIME=true;
                    }
                }catch (JSONException j)
                {
                    j.printStackTrace();
                }

                if(!BADTIME)
                ((ImageButton)appView.findViewById(R.id.buttonArrowProceed)).setVisibility(View.VISIBLE);
                else
                {
                    ApplicantName.setError("This ID Cannot be Updated!");
                    // Toast.makeText(getApplicationContext(), "NET IS ABSENT", Toast.LENGTH_LONG).show();
                    ArrowProceed.setVisibility(View.GONE);
                }

                // Drawable myIcon= ContextCompat.getDrawable(getActivity(),R.drawable.tick_green);
               // Drawable myIcon= ContextCompat.getDrawable(getActivity(),R.mipmap.ic_shortcut_arrow_forward);
              // myIcon.setBounds(0, 0, myIcon.getIntrinsicWidth(), myIcon.getIntrinsicHeight());
               // ApplicantName.setError("ID Found!",myIcon);
                //ProceedButton.setEnabled(true);
                //   Log.d(mess,message);
               /* try {


                    //     Log.d(mess,"json[STATE]->"+jsonObject.get("STATE").toString());
                    //obj.get("name").toString();
                    //anteLocation.FEDCODE=jsonObject.get("FED_CODE").toString();


                    //   Log.d(mess,"charAt(4)->"+jsonObject.get("ID").toString().charAt(4));
                    //    Log.d(mess,"fedcode->"+jsonObject.get("FED_CODE").toString());
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }*/

               // JSONObject obj = (JSONObject) JSONValue.parse(json);


                //Toast.makeText(context,"NET IS PRESENT",Toast.LENGTH_LONG).show();
            }
            else {

                //    Log.d(mess,"ELSE: UID is Absent");
                ApplicantName.setError("Please Enter a Valid ID");
                // Toast.makeText(getApplicationContext(), "NET IS ABSENT", Toast.LENGTH_LONG).show();
                ProceedButton.setEnabled(false);
                ArrowProceed.setVisibility(View.GONE);

            }

        }
    }

    private class getAsyncIDD extends AsyncTask<String,String,String> {
        URL url;
        HttpURLConnection conn;


        URL uri;
        HttpURLConnection connection;

        @Override
        protected String doInBackground(String... params) {
            String mess = "UID BACKGROUND";
            //  Log.d(mess, "Inside doinBackground");
            Socket socket = new Socket();
            try {
                //socket=new Socket();
                socket.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
                //socket.close();
                if (socket.isConnected()) {
                    socket.close();

                    //   Log.d(mess, "Inside IF: for sock is connected");
                    try {

                        uri = new URL(MainActivity.SERVER + "getIDD.php");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        //        Log.d("inside catch", "33");
                        //return e.toString();
                    }


                    try {
                        connection = (HttpURLConnection) uri.openConnection();
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);
                        //    Log.d(mess, "88");
                        //    Log.d(mess, params[0]);

                        //-----------SENDING FIELDS for RETRIEVING ID FROM SERVER--------------------


                        Uri.Builder builder =new Uri.Builder().appendQueryParameter("WHO", params[0]).appendQueryParameter("NAME", params[1]).appendQueryParameter("DOB", params[2]).appendQueryParameter("FCODE", params[3]);

                        String query = builder.build().getEncodedQuery();
                        //     Log.d(mess, "QUERY->" + query);
                        OutputStream os = connection.getOutputStream();
                        //      Log.d(mess, "99");
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        //    Log.d(mess, "100");
                        bw.write(query);
                        bw.flush();
                        bw.close();
                        os.close();
                        connection.connect();
                        //     Log.d(mess, "101");
                        //  connection.disconnect();

                        //-----------SENDING FIELDS for RETRIEVING ID FROM SERVER--------------------

                        ///--------RECEVING UID FOR THAT FIELD FROM SERVER---------------------------
                        try {
                            int responseCode = connection.getResponseCode();
                            //       Log.d(mess, "102");
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                //         Log.d(mess, "103");
                                InputStream inputStream = connection.getInputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                }
                                //       Log.d("ACKNOWLEDGE if uid->", sb.toString());
                                connection.disconnect();
                                return sb.toString();
                            } else {
                                //        Log.d("ACKNOWLEDGE", "Nothing!!");
                                connection.disconnect();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            //    Log.d(mess, "104");
                            return "FALSE";
                        }
                        ///--------RECEVING ALL DATA FOR THAT UID FROM SERVER---------------------------
                    } catch (IOException e) {
                        e.printStackTrace();
                        //  Log.d(mess, "44");
                        return "FALSE";
                    }

                } else
                    return "FALSE";
            } catch (IOException e) {
                e.printStackTrace();
                return "FALSE";
            } finally {
                // socket.close();
            }
            return "FALSE";
        }

        @Override
        protected void onPostExecute(String message) {
            //mConsumer.accept(internet);
            String mess="getIDD PE->";
            //  Log.d(mess,"Entered onPostExecute");

            if(!message.equals("FALSE"))
            {
                //    Log.d(mess,"UID is Present");

                //   Log.d(mess,message);
                try {
                    jsonObject = new JSONObject(message);
                    //     Log.d(mess,"json[UID]->"+jsonObject.get("UID").toString());
                    //obj.get("name").toString();
                    //anteLocation.FEDCODE=jsonObject.get("FED_CODE").toString();
                    ApplicantName.setText(jsonObject.get("UID").toString());
                    ApplicantName.setError(null);
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                    ApplicantName.setText("");
                }

            }
            else {

                //    Log.d(mess,"ELSE: UID is Absent");
                ApplicantName.setError("Please Enter a Valid ID");
                ApplicantName.setText("");
                ArrowProceed.setVisibility(View.INVISIBLE);
                // Toast.makeText(getApplicationContext(), "NET IS ABSENT", Toast.LENGTH_LONG).show();
                ProceedButton.setEnabled(false);

            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.RECEIVED=false;
        //Log.d("IN RESUME","Inside resume() iof ApplName");
    }
}
