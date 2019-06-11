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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.deliveryApplicant;

public class Delivery extends Fragment {

     String Place=null;
     String Status=null;
     //String Sex="";
     String Colostrum=null;
   //  String Weight="";
     String twins=null;
     String aborMonth=null;
     String EDD=null;

     String Gravida=null;
     String Outcome="";

    SwitchCompat switchCompat;



    EditText editDoB;
    EditText editGravida;
    EditText editAbortion;


    TableLayout tableLayoutDelivery;
    TableLayout tableLayoutPreD;

    AppCompatCheckBox checkBoxNormal;
    AppCompatCheckBox checkBoxAbortion;
    AppCompatCheckBox checkBoxMMR;
    AppCompatCheckBox checkBoxStill;

    RadioButton radioHospital;
    RadioButton radioHome;
    RadioButton radioNormal;
    RadioButton radioCaes;
    RadioButton radioColYes;
    RadioButton radioColNo;

    TextView heading;


View viewDelivery;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewDelivery=inflater.inflate(R.layout.delivery,container,false);

        checkBoxNormal=viewDelivery.findViewById(R.id.checkNormal);
        checkBoxAbortion=viewDelivery.findViewById(R.id.checkAbortion);
        checkBoxMMR=viewDelivery.findViewById(R.id.checkMMR);
        checkBoxStill=viewDelivery.findViewById(R.id.checkStill);

        editDoB=viewDelivery.findViewById(R.id.editTextDelDob);

        editGravida=viewDelivery.findViewById(R.id.editTextDelGravida);

        editAbortion=viewDelivery.findViewById(R.id.editTextDelAbortion);

        tableLayoutDelivery= ((TableLayout)viewDelivery.findViewById(R.id.TableDelivery));

        heading=((TextView)viewDelivery.findViewById(R.id.textViewHeadingDelivery));

        if (deliveryApplicant.ApplName!= null) {
            heading.setText("Enter Delivery Details for " + deliveryApplicant.ApplName);
        }

        checkBoxNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TableRow.LayoutParams paramsExample = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
                //TableRow.LayoutParams paramsExampleHide = new TableRow.LayoutParams(0,0);
                if(isChecked)
                {
                    checkBoxAbortion.setEnabled(false);
                    checkBoxStill.setEnabled(false);
                    viewDelivery.findViewById(R.id.TableDelivery).setVisibility(View.VISIBLE);
                    // viewDelivery.findViewById(R.id.tableDelChildS).setVisibility(View.VISIBLE);

                    if(!deliveryApplicant.ID_ENTERED) {
                        viewDelivery.findViewById(R.id.LinearDelChildDetails).setVisibility(View.VISIBLE);
                        viewDelivery.findViewById(R.id.LinearChildBanner).setVisibility(View.VISIBLE);
                    }
                    viewDelivery.findViewById(R.id.LinearBirthBanner).setVisibility(View.VISIBLE);
                    viewDelivery.findViewById(R.id.LinearBirthDetails).setVisibility(View.VISIBLE);
                    ((TextView)viewDelivery.findViewById(R.id.textviewBirthBanner)).setText("Delivery Details");

                }
                if(!isChecked)
                {
                    if(!checkBoxMMR.isChecked())
                        checkBoxAbortion.setEnabled(true);
                    checkBoxStill.setEnabled(true);
                    viewDelivery.findViewById(R.id.TableDelivery).setVisibility(View.GONE);
                    //   viewDelivery.findViewById(R.id.tableChildGrowth).setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.LinearChildBanner).setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.LinearDelChildDetails).setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.LinearBirthBanner).setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.LinearBirthDetails).setVisibility(View.GONE);



                }
            }
        });

        checkBoxAbortion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    checkBoxNormal.setEnabled(false);
                    checkBoxStill.setEnabled(false);
                    checkBoxMMR.setEnabled(false);
                    viewDelivery.findViewById(R.id.rowDelAbortion).setVisibility(View.VISIBLE);

                    viewDelivery.findViewById(R.id.LinearBirthBanner).setVisibility(View.VISIBLE);
                    viewDelivery.findViewById(R.id.LinearBirthDetails).setVisibility(View.VISIBLE);

                    ((TextView)viewDelivery.findViewById(R.id.textviewBirthBanner)).setText("Abortion Details");



                }
                if(!isChecked)
                {
                    checkBoxNormal.setEnabled(true);
                    checkBoxStill.setEnabled(true);
                    checkBoxMMR.setEnabled(true);
                    viewDelivery.findViewById(R.id.rowDelAbortion).setVisibility(View.GONE);

                    viewDelivery.findViewById(R.id.LinearBirthBanner).setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.LinearBirthDetails).setVisibility(View.GONE);


                }
            }
        });

        checkBoxMMR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    checkBoxAbortion.setEnabled(false);
                }
                if(!isChecked&&!checkBoxNormal.isChecked()&&!checkBoxStill.isChecked())
                {
                    checkBoxAbortion.setEnabled(true);
                }
            }
        });

        checkBoxStill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    checkBoxAbortion.setEnabled(false);
                    checkBoxNormal.setEnabled(false);
                }
                if(!isChecked)
                {
                    checkBoxAbortion.setEnabled(true);
                    checkBoxNormal.setEnabled(true);
                }
            }
        });

        if(deliveryApplicant.ID_ENTERED)
        {


            try {
                editGravida.setText(deliveryApplicant.jsonObject.get("GRAVIDA").toString());
                String checkOutcome=deliveryApplicant.jsonObject.get("OUTCOME").toString();
                if(checkOutcome.contains("CB"))
                    checkBoxNormal.setChecked(true);
                if(checkOutcome.contains("AB"))
                    checkBoxAbortion.setChecked(true);
                if(checkOutcome.contains("MD"))
                    checkBoxMMR.setChecked(true);
                if(checkOutcome.contains("SB"))
                    checkBoxStill.setChecked(true);

                viewDelivery.findViewById(R.id.LinearDelChildDetails).setVisibility(View.GONE);
                viewDelivery.findViewById(R.id.LinearChildBanner).setVisibility(View.GONE);

                if(checkBoxNormal.isChecked()) {
                    radioHospital = viewDelivery.findViewById(R.id.radioButtonHos);
                    radioHome = viewDelivery.findViewById(R.id.radioButtonHome);
                    radioNormal = viewDelivery.findViewById(R.id.radioButtonNor);
                    radioCaes = viewDelivery.findViewById(R.id.radioButtonCaes);
                    radioColYes = viewDelivery.findViewById(R.id.radioButtonCol_Y);
                    radioColNo = viewDelivery.findViewById(R.id.radioButtonCol_N);

                    if (deliveryApplicant.jsonObject.get("PLACE").toString().equals("HOSPITAL"))
                        radioHospital.setChecked(true);
                    else radioHome.setChecked(true);

                    if (deliveryApplicant.jsonObject.get("STATUS").toString().equals("CAESARIAN"))
                        radioCaes.setChecked(true);
                    else radioNormal.setChecked(true);

                    if (deliveryApplicant.jsonObject.get("COLOSTRUM").toString().equals("YES"))
                        radioColYes.setChecked(true);
                    else radioColNo.setChecked(true);


                    editDoB.setText(deliveryApplicant.jsonObject.get("DOB").toString());

                }

                if(checkBoxAbortion.isChecked())
                {
                    editAbortion.setText(deliveryApplicant.jsonObject.get("ABORTION_MONTH").toString());

                }



            }
            catch (JSONException j)
            {
                j.printStackTrace();
            }

        }

        switchCompat=viewDelivery.findViewById(R.id.switchkids);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                   // Toast.makeText(getContext(), "HAVE KIDS", Toast.LENGTH_LONG).show();

                    //tableLayoutDelivery.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                   // tableLayoutDelivery.setVisibility(View.VISIBLE);
                    //tableLayoutPreD.setLayoutParams(new TableLayout.LayoutParams(0, 0));
                    //tableLayoutPreD.setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.tableDelChildS).setVisibility(View.GONE);
                    viewDelivery.findViewById(R.id.tableDelChildDetailsTwins).setVisibility(View.VISIBLE);
                    //  TableRow.LayoutParams paramsExample = new TableRow.LayoutParams(0,0);
                    //  viewPreD.findViewById(R.id.textviewNoDelivery).setLayoutParams(paramsExample);
                }
                if(!isChecked) {
                  //  Toast.makeText(getContext(), "NO KIDS", Toast.LENGTH_LONG).show();
                    //tableLayoutDelivery.setLayoutParams(new TableLayout.LayoutParams(0, 0));
                    //tableLayoutDelivery.setVisibility(View.GONE);
                    // tableLayoutPreD.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    //tableLayoutPreD.setVisibility(View.VISIBLE);

                    viewDelivery.findViewById(R.id.tableDelChildS).setVisibility(View.VISIBLE);
                    viewDelivery.findViewById(R.id.tableDelChildDetailsTwins).setVisibility(View.GONE);

                }
            }
        });

        editDoB.addTextChangedListener(new GenericTextWatcher(editDoB));

        return viewDelivery;
    }
}
