package de.fhw.flippermods.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

  @Id
  @GeneratedValue
  Long id;

  String username;
  String passwordMD5;
  String email;

  Long tiltValue;

  public Profile(ProfileInfo info) {
    this.username = info.username;
    this.passwordMD5 = info.passwordMD5;
  }

}
