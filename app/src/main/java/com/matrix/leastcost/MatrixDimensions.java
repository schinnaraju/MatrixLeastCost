package com.matrix.leastcost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.matrix.leastcost.utility.SharedMethods;

public class MatrixDimensions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_dimentions);
        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rowEt = (EditText) findViewById(R.id.maxRowInput);
                EditText columnEt = (EditText) findViewById(R.id.maxColumnInput);
                if(validateInput(rowEt) && validateInput(columnEt)) {
                    Intent matrixInputActivity = new Intent(MatrixDimensions.this, MatrixInput.class);
                    matrixInputActivity.putExtra("rowMax", Integer.parseInt(rowEt.getText().toString()));
                    matrixInputActivity.putExtra("columnMax", Integer.parseInt(columnEt.getText().toString()));
                    startActivity(matrixInputActivity);
                } else {
                    SharedMethods.showInvalidInputDialog(MatrixDimensions.this, "Empty Input Found", "Kindly enter right dimensions.");
                }
            }
        });
    }
    /**
     *  step 1 : check the input values
     *  step 2 : alert invalid input if either row or column is "empty" or "0"
     *  step 3 : start the maxtrix input activity and pass the row and cloumn specification as extra
     */

    public boolean validateInput(EditText etText){
        String inputDimension = etText.getText().toString().trim();
        return inputDimension.length() > 0 && Integer.parseInt(inputDimension) > 0;
    }
}
