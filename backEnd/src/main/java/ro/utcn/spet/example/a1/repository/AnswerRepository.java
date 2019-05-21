
package ro.utcn.spet.example.a1.repository;

        import ro.utcn.spet.example.a1.entity.Answer;
        import ro.utcn.spet.example.a1.entity.Question;

        import java.util.List;
        import java.util.Optional;
        import java.util.function.Supplier;

public interface AnswerRepository  {

    Answer save(Answer answer);

    void remove(Answer answer);

    Optional<Answer> findById(int id);

    List<Answer> findAllAnswersforQuestion(Integer questionId);

    List<Answer> findAllAnswersforUser(Integer userId);

    List<Answer> findAll();


}