package com.airwallex.domain;

import org.junit.Assert;
import org.junit.Test;

public class OperatorsEnumTest {

    @Test
    public void testGetDescription() {
        Assert.assertEquals("Add", OperatorsEnum.ADD.getDescription());
    }

}