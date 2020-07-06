package com.mq.listener;

import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class CreateGroupListener implements MessageListener {

    private static final int RETRY_MAX_TIMES = 5;

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQObjectMessage) {
            ActiveMQObjectMessage acmessage = (ActiveMQObjectMessage) message;
            try {
                Object object = acmessage.getObject();
                // do something
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }


}
