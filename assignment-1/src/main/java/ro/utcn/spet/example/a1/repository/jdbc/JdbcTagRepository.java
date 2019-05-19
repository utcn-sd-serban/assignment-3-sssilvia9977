package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            tag.setId(insert(tag));
        } else {
            update(tag);
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM theTag", (resultSet, i) -> new Tag(resultSet.getInt("id"),
                resultSet.getString("tag_name")));
    }


    public Optional<Tag> findTagById(int id) {
        List<Tag> tag = template.query("SELECT * FROM theTag WHERE id = ?",  (resultSet, i)->  new Tag(resultSet.getInt("id"),
                resultSet.getString("tag_name")), id);
        return tag.isEmpty() ? Optional.empty() : Optional.of(tag.get(0));
    }


    @Override
    public Optional<Tag> findTagName(String name) {
        List<Tag> tag= template.query("SELECT * FROM theTag WHERE tag_name = ?",
                (resultSet, i)-> new Tag(resultSet.getInt("id"),
                        resultSet.getString("tag_name")), name);
        return tag.isEmpty() ? Optional.empty() : Optional.of(tag.get(0));
    }

    public void remove(Tag tag) {
        template.update("DELETE FROM theTag WHERE id = ?", tag.getId());
    }

    private int insert(Tag tag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("theTag");
        insert.usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("tag_name", tag.getName());

        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Tag tag){
        template.update("UPDATE theTag set tag_name = ? where id = ?", tag.getName(),tag.getId());
    }

}

