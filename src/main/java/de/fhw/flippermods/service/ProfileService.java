package de.fhw.flippermods.service;

import de.fhw.flippermods.ProfileRepository;
import de.fhw.flippermods.model.Profile;
import de.fhw.flippermods.model.ProfileInfo;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {


  private final ProfileRepository profileRepository;

  public ProfileService(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  public Profile createProfile(ProfileInfo info) {
    Optional<Profile> byUsername = getProfileByName(info.username);
    if (!byUsername.isPresent()) {
      Profile profile = new Profile(info);
      profile = profileRepository.save(profile);
      return profile;
    } else {
      throw new RuntimeException("Username already taken");
    }
  }

  private Optional<Profile> getProfileByName(String username) {
    return profileRepository.findByUsername(username);
  }

  public boolean checkUsernameExists(String username) {
    return getProfileByName(username).isPresent();
  }

  public Profile loginUser(ProfileInfo info) {
    return profileRepository
        .findByUsernameAndPasswordMD5(info.username, info.passwordMD5)
        .orElseThrow(() -> new RuntimeException("Wrong Username/Password combination."));
  }


  public boolean deleteProfile(ProfileInfo info) {
    Optional<Profile> profile = profileRepository
        .findByUsernameAndPasswordMD5(info.username, info.passwordMD5);
    boolean present = profile.isPresent();
    if (present) {
      profileRepository.delete(profile.get());
    }
    return present;
  }
}
