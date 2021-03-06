/*package ro.utcn.spet.example.a1.seed;

import static ro.utcn.spet.example.a1.entity.VoteValue.*;


        import lombok.RequiredArgsConstructor;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.core.Ordered;
        import org.springframework.core.annotation.Order;
        import org.springframework.stereotype.Component;
        import org.springframework.transaction.annotation.Transactional;
        import ro.utcn.spet.example.a1.entity.Answer;
        import ro.utcn.spet.example.a1.entity.Question;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.entity.VoteAnswer;
import ro.utcn.spet.example.a1.repository.*;

import java.util.Date;

@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class  VoteAnswerSeed  implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        VoteAnswerRepository repository = factory.createVoteAnswerRepository();
       if (repository.findAll().isEmpty()) {
            repository.save(new VoteAnswer(2, 3, DOWN));
            repository.save(new VoteAnswer(2, 2, UP));
            repository.save(new VoteAnswer(1, 2, UP));
       }

    }
}

*/
