package springmvctp.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvctp.dao.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}