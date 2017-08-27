package com.matrix.leastcost;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.matrix.leastcost.adapter.CostMatrixAdapter;
import com.matrix.leastcost.utility.SharedMethods;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MatrixInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_matrix);
            // GET THE MATRIX DIMENSIONS
            final int rows = getIntent().getIntExtra("rowMax", 1);
            final int columns = getIntent().getIntExtra("columnMax", 1);

            // INITIALISE YOUR GRID
            final GridView grid=(GridView)findViewById(R.id.costMatrixGridView);
            grid.setNumColumns(columns);

            // CREATE AN ADAPTER  (MATRIX ADAPTER)
            final CostMatrixAdapter adapter = new CostMatrixAdapter(getApplicationContext(), rows * columns);

            // ATTACH THE ADAPTER TO GRID
            grid.setAdapter(adapter);

            findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONArray matrixInputArray = adapter.getMatrixCostArray();
                    int[][] costMatrixArray = new int[rows][columns];
                    int row = 0, col = 0;
                    boolean hasAllInput = true;
                    for(int index = 0; index < matrixInputArray.length(); index++){
                        try {
                            EditText input = (EditText) matrixInputArray.get(index);
                            String inputVal = input.getText().toString();
                            // If any one of the input is empty then break the calculation and show the alert dialog
                            if(inputVal.isEmpty()){
                                hasAllInput = false;
                                break;
                            }

                            costMatrixArray[row][col] = Integer.parseInt(inputVal);

                            col++;
                            if(col == columns){
                                col = 0;
                                row++;
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    if(hasAllInput) {
                        calculateLeastPath(costMatrixArray);
                    } else {
                        SharedMethods.showInvalidInputDialog(MatrixInput.this, "Empty Input Found", "Kindly fill cost in all matrix input.");
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *
     * @param costMatrix :: user matrix input to find the least cost path
     *                   step 1 : Calculate the least path and cost associated with each row
     *                   step 2 : Calculate the least path and cost associated amoung all rows traversal
     *                   step 3 : launch the result activity to display the values
     */
    private void calculateLeastPath(int[][] costMatrix) {
        ArrayList<JSONArray> leastCost = new ArrayList<JSONArray>();
        // Calculate row wise least path
        for (int row = 0; row < costMatrix.length; row++) {
            leastCost.add(findLeastPath(row, 0, costMatrix));
        }
//        Log.d("leastCost", leastCost.toString());
        JSONArray leastCostObj = getLeastPath(leastCost);
        try {
//            Log.d("leastCostObj", leastCostObj.toString());
            Intent leastPathResult = new Intent(this, LeastPath.class);
            int cost = leastCostObj.getInt(0);
            String isValidInput = cost > 50 ? "No" : "Yes";
            String leastPath = leastCostObj.getJSONArray(1).toString().replaceAll("[\\[\\]]", "");
            if (cost > 50) {
                cost = 0;
                leastPath = "";
                JSONArray pathArray = leastCostObj.getJSONArray(1);
                for ( int index = 0; index < pathArray.length(); index++ ) {
                    if(cost + costMatrix[pathArray.getInt(index) - 1][index] > 50) {
                        break;
                    }
                    cost = cost + costMatrix[pathArray.getInt(index) - 1][index];
                    leastPath = leastPath + (leastPath.isEmpty() ? "" : ", ") + pathArray.getInt(index);
                }
            }
            leastPathResult.putExtra("leastCost", cost);
            leastPathResult.putExtra("isValidInput", isValidInput);
            leastPathResult.putExtra("leastPath", leastPath);

            startActivity(leastPathResult);
        } catch (JSONException e) {
            Log.e("JSON Exception", e.getLocalizedMessage());
        }
    }

    /**
     *
     * @param leastCost : array of least paths (Row wise)
     * @return :: the least cost and it's path
     */
    private JSONArray getLeastPath(ArrayList<JSONArray> leastCost){
        JSONArray leastValueObj = new JSONArray();
        try {
            leastValueObj = leastCost.get(0);
            for (int index = 1; index < leastCost.size(); index++) {
                if (leastValueObj.getInt(0) > leastCost.get(index).getInt(0)) {
                    leastValueObj = leastCost.get(index);
                }
            }
        } catch (JSONException e){
            Log.e("Exception getLeastPath", e.getLocalizedMessage());
        }
        return leastValueObj;
    }
    /**
     *
     * @param row :: current row index, Used to find the least from sum of current row,col with next column and horizontal, diagonal up and diagonal down rows
     * @param col :: current column index
     * @param matrix :: Cost matrix, least path calculation
     * @return :: JSONArray value with least cost and its path of current row and column
     */
    private JSONArray findLeastPath(int row, int col, int[][] matrix){
        JSONArray leastPathValue = new JSONArray();
        try {
            JSONArray leastPath = new JSONArray();
            leastPath.put(row+1);
            int least = matrix[row][col];;
            if (col == matrix[0].length - 1) {
                // Last column value hence return the cost and path associated with it
                leastPathValue.put(least);
                leastPathValue.put(leastPath);
            } else {
                // Calculate the least cost from next column & adjacent rows
                int dowmIndex = row == matrix.length - 1 ? 0 : row + 1;
                int upIndex = row == 0 ? matrix.length - 1 : row - 1;

                JSONArray diagonalUp = findLeastPath(upIndex, (col + 1), matrix);
                JSONArray horizontal = findLeastPath(row, (col + 1), matrix);
                JSONArray diagonalDown = findLeastPath(dowmIndex, (col + 1), matrix);
                if(diagonalUp.length() > 0) {
                    int up = least + diagonalUp.getInt(0);
                    int hori = least + horizontal.getInt(0);
                    int down = least + diagonalDown.getInt(0);
                    // Least cost amount the diagonal traversal
                    least = Math.min(up, Math.min(hori, down));
                    // Add the least cost path
                    if (least == up) {
                        addArrayPath(diagonalUp.getJSONArray(1), leastPath);
                    } else if (least == down) {
                        addArrayPath(diagonalDown.getJSONArray(1), leastPath);
                    } else {
                        addArrayPath(horizontal.getJSONArray(1), leastPath);
                    }
                }
                leastPathValue.put(least);
                leastPathValue.put(leastPath);
            }
        }catch (JSONException e){
            Log.e("Exception findLeastPath", e.getLocalizedMessage());
        }
        return leastPathValue;
    }

    /**
     *
     * @param currentPath :: Current least path array
     * @param leastPath :: existing least path array
     * @return :: merged least path array
     */
    private JSONArray addArrayPath(JSONArray currentPath, JSONArray leastPath){
        try {
            for (int index = 0; index < currentPath.length(); index++) {
                leastPath.put(currentPath.get(index));
            }
        }catch (JSONException e){
            Log.e("Exception addArrayPath", e.getLocalizedMessage());
        }
        return leastPath;
    }
}
