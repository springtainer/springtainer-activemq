package com.avides.springboot.testcontainer.activemq;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

public class NoLoggingExceptionListener implements ExceptionListener
{
    @Override
    public void onException(JMSException exception)
    {
        // no logging necessary
    }
}
