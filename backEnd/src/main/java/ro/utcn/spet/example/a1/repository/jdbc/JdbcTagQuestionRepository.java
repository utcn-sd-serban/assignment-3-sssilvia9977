package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.Tag_question;
import ro.utcn.spet.example.a1.repository.TagQuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JdbcTagQuestionRepository implements TagQuestionRepository {

    private final JdbcTemplate template;

    @Override
    public Tag_question save(Tag_question tg) {
        if (tg.getId() == null) {
            tg.setId(insert(tg));
        } else {
            update(tg);
        }
        return tg;
    }

    @Override
    public List<Tag_question> findAll() {
        return template.query("SELECT * FROM tag_question", (resultSet, i) -> new Tag_question(resultSet.getInt("id"),
                resultSet.getInt("question_id"), resultSet.getInt("tag_id")));
    }

    @Override
    public List<Tag_question> findQuestionTag(Integer qid) {
        return findAll().stream().filter((Tag_question t) -> t.getTagId().equals(qid)).collect(Collectors.toList());
    }


    public void remove(Tag_question tag_question) {
        template.update("DELETE FROM tag_question WHERE id = ?", tag_question.getId());
    }


    private int insert(Tag_question tag_question){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag_question");
        insert.usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("question_id", tag_question.getQuestionId());
        data.put("tag_id", tag_question.getTagId());

        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Tag_question tag_question){
        template.update("UPDATE tag_question set question_id = ?, tag_id = ? where id = ?", tag_question.getQuestionId(), tag_question.getTagId(), tag_question.getId());
    }



}
