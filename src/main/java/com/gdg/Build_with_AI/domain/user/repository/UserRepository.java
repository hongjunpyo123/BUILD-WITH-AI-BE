package com.gdg.Build_with_AI.domain.user.repository;

import com.gdg.Build_with_AI.domain.user.entity.User;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserUuid(String userUuid);
  Optional<User> findByProviderId(String providerId);

}
