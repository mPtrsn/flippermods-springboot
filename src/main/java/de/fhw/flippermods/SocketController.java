package de.fhw.flippermods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@Controller
@RequestMapping("arduino")
@EnableScheduling
public class SocketController {

    private SimpMessagingTemplate template;

    @Autowired
    public SocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.template = simpMessagingTemplate;
    }

    @MessageMapping("/hello/{id}")
    @SendTo("/topic/greetings/{id}")
    public Greeting greeting(HelloMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @PostMapping("{id}")
    @SendTo("/topic/data/{id}")
    public ResponseEntity redirectMessage(@PathVariable("id") Long id, @RequestBody DataPackage dataPackage) {
        this.template.convertAndSend("/topic/data/" + id, dataPackage);
        return ResponseEntity.ok().build();
    }
}
