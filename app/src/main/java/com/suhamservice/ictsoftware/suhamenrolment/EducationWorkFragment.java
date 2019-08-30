package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;

public class EducationWorkFragment extends Fragment {

    //----FINAL VALUES for the EDITable Text------

    static String EDUCATION="";
    static String WORK="";
    static String STUDY="";
    static boolean CURRSTUDY;

    Spinner spinEdu;
    Spinner spinWork;
    RadioButton buttNoStudy;
    RadioButton buttYesStudy;


    //----FINAL VALUES for the EDITable Text------

    View educationView;
    TextView heading;
    //Spinner he;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        MainActivity.EDUWORKSAVED=false;
        educationView= inflater.inflate(R.layout.educationfragment,container,false);

        heading=((TextView)educationView.findViewById(R.id.textViewHeadingE));

        buttNoStudy =educationView.findViewById(R.id.radioButtonENo);
        buttYesStudy=educationView.findViewById(R.id.radioButtonEYes);
        spinEdu=educationView.findViewById(R.id.spinnerHE);
        spinWork=educationView.findViewById(R.id.spinnerOcc);


        if (applicationName.ApplName!=null)
        {
            heading.setText("Enter Education/Work Details for "+applicationName.ApplName);
        }

        if(applicationName.ID_ENTERED)
        {
            try {

               // Log.d("IN GIRL Location","In try part");
                ArrayAdapter<String> arrayAdapterHE =
                        new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerEntryHE));
                ArrayAdapter<String> arrayAdapterOcc =
                        new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerEntryOcc));
                spinEdu.setSelection(arrayAdapterHE.getPosition(applicationName.jsonObject.get("EDUCATION").toString()));
                spinWork.setSelection(arrayAdapterOcc.getPosition(applicationName.jsonObject.get("WORK").toString()));

                if(applicationName.jsonObject.get("STUDY").toString().equals("NO"))
                {
                    buttNoStudy.setChecked(true);
                }
                else
                    buttYesStudy.setChecked(true);

                //FedCode.setText(applicationName.jsonObject.get("FED_CODE").toString());

                ////Log.d("IN DELL Location","FedCode.gettext->"+FedCode.getText());

            }
            catch (JSONException j)
            {
                j.printStackTrace();
            }
        }
        //else
          //  Log.d("IN GIRL Location","Else part");
       //he=educationView.findViewById(R.id.spinnerHE);

        return educationView;
    }
}
