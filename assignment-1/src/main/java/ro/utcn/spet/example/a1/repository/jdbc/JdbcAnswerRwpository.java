package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.Answer;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JdbcAnswerRwpository implements AnswerRepository {

    private final JdbcTemplate template;

    @Override
    public Answer save(Answer answer) {
        if (answer.getId() == null) {
            answer.setId(insert(answer));
        } else {
            update(answer);
        }
        return answer;
    }

    @Override
    public void remove(Answer answer) {
        template.update("DELETE FROM answer WHERE id = ?", answer.getId());
    }

    @Override
    public Optional<Answer> findById(int id) {
        List<Answer> answers = template.query("SELECT * FROM answer WHERE id = ?",
                (resultSet, i)-> new Answer(resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("text"),
                        resultSet.getDate("creation_time")), id);
        return answers.isEmpty() ? Optional.empty() : Optional.of(answers.get(0));
    }

    @Override
    public List<Answer> findAllAnswersforQuestion(Integer questionId) {
        return findAll().stream().filter((Answer t) -> t.getQuestionId().equals(questionId)).collect(Collectors.toList());
    }


    public List<Answer> findAllAnswersforUser(Integer userId) {
        return findAll().stream().filter((Answer t) -> t.getUserId().equals(userId)).collect(Collectors.toList());
    }


    @Override
    public List<Answer> findAll() {
        return template.query("SELECT * FROM answer", (resultSet, i) -> new Answer(resultSet.getInt("id"),resultSet.getInt("question_id"),
                resultSet.getInt("user_id"), resultSet.getString("text"),resultSet.getDate("creation_time")));
    }


    private int insert(Answer answer) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("answer");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("question_id", answer.getQuestionId());
        map.put("user_id", answer.getUserId());
        map.put("text", answer.getText());
        map.put("creation_time", answer.getCreationTime());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Answer answer) {
        template.update("UPDATE answer SET text = ? WHERE id = ?",
                answer.getText(), answer.getId());
    }

}
