package de.fhw.flippermods.controller;

import de.fhw.flippermods.model.DataPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("test")
public class TestController {


    @GetMapping
    public ResponseEntity test(){
        return ResponseEntity.ok("erfolgreich");
    }

    @PostMapping("data")
    public ResponseEntity sendData(@RequestBody DataPackage data){
        log.info(data.getText());
        log.info(data.getNumber() + "");
        return ResponseEntity.ok().build();
    }


}
