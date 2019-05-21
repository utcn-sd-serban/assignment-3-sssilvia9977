
package ro.utcn.spet.example.a1.service;

        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.entity.VoteAnswer;
        import ro.utcn.spet.example.a1.entity.VoteQuestion;
        import ro.utcn.spet.example.a1.exception.VoteAnswerNotFoundException;
        import ro.utcn.spet.example.a1.exception.VoteQuestionNotFoundException;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.VoteAnswerRepository;
        import ro.utcn.spet.example.a1.repository.VoteQuestionRepository;

        import javax.transaction.Transactional;
        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteAnswerManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<VoteAnswer> listAllVotesAnswer(Integer ansID) {
        return (repositoryFactory.createVoteAnswerRepository().findAllAnswersVotes(ansID) );
    }

    @Transactional
    public List<VoteAnswer> listAllVotesUser(Integer userId) {
        return (repositoryFactory.createVoteAnswerRepository().findAllUserVotes(userId) );
    }

    @Transactional
    public void addVoteAnswer(StackUser user, Integer ansID, int value){
        List<VoteAnswer> voteAnswer =repositoryFactory.createVoteAnswerRepository().findAllAnswersVotes(ansID).stream().filter(
                (VoteAnswer v) -> v.getUsesr_id().equals(user.getId())).collect(Collectors.toList()); //am toate voturile pt un answer ale unui anumit user
        if(voteAnswer.isEmpty()){
            repositoryFactory.createVoteAnswerRepository().save(new VoteAnswer(user.getId(), ansID, value));
            System.out.println("OK");
        }
        else{
            //daca am votat dar acum dau alta valoare
            if(repositoryFactory.createVoteAnswerRepository().findVoteWithUserAndAnswer(user.getId(), ansID).get().getValue() != value){
                updateVoteAnswer(repositoryFactory.createVoteAnswerRepository().findVoteWithUserAndAnswer(user.getId(), ansID).get().getId(), value);
                System.out.println("Vote Updated");
            }
            else {
                System.out.println("You already voted "+ value +"  on this answer" );
            }

        }

    }


    public Optional<VoteAnswer> findById(int id)
    {
        return repositoryFactory.createVoteAnswerRepository().findById(id);
    }


    @Transactional
    public void updateVoteAnswer(int voteId, int value) {
        VoteAnswerRepository repository = repositoryFactory.createVoteAnswerRepository();
        VoteAnswer voteAnswer = repository.findById(voteId).orElseThrow(VoteAnswerNotFoundException::new);
        voteAnswer.setValue(value);
        repository.save(voteAnswer);
    }


}
