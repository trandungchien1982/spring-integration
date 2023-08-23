package integration.module2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class Service02 implements MessageHandler {
    Log log = LogFactory.getLog(getClass());

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("Receive message 2 (from Service02: " +  message.getPayload());
    }
}
