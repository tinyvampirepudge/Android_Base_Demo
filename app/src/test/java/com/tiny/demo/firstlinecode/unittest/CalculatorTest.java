package com.tiny.demo.firstlinecode.unittest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Desc:
 * Created by tiny on 2017/10/9.
 * Version:
 */
public class CalculatorTest {
    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {
        //expected: 6, sum of 1 and 5
        Assert.assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    @Test
    public void substract() throws Exception {
        Assert.assertEquals(1d, mCalculator.substract(5d, 4d), 0);
    }

    @Test
    public void divide() throws Exception {
        Assert.assertEquals(4d, mCalculator.divide(20d, 5d), 0);
    }

    @Test
    public void multiply() throws Exception {
        Assert.assertEquals(10d, mCalculator.multiply(2d, 5d), 0);
    }

}