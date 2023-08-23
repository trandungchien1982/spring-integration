package integration.module1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Date;

@Service
public class Service01 {

    Log log = LogFactory.getLog(getClass());

    String appendFilePath = "OutputLogs/output.log";

    @PostConstruct
    void init() {
        writeLine("\n------------------- New Logs at time: " + new Date() + " ------------------");
    }

    public void handleMessage(Message<?> message) throws MessagingException {

        String logCnt = "Receive message 1 (from Service01): " +  message.getPayload();
        log.info(logCnt);
        writeLine(logCnt);

    }

    public void writeLine(String data) {
        try {
            FileUtils.writeStringToFile(new File(appendFilePath), data + "\n", true);
        } catch (Exception ex){}
    }
}
