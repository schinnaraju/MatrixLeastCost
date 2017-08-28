package com.matrix.leastcost;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.matrix.leastcost.utility.SharedMethods;

public class MatrixDimentions extends AppCompatActivity {

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
                        SharedMethods.showInvalidInputDialog(MatrixDimentions.this, "Empty Input Found", "Kindly fill both values");
                    } else {
                        Intent matrixInputActivity = new Intent(MatrixDimentions.this, MatrixInput.class);
                        matrixInputActivity.putExtra("rowMax", Integer.parseInt(rowEt.getText().toString()));
                        matrixInputActivity.putExtra("columnMax", Integer.parseInt(columnEt.getText().toString()));
                        startActivity(matrixInputActivity);
                    }
            }
        });
    }

    /**
     *  step 1 : check the input values
     *  step 2 : alert invalid input if either row or column is empty
     *  step 3 : start the maxtrix input activity and pass the row and cloumn specification as extra
     */

    public boolean validateInput(EditText etText){
        return etText.getText().toString().trim().length() > 0;
    }
}
