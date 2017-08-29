package com.matrix.leastcost.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by sampath_k on 27/08/17.
 * All shared methods will be coded here to reduce code duplication
 */

public class SharedMethods {

    public static void showInvalidInputDialog(Context context, String title, String message) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(true);

            AlertDialog invalidInputDialog = alertDialogBuilder.create();

            invalidInputDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
