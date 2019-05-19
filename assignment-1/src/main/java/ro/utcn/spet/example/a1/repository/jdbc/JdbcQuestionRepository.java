package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.repository.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {

    private final JdbcTemplate template;

    @Override
    public Question save(Question question) {
        if (question.getId() == null) {
            question.setId(insert(question));
        } else {
            update(question);
        }
        return question;
    }

    @Override
    public Optional<Question> filterByTitle(String title) {
        List<Question> questions = template.query("SELECT * FROM question WHERE title = ?",
                (resultSet, i)-> new Question(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("creation_time")), title);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    public Optional<Question> findById(int id) {

        List<Question> questions = template.query("SELECT * FROM question WHERE id = ?",
                (resultSet, i)-> new Question(resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getDate("creation_time")), id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));

    }



    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE id = ?", question.getId());
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM question", (resultSet, i) -> new Question(resultSet.getInt("id"),resultSet.getInt("user_id"),
                resultSet.getString("title"), resultSet.getString("text"),resultSet.getDate("creation_time")));
    }


    private int insert(Question question){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("user_id", question.getUserId());
        data.put("title", question.getTitle());
        data.put("text", question.getText());
        data.put("creation_time",question.getCreationTime() );


        return insert.executeAndReturnKey(data).intValue();

    }

    private void update(Question question){
        template.update("UPDATE question set title = ?, text = ? where id = ?", question.getTitle(), question.getText(), question.getId());
    }

}

