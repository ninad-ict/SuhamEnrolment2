package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childApplicant;

public class ChildBirth extends Fragment {

    EditText editDoB;
    EditText editCBName;
    EditText editCBWeight;

    RadioButton radioCBM;
    RadioButton radioCBF;
    RadioButton radioColY;
    RadioButton radioColN;


     String ChildName="";
     String ChildWeight="";
     String ChildGender="";
     String ChildDOB="";
     //static String babyDOB="";
     String ChildCol="";
View viewChildBirth;
    TextView heading;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewChildBirth=inflater.inflate(R.layout.childbirth,container,false);


        heading = ((TextView) viewChildBirth.findViewById(R.id.textViewHeadingCB));

        if (childApplicant.ApplName != null) {
            heading.setText("Enter Birth Details for " + childApplicant.ApplName);
        }
        editDoB=viewChildBirth.findViewById(R.id.editTextChildDob);
        editCBName=viewChildBirth.findViewById(R.id.editTextChildName);
        editCBWeight=viewChildBirth.findViewById(R.id.editTextChildBWeight);
        radioCBF=viewChildBirth.findViewById(R.id.radioButtonChildFemale);
        radioCBM=viewChildBirth.findViewById(R.id.radioButtonChildMale);
        radioColN=viewChildBirth.findViewById(R.id.radioButtonChildCol_N);
        radioColY=viewChildBirth.findViewById(R.id.radioButtonChildCol_Y);

        editCBName.setText(childApplicant.ApplName);
        if(childApplicant.ID_ENTERED) {
            try {
                if (childApplicant.ID_WHO == "CHILD")
                {

                heading.setText("Enter Birth Details for " + childApplicant.ApplName);
                editDoB.setText(childApplicant.jsonObject.get("DOB").toString());
                editCBName.setText(childApplicant.jsonObject.get("NAME").toString());
                editCBWeight.setText(childApplicant.jsonObject.get("WEIGHTB").toString());
                if (childApplicant.jsonObject.get("SEX").toString().equals("MALE"))
                    radioCBM.setChecked(true);
                else
                    radioCBF.setChecked(true);

                if (childApplicant.jsonObject.get("COLOSTRUM").toString().equals("YES"))
                    radioColY.setChecked(true);
                else
                    radioColN.setChecked(true);
            }
                if(childApplicant.ID_WHO=="MOTH")
                {
                    editCBName.setText("");
                    //heading.setText("Enter Birth Details for Child of " + childApplicant.ApplName);
                }

            }
            catch (JSONException j)
            {
                j.printStackTrace();
            }
        }


        editDoB.addTextChangedListener(new GenericTextWatcher(editDoB));

        return viewChildBirth;
    }
}
