package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class EmployeeRegistration extends Fragment {

    View viewEmpReg;
    Button buttonSubmit;
    Button buttonClear;
    Button buttonRefresh;
    Boolean AERonSERVER=false;
    TableLayout tableEmpReg;

    Boolean REGISTERED=false;
    TextView textEmpRegWait;

    EditText editName;
    EditText editPhone;
    EditText editAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String mess="IN-OCV-ER";
        //Log.d(mess,"First Line");
        viewEmpReg = inflater.inflate(R.layout.emp_registration, container, false);
       /* buttonSubmit=viewEmpReg.findViewById(R.id.buttonEmpRegSubmit);
        tableEmpReg=viewEmpReg.findViewById(R.id.TableEmpReg);
        buttonRefresh=viewEmpReg.findViewById(R.id.buttonEmpRegRefresh);
        buttonClear=viewEmpReg.findViewById(R.id.buttonEmpRegClear);


        textEmpRegWait=viewEmpReg.findViewById(R.id.TextViewEmpRegWait);

        editName=((EditText)viewEmpReg.findViewById(R.id.EditEmpRegName));
        editPhone=((EditText)viewEmpReg.findViewById(R.id.EditEmpRegPhone));
        editAddress=((EditText)viewEmpReg.findViewById(R.id.EditEmpRegAddress));

        //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
        DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
        String state=DB.isEMPRegFormFilled();
        //DB.close();
        //Log.d(mess,"state->"+state);

     /* if(state.equals("WAIT"))
      {
          tableEmpReg.setVisibility(View.GONE);
          textEmpRegWait.setVisibility(View.VISIBLE);
          return viewEmpReg;
      }*/
/*
      if(!state.equals("EMPTY"))
      {

          //Log.d(mess,"NOT EMPTY");
          new checkAsyncPermit().execute(state);

          if(state.contains("WAIT"))
          {//Log.d(mess,"IN WAIT");
              tableEmpReg.setVisibility(View.GONE);
              textEmpRegWait.setVisibility(View.VISIBLE);
              buttonRefresh.setVisibility(View.VISIBLE);
              REGISTERED=false;
              return viewEmpReg;
          }
          if(state.contains("SUCCESS"))
          {   //Log.d(mess,"IN SUCCESS");
              tableEmpReg.setVisibility(View.VISIBLE);
              textEmpRegWait.setVisibility(View.GONE);
              buttonRefresh.setVisibility(View.GONE);
              REGISTERED=true;
              return viewEmpReg;
          }
      }

      REGISTERED=false;
*/
/*buttonRefresh.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Log.d("REFRESH","Inside it");
       // new MainActivity();
      // getActivity().recreate();
      // getActivity().finish();

    }
});*/

      /*  buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Submit Clicked->","First Line");
                if(
                        MainActivity.isEmpty(editName)||
                        MainActivity.isEmpty(editPhone)||
                        MainActivity.isEmpty(editAddress)
                )
                {
                    //Log.d("Submit Clicked->","Second Line");
                   // Toast.makeText(getContext(),"Please fill in the Details",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    String Name=(editName).getText().toString();
                    String Phone=(editPhone).getText().toString();
                    String Address=(editAddress).getText().toString();

                    //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                    DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                   ContentValues result=DB.sendEmpRegData(Name,Phone,Address);

                    //DB.close();

                    if(result!=null)
                        new sendAsyncEmpReg().execute(
                                result.getAsString("EMPID"),
                                result.getAsString("NAME"),
                                result.getAsString("PHONE"),
                                result.getAsString("ADDRESS"),
                                result.getAsString("TIME"),
                                result.getAsString("STATUS"));

                        /*if(AERonSERVER)
                        {
                            tableEmpReg.setVisibility(View.GONE);
                            textEmpRegWait.setVisibility(View.VISIBLE);
                        }*/
            /*    }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setText("");
                editPhone.setText("");
                editAddress.setText("");
            }
        });
*/
        return viewEmpReg;

    }

    //----SENDING DATA TO SERVER FOR EMP REG-------------------
   /* private class sendAsyncEmpReg extends AsyncTask<String,String,String> {
        URL url;
        HttpURLConnection conn;


        URL uri;
        HttpURLConnection connection;

        @Override
        protected String doInBackground(String... params) {
            String mess = "AER-BACKGROUND";
            //Log.d(mess, "Inside doinBackground");
            Socket socket = new Socket();
            try {
                //socket=new Socket();
                socket.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
                //socket.close();
                if (socket.isConnected()) {
                    socket.close();

                    //Log.d(mess, "Inside IF: for sock is connected");
                    try {

                        uri = new URL(MainActivity.SERVER + "sendAER.php");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        //Log.d("inside catch", "33");
                        //return e.toString();
                    }


                    try {
                        connection = (HttpURLConnection) uri.openConnection();
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);
                        //Log.d(mess, "88");
                        //Log.d(mess, params[0]);

                        //-----------SENDING FIELDS for RETRIEVING ID FROM SERVER--------------------


                        Uri.Builder builder = new Uri.Builder().appendQueryParameter("EMPID", params[0]).appendQueryParameter("NAME", params[1]).appendQueryParameter("PHONE", params[2]).appendQueryParameter("ADDRESS", params[3]).appendQueryParameter("TIME", params[4]).appendQueryParameter("STATUS", params[5]);

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
                                //Log.d("AER-ACKNOWLEDGE->", sb.toString());
                                connection.disconnect();
                                return sb.toString();
                            } else {
                                //Log.d("AER-ACKNOWLEDGE->", "Nothing!!");
                                connection.disconnect();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            //Log.d(mess, "104");
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
        protected void onPostExecute(String message) {
            //mConsumer.accept(internet);
            String mess = "AER-PE->";
            //Log.d(mess, "Entered onPostExecute");

            if (message.equals("AER-executed")) {


                //Log.d(mess, "AER inserted IN SERVER");
                tableEmpReg.setVisibility(View.GONE);
                textEmpRegWait.setVisibility(View.VISIBLE);
                buttonRefresh.setVisibility(View.VISIBLE);
                AERonSERVER=true;



            } else {

                //Log.d(mess, "AER FAILED");
                AERonSERVER=false;
               // Toast.makeText(getActivity(),"Please Check Network Connection",Toast.LENGTH_LONG).show();
                //DataBaseHelper DB = new DataBaseHelper(getContext(), null, null, 1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                DB.deleteEmpReg();
                //DB.close();

            }

        }
    }*/
    //----SENDING DATA TO SERVER FOR EMP REG-------------------

    //--------CHECKING EMP PERMIT STATUS FROM SERVER--------------
  /*  private class checkAsyncPermit extends AsyncTask<String,String,String> {
        URL url;
        HttpURLConnection conn;

        String EmpId;
        URL uri;
        HttpURLConnection connection;

        @Override
        protected String doInBackground(String... params) {
            String mess = "checkAER-BACKGROUND";
            //Log.d(mess, "Inside doinBackground");

            Socket socket = new Socket();
            try {
                //socket=new Socket();
                socket.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
                //socket.close();
                if (socket.isConnected()) {
                    socket.close();

                    //Log.d(mess, "Inside IF: for sock is connected");
                    try {

                        uri = new URL(MainActivity.SERVER + "checkAERPermit.php");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        //Log.d("inside catch", "33");
                        //return e.toString();
                    }


                    try {
                        connection = (HttpURLConnection) uri.openConnection();
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);
                        //Log.d(mess, "88");
                        //Log.d(mess, params[0]);

                        //-----------SENDING EMPID FOR CHECKING PERMIT STATUS FROM SERVER--------------------

                        EmpId=params[0].split("-")[1];
                        //Log.d(mess,"EMpid->"+EmpId);
                        Uri.Builder builder = new Uri.Builder().appendQueryParameter("EMPID", EmpId);

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

                        //-----------SENDING EMPID FOR CHECKING PERMIT STATUS FROM SERVER--------------------

                        ///--------RECEVING STATUS FOR THAT EMPID FROM SERVER---------------------------
                        try {
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
                                //Log.d("AER-ACKNOWLEDGE->", sb.toString());
                                connection.disconnect();
                                return sb.toString();
                            } else {
                                //Log.d("AER-ACKNOWLEDGE->", "Nothing!!");
                                connection.disconnect();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            //Log.d(mess, "104");
                            return "FALSE";
                        }
                        ///--------RECEVING STATUS FOR THAT EMPID FROM SERVER---------------------------
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
        protected void onPostExecute(String message) {

            //Log.d("checkAER-PE","message->"+message);
       if(!message.equals("FALSE")) {
           //DataBaseHelper DB = new DataBaseHelper(getContext(), null, null, 1);
           DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
           DB.updateEmpRegStatus(EmpId, message);
           //DB.close();
       }



        }
    }*/
    //--------CHECKING EMP PERMIT STATUS FROM SERVER--------------
}

