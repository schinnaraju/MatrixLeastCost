package com.matrix.leastcost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MatrixInputActivityTest
{
    private MatrixInput activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MatrixInput.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void calculateLeastPathTestMatrix1() throws Exception
    {
        assertEquals(TestValues.matrix1OutPut,activity.calculateLeastPath(TestValues.matrix1));
    }

    @Test
    public void calculateLeastPathTestMatrix2() throws Exception
    {
        assertEquals(TestValues.matrix2OutPut,activity.calculateLeastPath(TestValues.matrix2));
    }

    @Test
    public void calculateLeastPathTestMatrix3() throws Exception
    {
        assertEquals(TestValues.matrix3OutPut,activity.calculateLeastPath(TestValues.matrix3));
    }

}