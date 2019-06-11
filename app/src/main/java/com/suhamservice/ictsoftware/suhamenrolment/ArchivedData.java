package com.suhamservice.ictsoftware.suhamenrolment;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ArchivedData extends Fragment {
View ViewArchive;
Button ButtonRefresh;
Button ButtonHome;
InternetChecker internetChecker=new InternetChecker();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
String mess="OnCreate:Arch";
        ViewArchive=inflater.inflate(R.layout.archievefragment,container,false);

        //-------CODE FOR HOME BUTTON------------------------

        ButtonRefresh=ViewArchive.findViewById(R.id.ButtonArRefresh);
        ButtonHome=ViewArchive.findViewById(R.id.ButtonArBack);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame1, new HomePage());

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //-------CODE FOR HOME BUTTON------------------------

        //-------CODE FOR REFRESH BUTTON------------------------
        ButtonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(getContext(),"YOU CLICKED",Toast.LENGTH_LONG).show();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                //intentFilter.addAction();
                getActivity().registerReceiver(internetChecker, intentFilter);
               // new ArchivedData();
                //ViewArchive.refreshDrawableState();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame1, new ArchivedData());

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        //-------CODE FOR REFRESH BUTTON------------------------


        //----------CODE FOR DISPLAYING ARCHIVED DATA IN TABLE------------------

        TableLayout tableLayout=ViewArchive.findViewById(R.id.TableArch);

        //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
        DataBaseHelper DB=DataBaseHelper.getInstance(getContext());

        String data[][]=DB.getArchive();
        //DB.close();
        //  Log.d(mess,"data.length->"+data.length);
        TableRow[] tableRows=new TableRow[data.length];
        //  Log.d(mess,"tableRows.length->"+tableRows.length);

        for (int i=0;i<tableRows.length;i++)
        {
            //TextView[] textView=new TextView[data[i].length];
            tableRows[i]=new TableRow(getActivity());
            //TableRow.LayoutParams.WRAP_CONTENT;
            //tableRows[i].setLayoutParams();
           // tableRows[i].setMinimumWidth( TableRow.LayoutParams.MATCH_PARENT);
          //  tableRows[i].setMinimumHeight(TableRow.LayoutParams.WRAP_CONTENT);

            for (int j=0;j<data[i].length;j++)
            {//Log.d(mess,"data["+i+"]["+j+"]->"+data[i][j]);
                TextView textView=new TextView(getActivity());
                //tableRows[i].LayoutParams para
                TableRow.LayoutParams params=new TableRow.LayoutParams();
               // params=(TableRow.LayoutParams)textView.getLayoutParams();
              //  tableRows[i].LayoutParams para_span=(TableRow.LayoutParams)textView.getLayoutParams();


                switch (j)
                {
                    case 0:
                        params.span=2;
                        //       Log.d(mess,"params.span->"+params.span);
                        break;
                    case 1:
                        params.span=4;
                        //    Log.d(mess,"params.span->"+params.span);
                        break;
                    case 2:
                        params.span=4;
                        //  Log.d(mess,"params.span->"+params.span);
                        break;

                        default:
                            params.span=1;
                            break;
                }

                //textView.setHeight(android.text.style.);
                textView.setText(data[i][j]);
                textView.setTextSize(20);
               // ContextCompat.getDrawable(getContext(),R.drawable.cell_shape);

                textView.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.cell_shape));
                textView.setTextColor(getResources().getColor(R.color.Black,null));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


               // params.span=5;
               // textView.setLayoutParams(params);

                // Log.d(mess, "textView.getText()->" + textView.getText().toString());

                //tableRows[i].addView(textView);
                tableRows[i].addView(textView,params);


            }
            tableLayout.addView(tableRows[i]);
        }


        /*TextView t1=new TextView(getContext());
        t1.setText("HIII");
        tableRow.addView(t1);
        tableLayout.addView(tableRow);*/


        //----------CODE FOR DISPLAYING ARCHIVED DATA ------------------


        return ViewArchive;
    }



}
