package com.matrix.leastcost.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.matrix.leastcost.R;

import org.json.JSONArray;

/**
 * Created by sampath_k on 26/08/17.
 */

public class CostMatrixAdapter extends BaseAdapter {
    private Context context;
    private int matrixList;
    private JSONArray inputArray = new JSONArray();
    public CostMatrixAdapter(Context context, int matrixList) {
        this.context = context;
        this.matrixList = matrixList;
    }
    public JSONArray getMatrixCostArray(){
        return this.inputArray;
    }
    @Override
    public int getCount() {
        return matrixList;
    }

    @Override
    public Object getItem(int i) {
        return matrixList;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = View.inflate(context, R.layout.cost_input, null);
            }
            if(inputArray.length() == i) {
                inputArray.put((EditText) view.findViewById(R.id.costInput));
            }
        return view;
    }
}
