/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package io.ballerina.messaging.broker.common.data.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class DecimalTest {

    private static final Decimal TEST_OBJECT = Decimal.parse(new BigDecimal(1.0d));

    private static final BigDecimal DATA_VALUE = new BigDecimal(1.0d);

    @Test
    public void testGetSize() {
        Assert.assertEquals(TEST_OBJECT.getSize(), 5, "Size of Decimal should be 5");
    }

    @Test
    public void testEquals() {
        Decimal other = Decimal.parse(new BigDecimal(3.0d));
        Decimal similar = Decimal.parse(DATA_VALUE);

        Assert.assertTrue(TEST_OBJECT.equals(TEST_OBJECT), "equals() should return true for similar objects");
        Assert.assertTrue(TEST_OBJECT.equals(similar), "equals() should return true for similar objects");

        Assert.assertFalse(TEST_OBJECT.equals(other), "equals() should return false for different objects");
        Assert.assertFalse(TEST_OBJECT.equals(DATA_VALUE), "equals() should return false for different objects");
    }

    @Test
    public void testHashCode() {
        Decimal similar = Decimal.parse(DATA_VALUE);
        Assert.assertEquals(similar.hashCode(), TEST_OBJECT.hashCode(), "Hashcode should match for similar data");
    }

    @Test
    public void testParse() {
        ByteBuf buf = Unpooled.buffer(8);
        TEST_OBJECT.write(buf);

        Decimal parsedObject = Decimal.parse(buf);
        Assert.assertEquals(parsedObject, TEST_OBJECT, "Encoding and decoding should result in the same object");
    }
}
