package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.VoteQuestion;
import ro.utcn.spet.example.a1.repository.VoteQuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryVoteQuestionRepository implements VoteQuestionRepository {

    private final Map<Integer, VoteQuestion> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public VoteQuestion save(VoteQuestion voteQuestion) {
        if (voteQuestion.getId() == null) {
            voteQuestion.setId(currentId.incrementAndGet());
        }
        data.put(voteQuestion.getId(), voteQuestion);
        return voteQuestion;

    }

    @Override
    public List<VoteQuestion> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<VoteQuestion> findAllUserVotes(int userId) {
        return data.values().stream().filter((VoteQuestion t) -> t.getUsesr_id().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteQuestion> findVoteWithUserAndQuestion(int userId, int qid) {
        return Optional.ofNullable(data.values().stream().filter(
                (VoteQuestion v) -> v.getUsesr_id() == userId && qid == v.getQuestionId()).collect(Collectors.toList()).get(0));
    }

    @Override
    public List<VoteQuestion> findAllQuestionVotes(int questionId) {
        return data.values().stream().filter((VoteQuestion t) -> t.getQuestionId().equals(questionId)).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteQuestion> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(VoteQuestion question) {
        data.remove(question.getId());
    }
}
