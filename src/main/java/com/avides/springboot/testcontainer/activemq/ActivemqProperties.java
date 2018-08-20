package com.avides.springboot.testcontainer.activemq;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.avides.springboot.testcontainer.common.container.AbstractEmbeddedContainerProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties("embedded.container.activemq")
@Getter
@Setter
@ToString(callSuper = true)
public class ActivemqProperties extends AbstractEmbeddedContainerProperties
{
    public static final String BEAN_NAME = "embeddedActivemqContainer";

    private int port = 61616;

    public ActivemqProperties()
    {
        setDockerImage("rmohr/activemq:5.15.4-alpine");
    }
}
