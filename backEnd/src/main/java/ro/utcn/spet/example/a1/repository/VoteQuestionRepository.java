package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.VoteQuestion;

import java.util.List;
import java.util.Optional;

public interface VoteQuestionRepository {

    VoteQuestion save(VoteQuestion voteQuestion);

    List<VoteQuestion> findAll();

    List<VoteQuestion> findAllUserVotes(int userId);

    Optional<VoteQuestion> findVoteWithUserAndQuestion(int userId, int qid);

    List<VoteQuestion> findAllQuestionVotes(int questionId);

    Optional<VoteQuestion> findById(int id);

    void remove(VoteQuestion question);
}
