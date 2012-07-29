package org.recxx.compare;

// SNI Services ltd
// Shaine Ismail
// 29/07/2012


import junit.framework.Assert;
import org.junit.Test;

public class CompareTest {

    @Test
    public void testCompareString() throws Exception {
        String one = "TEST STRING ONE";
        String two = "TEST STRING TWO";

        Assert.assertTrue(Compare.value(one, one));
        Assert.assertTrue(Compare.value(two, two));
        Assert.assertFalse(Compare.value(one, two));
    }

    @Test
    public void testCompareValue() throws Exception {
        double one = 1.000000001d;
        double two = 1.000000002d;
        Assert.assertTrue(Compare.value(one,one));
        Assert.assertTrue(Compare.value(two,two));
        Assert.assertFalse(Compare.value(one,two));

    }

    @Test
    public void testCompareValueWithTolerance() throws Exception {
        double one = 1.000000001d;
        double two = 1.000000002d;
        double tolerance = 1;
        Assert.assertTrue(Compare.value(one,one));
        Assert.assertTrue(Compare.value(two,two));
        Assert.assertFalse(Compare.value(one,two));

        for (int i =0 ; i <= 8 ; i++ ) {
            Assert.assertTrue(Compare.value(one,two,tolerance));
            tolerance *= 10;
        }

        Assert.assertFalse(Compare.value(one,two,tolerance));
    }
}
