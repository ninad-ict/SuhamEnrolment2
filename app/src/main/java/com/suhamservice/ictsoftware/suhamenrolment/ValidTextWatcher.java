package com.suhamservice.ictsoftware.suhamenrolment;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

//import static com.suhamservice.ictsoftware.suhamenrolment.GirlFragment.drawGirlLocation;

public class ValidTextWatcher implements TextWatcher {



    private EditText editText;
    Boolean BACK_PRESSED=false;
    public ValidTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if(editText.length()==0)
        {
            editText.setError("Enter Valid Value");
            //drawGirlLocation.setVisibility(View.GONE);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
      if(editText.length()==0)
      {
          editText.setError("Enter Valid Value");
          //drawGirlLocation.setVisibility(View.GONE);
      }
      else {
          editText.setError(null);

      }

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
