/*
package ro.utcn.spet.example.a1.seed;

        import lombok.RequiredArgsConstructor;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.core.Ordered;
        import org.springframework.core.annotation.Order;
        import org.springframework.stereotype.Component;
        import org.springframework.transaction.annotation.Transactional;
        import ro.utcn.spet.example.a1.entity.Answer;
        import ro.utcn.spet.example.a1.entity.Question;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.repository.AnswerRepository;
        import ro.utcn.spet.example.a1.repository.QuestionRepository;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.StackUserRepository;

        import java.util.Date;

@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class  AnswerSeed  implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        AnswerRepository repository = factory.createAnswerRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new Answer(1, 2, "Answer to all your prayers"));
            repository.save(new Answer(2,1, "Food is not the answer"));
            repository.save(new Answer(1,1,  "What ever the question, the answer is yes"));
        }

    }
}

*/
