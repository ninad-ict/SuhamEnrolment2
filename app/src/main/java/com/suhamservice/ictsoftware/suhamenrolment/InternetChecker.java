package com.suhamservice.ictsoftware.suhamenrolment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

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

public class InternetChecker extends BroadcastReceiver {
    //DataBaseHelper DB=new DataBaseHelper(new InternetChecker(),null,null,1);
    //DataBaseHelper db=new DataBaseHelper()

    JSONObject jsonGetUID=null;

    @Override
    public void onReceive(Context context, Intent intent) {
        String mess = "OnReceive";
        //Log.d(mess,"First Line");
        if(!MainActivity.RECEIVED) {
            MainActivity.RECEIVED = true;

            //Log.d(mess, "Just Entered onReceive");
            //DataBaseHelper DB = new DataBaseHelper(context, null, null, 1);
            DataBaseHelper DB=DataBaseHelper.getInstance(context);
            String StoreIds[] = DB.getIds();

            if (!StoreIds.equals(null)) {
                for (int i = 0; i < StoreIds.length; i++) {
                    String params[] = DB.uploadData(StoreIds[i]);
                    //Log.d(mess, "PARAMS to be uploded->>" + params[0] + "->" + params[1] + "->" + params[2] + "->" + params[3] + "->" + params[4]);
                    new SendToServer(context).execute(params);
                }
            }
           // DB.close();

        }
        /*try {
            wait(1000);
        }
        catch (InterruptedException e )
        {
            e.printStackTrace();
        }*/

        // ----THE WHILE LOOP----------
        /*while (DB.rowPresent()) {
         //Log.d(mess,"INSIDE WHILE");
            String params[]=DB.uploadData();
            //new InternetAsyncCheck().execute(LocationFragment.REGION, LocationFragment.FED, LocationFragment.PANCH, LocationFragment.VILLAGE);
            new SendToServer(context).execute(params);

        }*/
        // ----THE WHILE LOOP----------
    }



public class SendToServer extends AsyncTask<String,String,String> {

private final Context myContext;

public SendToServer(Context context)
{
    super();
    this.myContext=context;
}

URL uri;
HttpURLConnection connection;
HttpURLConnection connection2;
String IDtoDelete;

        @Override
        protected String doInBackground(String... params) {
            String mess="BACKGROUND";
            //Log.d(mess,"Inside doinBackground");
            Socket socket=new Socket();
            try
            {
                //socket=new Socket();
                socket.connect(new InetSocketAddress("8.8.8.8",53),10000);
                //socket.close();
                if(socket.isConnected())
                {socket.close();

                    //Log.d(mess,"Inside IF: for sock is connected");
                    try {

                        uri = new URL(MainActivity.SERVER+"SendToServer.php");
                    }
                    catch (MalformedURLException e)
                    {
                        e.printStackTrace();
                        //Log.d("inside catch","33");
                        //return e.toString();
                    }
                    try{
                        connection=(HttpURLConnection)uri.openConnection();
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setConnectTimeout(10000);
                        connection.setReadTimeout(10000);
                        //Log.d(mess,"88");
                        //Log.d(mess,params[0]);

                        Uri.Builder builder=null;

                        //DataBaseHelper DB=new DataBaseHelper(myContext,null,null,1);
                        DataBaseHelper DB=DataBaseHelper.getInstance(myContext);

                        String EMPID=DB.getEmpRegData()[5];
                        //DB.close();

                        if(params[0]=="GIRL")
                            builder=new Uri.Builder().appendQueryParameter("WHO",params[0]).appendQueryParameter("STATE",params[1]).appendQueryParameter("DISTRICT",params[2]).appendQueryParameter("FED_CODE",params[3]).appendQueryParameter("PANCHAYAT",params[4]).appendQueryParameter("FEDERATION",params[5]).appendQueryParameter("VILLAGE",params[6]).appendQueryParameter("ID",params[7]).appendQueryParameter("GNAME",params[8]).appendQueryParameter("MOTHER",params[9]).appendQueryParameter("FATHER",params[10]).appendQueryParameter("DOB",params[11]).appendQueryParameter("M_MEMBER",params[12]).appendQueryParameter("F_MEMBER",params[13]).appendQueryParameter("MARRIAGE_YEAR",params[14]).appendQueryParameter("EDUCATION",params[15]).appendQueryParameter("WORK",params[16]).appendQueryParameter("STUDY",params[17]).appendQueryParameter("YEAR_PUBERTY",params[18]).appendQueryParameter("HB",params[19]).appendQueryParameter("HEIGHT",params[20]).appendQueryParameter("WEIGHT",params[21]).appendQueryParameter("TIME",params[22]).appendQueryParameter("ADDMOD",params[23]).appendQueryParameter("GROUPNAME",params[24]).appendQueryParameter("GROUPTYPE",params[25]).appendQueryParameter("CONTACT",params[26]).appendQueryParameter("TRANS",params[27]).appendQueryParameter("EMPID",EMPID);

                        if(params[0]=="PREG")
                            builder=new Uri.Builder().appendQueryParameter("WHO",params[0]).appendQueryParameter("STATE",params[1]).appendQueryParameter("DISTRICT",params[2]).appendQueryParameter("FED_CODE",params[3]).appendQueryParameter("PANCHAYAT",params[4]).appendQueryParameter("FEDERATION",params[5]).appendQueryParameter("VILLAGE",params[6]).appendQueryParameter("ID",params[7]).appendQueryParameter("NAME",params[8]).appendQueryParameter("HUSBAND",params[9]).appendQueryParameter("H_MEMBER",params[10]).appendQueryParameter("DOB",params[11]).appendQueryParameter("EDUCATION",params[12]).appendQueryParameter("LMP",params[13]).appendQueryParameter("EDD",params[14]).appendQueryParameter("GRAVIDA",params[15]).appendQueryParameter("WHEN_REGISTERED",params[16]).appendQueryParameter("MONTH_PREGNANT",params[17]).appendQueryParameter("TIME",params[18]).appendQueryParameter("ADDMOD",params[19]).appendQueryParameter("CONTACT",params[20]).appendQueryParameter("TRANS",params[21]).appendQueryParameter("EMPID",EMPID);

                        if(params[0]=="DELL")
                            builder=new Uri.Builder().appendQueryParameter("WHO",params[0]).appendQueryParameter("STATE",params[1]).appendQueryParameter("DISTRICT",params[2]).appendQueryParameter("FED_CODE",params[3]).appendQueryParameter("PANCHAYAT",params[4]).appendQueryParameter("FEDERATION",params[5]).appendQueryParameter("VILLAGE",params[6]).appendQueryParameter("ID",params[7]).appendQueryParameter("NAME",params[8]).appendQueryParameter("HUSBAND",params[9]).appendQueryParameter("H_MEMBER",params[10]).appendQueryParameter("DOB",params[11]).appendQueryParameter("EDUCATION",params[12]).appendQueryParameter("PLACE",params[13]).appendQueryParameter("STATUS",params[14]).appendQueryParameter("COLOSTRUM",params[15]).appendQueryParameter("TWINS",params[16]).appendQueryParameter("OUTCOME",params[17]).appendQueryParameter("ABORTION_MONTH",params[18]).appendQueryParameter("DOD",params[19]).appendQueryParameter("GRAVIDA",params[20]).appendQueryParameter("TIME",params[21]).appendQueryParameter("ADDMOD",params[22]).appendQueryParameter("CONTACT",params[23]).appendQueryParameter("TRANS",params[24]).appendQueryParameter("EMPID",EMPID);

                        if(params[0]=="CHILD")
                            builder=new Uri.Builder().appendQueryParameter("WHO",params[0]).appendQueryParameter("STATE",params[1]).appendQueryParameter("DISTRICT",params[2]).appendQueryParameter("FED_CODE",params[3]).appendQueryParameter("PANCHAYAT",params[4]).appendQueryParameter("FEDERATION",params[5]).appendQueryParameter("VILLAGE",params[6]).appendQueryParameter("ID",params[7]).appendQueryParameter("MOM",params[8]).appendQueryParameter("M_MEMBER",params[9]).appendQueryParameter("MOM_DOB",params[10]).appendQueryParameter("DAD",params[11]).appendQueryParameter("D_MEMBER",params[12]).appendQueryParameter("NAME",params[13]).appendQueryParameter("SEX",params[14]).appendQueryParameter("WEIGHTB",params[15]).appendQueryParameter("DOB",params[16]).appendQueryParameter("COLOSTRUM",params[17]).appendQueryParameter("ORDERBIRTH",params[18]).appendQueryParameter("HEIGHT",params[19]).appendQueryParameter("WEIGHTG",params[20]).appendQueryParameter("TIME",params[21]).appendQueryParameter("ADDMOD",params[22]).appendQueryParameter("MOMID",params[23]).appendQueryParameter("CONTACT",params[24]).appendQueryParameter("TRANS",params[25]).appendQueryParameter("EMPID",EMPID);


                        String query=builder.build().getEncodedQuery();
                        //Log.d(mess,"QUERY->"+query);
                        OutputStream os=connection.getOutputStream();
                        //Log.d(mess,"99");
                        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                        //Log.d(mess,"100");
                        bw.write(query);
                        bw.flush();
                        bw.close();
                        os.close();
                        connection.connect();
                        //Log.d(mess,"101");
                      //  connection.disconnect();

                        ///--------Checking ACKNOWLEDGEMENT FROM SERVER---------------------------
                        try
                        {
                            int responseCode=connection.getResponseCode();

                            if(responseCode==HttpURLConnection.HTTP_OK)
                            {
                                InputStream inputStream= connection.getInputStream();
                                BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder sb= new StringBuilder();
                                String line;
                                while ((line=br.readLine())!=null)
                                {
                                    sb.append(line);
                                }
                                //Log.d("ACKNOWLEDGE->",sb.toString());
                                connection.disconnect();
                            }
                            else
                            {
                                //Log.d("ACKNOWLEDGE","Nothing!!");
                                connection.disconnect();
                            }

                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                            return e.toString();
                        }


                ///--------Checking ACKNOWLEDGEMENT FROM SERVER-----------------------

                    //----CODE FOR DELETING THE SQLITE DB ROW SENT TO SERVER---


                        try {

                            uri = new URL(MainActivity.SERVER+"GetFromServer.php");
                        }
                        catch (MalformedURLException e)
                        {
                            e.printStackTrace();
                            //Log.d("inside catch","33");
                            //return e.toString();
                        }
                        try {
                             mess="CONNECTION 2";
                            connection2=(HttpURLConnection)uri.openConnection();
                            connection2.setDoOutput(true);
                            connection2.setDoInput(true);
                            connection2.setConnectTimeout(10000);
                            connection2.setReadTimeout(10000);
                            Uri.Builder builder2=new Uri.Builder().appendQueryParameter("ID",params[7]).appendQueryParameter("WHO",params[0]);
                            String query2=builder2.build().getEncodedQuery();
                            OutputStream os2=connection2.getOutputStream();
                            //Log.d(mess,"QUERY2 ->"+query2);
                            BufferedWriter bw2=new BufferedWriter(new OutputStreamWriter(os2,"UTF-8"));
                            //Log.d(mess,"100");
                            bw2.write(query2);
                            bw2.flush();
                            bw2.close();
                            os2.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                            return e.toString();
                        }



                        try {
                            int responsecode=connection2.getResponseCode();
                            if(responsecode==HttpURLConnection.HTTP_OK)
                            {
                                InputStream inputStream= connection2.getInputStream();
                                BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
                                StringBuilder sb= new StringBuilder();
                                String line;
                                while ((line=br.readLine())!=null)
                                {
                                    sb.append(line);
                                }
                                //Log.d("Received Data:",sb.toString());
                                connection2.disconnect();
                                IDtoDelete=params[7];
                                return (sb.toString());
                            }

                            return "FAIL";

                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                            return e.toString();
                        }

                        //----CODE FOR DELETING THE SQLITE DB ROW SENT TO SERVER---

                        //return "SENT";
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        //Log.d(mess,"44");
                        return e.toString();
                    }


                }
                else
                    return "FAIL";
            }catch (IOException e)
            {
                e.printStackTrace();
                return e.toString();
            }
            finally {
               // socket.close();
            }
            //return "FAIL";




        }

        @Override
        protected void onPostExecute(String message) {
            //mConsumer.accept(internet);
            String mess="POST-EXECUTE";
            //Log.d(mess,"Entered onPostExecute");

            if(!message.equals("FAIL"))
            {//Log.d(mess,"IF: Message Succeeded");
                try {
                    jsonGetUID = new JSONObject(message);
                    //DataBaseHelper DB=new DataBaseHelper(myContext,null,null,1);
                    DataBaseHelper DB=DataBaseHelper.getInstance(myContext);
                    DB.deleteData(jsonGetUID.get("ID").toString());
                    //DB.updateArchive(jsonGetUID.get("UID").toString());
                    DB.setArchiveData(jsonGetUID.get("NAME").toString(), jsonGetUID.get("UID").toString(), "NO");
                    //DB.close();
                }
                catch (JSONException j)
                {
                    j.printStackTrace();
                }


                //Toast.makeText(context,"NET IS PRESENT",Toast.LENGTH_LONG).show();
            }
            //else {
                //Log.d(mess,"ELSE: Message failed");
                // Toast.makeText(getApplicationContext(), "NET IS ABSENT", Toast.LENGTH_LONG).show();
            //}

        }


    }

}
