package de.fhw.flippermods.controller;

import de.fhw.flippermods.model.DataPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
//@RequestMapping("arduino")
//@EnableScheduling
public class SocketController {

    private SimpMessagingTemplate template;

    @Autowired
    public SocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.template = simpMessagingTemplate;
    }

    @MessageMapping("/data/{id}")
    @SendTo("/secured/data/{id}")
    public DataPackage redirectData(@DestinationVariable Long id, DataPackage dataPackage) {
        //log.info(dataPackage.toString());
        return dataPackage;
    }

//    @MessageMapping("/data")
////    @SendTo("/secured/data/{id}")
//    public String redirectData2(String str ) {
//        log.info(str);
//
//        return str;
//    }
/*
    @PostMapping("{id}")
    @SendTo("/secured/data/{id}")
    public ResponseEntity redirectMessage(@PathVariable("id") Long id, @RequestBody DataPackage dataPackage) {
        log.info(dataPackage.getNumber() + "");
        this.template.convertAndSend("/secured/data/" + id, dataPackage);
        return ResponseEntity.ok().build();
    }

 */
}
