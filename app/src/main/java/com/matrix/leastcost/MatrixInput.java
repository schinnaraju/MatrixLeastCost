package com.matrix.leastcost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.matrix.leastcost.adapter.CostMatrixAdapter;
import com.matrix.leastcost.beanclasses.LeastCostBean;
import com.matrix.leastcost.utility.SharedMethods;

import org.json.JSONArray;
import org.json.JSONException;

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
                        LeastCostBean outputData = calculateLeastPath(costMatrixArray);

                        Intent leastPathResult = new Intent(MatrixInput.this, LeastPath.class);
                        leastPathResult.putExtra("leastCost", outputData.getCost());
                        leastPathResult.putExtra("isValidInput", outputData.getIsValidInput());
                        leastPathResult.putExtra("leastPath", outputData.getLeastPath());

                        startActivity(leastPathResult);
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
     *                   step 2 : Calculate the least path and cost associated among all rows traversal
     *                   step 3 : launch the result activity to display the values
     */
    public LeastCostBean calculateLeastPath(int[][] costMatrix){
        int leastCost = 0, leastRow = 0;
        for (int col = 0; col < costMatrix[0].length; col++) {
            for(int row = 0; row < costMatrix.length; row++){
                if(col == 0) {
                    costMatrix[row][col] = costMatrix[row][col];
                } else {
                    costMatrix[row][col] = costMatrix[row][col] + getLeastLeftValue(row, col, costMatrix);
                }
                if(col == costMatrix[0].length - 1){
                    leastRow = row == 0 || costMatrix[row][col] < leastCost ? row : leastRow;
                    leastCost = row == 0 || costMatrix[row][col] < leastCost ? costMatrix[row][col] : leastCost;
                }
            }
        }
        LeastCostBean leastCostBean = new LeastCostBean();
        if(leastCost > 50) {
            leastCostBean.setIsValidInput("No");
        }
        leastCostBean.setCost(leastCost);
        leastCostBean = validateLeastPath(leastRow, costMatrix[0].length - 1, costMatrix, leastCostBean);

        return leastCostBean;
    }

    /**
     *
     * @param row row to find the least cost and previous least cost
     * @param column column to find the least cost and previous least cost
     * @param matrix : Matrix with least cost calculated with each node
     * @param leastCostBean : least cost bean to map the result
     * @return add the least cost and path and then return as class object
     */
    private LeastCostBean validateLeastPath (int row, int column, int[][] matrix, LeastCostBean leastCostBean) {
        String path = leastCostBean.getLeastPath();

        if(leastCostBean.getCost() > 50){
            // If the cost is more than 50 then assign the previous least value
            leastCostBean.setCost(column == 0 ? 0 : matrix[findPreviousMinRow(row, column, matrix)][column - 1]);
        } else {
            // add the path only if the cost is lesser than 50
            leastCostBean.setLeastPath( (path.isEmpty() ? ((row + 1) + "") : ((row + 1) + ", ")) + path);
        }
        // if it reaches to the start of matrix column then return
        if(column == 0) {
            return leastCostBean;
        }

        validateLeastPath(findPreviousMinRow(row, column, matrix), column - 1, matrix, leastCostBean);

        return leastCostBean;
    }

    /**
     *
     * @param row : Current row to find the previous row
     * @param column : current column
     * @param matrix : previous least cost matrix with node's are already populated with least cost
     * @return
     */
    private int findPreviousMinRow(int row, int column, int[][] matrix){
        // find the previous rows to get the minimal cost
        int leftDiagonalDown = row == matrix.length - 1 ? 0 : row + 1;
        int leftDiagonalUp = row == 0 ? matrix.length - 1 : row - 1;

        int diagonalUp = matrix[leftDiagonalUp][column - 1];
        int hori = matrix[row][column - 1];
        int diagonalDown = matrix[leftDiagonalDown][column - 1];
        // Least cost amount the diagonal traversal
        int least = Math.min(diagonalUp, Math.min(hori, diagonalDown));
        // Add the least cost path
        int previousRow = row;
        if (least == diagonalUp) {
            previousRow = leftDiagonalUp;
        } else if (least == diagonalDown) {
            previousRow = leftDiagonalDown;
        }
        return previousRow;
    }
    /**
     *
     * @param row : Current row
     * @param col : Current column
     * @param matrix : matrix
     * @return return the minimum cost from previous diagonal up, down and horizontal values
     */
    private int getLeastLeftValue(int row, int col, int[][] matrix){
        int leftDiagonalDown = row == matrix.length - 1 ? 0 : row + 1;
        int leftDiagonalUp = row == 0 ? matrix.length - 1 : row - 1;
        return Math.min(matrix[leftDiagonalUp][col-1], Math.min(matrix[leftDiagonalDown][col-1], matrix[row][col-1]));
    }
}
