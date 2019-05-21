/*package ro.utcn.spet.example.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.StackUserRepository;

@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StackUserSeed implements CommandLineRunner {
	private final RepositoryFactory factory;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		StackUserRepository repository = factory.createStackUserRepository();
		if (repository.findAll().isEmpty()) {
			repository.save(new StackUser("A", "B", "A.B@example.com", passwordEncoder.encode("AB")));
			repository.save(new StackUser("C", "D", "C.D@example.com", passwordEncoder.encode("CD")));
			repository.save(new StackUser("E", "F", "E.F@example.com", passwordEncoder.encode("EF")));
			repository.save(new StackUser("Alex", "F", "E.F@example.com", passwordEncoder.encode("AlexF")));
		}
	}
}
*/