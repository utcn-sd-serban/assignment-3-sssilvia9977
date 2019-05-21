package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.VoteAnswer;
import ro.utcn.spet.example.a1.entity.VoteQuestion;
import ro.utcn.spet.example.a1.repository.VoteAnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class JdbcVoteAnswerRepository  implements VoteAnswerRepository {
    private final JdbcTemplate template;
    @Override
    public VoteAnswer save(VoteAnswer voteAnswer) {
        if (voteAnswer.getId() == null) {
            voteAnswer.setId(insert(voteAnswer));
        } else {
            update(voteAnswer);
        }
        return voteAnswer;
    }

    @Override
    public List<VoteAnswer> findAll() {
        return template.query("SELECT * FROM vote_answer", (resultSet, i) -> new VoteAnswer(resultSet.getInt("id"),resultSet.getInt("user_id"),
                resultSet.getInt("answer_id"), resultSet.getInt("vote_value")));
    }

    @Override
    public List<VoteAnswer> findAllUserVotes(int userId) {
        return findAll().stream().filter((VoteAnswer t) -> t.getUsesr_id().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<VoteAnswer> findAllAnswersVotes(int answerId) {
        return findAll().stream().filter((VoteAnswer t) -> t.getAnswerId().equals(answerId)).collect(Collectors.toList());
    }

    @Override
    public Optional<VoteAnswer> findVoteWithUserAndAnswer(int userId, int qid) {
        return Optional.ofNullable(findAll().stream().filter(
                (VoteAnswer v) -> v.getUsesr_id() == userId && qid == v.getAnswerId()).collect(Collectors.toList()).get(0));
    }

    @Override
    public Optional<VoteAnswer> findById(int id) {
        List<VoteAnswer> questions = template.query("SELECT * FROM vote_answer WHERE id = ?",
                (resultSet, i)-> new VoteAnswer(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("answer_id"),
                        resultSet.getInt("vote_value")),
                id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    @Override
    public void remove(VoteAnswer question) {
        template.update("DELETE FROM vote_answer WHERE id = ?", question.getId());
    }



    private int insert(VoteAnswer voteAnswer) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("vote_answer");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", voteAnswer.getUsesr_id());
        map.put("answer_id", voteAnswer.getAnswerId());
        map.put("vote_value", voteAnswer.getValue());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(VoteAnswer voteAnswer) {
        template.update("UPDATE vote_answer SET vote_value = ? WHERE id = ?",
                voteAnswer.getValue(), voteAnswer.getId());
    }

}
