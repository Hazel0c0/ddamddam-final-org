package kr.co.ddamddam.user.repository;

import kr.co.ddamddam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //이메일 중복체크
    @Query("select count(*) from User u where u.userEmail=:userEmail")
    boolean existsByEmail(String userEmail);

    Optional<User> findByUserEmail(String userEmail);

}
