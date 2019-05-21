/*package ro.utcn.spet.example.a1.seed;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.Tag_question;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;

import ro.utcn.spet.example.a1.repository.TagQuestionRepository;

import java.util.Date;

@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class  TagQuestionSeed  implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
      /* TagQuestionRepository repository = factory.createTagQuestionRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new Tag_question(1, 1));
            repository.save(new Tag_question(2, 2));
            repository.save(new Tag_question(3, 3));
        }*/
 /*   }
}

*/