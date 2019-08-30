package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childApplicant;

public class Parents extends Fragment {

    static String MomName="";
    static String MomDoB="";
    static String FatherName="";
    static String MomMem="";
    static String DadMem="";
    static String MomId=null;

    View viewParents;
    TextView heading;
    EditText editDoB;
    EditText editMother;
    EditText editFather;
    EditText editMomId;
    Spinner spinMom;
    Spinner spinDad;

    EditText editContact;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewParents=inflater.inflate(R.layout.parents,container,false);
        heading = ((TextView) viewParents.findViewById(R.id.textViewHeadingParents));

        if (childApplicant.ApplName != null) {
            heading.setText("Enter Parents Details for " + childApplicant.ApplName);
        }

        editDoB=viewParents.findViewById(R.id.editTextMomDoB);
        editMother=viewParents.findViewById(R.id.editTextNameMom);
        editFather=viewParents.findViewById(R.id.editTextFather);
        spinMom=viewParents.findViewById(R.id.spinnerParMemberM);
        spinDad=viewParents.findViewById(R.id.spinnerParMemberF);
        editMomId=viewParents.findViewById(R.id.editTextMomId);

        editContact=viewParents.findViewById(R.id.editTextContact);

        if(childApplicant.ID_ENTERED)
        {
            //((TableRow)viewParents.findViewById(R.id.RowMomId)).setVisibility(View.GONE);
           // ((TableRow)viewParents.findViewById(R.id.RowNote)).setVisibility(View.GONE);

            /*if (childApplicant.ID_WHO.equals("MOTH"))
                heading.setText("Enter Parents Details of Child of " + childApplicant.ApplName);
            else
                heading.setText("Enter Parents Details for " + childApplicant.ApplName);*/

            try {
                editContact.setText(childApplicant.jsonObject.get("CONTACT").toString());
                if(childApplicant.ID_WHO.equals("MOTH"))
               editDoB.setText(childApplicant.jsonObject.get("DOB").toString());
                else if(childApplicant.ID_WHO.equals("CHILD"))
                    editDoB.setText(childApplicant.jsonObject.get("MOM_DOB").toString());



                if(childApplicant.ID_WHO.equals("MOTH"))
               {
                   editMother.setText(childApplicant.jsonObject.get("NAME").toString());
                   editFather.setText(childApplicant.jsonObject.get("HUSBAND").toString());
                   editMomId.setText(MomId);
                   editMomId.setEnabled(false);
                   ((TableRow)viewParents.findViewById(R.id.RowNote)).setVisibility(View.GONE);

                   ArrayAdapter<String> arrayAdapter =
                           new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerWomanMembership));

                   //spinMom.setSelection(arrayAdapter.getPosition(deliveryApplicant.jsonObject.get("M_MEMBER").toString()));
                   spinMom.setSelection(arrayAdapter.getPosition("SUHAM"));
                    arrayAdapter =
                           new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerManMembership));

                   spinDad.setSelection(arrayAdapter.getPosition(childApplicant.jsonObject.get("H_MEMBER").toString()));
               }

               if(childApplicant.ID_WHO.equals("CHILD"))
               {
                   editMother.setText(childApplicant.jsonObject.get("MOM").toString());
                   editFather.setText(childApplicant.jsonObject.get("DAD").toString());
                   editMomId.setText(childApplicant.jsonObject.get("MID").toString());

                   ArrayAdapter<String> arrayAdapter =
                           new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerWomanMembership));

                   //spinMom.setSelection(arrayAdapter.getPosition(deliveryApplicant.jsonObject.get("M_MEMBER").toString()));
                   spinMom.setSelection(arrayAdapter.getPosition(childApplicant.jsonObject.get("M_MEMBER").toString()));
                   arrayAdapter =
                           new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinnerManMembership));

                   spinDad.setSelection(arrayAdapter.getPosition(childApplicant.jsonObject.get("D_MEMBER").toString()));
               }
           }
           catch (JSONException j)
           {
               j.printStackTrace();
           }
        }
        else {

            ((TableRow)viewParents.findViewById(R.id.RowMomId)).setVisibility(View.VISIBLE);
            ((TableRow)viewParents.findViewById(R.id.RowNote)).setVisibility(View.VISIBLE);

        }

        editDoB.addTextChangedListener(new GenericTextWatcher(editDoB));

        return viewParents;
    }
}
