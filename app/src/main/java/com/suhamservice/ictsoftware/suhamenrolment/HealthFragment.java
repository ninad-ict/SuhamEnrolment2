package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;

public class HealthFragment extends Fragment {

    View healthView;
    TextView heading;
    static EditText EditPuberty;
    static int YOP=0;

    //----FINAL VALUES for the EDITable Text------

    static String PubertyYear="";
    static String HB="";
    static String HEIGHT="";
    static String WEIGHT="";

    EditText editHB;
    EditText editHeight;
    EditText editWeight;
    RadioButton butPubNO;
    RadioButton butPubYES;

    //----FINAL VALUES for the EDITable Text------


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        MainActivity.HEALTHSAVED=false;
        healthView= inflater.inflate(R.layout.healthfragment,container,false);
        EditPuberty=((EditText)healthView.findViewById(R.id.editTextPuberty));
        heading=((TextView)healthView.findViewById(R.id.textViewHeadingH));

        editHB=((EditText)healthView.findViewById(R.id.editTextHB));
        editHeight=((EditText)healthView.findViewById(R.id.editTextHeight));
        editWeight=((EditText)healthView.findViewById(R.id.editTextWeight));
        butPubNO=((RadioButton)healthView.findViewById(R.id.radioButtonPNo));
        butPubYES=((RadioButton)healthView.findViewById(R.id.radioButtonPYes));

        if (applicationName.ApplName!=null)
        {
            heading.setText("Enter Health Details for "+applicationName.ApplName);
        }

        if (applicationName.ID_ENTERED) {
            try {
                ////Log.d("IN GIRL PERSONAL", "In try part");

                editHB.setText(applicationName.jsonObject.get("HB").toString());
                editHeight.setText(applicationName.jsonObject.get("HEIGHT").toString());
                editWeight.setText(applicationName.jsonObject.get("WEIGHT").toString());

               // ArrayAdapter<String> arrayAdapter =
                //new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerMembership));


                if(applicationName.jsonObject.get("YEAR_PUBERTY").toString().equals("NULL"))
                {
                    ////Log.d("IN GIRL Health", "No PUB");
                    butPubNO.setChecked(true);
                    EditPuberty.setVisibility(View.INVISIBLE);
                }
                else
                {
                    ////Log.d("IN GIRL Health", "YES PUB");
                    butPubYES.setChecked(true);
                    EditPuberty.setVisibility(View.VISIBLE);
                    EditPuberty.setText(applicationName.jsonObject.get("YEAR_PUBERTY").toString());
                }
            }

            catch(JSONException j)
            {
                ////Log.d("IN DELL PregPersonal", "In Else part");
                j.printStackTrace();
            }
        }
        return healthView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(((RadioButton)healthView.findViewById(R.id.radioButtonPNo)).isChecked())
        {
            ((EditText)(healthView.findViewById(R.id.editTextPuberty))).setVisibility(View.INVISIBLE);
            ((TextView)(healthView.findViewById(R.id.textViewPubertyYear))).setVisibility(View.INVISIBLE);

        }

        if(MainActivity.PERSONALSAVED)
        {
            if ((((RadioButton)healthView.findViewById(R.id.radioButtonPYes)).isChecked())&&(HealthFragment.YOP<=PersonalFragment.YOB))
            {
                //EditPuberty.setText("");
                ((EditText)healthView.findViewById(R.id.editTextPuberty)).setText("");
            }
        }
    }
}


