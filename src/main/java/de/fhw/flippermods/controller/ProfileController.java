package de.fhw.flippermods.controller;

import de.fhw.flippermods.model.ProfileInfo;
import de.fhw.flippermods.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
@CrossOrigin
public class ProfileController {

  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }


  @PutMapping
  public ResponseEntity createUser(@RequestBody ProfileInfo profileInfo) {
    return ResponseEntity.ok(profileService.createProfile(profileInfo));
  }

  @GetMapping("name")
  public ResponseEntity checkUserName(@RequestBody String username) {
    if (profileService.checkUsernameExists(username)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PostMapping("login")
  public ResponseEntity login(@RequestBody ProfileInfo info) {
    log.info("controller: " + info.username);
    log.info("controller: " + info.passwordMD5);
    return ResponseEntity.ok(profileService.loginUser(info));
  }

  @DeleteMapping
  public ResponseEntity deleteProfile(@RequestBody ProfileInfo info) {
    if (profileService.deleteProfile(info)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
