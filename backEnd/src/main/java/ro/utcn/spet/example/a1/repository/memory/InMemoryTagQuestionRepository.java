
package ro.utcn.spet.example.a1.repository.memory;

        import ro.utcn.spet.example.a1.entity.Question;
        import ro.utcn.spet.example.a1.entity.Tag;
        import ro.utcn.spet.example.a1.entity.Tag_question;
        import ro.utcn.spet.example.a1.repository.TagQuestionRepository;
        import ro.utcn.spet.example.a1.repository.TagRepository;

        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.List;
        import java.util.Map;
        import java.util.concurrent.ConcurrentHashMap;
        import java.util.concurrent.atomic.AtomicInteger;
        import java.util.stream.Collectors;

public class InMemoryTagQuestionRepository implements TagQuestionRepository {

    private final Map<Integer, Tag_question> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Tag_question save(Tag_question t) {
        if (t.getId() == null) {
            t.setId(currentId.incrementAndGet());
        }
        data.put(t.getId(), t);
        return t;
    }

    @Override
    public List<Tag_question> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Tag_question> findQuestionTag(Integer tagId){
        return data.values().stream().filter((Tag_question t) -> t.getTagId().equals(tagId)).collect(Collectors.toList());
    }

}
