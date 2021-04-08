package api.ErrorHandler;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class Errors {

    public void handler(SNSEvent snsEvent){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        snsEvent.getRecords().forEach(record -> {
            logger.trace("Queue event: " +record.toString());
        });
    }
}
