package org.kuali.student.enrollment.registration.engine.listener;

import org.apache.log4j.Logger;
import org.kuali.student.enrollment.registration.engine.service.CourseRegistrationConstants;
import org.kuali.student.enrollment.registration.engine.util.MQPerformanceCounter;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * This class is designed to calculate performance metrics for a particular queue. In order to use
 * this class just place it at the front and end of a queue. This is designed for the Kuali Student Registration
 * Engine.
 */
public class SimplePerformanceListener implements MessageListener {

    public static final String QUEUE_NAME = "org.kuali.student.enrollment.registration.performanceStatsQueue";

    private static final Logger LOG = Logger.getLogger(SimplePerformanceListener.class);

    private JmsTemplate jmsTemplate;

    @Override
    public void onMessage(Message message) {

        long startTime = 0;
        long endTime = 0;
        String regReqId = "";

        try{
            if(message.getJMSReplyTo() != null){ // this is used for getting current stats
                // send mq message. use singlton perf counter
               jmsTemplate.convertAndSend(message.getJMSReplyTo(), MQPerformanceCounter.INSTANCE.getPerformanceStats());
            } else{ // we're part of the standard stat calculation
                //Pull the User ID and the Registration Request ID from the message.
                if (message instanceof MapMessage) {
                    try {
                        startTime = ((MapMessage) message).getLong("startTime");
                        endTime = ((MapMessage) message).getLong("endTime");
                        regReqId = ((MapMessage) message).getString(CourseRegistrationConstants.REGISTRATION_QUEUE_MESSAGE_REG_REQ_ID);
                    } catch (JMSException e) {
                        LOG.error("Error getting message content", e);
                    }
                }
                // use singleton perf counter to store / update stats
                MQPerformanceCounter.INSTANCE.updateCounts(regReqId, startTime, endTime);
            }
        }catch (Exception ex){
            LOG.error(ex);
        }
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }




}
