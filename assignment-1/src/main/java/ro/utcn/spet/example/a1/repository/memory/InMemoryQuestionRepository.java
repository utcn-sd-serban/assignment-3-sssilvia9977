package ro.utcn.spet.example.a1.repository.memory;


import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.repository.QuestionRepository;

import java.util.*;

public class InMemoryQuestionRepository implements QuestionRepository {
    private final Map<Integer, Question> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public synchronized Question save(Question question) {
        if(question.getId() != null)
        {
            data.put(question.getId(), question);
        }else
        {
            question.setId(currentId++);
            data.put(question.getId(), question);
        }
        return question;
    }


    //plus functie pt find by tag

    @Override
    public synchronized void remove(Question question) {
        data.remove(question.getId());
    }

    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Question> filterByTitle(String title) {
        return data.values().stream().filter((Question question) ->
                question.getTitle().equals(title)).findFirst();

    }


}
