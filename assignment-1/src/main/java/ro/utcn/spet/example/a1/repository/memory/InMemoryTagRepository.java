package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTagRepository implements TagRepository {

    private final Map<Integer, Tag> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            tag.setId(currentId.incrementAndGet());
        }
        data.put(tag.getId(), tag);
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Tag> findTagById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Tag> findTagName(String name) {
        return data.values().stream().filter((Tag t) ->
                t.getName().equals(name)).findFirst();
    }




}
