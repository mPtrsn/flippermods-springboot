package de.fhw.flippermods;

import de.fhw.flippermods.model.Profile;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
  Optional<Profile> findByUsername(String username);
  Optional<Profile> findByUsernameAndPasswordMD5(String username, String passwordMd5);
}
