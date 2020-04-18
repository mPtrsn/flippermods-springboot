package de.fhw.flippermods.controller;

import de.fhw.flippermods.model.DataPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("test")
@CrossOrigin("*")
public class TestController {


    @GetMapping
    public ResponseEntity test(){
        return ResponseEntity.ok("erfolgreich");
    }

    @PostMapping("data")
    public ResponseEntity sendData(@RequestBody DataPackage data){
        log.info(data.getId() +"");
        log.info(data.getNumber() + "");
        return ResponseEntity.ok().build();
    }


}
