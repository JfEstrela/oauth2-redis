package br.com.oauth2.redis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.oauth2.redis.entity.User;

@Repository
public interface ClientDetailsRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

}
