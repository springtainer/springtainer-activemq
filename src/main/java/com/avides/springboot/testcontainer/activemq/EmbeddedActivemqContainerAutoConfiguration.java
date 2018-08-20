package com.avides.springboot.testcontainer.activemq;

import static com.avides.springboot.testcontainer.activemq.ActivemqProperties.BEAN_NAME;

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

import com.avides.springboot.testcontainer.common.container.AbstractBuildingEmbeddedContainer;
import com.avides.springboot.testcontainer.common.container.EmbeddedContainer;

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

    public class ActivemqContainer extends AbstractBuildingEmbeddedContainer<ActivemqProperties>
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
            catch (Exception e)
            {
                return false;
            }
        }
    }
}
