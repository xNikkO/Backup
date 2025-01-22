package pl.edu.pjwstk.fanbasedata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.fanbasedata.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);
    User findUserByLogin(String login);
}
