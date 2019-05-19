
package ro.utcn.spet.example.a1.service;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import ro.utcn.spet.example.a1.entity.Question;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.entity.Tag;
        import ro.utcn.spet.example.a1.entity.Tag_question;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.TagQuestionRepository;
        import ro.utcn.spet.example.a1.repository.TagRepository;

        import java.util.Date;
        import java.util.List;
        import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagQuestionManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public void addTagQuestion(Integer questionIdd, Integer tagId){
        repositoryFactory.createTagQuestionRepository().save(new Tag_question(questionIdd, tagId));
    }

    @Transactional
    public List<Tag> listTags() {
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public List<Tag_question> findTagforQuestion(Integer tagId){
        TagQuestionRepository repository = repositoryFactory.createTagQuestionRepository();
       return repository.findQuestionTag(tagId);
    }



}
