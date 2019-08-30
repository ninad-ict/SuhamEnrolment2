package com.suhamservice.ictsoftware.suhamenrolment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.CHILDBIRTHSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.CHILDGROWTHSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.DELIVERYSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.EDUWORKSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_ANTE;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_CHILD;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_GIRL;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.ENROL_DELL;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.HEALTHSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.PARENTSSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.PERSONALSAVED;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.anteLocation;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.antePersonal;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.antePreDelivery;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.applicationName;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childLocation;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.locationFragment;
//import static com.example.ictsoftware.suhamenrolment.MainActivity.preDelivery;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.deliveryApplicant;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.DeliveryLocation;
import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.pregPersonal;

public class BaseFragment  extends Fragment {

    TextView heading;
    AppCompatImageView TickGirlLocation;
    AppCompatImageView TickPregLocation;
    AppCompatImageView TickMothLocation;
    AppCompatImageView TickChildLocation;
    AppCompatImageView TickGirlPersonal;
    AppCompatImageView TickGirlHealth;
    AppCompatImageView TickGirlWork;
    AppCompatImageView TickPregPersonal;
    AppCompatImageView TickMothPersonal;
    AppCompatImageView TickPregnancy;
    AppCompatImageView TickDelivery;
    AppCompatImageView TickParents;
    AppCompatImageView TickChildBirth;
    AppCompatImageView TickChildGrowth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.basefragment,container,false);
        //  Log.d("BaseFragment ","Inside onCreateView()");
        View baseView=inflater.inflate(R.layout.basefragment,container,false);

         TickGirlLocation=baseView.findViewById(R.id.GirlLocationTick);
         TickGirlPersonal=baseView.findViewById(R.id.GirlPersonalTick);
         TickGirlHealth=baseView.findViewById(R.id.GirlHealthTick);
         TickGirlWork=baseView.findViewById(R.id.GirlEduTick);
         TickPregLocation=baseView.findViewById(R.id.PregLocationTick);
         TickMothLocation=baseView.findViewById(R.id.MothLocationTick);
         TickChildLocation=baseView.findViewById(R.id.ChildLocationTick);

         TickPregPersonal=baseView.findViewById(R.id.PregPersonalTick);
         TickMothPersonal=baseView.findViewById(R.id.MothPersonalTick);
         TickPregnancy=baseView.findViewById(R.id.prenatalTick);
         TickDelivery=baseView.findViewById(R.id.DeliveryTick);
         TickParents=baseView.findViewById(R.id.ParentsTick);
         TickChildBirth=baseView.findViewById(R.id.ChildBirthTick);
         TickChildGrowth=baseView.findViewById(R.id.babyGrowthTick);

String NAME="";

        if(MainActivity.ENROL_GIRL)
            NAME=applicationName.ApplName;

        if(MainActivity.ENROL_DELL)
        {
            //   Log.d("IN ENROL_DELL","pregnantApplicant.ID_ENTERED->"+deliveryApplicant.ID_ENTERED);
            NAME=deliveryApplicant.ApplName;


            ((ImageView)baseView.findViewById(R.id.imageViewEducation)).setVisibility(View.GONE);
           // ((ImageView)baseView.findViewById(R.id.imageViewPregnant)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewEducation)).setVisibility(View.GONE);
           // ((TextView)baseView.findViewById(R.id.textviewPregnant)).setVisibility(View.VISIBLE);
            ((ImageView)baseView.findViewById(R.id.imageViewHealth)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewBabyDelivery)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewHealth)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewBabyDelivery)).setVisibility(View.VISIBLE);

            ((ImageView)baseView.findViewById(R.id.imageViewPersonal)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewPersonal)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewPersonalPreg)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewPregPersonal)).setVisibility(View.VISIBLE);

           // if(locationFragment.SAVELocation)
              //  TickGirlLocation.setVisibility(View.VISIBLE);


        }
        if(MainActivity.ENROL_ANTE)
        {
            NAME=anteApplicant.ApplName;

            ((ImageView)baseView.findViewById(R.id.imageViewEducation)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewEducation)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewHealth)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewHealth)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewPersonal)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewPersonal)).setVisibility(View.GONE);
            
            ((ImageView)baseView.findViewById(R.id.imageViewPersonalPreg)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewPregPersonal)).setVisibility(View.VISIBLE);


            ((ImageView)baseView.findViewById(R.id.imageViewPreNatal)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewPreNatal)).setVisibility(View.VISIBLE);


        }


        if(MainActivity.ENROL_CHILD) {
            NAME = childApplicant.ApplName;
            // Log.d("BaseFragment->Child",NAME);
            ((ImageView)baseView.findViewById(R.id.imageViewPersonal)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewParents)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewPersonal)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewParents)).setVisibility(View.VISIBLE);
            ((ImageView)baseView.findViewById(R.id.imageViewHealth)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewBabyGrowth)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewHealth)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewBabyGrowth)).setVisibility(View.VISIBLE);
            ((TextView)baseView.findViewById(R.id.textviewEducation)).setVisibility(View.GONE);
            ((TextView)baseView.findViewById(R.id.textviewChildBirth)).setVisibility(View.VISIBLE);
            ((ImageView)baseView.findViewById(R.id.imageViewEducation)).setVisibility(View.GONE);
            ((ImageView)baseView.findViewById(R.id.imageViewChildBirth)).setVisibility(View.VISIBLE);



        }
       //hideKeyboardFrom(getContext(),baseView);
       MainActivity.hideKeyboard(getActivity());


        heading=((TextView)baseView.findViewById(R.id.textViewHeadingB));
        if (ENROL_GIRL)
        {
            if((MainActivity.PERSONALSAVED)&&(MainActivity.EDUWORKSAVED)&&(MainActivity.HEALTHSAVED)&&(locationFragment.SAVELocation))
                heading.setText("Click Submit to Send Details for "+NAME);
            else
                heading.setText("Enter Details for "+NAME);

        }
        if(ENROL_DELL) {
            if ((DeliveryLocation.SAVELocation) && (MainActivity.PERSONALPREGSAVED) && (MainActivity.PREDELIVERYSAVED) && (MainActivity.PREDELIVERYSAVED))
                heading.setText("Click Submit to Send Details for " + NAME);
            else
                heading.setText("Enter Details for " + NAME);
        }
        if(ENROL_CHILD)
        {
            if ((childLocation.SAVELocation) && (MainActivity.PARENTSSAVED) && (MainActivity.CHILDBIRTHSAVED) && (MainActivity.CHILDGROWTHSAVED))
            {
                heading.setText("Click Submit to Send Details for " + NAME);

               /*if(childApplicant.ID_ENTERED)
               {
                   if(childApplicant.ID_WHO.equals("CHILD"))
                       heading.setText("Click Submit to Send Details for " + NAME);
                   if(childApplicant.ID_WHO.equals("MOTH"))
                       heading.setText("Click Submit to Send Details for " + NAME);
               }
               else
               {
                   heading.setText("Click Submit to Send Details for "+childApplicant.ApplName);
               }*/
            }
            else
            {
                heading.setText("Enter Details for " + NAME);
              /*  if(childApplicant.ID_ENTERED)
                {
                    if(childApplicant.ID_WHO.equals("CHILD"))
                        heading.setText("Click Submit to Send Details for " + NAME);
                    if(childApplicant.ID_WHO.equals("MOTH"))
                        heading.setText("Click Submit to Send Details for Child of(1) " + NAME);
                }
                else
                {
                    heading.setText("Click Submit to Send Details for "+childApplicant.ApplName);
                }*/
               /* if(!childApplicant.ID_ENTERED)
                    heading.setText("Enter Details for " + NAME);
            else if(childApplicant.ID_WHO=="MOTH") {
                try {
                    heading.setText("Enter Details for Child of(2) " + childApplicant.jsonObject.get("NAME").toString());
                }catch (JSONException j)
                {
                    j.printStackTrace();
                }
                }
            else
                    heading.setText("Enter Details For " + NAME);
*/
            }
        }

        if(ENROL_ANTE) {
            if ((anteLocation.SAVELocation) && (antePersonal.PPSAVED) && (antePreDelivery.PD_SAVED))
                heading.setText("Click Submit to Send Details for " + NAME);
            else
                heading.setText("Enter Details for " + NAME);
        }


       // heading.setBackgroundColor(heading.findViewById(R.color.skyblue));


        return baseView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        String mess="TICK-OnResume";
           // TickGirlLocation.setVisibility(View.GONE);
            TickGirlPersonal.setVisibility(View.GONE);
            TickGirlHealth.setVisibility(View.GONE);
            TickGirlWork.setVisibility(View.GONE);

           // TickPregLocation.setVisibility(View.GONE);
            TickPregPersonal.setVisibility(View.GONE);
            TickPregnancy.setVisibility(View.GONE);

          //  TickMothLocation.setVisibility(View.GONE);
            TickMothPersonal.setVisibility(View.GONE);
            TickDelivery.setVisibility(View.GONE);

           // TickChildLocation.setVisibility(View.GONE);
            TickParents.setVisibility(View.GONE);
            TickChildBirth.setVisibility(View.GONE);
            TickChildGrowth.setVisibility(View.GONE);

        if(ENROL_GIRL)
        {
            Log.d(mess,"In-Girl");
           // if(locationFragment.SAVELocation)
           //     TickGirlLocation.setVisibility(View.VISIBLE);
            if(PERSONALSAVED)
                TickGirlPersonal.setVisibility(View.VISIBLE);
            if(HEALTHSAVED)
                TickGirlHealth.setVisibility(View.VISIBLE);
            if(EDUWORKSAVED)
                TickGirlWork.setVisibility(View.VISIBLE);
        }
        if(ENROL_ANTE)
        {
          //  if(anteLocation.SAVELocation)
           //     TickPregLocation.setVisibility(View.VISIBLE);
            if(antePersonal.PPSAVED)
                TickPregPersonal.setVisibility(View.VISIBLE);
            if(antePreDelivery.PD_SAVED)
                TickPregnancy.setVisibility(View.VISIBLE);
        }
        if(ENROL_DELL)
        {
         //   if(DeliveryLocation.SAVELocation)
          //      TickMothLocation.setVisibility(View.VISIBLE);
            if(pregPersonal.PPSAVED)
                TickMothPersonal.setVisibility(View.VISIBLE);
            if(DELIVERYSAVED)
                TickDelivery.setVisibility(View.VISIBLE);

        }
        if(ENROL_CHILD)
        {
          //  if(childLocation.SAVELocation)
           //     TickChildLocation.setVisibility(View.VISIBLE);
            if(PARENTSSAVED)
                TickParents.setVisibility(View.VISIBLE);
            if(CHILDBIRTHSAVED)
                TickChildBirth.setVisibility(View.VISIBLE);
            if(CHILDGROWTHSAVED)
                TickChildGrowth.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //  Log.d("BaseFragment ","Inside onPause()");

    }
}
