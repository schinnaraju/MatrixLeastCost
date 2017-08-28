package com.matrix.leastcost;

import com.matrix.leastcost.beanclasses.LeastCostBean;

/**
 * Created by sampath_k on 26/08/17.
 */

public class TestValues {

    public static int[][] matrix1 = {{3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 8, 6, 4}};
    public static int[][] matrix2 = {{3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 1, 2, 3}};
    public  static int[][] matrix3 = {{19, 10, 19, 10, 19},
            {21, 23, 20, 19, 12},
            {20, 12, 20, 11, 10,}};
    public static int[][] matrix10 = {{6,3,-5,9},
            {-5,2,4,10},
            {3,-2,6,10},
            {6,-1,-2,10}};
    public static int[][] matrix9 = {{60, 3, 3, 6},
            {6, 3, 7, 9},
            {5, 6, 8, 3}};
    public static int[][] matrix13 = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
    public static int[][] matrix12 = {{51, 51, 51},
            {0, 51, 51},
            {51, 51, 51},
            {5, 5, 51}};
    public static int[][] matrix11 = {{51, 51},
            {0, 51},
            {51, 51},
            {5, 5}};
    public static int[][] matrix14 = {{1,1,1,1,1,1,1,1}};
    public static int[][] matrix15 = {{1},
            {1},
            {1},
            {1},
            {1},
            {1}};

    public static LeastCostBean matrix1OutPut = new LeastCostBean("Yes",16,"1,2,3,4,4,5");
    public static LeastCostBean matrix2OutPut = new LeastCostBean("Yes",11,"1,2,1,5,4,5");
    public static LeastCostBean matrix3OutPut = new LeastCostBean("No",48,"1,1,1");
}
