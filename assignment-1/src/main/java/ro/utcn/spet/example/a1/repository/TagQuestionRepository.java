package ro.utcn.spet.example.a1.repository;

import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.Tag_question;

import java.util.List;

public interface TagQuestionRepository {

    Tag_question save(Tag_question tg);

    List<Tag_question> findAll();

    List<Tag_question> findQuestionTag(Integer qid);

}
