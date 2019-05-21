package ro.utcn.spet.example.a1.service;


import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.dto.QuestionDTO;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.entity.Tag_question;
import ro.utcn.spet.example.a1.exception.QuestionNotFoundException;
import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
import ro.utcn.spet.example.a1.repository.QuestionRepository;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionManagementService {

    private final RepositoryFactory repositoryFactory;


    @Transactional
    public QuestionDTO findById(int id)
    {
        Question question = repositoryFactory.createQuestionRepository().findById(id).get();

        int index = 0;
        List<String>tags = new ArrayList<>();
        for(Tag_question tagQuestion : repositoryFactory.createTagQuestionRepository().findQuestionTag(question.getId()))
        {
           tags.add(repositoryFactory.createTagRepository().findTagById(tagQuestion.getTagId()).get().getName());
        }
        StackUser user = repositoryFactory.createStackUserRepository().findById(question.getUserId()).get();
        return QuestionDTO.ofEntity(question, user.getEmailAddress(), tags);
    }


    @Transactional
    public Question addQuestion(QuestionDTO dto) {
        StackUser user = repositoryFactory.createStackUserRepository().findByEmail(dto.getUser()).get();
        Question question = new Question(null, user.getId(), dto.getTitle(), dto.getText(), dto.getCreationTime());
        repositoryFactory.createQuestionRepository().save(question);
        for(String tag : dto.getTags())
        {
            if(!repositoryFactory.createTagRepository().findTagName(tag).isPresent())
            {
                repositoryFactory.createTagRepository().save(new Tag(tag));
            }

            Tag_question tagQuestion = new Tag_question(question.getId(), repositoryFactory.createTagRepository().findTagName(tag).get().getId());

            repositoryFactory.createTagQuestionRepository().save(tagQuestion);
        }

        return question;
    }

    @Transactional
    public List<QuestionDTO> findAllQuestions() {

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        List<Tag> tags = repositoryFactory.createTagRepository().findAll();
        questions.forEach(q->{
                List<Integer> tagQuestionList = repositoryFactory.createTagQuestionRepository().findAll().stream().
                        filter(x->x.getQuestionId().equals(q.getId())).map(Tag_question::getTagId).
                        collect(Collectors.toList());
                List<String> tagNameList = tags.stream().filter(x->tagQuestionList.contains(x.getId()))
                        .map(Tag::getName).collect(Collectors.toList());

                StackUser stackUser = repositoryFactory.createStackUserRepository().findById(q.getUserId()).orElseThrow(StackUserNotFoundException::new);
                questionDTOS.add(QuestionDTO.ofEntity(q, stackUser.getEmailAddress(), tagNameList));
        });
        return questionDTOS;
    }

    /*
    @Transactional
    public Optional<QuestionDTO> filterTitle(String title) {
        return repositoryFactory.createQuestionRepository().filterByTitle(title).map(QuestionDTO::ofEntity);

    }

    public Optional<QuestionDTO> findById(int id) {
        return repositoryFactory.createQuestionRepository().findById(id).map(QuestionDTO::ofEntity);
    }
    */
    @Transactional
    public void remove(String title) {
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.filterByTitle(title).orElseThrow(QuestionNotFoundException::new);
        repository.remove(question);
    }

}
