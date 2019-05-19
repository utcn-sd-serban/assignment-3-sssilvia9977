package ro.utcn.spet.example.a1.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "Memory")
public interface RepositoryFactory {

	StackUserRepository createStackUserRepository();

	QuestionRepository createQuestionRepository();

	TagRepository createTagRepository();

	TagQuestionRepository createTagQuestionRepository();

	AnswerRepository createAnswerRepository();

	VoteAnswerRepository createVoteAnswerRepository();

	VoteQuestionRepository createVoteQuestionRepository();

}
