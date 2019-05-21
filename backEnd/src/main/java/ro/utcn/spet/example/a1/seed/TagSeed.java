/*package ro.utcn.spet.example.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.StackUserRepository;
import ro.utcn.spet.example.a1.repository.TagRepository;

@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TagSeed implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        TagRepository repository = factory.createTagRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new Tag("food"));
            repository.save(new Tag("end of the world"));
        }
    }
}
*/