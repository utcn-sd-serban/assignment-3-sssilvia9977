package ro.utcn.spet.example.a1.repository;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import ro.utcn.spet.example.a1.entity.StackUser;

import java.util.List;
import java.util.Optional;

public interface StackUserRepository {
	List<StackUser> findAll();

	StackUser save(StackUser stackUser);

	void remove(StackUser stackUser);

	Optional<StackUser> findById(int id);

	Optional<StackUser> findByEmail(String firstname);

	Optional<StackUser> findAuthentication(String firstname, String lastname, String password);
}
