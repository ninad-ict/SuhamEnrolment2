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
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_ANTE;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_DELL;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.deliveryApplicant;

public class PregPersonal extends Fragment {
    View v;
    EditText editDoB;
    EditText editHusband;
    Spinner SpinHusband;
    Spinner SpinEducation;

    TextWatcher textWatcher;

    TextView heading;
    static int YOB=0;
    // String rName="";

    //----FINAL VALUES for the EDITable Text------
    //static Boolean ISMARRIED=false;
    String Name="";
   String Husband="";
   String HusbandMem="";
    //static String Father="";
    String DOB="";
    String Education="";
    Boolean PPSAVED=false;
  //  static String MarrYear="";

    //----FINAL VALUES for the EDITable Text------
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // MainActivity.PERSONALSAVED=false;

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       // MainActivity.PERSONALSAVED=false;
            v=inflater.inflate(R.layout.pregpersonal,container,false);


        //TableLayout tablePersonal=(TableLayout)v.findViewById(R.id.TablePersonal);
        //TableRow rowMother=(TableRow)v.findViewById(R.id.rowMother);
        // tablePersonal.removeView(rowMother);

        heading = ((TextView) v.findViewById(R.id.textViewHeadingPregP));
        editHusband = ((EditText) v.findViewById(R.id.editTextHusband));
        editDoB=v.findViewById(R.id.editTextDobP);
        SpinHusband=((Spinner)v.findViewById(R.id.spinnerPregHusband));
        SpinEducation=((Spinner)v.findViewById(R.id.spinnerEntryPregEdu));

        if(ENROL_ANTE)
        {
            if (anteApplicant.ApplName != null) {
                heading.setText("Enter Personal Details for " + anteApplicant.ApplName);
                ((EditText) v.findViewById(R.id.editTextNameP)).setText(anteApplicant.ApplName);
            }

            if (anteApplicant.ID_ENTERED) {
                try {
                    //Log.d("IN DELL PregPersonal", "In try part");
                    editDoB.setText(anteApplicant.jsonObject.get("DOB").toString());

                    if (anteApplicant.ID_WHO=="PREG") {
                        editHusband.setText(anteApplicant.jsonObject.get("HUSBAND").toString());
                        ArrayAdapter<String> arrayAdapter =
                                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerMembership));

                        SpinHusband.setSelection(arrayAdapter.getPosition(anteApplicant.jsonObject.get("H_MEMBER").toString()));
                        arrayAdapter =
                                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerEntryPregEdu));

                        SpinEducation.setSelection(arrayAdapter.getPosition(anteApplicant.jsonObject.get("EDUCATION").toString()));

                    }

                }

                catch(JSONException j)
                {
                   // //Log.d("IN DELL PregPersonal", "In Else part");
                    j.printStackTrace();
                }
            }
        }

        if(ENROL_DELL) {
            if (deliveryApplicant.ApplName != null) {
                heading.setText("Enter Personal Details for " + deliveryApplicant.ApplName);
                ((EditText) v.findViewById(R.id.editTextNameP)).setText(deliveryApplicant.ApplName);
            }

            if (deliveryApplicant.ID_ENTERED) {
                try {
                    //Log.d("IN DELL PregPersonal", "In try part");
                    editDoB.setText(deliveryApplicant.jsonObject.get("DOB").toString());
                    if ((deliveryApplicant.ID_WHO.equals("PREG"))||(deliveryApplicant.ID_WHO.equals("MOTH")))
                    {
                        editHusband.setText(deliveryApplicant.jsonObject.get("HUSBAND").toString());

                       // String[] MemberArray = getResources().getStringArray(R.array.spinnerMembership);
                        ArrayAdapter<String> arrayAdapter =
                                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerMembership));

                        SpinHusband.setSelection(arrayAdapter.getPosition(deliveryApplicant.jsonObject.get("H_MEMBER").toString()));
                         arrayAdapter =
                                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerEntryPregEdu));

                        SpinEducation.setSelection(arrayAdapter.getPosition(deliveryApplicant.jsonObject.get("EDUCATION").toString()));


                    }
                }

                catch(JSONException j)
            {
                ////Log.d("IN DELL PregPersonal", "In Else part");
                j.printStackTrace();
            }
        }

        }



    editDoB.addTextChangedListener(new GenericTextWatcher(editDoB));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.PERSONALPREGSAVED=false;
    }

    /*  @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       // (getActivity().findViewById(R.id.editTextName)).toString();
    }*/
}
