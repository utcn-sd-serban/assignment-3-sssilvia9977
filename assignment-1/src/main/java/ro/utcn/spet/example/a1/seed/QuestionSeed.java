/*package ro.utcn.spet.example.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.QuestionRepository;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.StackUserRepository;

import java.util.Date;

@Component
    @RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public class  QuestionSeed  implements CommandLineRunner {
        private final RepositoryFactory factory;

        @Override
        @Transactional
        public void run(String... args) throws Exception {
            QuestionRepository repository = factory.createQuestionRepository();
            if (repository.findAll().isEmpty()) {
                repository.save(new Question(1, "A s question", "I just want to see this works", new Date()));
                repository.save(new Question(2, "C s question", "I just want to see foood", new Date()));
                repository.save(new Question(3, "E s question", "I just want to see the world burn", new Date()));
            }

        }
    }

*/
