package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.VoteAnswer;
import ro.utcn.spet.example.a1.repository.VoteAnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryVoteAnswerRepository implements VoteAnswerRepository {
    private final Map<Integer, VoteAnswer> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public VoteAnswer save(VoteAnswer voteAnswer) {
        if (voteAnswer.getId() == null) {
            voteAnswer.setId(currentId.incrementAndGet());
        }
        data.put(voteAnswer.getId(), voteAnswer);
        return voteAnswer;
    }

    @Override
    public List<VoteAnswer> findAll() {
        return new ArrayList<>(data.values());
    }


    @Override
    public List<VoteAnswer> findAllUserVotes(int userId) {
        return data.values().stream().filter((VoteAnswer t) -> t.getUsesr_id().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<VoteAnswer> findAllAnswersVotes(int asnId) {
        return data.values().stream().filter((VoteAnswer t) -> t.getAnswerId().equals(asnId)).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteAnswer> findVoteWithUserAndAnswer(int userId, int qid) {
            return Optional.ofNullable(data.values().stream().filter(
                    (VoteAnswer v) -> v.getUsesr_id() == userId && qid == v.getAnswerId()).collect(Collectors.toList()).get(0));
        }


    @Override
    public Optional<VoteAnswer> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(VoteAnswer answer) {
        data.remove(answer.getId());
    }
}
