package uz.rootec.appcovidserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.rootec.appcovidserver.entity.User;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);

//    Optional<User> findByEmail(String email);


}
