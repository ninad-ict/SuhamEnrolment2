package com.suhamservice.ictsoftware.suhamenrolment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.isEmpty;

public class EmployeeLogin extends Fragment {

    View EmpLoginView;
    EditText editLogin;
    static String userName=null;
    EditText editPassword;
    Button buttonLogin;
    static String LOGNAME="";
    static String LOGPASS="";
    static LocationFragment EmployeeLocation=new LocationFragment();

    static Boolean LOGIN=false;
    JSONObject jsonEmpData=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LOGIN=false;
        EmpLoginView = inflater.inflate(R.layout.employeelogin, container, false);

        editLogin=EmpLoginView.findViewById(R.id.input_login);
        editPassword=EmpLoginView.findViewById(R.id.input_password);
        buttonLogin=EmpLoginView.findViewById(R.id.buttonLogin);
        DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
        if(!DB.getUser().equals("-1"))
        {
            editLogin.setText(DB.getUser());
        }

        //EmpLoginView.findViewById(R.id.toolbar_top).setEnabled(false);

         //expandableListView.setEnabled(false);
        //expandableListView.setVisibility(View.GONE);
        //expandableListView.setVisibility(View.GONE);
        //EmpLoginView.findViewById(R.id.mainFrame).setVisibility(View.INVISIBLE);
        //mydrawLayout .setEnabled(false);
        //mydrawLayout.setVisibility(View.GONE);
        //myToolbar.setEnabled(false);
       // myToolbar.setVisibility(View.GONE);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mess="IN-LoginButtonClick";

                if(isEmpty(editLogin)||isEmpty(editPassword))
                {
                    Toast.makeText(getContext(),"Please Enter Correct Details",Toast.LENGTH_LONG).show();
                    return;
                }
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                DB.setUser(editLogin.getText().toString());
                Log.d(mess,"Login->"+editLogin.getText().toString()+"Password->"+editPassword.getText().toString());
                Boolean EmpValidity=DB.checkEmpStatus(editLogin.getText().toString(),editPassword.getText().toString());
                Log.d(mess,"EmpValidity->"+EmpValidity);

                if(!EmpValidity)
                {
                    //go to AsyncEmpCheck
                    new AsyncEmpCheck().execute(editLogin.getText().toString(),editPassword.getText().toString());

                }
                else
                {
                    LOGIN=true;
                    MainActivity.ENROL_EMP=true;
                    MainActivity.ENROL_GIRL=false;
                    MainActivity.ENROL_ANTE=false;
                    MainActivity.ENROL_DELL=false;
                    MainActivity.ENROL_CHILD=false;

                    LOGNAME=editLogin.getText().toString();
                    LOGPASS=editPassword.getText().toString();

                    //expandableListView.setEnabled(true);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                     DB=DataBaseHelper.getInstance(getContext());
                    String locDetails[]=DB.getEmpLocation(DB.getEmpRegData()[0]);
                    if(!locDetails[0].equals("-1"))
                    {
                        fragmentTransaction.replace(R.id.Frame1, new MainMenuFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    else {
                        EmployeeLocation=new LocationFragment();
                        fragmentTransaction.replace(R.id.Frame1, EmployeeLocation);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                }
            }
        });



        return  EmpLoginView;
    }


     private class AsyncEmpCheck extends AsyncTask<String,String,String> {

        URL url;

        HttpURLConnection conn;


        URL uri;
        HttpURLConnection connection;

        @Override
        protected String doInBackground(String... params) {
            String mess = "AER-BACKGROUND";
            Log.d(mess,"FIRST LINE");
            //Log.d(mess, "Inside doinBackground");

            Socket socket = new Socket();
            try {
                //socket=new Socket();
                Log.d(mess,"LINE 2");
                socket.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
                //socket.close();
                if (socket.isConnected()) {
                    socket.close();
                    Log.d(mess,"LINE 3");
                    //Log.d(mess, "Inside IF: for sock is connected");
                    try {

                        uri = new URL(MainActivity.SERVER + "checkEmp.php");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        Log.d(mess,"LINE 4");
                       // Toast.makeText(getContext(),"Unable To Login!",Toast.LENGTH_LONG).show();
                        return "FALSE";
                        //Log.d("inside catch", "33");
                        //return e.toString();
                    }


                    try {
                        Log.d(mess,"LINE 5");
                        connection = (HttpURLConnection) uri.openConnection();
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);
                        //Log.d(mess, "88");
                        //Log.d(mess, params[0]);

                        //-----------SENDING FIELDS for RETRIEVING ID FROM SERVER--------------------


                        Uri.Builder builder = new Uri.Builder().appendQueryParameter("LOGIN", params[0]).appendQueryParameter("PASS", params[1]);

                        String query = builder.build().getEncodedQuery();
                        //Log.d(mess, "QUERY->" + query);
                        OutputStream os = connection.getOutputStream();
                        //Log.d(mess, "99");
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        //Log.d(mess, "100");
                        bw.write(query);
                        bw.flush();
                        bw.close();
                        os.close();
                        connection.connect();
                        //Log.d(mess, "101");
                        //  connection.disconnect();

                        //-----------SENDING FIELDS for RETRIEVING ID FROM SERVER--------------------

                        ///--------RECEVING UID FOR THAT FIELD FROM SERVER---------------------------
                        try {
                            Log.d(mess,"LINE 6");
                            int responseCode = connection.getResponseCode();
                            //Log.d(mess, "102");
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                //Log.d(mess, "103");
                                InputStream inputStream = connection.getInputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                }
                                Log.d(mess,"LINE 7");
                                Log.d(mess, "Sb.toString()->"+sb.toString());
                                connection.disconnect();
                                if(sb.toString().contains("FALSE")) {
                                    Log.d("CONTAINS-FALSE","FALSE");
                                    return "FALSE";
                                }
                                return sb.toString();
                            } else {
                                //Log.d("AER-ACKNOWLEDGE->", "Nothing!!");
                                connection.disconnect();
                            }

                        } catch (IOException e) {
                           // Toast.makeText(getContext(),"Unable To Login!",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            //Log.d(mess, "104");
                            return "FALSE";
                        }
                        ///--------RECEVING ALL DATA FOR THAT UID FROM SERVER---------------------------
                    } catch (IOException e) {
                       // Toast.makeText(getContext(),"Unable To Login!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        //Log.d(mess, "44");
                        return "FALSE";
                    }

                } else
                    return "FALSE";
            } catch (IOException e) {
                //Toast.makeText(getContext(),"Unable To Login!",Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return "FALSE";
            } finally {
                // socket.close();
            }
            return "FALSE";
        }

        @Override
        protected void onPostExecute(String message) {
            Log.d("XXXX","MESSAGE->"+message);
            if(!message.equals("FALSE"))
            {Log.d("XXXX2",message);
                try {
                    jsonEmpData = new JSONObject(message);
                    DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                    DB.storeEmpDetails(
                            jsonEmpData.get("ID").toString(),
                            jsonEmpData.get("NAME").toString(),
                            jsonEmpData.get("PIN").toString(),
                            jsonEmpData.get("MOBILE").toString(),
                            jsonEmpData.get("ADDRESS").toString()
                    );
                    LOGIN=true;
                    MainActivity.ENROL_EMP=true;
                    MainActivity.ENROL_GIRL=false;
                    MainActivity.ENROL_ANTE=false;
                    MainActivity.ENROL_DELL=false;
                    MainActivity.ENROL_CHILD=false;
                    LOGNAME=editLogin.getText().toString();
                    LOGPASS=editPassword.getText().toString();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.Frame1, EmployeeLocation);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                catch (JSONException j)
                {
                    //Toast.makeText(getContext(),"Error in EMP JSON",Toast.LENGTH_LONG).show();
                    Log.d("XXXX3",message);
                    j.printStackTrace();
                }
            }
            else
            {
                Log.d("XXXX4",message);
                LOGIN=false;
                Toast.makeText(getContext(),"LOGIN FAILED!",Toast.LENGTH_LONG).show();
            }



        }
    }
    //----SENDING DATA TO SERVER FOR EMP REG-------------------



}
