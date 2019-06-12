package com.suhamservice.ictsoftware.suhamenrolment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProfileAssociate extends Fragment {
View profileView;
EditText editName;
EditText editphone;
EditText editadd;
EditText edittime;
EditText editpermit;
static String EMPID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileView= inflater.inflate(R.layout.emp_profile,container,false);

        editName=profileView.findViewById(R.id.EditEmpRegName);
        editphone=profileView.findViewById(R.id.EditEmpRegPhone);
        editadd=profileView.findViewById(R.id.EditEmpRegAddress);
       // edittime=profileView.findViewById(R.id.EditEmpRegTime);
      //  editpermit=profileView.findViewById(R.id.EditEmpRegPermit);

        //DataBaseHelper DB = new DataBaseHelper(getContext(), null, null, 1);
        DataBaseHelper DB=DataBaseHelper.getInstance(getContext());
        String[] EmpData=DB.getEmpRegData();
        //DB.close();;
        editName.setText(EmpData[1]);
        editphone.setText(EmpData[3]);
        editadd.setText(EmpData[4]);
       // edittime.setText(EmpData[3]);
       /* if(EmpData[4].equals("Y"))
        editpermit.setText("YES");
        else
        editpermit.setText("NO");
        EMPID=EmpData[5];*/
        return profileView;
    }
}
