package com.matrix.leastcost;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MatrixDimentionsActivityTest
{
    private MatrixDimensions activity;
    private EditText rowEt,columnEt;
    private Button submit;
    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MatrixDimensions.class )
                .create()
                .resume()
                .get();

        rowEt = (EditText) activity.findViewById(R.id.maxRowInput);
        columnEt = (EditText) activity.findViewById(R.id.maxColumnInput);
        submit = (Button) activity.findViewById(R.id.submitButton);
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }
    @Test
    public void performValidSubmitClick() throws Exception {
        rowEt.setText("2");
        columnEt.setText("2");
        submit.performClick();
    }
    @Test
    public void performInvalidSubmitClick() throws Exception {
        rowEt.setText("");
        columnEt.setText("");
        submit.performClick();
    }
    @Test
    public void validateColumnEditText1() throws Exception
    {
        columnEt.setText("");
        assertFalse(activity.validateInput(columnEt));
    }

    @Test
    public void validateColumnEditText2() throws Exception
    {
        columnEt.setText("123");
        assertTrue(activity.validateInput(columnEt));
    }

    @Test
    public void validateRowEditText1() throws Exception
    {
        rowEt.setText("");
        assertFalse(activity.validateInput(rowEt));
    }

    @Test
    public void validateRowEditText2() throws Exception
    {
        rowEt.setText("123");
        assertTrue(activity.validateInput(rowEt));
    }

}