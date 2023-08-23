package integration.module1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class Service01 {

    Log log = LogFactory.getLog(getClass());

    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("Receive message 1 (from Service01): " +  message.getPayload());
    }
}
