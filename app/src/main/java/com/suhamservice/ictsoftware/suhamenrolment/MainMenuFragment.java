package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainMenuFragment extends Fragment {

View MainMenuView;
CardView cardGirl;
CardView cardMother;
CardView cardPregnant;
CardView cardChild;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainMenuView=inflater.inflate(R.layout.mainmenu,container,false);
        cardGirl=MainMenuView.findViewById(R.id.CardGirl);
        cardMother=MainMenuView.findViewById(R.id.CardMother);
        cardPregnant=MainMenuView.findViewById(R.id.CardPregnant);
        cardChild=MainMenuView.findViewById(R.id.CardChild);

         fragmentManager = getActivity().getSupportFragmentManager();
         fragmentTransaction = fragmentManager.beginTransaction();

        cardGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ENROL_EMP=false;
                MainActivity.ENROL_GIRL=true;
                MainActivity.ENROL_ANTE=false;
                MainActivity.ENROL_DELL=false;
                MainActivity.ENROL_CHILD=false;

                fragmentTransaction.replace(R.id.Frame1,MainActivity.applicationName);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        cardPregnant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ENROL_EMP=false;
                MainActivity.ENROL_GIRL=false;
                MainActivity.ENROL_ANTE=true;
                MainActivity.ENROL_DELL=false;
                MainActivity.ENROL_CHILD=false;

                fragmentTransaction.replace(R.id.Frame1,MainActivity.anteApplicant);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        cardMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ENROL_EMP=false;
                MainActivity.ENROL_GIRL=false;
                MainActivity.ENROL_ANTE=false;
                MainActivity.ENROL_DELL=true;
                MainActivity.ENROL_CHILD=false;

                fragmentTransaction.replace(R.id.Frame1,MainActivity.deliveryApplicant);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        cardChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ENROL_EMP=false;
                MainActivity.ENROL_GIRL=false;
                MainActivity.ENROL_ANTE=false;
                MainActivity.ENROL_DELL=false;
                MainActivity.ENROL_CHILD=true;

                fragmentTransaction.replace(R.id.Frame1,MainActivity.childApplicant);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });




        return MainMenuView;
    }
}
