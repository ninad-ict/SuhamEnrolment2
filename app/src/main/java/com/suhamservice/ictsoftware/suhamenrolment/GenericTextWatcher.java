package com.suhamservice.ictsoftware.suhamenrolment;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {
    private EditText editText;
    Boolean BACK_PRESSED=false;
    public GenericTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String mess="AFTER-TC";
        //Log.d(mess,"Just Entered AfterTC");

              /*if(view.length()==0)
                  BACK_PRESSED=false;
              else
                  BACK_PRESSED=true;*/

        if(s.length()==1 && Integer.parseInt(s.toString().substring(0,1))>3)
        {
            editText.setText("0"+s);

            return;
        }

        if(s.length()==4 && Integer.parseInt(s.toString().substring(3,4))>1)
        {
            String tempDob=s.toString().substring(0,3);
            editText.setText(tempDob+"0"+s.toString().substring(3,4)+"-");
            editText.setSelection(editText.length());
            return;
        }


        if(s.length()!=0 && s.charAt(s.length()-1)=='-')
        {
            BACK_PRESSED=true;
            //Log.d("B-C-IF","last CHAR->"+s.charAt(s.length()-1));
        }
        else if(s.length()==1 || s.length()==4)
        {
            BACK_PRESSED=false;
        }
        // else
        // BACK_PRESSED=false;

        if(editText.length()==2 &&editText.getText().toString().contains("-"))
        {
            editText.setText("0"+s);
            editText.setSelection(3);
        }
        else if(editText.length()==2 && BACK_PRESSED==false)
        {//Log.d(mess,""+editText.length());
            editText.setText(s+"-");
            //Log.d(mess,"INSIDE 2nd IF->view is->"+editText.getText().toString());
            editText.setSelection(3);
        }
        if((editText.length()==5) && (editText.getText().toString().substring(4,5).equals("-"))) //12-1-
        {
            //Log.d(mess,"subsequence is ->"+editText.getText().toString().substring(4,5));
            String date=s.toString().substring(0,3);
            String month =s.toString().substring(3,5);
            String modDate=date+"0"+month;
            editText.setText(modDate);
            editText.setSelection(6);
        }
        else if(editText.length()==5 && BACK_PRESSED==false)
        {
            //Log.d(mess,"Fail subsequence is ->"+editText.getText().toString().substring(4,5));
            editText.setText(s+"-");
            //Log.d(mess,"INSIDE 2nd IF->view is->"+editText.getText().toString());
            editText.setSelection(6);
        }
        if(s.length() == 8) {
            int i = Integer.parseInt(s.toString().substring(6,8));

            if ( (i <= 99) && (i > 20)) {
                //12-01-91
                String tempDate=s.toString().substring(0,6);
                editText.setText(tempDate+"19"+Integer.toString(i));
                editText.setSelection(editText.getText().length());
            }
            if(i>0&&i<20&&i!=19)
            {
                String tempDate=s.toString().substring(0,6);
                editText.setText(tempDate+"20"+s.toString().substring(6,8));
                editText.setSelection(editText.getText().length());
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
