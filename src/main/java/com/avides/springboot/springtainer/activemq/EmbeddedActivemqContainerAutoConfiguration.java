package com.avides.springboot.springtainer.activemq;

import static com.avides.springboot.springtainer.activemq.ActivemqProperties.BEAN_NAME;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import com.avides.springboot.springtainer.common.container.AbstractBuildingEmbeddedContainer;
import com.avides.springboot.springtainer.common.container.EmbeddedContainer;

import lombok.SneakyThrows;

@Configuration
@ConditionalOnProperty(name = "embedded.container.activemq.enabled", matchIfMissing = true)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(ActivemqProperties.class)
public class EmbeddedActivemqContainerAutoConfiguration
{
    @ConditionalOnMissingBean(ActivemqContainer.class)
    @Bean(BEAN_NAME)
    public EmbeddedContainer activemqContainer(ConfigurableEnvironment environment, ActivemqProperties properties)
    {
        return new ActivemqContainer("activemq", environment, properties);
    }

    public static class ActivemqContainer extends AbstractBuildingEmbeddedContainer<ActivemqProperties>
    {
        public ActivemqContainer(String service, ConfigurableEnvironment environment, ActivemqProperties properties)
        {
            super(service, environment, properties);
        }

        @Override
        protected Map<String, Object> providedProperties()
        {
            Map<String, Object> provided = new HashMap<>();
            provided.put("embedded.container.activemq.host", getContainerHost());
            provided.put("embedded.container.activemq.port", Integer.valueOf(getContainerPort(properties.getPort())));
            return provided;
        }

        @SneakyThrows
        @Override
        protected boolean isContainerReady(ActivemqProperties properties)
        {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://" + getContainerHost() + ":" + getContainerPort(properties.getPort()));
            factory.setExceptionListener(new NoLoggingExceptionListener());
            try (final Connection connection = factory.createConnection())
            {
                connection.start();
                return true;
            }
        }
    }
}
