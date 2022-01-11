package testetqi.tqi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testetqi.tqi.model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    public Optional<UserModel> findByEmail(String email);
}
