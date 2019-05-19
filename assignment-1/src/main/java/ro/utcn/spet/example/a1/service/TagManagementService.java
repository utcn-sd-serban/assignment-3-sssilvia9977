package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.TagRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagManagementService {

   private final RepositoryFactory repositoryFactory;

    @Transactional
    public void addTag(String name){
        if(repositoryFactory.createTagRepository().findTagName(name).isPresent()) return;
        else repositoryFactory.createTagRepository().save(new Tag(name));
    }

    @Transactional
    public List<Tag> findAll() {
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional
    public Optional<Tag> findTag(String name){
        TagRepository repository = repositoryFactory.createTagRepository();
        return repository.findTagName( name);
    }


}
