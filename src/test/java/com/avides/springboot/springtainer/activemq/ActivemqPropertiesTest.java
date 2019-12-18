package com.avides.springboot.springtainer.activemq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActivemqPropertiesTest
{
    @Test
    public void testDefaults()
    {
        ActivemqProperties properties = new ActivemqProperties();
        assertTrue(properties.isEnabled());
        assertEquals(30, properties.getStartupTimeout());
        assertEquals("rmohr/activemq:5.15.9-alpine", properties.getDockerImage());
        assertEquals(61616, properties.getPort());
    }
}
