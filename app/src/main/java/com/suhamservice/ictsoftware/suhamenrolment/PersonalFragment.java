package com.suhamservice.ictsoftware.suhamenrolment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;

public class PersonalFragment extends Fragment {
    View v;

    EditText editDoB;
    EditText editMother;
    EditText editFather;
    EditText editMarriage;
    Spinner spinMom;
    Spinner spinDad;

    RadioButton buttonNO;
    RadioButton buttonYES;


    TextView heading;
    static int YOB=0;
    static TextWatcher TWbod;
   // String rName="";

    //----FINAL VALUES for the EDITable Text------
    static Boolean ISMARRIED=false;
    static String Name="";
    static String Mother="";
    static String MotherMem="";
    static String Father="";
    static String FatherMem="";
    static String DOB="";
    static String MarrYear="";

    //----FINAL VALUES for the EDITable Text------
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.PERSONALSAVED=false;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.PERSONALSAVED=false;
        v=inflater.inflate(R.layout.personalfragment,container,false);


        //TableLayout tablePersonal=(TableLayout)v.findViewById(R.id.TablePersonal);
        //TableRow rowMother=(TableRow)v.findViewById(R.id.rowMother);
       // tablePersonal.removeView(rowMother);

            heading = ((TextView) v.findViewById(R.id.textViewHeadingP));
            if (applicationName.ApplName != null) {
                heading.setText("Enter Personal Details for " + applicationName.ApplName);
                ((EditText) v.findViewById(R.id.editTextName)).setText(applicationName.ApplName);
            }

            if (ISMARRIED) {
                v.findViewById(R.id.editTextMarrYear).setVisibility(View.VISIBLE);
               // v.findViewById(R.id.textViewMarrYear).setVisibility(View.VISIBLE);
            } else {
                v.findViewById(R.id.editTextMarrYear).setVisibility(View.INVISIBLE);
                //v.findViewById(R.id.textViewMarrYear).setVisibility(View.INVISIBLE);
            }

            editDoB=v.findViewById(R.id.editTextDob);
            editMother=v.findViewById(R.id.editTextMother);
            editFather=v.findViewById(R.id.editTextFather);
            editMarriage=v.findViewById(R.id.editTextMarrYear);
            buttonNO=v.findViewById(R.id.radioButtonNo);
            buttonYES=v.findViewById(R.id.radioButtonYes);
            spinMom=v.findViewById(R.id.spinnerMomMember);
            spinDad=v.findViewById(R.id.spinnerDadMember);

        if (applicationName.ID_ENTERED) {
            try {
                //Log.d("IN GIRL PERSONAL", "In try part");
                editDoB.setText(applicationName.jsonObject.get("DOB").toString());

                editMother.setText(applicationName.jsonObject.get("MOTHER").toString());
                editFather.setText(applicationName.jsonObject.get("FATHER").toString());

                ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerMembership));

                spinDad.setSelection(arrayAdapter.getPosition(applicationName.jsonObject.get("F_MEMBER").toString()));
                spinMom.setSelection(arrayAdapter.getPosition(applicationName.jsonObject.get("M_MEMBER").toString()));


                if(applicationName.jsonObject.get("MARRIAGE_YEAR").toString().equals("NULL"))
                {
                    //Log.d("IN GIRL PERSONAL", "No marriage");
                    buttonNO.setChecked(true);
                    editMarriage.setVisibility(View.INVISIBLE);
                }
                else
                {
                    //Log.d("IN GIRL PERSONAL", "YES marriage");
                    buttonYES.setChecked(true);
                    editMarriage.setVisibility(View.VISIBLE);
                    editMarriage.setText(applicationName.jsonObject.get("MARRIAGE_YEAR").toString());
                }



            }

            catch(JSONException j)
            {
               // //Log.d("IN DELL PregPersonal", "In Else part");
                j.printStackTrace();
            }
        }

        editDoB.addTextChangedListener(new GenericTextWatcher(editDoB));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.PERSONALSAVED=false;
    }
}
