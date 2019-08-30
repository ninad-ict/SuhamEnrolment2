package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.EDUWORKSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.HEALTHSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.PERSONALSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.isEmpty;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.locationFragment;

public class GirlFragment extends Fragment {

    View viewGirl;
    TextView textHeading;
    TextView textGirlLocation;
    TextView textGirlPersonal;
    TextView textGirlWork;
    TextView textGirlHealth;
    TextView textTest;
    FrameLayout frameGirlLocation;
    FrameLayout frameGirlPersonal;
    FrameLayout frameGirlWork;
    FrameLayout frameGirlHealth;

    Button buttonSubmit;

    TableRow rowGirlLocation;

    ScrollView scrollGirl;

    //--------location details----------------

    EditText editFedCode;
    AutoCompleteTextView editFedName;
    AutoCompleteTextView editState;
    AutoCompleteTextView editDistrict;
    EditText editPanch;
    EditText editVillage;
    EditText editContact;
    EditText editGroup;

    boolean OR1=false,OR2=false;

    TextWatcher TWfedCode;
    TextWatcher TWfedState;
    TextWatcher TWfedDistrict;
    TextWatcher TWfedFed;

    TextWatcher TWvalidfield;

    AppCompatImageView drawGirlTickLocation;
    AppCompatImageView drawGirlTickPersonal;
    AppCompatImageView drawGirlTickWork;
    AppCompatImageView drawGirlTickHealth;

    AppCompatImageView drawGirlCrossLocation;
    AppCompatImageView drawGirlCrossPersonal;
    AppCompatImageView drawGirlCrossWork;
    AppCompatImageView drawGirlCrossHealth;



    Boolean LOCATIONSAVED=false;
    //--------location details----------------

    //--------Personal details----------------
    EditText editName;
    EditText editDob;
    EditText editMother;
    EditText editFather;
    EditText editMarrYear;

    RadioButton radioMarrY;
    RadioButton radioMarrN;

    Spinner spinMom;
    Spinner spinDad;
    //--------Personal details----------------

    //--------Work A Education details----------------
    Spinner spinStudy;
    Spinner spinWork;
    RadioButton radioStudyY;
    RadioButton radioStudyN;
    //--------Work A Education details----------------

    //--------Health details----------------
    EditText editHB;
    EditText editHeight;
    EditText editWeight;
    EditText editPubertyYear;
    RadioButton radioPubY;
    RadioButton radioPubN;
    //--------Health details----------------




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGirl=inflater.inflate(R.layout.girllayout, container, false);

        buttonSubmit=viewGirl.findViewById(R.id.buttonGirlNext);

        scrollGirl=viewGirl.findViewById(R.id.ScrollGirl);
        textHeading=viewGirl.findViewById(R.id.textGirlHeading);

        textHeading.setText("Enter Details For "+applicationName.ApplName);

        textGirlLocation=viewGirl.findViewById(R.id.textGirlLocation);
        textGirlPersonal=viewGirl.findViewById(R.id.textGirlPersonal);
        textGirlWork=viewGirl.findViewById(R.id.textGirlWork);
        textGirlHealth=viewGirl.findViewById(R.id.textGirlHealth);

        //textTest=viewGirl.findViewById(R.id.TextTest);

        frameGirlLocation=viewGirl.findViewById(R.id.FrameGirllocation);
        frameGirlPersonal=viewGirl.findViewById(R.id.FrameGirlPersonal);
        frameGirlWork=viewGirl.findViewById(R.id.FrameGirlEducation);
        frameGirlHealth=viewGirl.findViewById(R.id.FrameGirlHealth);

        //-------------ASSIGN VIEWS TO THE LOCATION VARIABLES-----------

        editFedCode=viewGirl.findViewById(R.id.editTextFedCode);
        editFedName=viewGirl.findViewById(R.id.editTextFed);
        editState=viewGirl.findViewById(R.id.editTextState);
        editDistrict=viewGirl.findViewById(R.id.editTextDist);
        editPanch=viewGirl.findViewById(R.id.editTextPanch);
        editVillage=viewGirl.findViewById(R.id.editTextVillage);

        editContact=viewGirl.findViewById(R.id.editTextContact);
        editGroup=viewGirl.findViewById(R.id.editTextGirlGroup);

        drawGirlTickLocation=viewGirl.findViewById(R.id.GirlLocationTick);
        drawGirlTickPersonal=viewGirl.findViewById(R.id.GirlPersonalTick);
        drawGirlTickHealth=viewGirl.findViewById(R.id.GirlHealthTick);
        drawGirlTickWork=viewGirl.findViewById(R.id.GirlWorkTick);

        drawGirlCrossPersonal=viewGirl.findViewById(R.id.GirlPersonalCross);
        drawGirlCrossLocation=viewGirl.findViewById(R.id.GirlLocationCross);
        drawGirlCrossWork=viewGirl.findViewById(R.id.GirlWorkCross);
        drawGirlCrossHealth=viewGirl.findViewById(R.id.GirlHealthCross);


        //-------------ASSIGN VIEWS TO THE LOCATION VARIABLES-----------

        //-------------ASSIGN VIEWS TO THE PERSONAL VARIABLES-----------

         editName=viewGirl.findViewById(R.id.editTextName);
         editDob=viewGirl.findViewById(R.id.editTextDob);
         editMother=viewGirl.findViewById(R.id.editTextMother);
         editFather=viewGirl.findViewById(R.id.editTextFather);
        editMarrYear=viewGirl.findViewById(R.id.editTextMarrYear);

         radioMarrY=viewGirl.findViewById(R.id.radioButtonYes);
         radioMarrN=viewGirl.findViewById(R.id.radioButtonNo);

         spinMom=viewGirl.findViewById(R.id.spinnerMomMember);
         spinDad=viewGirl.findViewById(R.id.spinnerDadMember);

        //-------------ASSIGN VIEWS TO THE PERSONAL VARIABLES-----------

        //-------------ASSIGN VIEWS TO THE WORK A EDUCATION VARIABLES-----------

         spinStudy=viewGirl.findViewById(R.id.spinnerHE);
         spinWork=viewGirl.findViewById(R.id.spinnerOcc);
         radioStudyY=viewGirl.findViewById(R.id.radioButtonEYes);
         radioStudyN=viewGirl.findViewById(R.id.radioButtonENo);

        //-------------ASSIGN VIEWS TO THE WORK A EDUCATION VARIABLES-----------

        //-------------ASSIGN VIEWS TO THE HEALTH VARIABLES-----------

         editHB=viewGirl.findViewById(R.id.editTextHB);
         editHeight=viewGirl.findViewById(R.id.editTextHeight);
         editWeight=viewGirl.findViewById(R.id.editTextWeight);
         editPubertyYear=viewGirl.findViewById(R.id.editTextPuberty);
         radioPubY=viewGirl.findViewById(R.id.radioButtonPYes);
         radioPubN=viewGirl.findViewById(R.id.radioButtonPYes);

        //-------------ASSIGN VIEWS TO THE HEALTH VARIABLES-----------

//-------------------TEXTWACTHER FOR LOCATION DETAILS----------------
        editPanch.addTextChangedListener(new ValidTextWatcher(editPanch));
        editVillage.addTextChangedListener(new ValidTextWatcher(editVillage));
        editFedCode.addTextChangedListener(new ValidTextWatcher(editFedCode));
        editFedName.addTextChangedListener(new ValidTextWatcher(editFedName));
        editState.addTextChangedListener(new ValidTextWatcher(editState));
        editDistrict.addTextChangedListener(new ValidTextWatcher(editDistrict));
//-------------------TEXTWACTHER FOR LOCATION DETAILS----------------

        //-------------------TEXTWACTHER FOR PERSONAL DETAILS----------------
        editName.addTextChangedListener(new ValidTextWatcher(editName));
        editDob.addTextChangedListener(new ValidTextWatcher(editDob));
        editDob.addTextChangedListener(new GenericTextWatcher(editDob));
        editMother.addTextChangedListener(new ValidTextWatcher(editMother));
        editFather.addTextChangedListener(new ValidTextWatcher(editFather));
        editMarrYear.addTextChangedListener(new ValidTextWatcher(editMarrYear));
        //-------------------TEXTWACTHER FOR PERSONAL DETAILS----------------


        //-------------------TEXTWACTHER FOR HEALTH DETAILS----------------
        editWeight.addTextChangedListener(new ValidTextWatcher(editWeight));
        editHeight.addTextChangedListener(new ValidTextWatcher(editHeight));
        editPubertyYear.addTextChangedListener(new ValidTextWatcher(editPubertyYear));



        //-------------------TEXTWACTHER FOR HEALTH DETAILS----------------


  frameGirlLocation.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
      @Override
      public void onViewAttachedToWindow(View v) {
          Toast.makeText(getContext(),"PRESENT!!!", Toast.LENGTH_LONG).show();
      }

      @Override
      public void onViewDetachedFromWindow(View v) {
          Toast.makeText(getContext(),"ABSENT!!!", Toast.LENGTH_LONG).show();
      }
  });


        textGirlLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if((textGirlLocation.getCompoundDrawables()[0].getConstantState()).equals(getResources().getDrawable(R.drawable.white_plus).getConstantState()))
                     textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);
                if(textGirlLocation.getCompoundDrawables()[0].equals(R.drawable.white_minus));
                textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
*/
                //checkLocation();
                if(frameGirlLocation.isShown()) {
                    frameGirlLocation.setVisibility(View.GONE);
                    textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                }
                else
                {
                    //-----VALIDATION CHECKS------------------
                   /* if(isEmpty(editFedCode))
                    editFedCode.setError("Please enter a Valid Value");
                    if(isEmpty(editFedName))
                    editFedName.setError("Please enter a Valid Value");
                    if(isEmpty(editState))
                    editState.setError("Please enter a Valid Value");
                    if(isEmpty(editDistrict))
                    editDistrict.setError("Please enter a Valid Value");
                    if(isEmpty(editPanch))
                    editPanch.setError("Please enter a Valid Value");
                    if(isEmpty(editVillage))
                    editVillage.setError("Please enter a Valid Value");*/
                    //-----VALIDATION CHECKS------------------

                    drawGirlCrossLocation.setVisibility(View.GONE);
                    drawGirlTickLocation.setVisibility(View.GONE);

                    frameGirlLocation.setVisibility(View.VISIBLE);
                    textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);

                    //----------CHECK FOR VALIDATION OF FIELDS---------

                    //----------CHECK FOR VALIDATION OF FIELDS---------


                    frameGirlPersonal.setVisibility(View.GONE);
                    frameGirlWork.setVisibility(View.GONE);
                    frameGirlHealth.setVisibility(View.GONE);
                    textGirlPersonal.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    textGirlHealth.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    textGirlWork.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    scrollGirl.fullScroll(ScrollView.FOCUS_UP);

                }
            }
        });

        textGirlPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if((textGirlLocation.getCompoundDrawables()[0].getConstantState()).equals(getResources().getDrawable(R.drawable.white_plus).getConstantState()))
                     textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);
                if(textGirlLocation.getCompoundDrawables()[0].equals(R.drawable.white_minus));
                textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
*/
                if(frameGirlPersonal.isShown()) {
                    frameGirlPersonal.setVisibility(View.GONE);
                    textGirlPersonal.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);

                }
                else
                {
                    drawGirlCrossPersonal.setVisibility(View.GONE);
                    drawGirlTickPersonal.setVisibility(View.GONE);
                    //checkLocation();

                   /* if(isEmpty(editName))
                        editName.setError("Please enter a Valid Value");
                    if(isEmpty(editMother))
                        editMother.setError("Please enter a Valid Value");
                    if(isEmpty(editFather))
                        editFather.setError("Please enter a Valid Value");
                    if(isEmpty(editDob))
                        editDob.setError("Please enter a Valid Value");
                    if(isEmpty(editMarrYear))
                        editMarrYear.setError("Please enter a Valid Value");*/

                    frameGirlPersonal.setVisibility(View.VISIBLE);
                    textGirlPersonal.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);

                    frameGirlLocation.setVisibility(View.GONE);
                    frameGirlWork.setVisibility(View.GONE);
                    frameGirlHealth.setVisibility(View.GONE);
                    textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    textGirlHealth.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    textGirlWork.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    scrollGirl.fullScroll(ScrollView.FOCUS_UP);

                }
            }
        });

        textGirlWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if((textGirlLocation.getCompoundDrawables()[0].getConstantState()).equals(getResources().getDrawable(R.drawable.white_plus).getConstantState()))
                     textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);
                if(textGirlLocation.getCompoundDrawables()[0].equals(R.drawable.white_minus));
                textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
*/
                if(frameGirlWork.isShown()) {
                    frameGirlWork.setVisibility(View.GONE);
                    textGirlWork.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                }
                else
                {
                    drawGirlCrossWork.setVisibility(View.GONE);
                    drawGirlTickWork.setVisibility(View.GONE);
                    //checkLocation();
                    frameGirlWork.setVisibility(View.VISIBLE);
                    textGirlWork.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);

                    frameGirlLocation.setVisibility(View.GONE);
                    frameGirlHealth.setVisibility(View.GONE);
                    textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    textGirlHealth.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    frameGirlPersonal.setVisibility(View.GONE);
                    textGirlPersonal.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    scrollGirl.fullScroll(ScrollView.FOCUS_UP);

                }
            }
        });

        textGirlHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if((textGirlLocation.getCompoundDrawables()[0].getConstantState()).equals(getResources().getDrawable(R.drawable.white_plus).getConstantState()))
                     textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);
                if(textGirlLocation.getCompoundDrawables()[0].equals(R.drawable.white_minus));
                textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
*/
                if(frameGirlHealth.isShown()) {
                    frameGirlHealth.setVisibility(View.GONE);
                    textGirlHealth.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                }
                else
                {
                    drawGirlCrossHealth.setVisibility(View.GONE);
                    drawGirlTickHealth.setVisibility(View.GONE);
                   // checkLocation();
                    frameGirlHealth.setVisibility(View.VISIBLE);
                    textGirlHealth.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_minus, 0, 0, 0);

                    frameGirlLocation.setVisibility(View.GONE);
                    frameGirlWork.setVisibility(View.GONE);
                    textGirlLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    textGirlWork.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    frameGirlPersonal.setVisibility(View.GONE);
                    textGirlPersonal.setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_plus, 0, 0, 0);
                    scrollGirl.fullScroll(ScrollView.FOCUS_UP);

                }
            }
        });


        //------------------FINAL SUBMIT---------------------------
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkLocation=checkLocation();
                Boolean checkPersonal=checkPersonal();
                Boolean checkWork=checkWork();
                Boolean checkHealth=checkHealth();

                if(!checkLocation||!checkPersonal||!checkHealth||!checkWork)
                {
                    Toast.makeText(getContext(),"Verify Entered Details",Toast.LENGTH_LONG).show();
                    return;
                }



            }
        });
        //------------------FINAL SUBMIT---------------------------


        editFedCode.addTextChangedListener(TWfedCode=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //state.setText("");
                //district.setText("");
                //federation.setText("");
                if(editFedCode.getText().toString().equals(""))
                {
                    editState.setEnabled(true);
                    editDistrict.setEnabled(true);
                    editFedName.setEnabled(true);
                    editFedName.setText("");
                    editState.setText("");
                    editDistrict.setText("");
                }

                if(!OR2) {

                    //DataBaseHelper DB = new DataBaseHelper(getContext(), null, null, 1);
                    DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                    String[] location = DB.getLocation(s.toString());
                    if (location[0] != "-1") {
                        OR1 = true;
                        editState.setText(location[0]);
                        editDistrict.setText(location[1]);
                        editFedName.setText(location[2]);
                        editState.setEnabled(false);
                        editDistrict.setEnabled(false);
                        editFedName.setEnabled(false);
                        editFedCode.setError(null);

                    } else {

                        // state.setText("");
                        //  district.setText("");
                        //  federation.setText("");
                        // state.setEnabled(true);
                        // district.setEnabled(true);
                        // federation.setEnabled(true);
                        if(!editFedCode.getText().toString().equals("9999"))
                            editFedCode.setError("Please Enter a Valid Code");
                        OR1 = false;

                        /*FedCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                FedCode.setError("Please Enter a Valid Code");
                                locationFragment.SAVELocation = false;
                            }
                        });*/
                    }
                }
            }
        });

        editState.addTextChangedListener(TWfedState=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.d("InState ATC",s.toString());
                editDistrict.setText("");
                editFedName.setText("");
                if(!OR1) {

                    if (editState.getText().toString().equals(""))
                        editFedCode.setEnabled(true);
                    else
                        editFedCode.setEnabled(false);

                }
                OR2=false;
                //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                String getstate[]=DB.getState(s.toString());
                if(!getstate[0].equals("-1")) {
                    ArrayAdapter<String> arrayAdapterState =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getstate);
                    editState.setThreshold(1);
                    editState.setAdapter(arrayAdapterState);
                }
                //DB.close();
            }
        });


        editDistrict.addTextChangedListener(TWfedDistrict=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editFedName.setText("");
                if(!OR1) {
                    if (editState.getText().toString().equals("") && editDistrict.getText().toString().equals(""))
                        editFedCode.setEnabled(true);
                    else
                        editFedCode.setEnabled(false);
                }
                OR2=false;
                //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                String getdistrict[]=DB.getDistrict(s.toString(),editState.getText().toString());
                if(!getdistrict[0].equals("-1")) {
                    //Log.d("In DIST ATC",getdistrict[0]);
                    ArrayAdapter<String> arrayAdapterState =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getdistrict);
                    editDistrict.setThreshold(0);
                    editDistrict.setAdapter(arrayAdapterState);

                }
                //DB.close();

            }
        });


        editFedName.addTextChangedListener(TWfedFed=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.d("IN-FED-ATC","JUST-Entered-FED-ATC");
                OR2=false;
                //FedCode.setText("");
                if(!OR1) {
                    //Log.d("IN-FED-ATC","2nd log");
                    if (editState.getText().toString().equals("") && editDistrict.getText().toString().equals("") && editFedName.getText().toString().equals(""))
                        editFedCode.setEnabled(true);
                    else
                        editFedCode.setEnabled(false);
                }

                if(s.toString().equals("SUHAM"))
                {
                    editFedCode.setText("9999");
                    OR2=true;
                    return;
                }


                //DataBaseHelper DB=new DataBaseHelper(getContext(),null,null,1);
                DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
                String getFed[]=DB.getFed(s.toString(),editState.getText().toString(),editDistrict.getText().toString());
                if(!getFed[0].equals("-1")) {
                    //Log.d("IN-FED-ATC","getFed[0]->"+getFed[0]);
                    ArrayAdapter<String> arrayAdapterState =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getFed);
                    editFedName.setThreshold(0);
                    editFedName.setAdapter(arrayAdapterState);
                    editFedName.showDropDown();
                    editFedName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editFedName.showDropDown();
                        }
                    });

                    //if(FedCode.getText().toString().equals("")) {
                    if(!OR1) {
                        int code=9999;
                        //Log.d("IN-FED-ATC",federation.getText().toString());
                        if(!editFedName.getText().toString().equals("SUHAM"))
                        {
                            //Log.d("IN-FED-ATC","fed not equals suham");
                            code = DB.setCode(editState.getText().toString(), editDistrict.getText().toString(), editFedName.getText().toString());
                        }
                        if (code != 0) {
                            //Log.d("IN-FED-ATC", "code->" + code);
                            OR2=true;
                            editFedCode.setEnabled(false);
                            editFedCode.setText(Integer.toString(code));
                            editFedCode.setError(null);
                            // FedCode.setEnabled(false);
                        }
                    }
                }

            }
        });

        return viewGirl;
    }

    public boolean checkLocation()
    {
        if(isEmpty(editFedCode))
            editFedCode.setError("Please enter a Valid Value");
        if(isEmpty(editFedName))
            editFedName.setError("Please enter a Valid Value");
        if(isEmpty(editState))
            editState.setError("Please enter a Valid Value");
        if(isEmpty(editDistrict))
            editDistrict.setError("Please enter a Valid Value");
        if(isEmpty(editPanch))
            editPanch.setError("Please enter a Valid Value");
        if(isEmpty(editVillage))
            editVillage.setError("Please enter a Valid Value");
        if(isEmpty(editContact))
            editContact.setError("Please enter a Valid Value");
        if(isEmpty(editGroup))
            editGroup.setError("Please enter a Valid Value");

        if(isEmpty(editFedCode)||isEmpty(editFedName)||isEmpty(editState)||isEmpty(editDistrict)||isEmpty(editPanch)||isEmpty(editVillage)||isEmpty(editContact)||isEmpty(editGroup))
        {
            drawGirlCrossLocation.setVisibility(View.VISIBLE);
            drawGirlTickLocation.setVisibility(View.GONE);
            LOCATIONSAVED=false;
            locationFragment.SAVELocation=false;
            return false;
        }

        locationFragment.SAVELocation = true;
        //------SAVING DATA FROM EDITTEXT INTO FIXED VARIABLES---------

        locationFragment.STATE=editState.getText().toString();
        locationFragment.DISTRICT=editDistrict.getText().toString();
        locationFragment.FED=editFedName.getText().toString();
        locationFragment.FEDCODE=editFedCode.getText().toString();
        locationFragment.PANCH=editPanch.getText().toString();
        locationFragment.VILLAGE=editVillage.getText().toString();
        locationFragment.Group=editGroup.getText().toString();
        locationFragment.Contact=editContact.getText().toString();

        if(((RadioButton)viewGirl.findViewById(R.id.radioButtonSchool)).isChecked())
        {
            locationFragment.GroupType="School";
        }
        else
        {
            locationFragment.GroupType="AdlGroup";
        }

        drawGirlCrossLocation.setVisibility(View.GONE);
        drawGirlTickLocation.setVisibility(View.VISIBLE);
        LOCATIONSAVED=true;
        locationFragment.SAVELocation=true;
        return true;
    }

    public  boolean checkPersonal()
    {
                    if(isEmpty(editName))
                        editName.setError("Please enter a Valid Value");
                    if(isEmpty(editMother))
                        editMother.setError("Please enter a Valid Value");
                    if(isEmpty(editFather))
                        editFather.setError("Please enter a Valid Value");
                    if(isEmpty(editDob))
                        editDob.setError("Please enter a Valid Value");
                    else if(new MainActivity().checkdate(editDob))
                        editDob.setError("Please enter a Valid Value");
                    else if(!isEmpty(editDob))
                    {
                        String YOB=editDob.getText().toString().split("-")[2];
                        if(editMarrYear.isShown()&&!isEmpty(editMarrYear))
                        {
                            if(Integer.valueOf(editMarrYear.getText().toString())<=Integer.valueOf(YOB))
                                editMarrYear.setError("Please enter a Valid Value");
                        }
                    }
                    if(editMarrYear.isShown()&&isEmpty(editMarrYear))
                        editMarrYear.setError("Please enter a Valid Value");

        String text="Select Applicable Programme";
        if(
                spinDad.getSelectedItem().toString().equals(text)||
                        spinMom.getSelectedItem().toString().equals(text)||!editName.getError().equals(null)||!editMother.getError().equals(null)||!editFather.getError().equals(null)
                        ||!editDob.getError().equals(null)||(editMarrYear.isShown()&&!editMarrYear.getError().equals(null))
        )
        {
            PERSONALSAVED=false;
            drawGirlCrossPersonal.setVisibility(View.VISIBLE);
            drawGirlTickPersonal.setVisibility(View.GONE);
            return false;
        }
        PersonalFragment.Name= editName.getText().toString();
        PersonalFragment.Mother= editMother.getText().toString();
        PersonalFragment.Father= editFather.getText().toString();
        PersonalFragment.DOB=editDob.getText().toString();
        PersonalFragment.MotherMem=spinMom.getSelectedItem().toString();
        PersonalFragment.FatherMem=spinDad.getSelectedItem().toString();

        if(editMarrYear.isShown())
            PersonalFragment.MarrYear=editMarrYear.getText().toString();
        else
            PersonalFragment.MarrYear="NULL";

        PERSONALSAVED=true;
        drawGirlCrossPersonal.setVisibility(View.GONE);
        drawGirlTickPersonal.setVisibility(View.VISIBLE);

   return true;
    }

    public boolean checkWork()
    {
        String SpinnerTextHE=spinStudy.getSelectedItem().toString();
        String SpinnerTextOcc=spinWork.getSelectedItem().toString();
        String[] EducationArray = getResources().getStringArray(R.array.spinnerEntryHE);
        String[] WorkArray = getResources().getStringArray(R.array.spinnerEntryOcc);

        if(SpinnerTextHE.equals(EducationArray[0])||SpinnerTextHE.equals("Select the Class Currently Studying:")||SpinnerTextOcc.equals(WorkArray[0]))
        {
            EDUWORKSAVED=false;
            drawGirlCrossWork.setVisibility(View.VISIBLE);
            drawGirlTickWork.setVisibility(View.GONE);
            return false;
        }
        EducationWorkFragment.WORK=spinWork.getSelectedItem().toString();
        EducationWorkFragment.EDUCATION=spinStudy.getSelectedItem().toString();

        if(radioStudyN.isChecked())
            EducationWorkFragment.STUDY="NO";
        else
            EducationWorkFragment.STUDY="YES";
        EDUWORKSAVED=true;
        drawGirlCrossWork.setVisibility(View.GONE);
        drawGirlTickWork.setVisibility(View.VISIBLE);
        return true;
    }

    public boolean checkHealth()
    {
        if(isEmpty(editHeight))
            editHeight.setError("Please enter a Valid Value");
        if(isEmpty(editWeight))
            editWeight.setError("Please enter a Valid Value");
        if(editPubertyYear.isShown()&&isEmpty(editPubertyYear))
            editPubertyYear.setError("Please enter a Valid Value");
        else if(!isEmpty(editDob)&&new MainActivity().checkdate(editDob))
        {
            if(Integer.valueOf(editPubertyYear.getText().toString())<=Integer.valueOf(editDob.getText().toString().split("-")[2]))
                editPubertyYear.setError("Please enter a Valid Value");

        }

        if(!editHeight.getError().equals(null)||!editWeight.getError().equals(null)||(editPubertyYear.isShown()&&!editPubertyYear.getError().equals(null)))
        {
            HEALTHSAVED=false;
            drawGirlTickHealth.setVisibility(View.GONE);
            drawGirlCrossHealth.setVisibility(View.VISIBLE);
            return false;
        }

        if(isEmpty(editHB))
            HealthFragment.HB="NULL";
        else
            HealthFragment.HB=editHB.getText().toString();

        HealthFragment.WEIGHT=editWeight.getText().toString();
        HealthFragment.HEIGHT=editHeight.getText().toString();
        if(editPubertyYear.isShown())
            HealthFragment.PubertyYear=editPubertyYear.getText().toString();

        HEALTHSAVED=true;
        drawGirlTickHealth.setVisibility(View.VISIBLE);
        drawGirlCrossHealth.setVisibility(View.GONE);
        return true;

    }

}
