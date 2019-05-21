package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Tag save(Tag tag);

    List<Tag> findAll();

    Optional<Tag> findTagById(int id);

    Optional<Tag> findTagName(String name);


}
