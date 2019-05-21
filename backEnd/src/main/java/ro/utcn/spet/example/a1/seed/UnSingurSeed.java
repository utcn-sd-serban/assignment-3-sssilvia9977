package ro.utcn.spet.example.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.*;
import ro.utcn.spet.example.a1.repository.*;

import java.util.Date;

import static ro.utcn.spet.example.a1.entity.VoteValue.DOWN;
import static ro.utcn.spet.example.a1.entity.VoteValue.UP;


@Component
@RequiredArgsConstructor
// The Order ensures that this command line runner is ran first (before the ConsoleController)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UnSingurSeed implements CommandLineRunner {

    private final RepositoryFactory factory;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
       // StackUserRepository repository = factory.createStackUserRepository();
        //repository.save(new StackUser("S", "S", "SS", passwordEncoder.encode("Sil")));
        StackUserRepository repository = factory.createStackUserRepository();
        if (repository.findAll().isEmpty()) {
            repository.save(new StackUser("A", "B", "A.B@example.com", passwordEncoder.encode("AB")));
            repository.save(new StackUser("C", "D", "C.D@example.com", passwordEncoder.encode("CD")));
            repository.save(new StackUser("E", "F", "qq", passwordEncoder.encode("qq")));
            repository.save(new StackUser("G", "H", "q", passwordEncoder.encode("q")));

        }


        QuestionRepository questionRepository = factory.createQuestionRepository();
        if (questionRepository.findAll().isEmpty()) {
            questionRepository.save(new Question(1, "A s question", "I just want to see this works", new Date()));
            questionRepository.save(new Question(2, "C s question", "I just want to see foood", new Date()));
            questionRepository.save(new Question(3, "E s question", "I just want to see the world burn", new Date()));
        }


        AnswerRepository answerRepository = factory.createAnswerRepository();
        if (answerRepository.findAll().isEmpty()) {
            answerRepository.save(new Answer(1, 2, "Answer to all your prayers"));
            answerRepository.save(new Answer(2, 1, "Food is not the answer"));
            answerRepository.save(new Answer(1, 1, "What ever the question, the answer is yes"));
        }

        TagRepository tagRepository = factory.createTagRepository();
        if (tagRepository.findAll().isEmpty()) {
            tagRepository.save(new Tag("food"));
            tagRepository.save(new Tag("books"));
            tagRepository.save(new Tag("end of the world"));
        }


        TagQuestionRepository tagQuestionRepository = factory.createTagQuestionRepository();
        if (tagQuestionRepository.findAll().isEmpty()) {
            tagQuestionRepository.save(new Tag_question(1, 1));
            tagQuestionRepository.save(new Tag_question(2, 2));
            tagQuestionRepository.save(new Tag_question(3, 3));

        }
/*

        VoteAnswerRepository voteAnswerRepository = factory.createVoteAnswerRepository();
        if (voteAnswerRepository.findAll().isEmpty()) {
            voteAnswerRepository.save(new VoteAnswer(2, 3, DOWN));
            voteAnswerRepository.save(new VoteAnswer(2, 2, UP));
            voteAnswerRepository.save(new VoteAnswer(1, 2, UP));
        }

        VoteQuestionRepository voteQuestionRepository = factory.createVoteQuestionRepository();
        if (voteQuestionRepository.findAll().isEmpty()) {
            voteQuestionRepository.save(new VoteQuestion(1, 2, UP));
            voteQuestionRepository.save(new VoteQuestion(2,1, DOWN));
            voteQuestionRepository.save(new VoteQuestion(2,2,  UP));
        }

*/

    /*

    Insert into stack_user (first_name,last_name, email_address, password ) values ('A', 'B', 'AB@yahoo.com', 'AB');
insert into stack_user (first_name,last_name ,email_address, password) values ('C', 'D', 'CD@yahoo.com', 'CD');

insert into question (user_id, title, text) values (1, 'A s question', 'What is the purpose of life?');
insert into question (user_id, title, text) values (2, 'C s question', 'What is the meaning of life?');


insert into theTag (tag_name) values ('food');
insert into theTag (tag_name) values ('books');
insert into theTag (tag_name) values ('corgi');





    */


    }
}

