package project.semi.securitytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.semi.securitytest.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
