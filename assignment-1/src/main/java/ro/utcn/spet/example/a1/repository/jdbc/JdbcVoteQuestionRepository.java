package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.VoteQuestion;
import ro.utcn.spet.example.a1.repository.VoteQuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JdbcVoteQuestionRepository implements VoteQuestionRepository {

    private final JdbcTemplate template;


    @Override
    public VoteQuestion save(VoteQuestion voteQuestion) {
        if (voteQuestion.getId() == null) {
            voteQuestion.setId(insert(voteQuestion));
        } else {
            update(voteQuestion);
        }
        return voteQuestion;
    }

    @Override
    public List<VoteQuestion> findAll() {
        return template.query("SELECT * FROM vote_question", (resultSet, i) -> new VoteQuestion(resultSet.getInt("id"),resultSet.getInt("user_id"),
                resultSet.getInt("question_id"), resultSet.getInt("vote_value")));
}

    @Override
    public List<VoteQuestion> findAllUserVotes(int userId) {
        return findAll().stream().filter((VoteQuestion t) -> t.getUsesr_id().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteQuestion> findVoteWithUserAndQuestion(int userId, int qid) {
        return Optional.ofNullable(findAll().stream().filter(
                (VoteQuestion v) -> v.getUsesr_id() == userId && qid == v.getQuestionId()).collect(Collectors.toList()).get(0));
}

    @Override
    public List<VoteQuestion> findAllQuestionVotes(int questionId) {
        return findAll().stream().filter((VoteQuestion t) -> t.getQuestionId().equals(questionId)).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteQuestion> findById(int id) {

        List<VoteQuestion> questions = template.query("SELECT * FROM vote_question WHERE id = ?",
                (resultSet, i)-> new VoteQuestion(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("vote_value")),
                id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    @Override
    public void remove(VoteQuestion question) {
        template.update("DELETE FROM vote_question WHERE id = ?", question.getId());
    }


    private int insert(VoteQuestion voteQuestion) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("vote_question");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", voteQuestion.getUsesr_id());
        map.put("question_id", voteQuestion.getQuestionId());
        map.put("vote_value", voteQuestion.getValue());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(VoteQuestion voteQuestion) {
        template.update("UPDATE vote_question SET vote_value = ? WHERE id = ?",
                voteQuestion.getValue(), voteQuestion.getId());
    }



}
