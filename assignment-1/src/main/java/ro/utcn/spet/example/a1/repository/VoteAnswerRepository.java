package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.VoteAnswer;

import java.util.List;
import java.util.Optional;

public interface VoteAnswerRepository {

    VoteAnswer save(VoteAnswer voteAnswer);

    List<VoteAnswer> findAll();

    List<VoteAnswer> findAllUserVotes(int userId);

    List<VoteAnswer> findAllAnswersVotes(int answerId);

    Optional<VoteAnswer> findVoteWithUserAndAnswer(int userId, int qid);


    Optional<VoteAnswer> findById(int id);

    void remove(VoteAnswer question);
}
