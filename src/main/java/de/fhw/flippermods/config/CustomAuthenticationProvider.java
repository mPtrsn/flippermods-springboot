package de.fhw.flippermods.config;

import de.fhw.flippermods.ProfileRepository;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final ProfileRepository profileRepository;

  @Autowired
  public CustomAuthenticationProvider(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String passwordMD5 = authentication.getCredentials().toString();
    log.info("authenticate {username: {}, password: {}}", username, passwordMD5);

    if (profileRepository.findByUsernameAndPasswordMD5(username, passwordMD5).isPresent()) {
      return new UsernamePasswordAuthenticationToken(username, passwordMD5, new ArrayList<>());
    } else {
      return null;
    }
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
  }
}
