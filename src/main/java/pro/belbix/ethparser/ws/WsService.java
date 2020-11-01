package pro.belbix.ethparser.ws;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WsService {
    public final static String UNI_TRANSACTIONS_TOPIC_NAME = "/topic/transactions";
    public final static String HARVEST_TRANSACTIONS_TOPIC_NAME = "/topic/harvest";

    private final SimpMessagingTemplate messagingTemplate;

    public WsService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(String destination, Object o) {
        messagingTemplate.convertAndSend(destination, o);
    }
}