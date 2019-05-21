package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.VoteQuestion;
import ro.utcn.spet.example.a1.exception.VoteQuestionNotFoundException;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.VoteQuestionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteQuestionManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<VoteQuestion> listAllVotesQuestion(Integer questionId) {
        return (repositoryFactory.createVoteQuestionRepository().findAllQuestionVotes(questionId) );
    }

    @Transactional
    public List<VoteQuestion> listAllVotesUser(Integer userId) {
        return (repositoryFactory.createVoteQuestionRepository().findAllUserVotes(userId) );
    }

    @Transactional
    public void addVoteQuestion(StackUser user, Integer questionId, int value){

        List<VoteQuestion> voteAnswer =repositoryFactory.createVoteQuestionRepository().findAllQuestionVotes(questionId).stream().filter(
                (VoteQuestion v) -> v.getUsesr_id().equals(user.getId())).collect(Collectors.toList()); //am toate voturile pt un answer ale unui anumit user
        if(voteAnswer.isEmpty()){
            repositoryFactory.createVoteQuestionRepository().save(new VoteQuestion(user.getId(), questionId, value));
        }
        else{
            //daca am votat dar acum dau alta valoare
            if(repositoryFactory.createVoteQuestionRepository().findVoteWithUserAndQuestion(user.getId(), questionId).get().getValue() != value){
                updateVoteQuestion(repositoryFactory.createVoteQuestionRepository().findVoteWithUserAndQuestion(user.getId(), questionId).get().getId(), value);
                System.out.println("Vote Updated");
            }
            else {
                System.out.println("You already voted "+ value +"  on this question" );
            }

        }

    }

    public Optional<VoteQuestion> findById(int id)
    {
        return repositoryFactory.createVoteQuestionRepository().findById(id);
    }

    @Transactional
    public void updateVoteQuestion(int voteId, int value) {
        VoteQuestionRepository repository = repositoryFactory.createVoteQuestionRepository();
        VoteQuestion voteQuestion = repository.findById(voteId).orElseThrow(VoteQuestionNotFoundException::new);
        voteQuestion.setValue(value);
        repository.save(voteQuestion);
    }


}
