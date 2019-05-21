package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question question);

    //Optional<Question> findByTag(Tag tag);

    void remove(Question question);

    Optional<Question> findById(int id);

    List<Question> findAll();

    Optional<Question> filterByTitle(String title);



}