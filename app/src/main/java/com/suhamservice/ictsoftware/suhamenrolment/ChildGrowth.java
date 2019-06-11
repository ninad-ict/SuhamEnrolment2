package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import static com.suhamservice.ictsoftware.suhamenrolment.MainActivity.childApplicant;

public  class ChildGrowth extends Fragment {

    View viewChildGrowth;
     String Order="";
     String Height="";
     String Weight="";
     String Age="";
    TextView heading;

    EditText editOrder;
    EditText editHeight;
    EditText editWeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewChildGrowth=inflater.inflate(R.layout.childgrowth,container,false);

        heading = ((TextView) viewChildGrowth.findViewById(R.id.textViewHeadingCG));

        if (childApplicant.ApplName != null) {
            heading.setText("Enter Growth Details of " + childApplicant.ApplName);
        }

        if(childApplicant.ID_ENTERED) {
          /*  if (childApplicant.ID_WHO=="MOTH")
            heading.setText("Enter Growth Details of Child of " + childApplicant.ApplName);
            else
                heading.setText("Enter Growth Details for " + childApplicant.ApplName);*/
            editOrder=viewChildGrowth.findViewById(R.id.editTextChildOrder);
            editHeight=viewChildGrowth.findViewById(R.id.editTextChildHeight);
            editWeight=viewChildGrowth.findViewById(R.id.editTextChildWeight);

            try {
                editOrder.setText(childApplicant.jsonObject.get("ORDERBIRTH").toString());
                editHeight.setText(childApplicant.jsonObject.get("HEIGHT").toString());
                editWeight.setText(childApplicant.jsonObject.get("WEIGHTG").toString());
            }
            catch (JSONException j)
            {
                j.printStackTrace();
            }

        }

        return viewChildGrowth;
    }
}
