package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.Answer;
import ro.utcn.spet.example.a1.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryAnswerRepository implements AnswerRepository {

    private final Map<Integer, Answer> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Answer save(Answer answer) {
        if (answer.getId() == null) {
            answer.setId(currentId.incrementAndGet());
        }
        data.put(answer.getId(), answer);
        return answer;
    }

    @Override
    public void remove(Answer answer) {
        data.remove(answer.getId());
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Answer> findAllAnswersforQuestion(Integer questionId) {
        return data.values().stream().filter((Answer t) -> t.getQuestionId().equals(questionId)).collect(Collectors.toList());
    }


    @Override
    public List<Answer> findAllAnswersforUser(Integer userId) {
        return data.values().stream().filter((Answer t) -> t.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<Answer> findAll() {
        return new ArrayList<>(data.values());
    }


}
