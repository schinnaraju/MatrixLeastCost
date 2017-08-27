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
                validateInput();
            }
        });
    }

    /**
     *  step 1 : check the input values
     *  step 2 : alert invalid input if either row or column is empty
     *  step 3 : start the maxtrix input activity and pass the row and cloumn specification as extra
     */
    private void validateInput(){
        String rowMax = ((EditText) findViewById(R.id.maxRowInput)).getText().toString();
        String columnMax = ((EditText) findViewById(R.id.maxColumnInput)).getText().toString();
        if(rowMax.isEmpty() || columnMax.isEmpty()){
            SharedMethods.showInvalidInputDialog(this, "Empty Input Found", "Kindly fill " + (rowMax.isEmpty() ? "row" : "column") + " input.");
        } else {
            Intent matrixInputActivity = new Intent(this, MatrixInput.class);
            matrixInputActivity.putExtra("rowMax", Integer.parseInt(rowMax));
            matrixInputActivity.putExtra("columnMax", Integer.parseInt(columnMax));
            startActivity(matrixInputActivity);
        }
    }
}
