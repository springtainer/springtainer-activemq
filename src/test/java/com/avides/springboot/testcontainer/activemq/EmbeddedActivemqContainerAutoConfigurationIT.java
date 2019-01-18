package com.avides.springboot.testcontainer.activemq;

import static org.assertj.core.api.Assertions.assertThat;

import javax.jms.Session;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

public class EmbeddedActivemqContainerAutoConfigurationIT extends AbstractIT
{
    @Test
    public void testGeneratedProperties()
    {
        assertThat(environment.getProperty("embedded.container.activemq.host")).isNotEmpty();
        assertThat(environment.getProperty("embedded.container.activemq.port")).isNotEmpty();

        System.out.println();
        System.out.println("Resolved properties:");
        System.out.println("Host:         " + environment.getProperty("embedded.container.activemq.host"));
        System.out.println("Port:         " + environment.getProperty("embedded.container.activemq.port"));
        System.out.println();
    }

    @Test
    public void testCreateQueue() throws Exception
    {
        try (final Session session = connectionFactory.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE))
        {
            assertThat(session.createQueue("foo")).isNotNull().hasFieldOrPropertyWithValue("queueName", "foo");
        }
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfiguration
    {
        // nothing
    }
}
