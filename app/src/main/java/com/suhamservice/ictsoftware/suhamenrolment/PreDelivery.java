package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteApplicant;

public class PreDelivery extends Fragment {

    View viewPreD;
    EditText editDD;
    EditText editGravida;
    EditText editLMP;
    EditText editPHC;
    EditText editPreg;

    TextView heading;
    SwitchCompat switchCompat;
    TableLayout tableLayoutDelivery;
    TableLayout tableLayoutPreD;

    AppCompatCheckBox checkBoxNormal;
    AppCompatCheckBox checkBoxAbortion;
    AppCompatCheckBox checkBoxMMR;
    AppCompatCheckBox checkBoxStill;


     String LMP="";
     String EDD="";
     String GRAVIDA="";
     String PHC="";
     String MONTHPREG="";

     boolean PD_SAVED=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewPreD=inflater.inflate(R.layout.predelivery,container,false);

        heading=((TextView)viewPreD.findViewById(R.id.textViewHeadingPD));

        tableLayoutDelivery= ((TableLayout)viewPreD.findViewById(R.id.TableDelivery));
        tableLayoutPreD= ((TableLayout)viewPreD.findViewById(R.id.TablePreDelivery2));

        if (MainActivity.anteApplicant.ApplName!= null) {
            heading.setText("Enter Pregnancy Details for " + MainActivity.anteApplicant.ApplName);
        }

         editGravida=viewPreD.findViewById(R.id.editTextGravida);
         editDD=viewPreD.findViewById(R.id.editTextEDD);
         editLMP=viewPreD.findViewById(R.id.editTextLMP);
         editPHC=viewPreD.findViewById(R.id.editTextPHC);
         editPreg=viewPreD.findViewById(R.id.editTextMP);

         if(anteApplicant.ID_ENTERED)
         {
             try {
                 //Log.d("IN ANTE PreDelivery","If->try part");
                 editGravida.setText(anteApplicant.jsonObject.get("GRAVIDA").toString());
                 editDD.setText(anteApplicant.jsonObject.get("EDD").toString());
                 editLMP.setText(anteApplicant.jsonObject.get("LMP").toString());
                 editPHC.setText(anteApplicant.jsonObject.get("WHEN_REGISTERED").toString());
                 editPreg.setText(anteApplicant.jsonObject.get("MONTH_PREGNANT").toString());
             }
             catch (JSONException j)
             {
                 j.printStackTrace();
             }
         }
         //else
          //   //Log.d("IN ANTE PreDelivery","Else part");


        editDD.addTextChangedListener(new GenericTextWatcher(editDD));
        editLMP.addTextChangedListener(new GenericTextWatcher(editLMP));
        editPHC.addTextChangedListener(new GenericTextWatcher(editPHC));

        return viewPreD;
    }

}
