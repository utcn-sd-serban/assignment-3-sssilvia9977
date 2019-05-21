package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.Answer;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.exception.AnswerNotFoundException;
import ro.utcn.spet.example.a1.exception.QuestionNotFoundException;
import ro.utcn.spet.example.a1.repository.AnswerRepository;
import ro.utcn.spet.example.a1.repository.QuestionRepository;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerManagementService {


    private final RepositoryFactory repositoryFactory;

    @Transactional
    public void addAnswer(Integer userId, Integer questionId, String text){
        repositoryFactory.createAnswerRepository().save(new Answer(userId, questionId, text));
    }

    @Transactional
    public List<Answer> findAllAnswers(){
        return (repositoryFactory.createAnswerRepository().findAll());
    }

    @Transactional
    public List<Answer> findUserAllAnswers(Integer userId){
        return (repositoryFactory.createAnswerRepository().findAllAnswersforUser(userId)  );
    }

    @Transactional
    public List<Answer> findQuestionAllAnswers(Integer qidd){
        return (repositoryFactory.createAnswerRepository().findAllAnswersforQuestion(qidd)  );
    }

    @Transactional
    public void updateText(int id, String text) {
        AnswerRepository repository = repositoryFactory.createAnswerRepository();
        Answer answer = repository.findById(id).orElseThrow(AnswerNotFoundException::new);
        answer.setText(text);
        repository.save(answer);
    }

    public Optional<Answer> findById(int id)
    {
        return repositoryFactory.createAnswerRepository().findById(id);
    }

    @Transactional
    public void remove(Integer id) {
        AnswerRepository repository = repositoryFactory.createAnswerRepository();
        Answer answer = repository.findById(id).orElseThrow(AnswerNotFoundException::new);
        repository.remove(answer);
    }

}
