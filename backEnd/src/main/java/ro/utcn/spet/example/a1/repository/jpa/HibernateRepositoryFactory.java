package ro.utcn.spet.example.a1.repository.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.repository.*;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JPA")
public class HibernateRepositoryFactory implements RepositoryFactory {
	private final EntityManager entityManager;

	@Override
	public StackUserRepository createStackUserRepository() {
		return new HibernateStackUserRepository(entityManager);
	}

	@Override
	public QuestionRepository createQuestionRepository() {
		return null;
	}

	@Override
	public TagRepository createTagRepository() {
		return null;
	}

	@Override
	public TagQuestionRepository createTagQuestionRepository() {
		return null;
	}

	@Override
	public AnswerRepository createAnswerRepository() {
		return null;
	}

	@Override
	public VoteAnswerRepository createVoteAnswerRepository() {
		return null;
	}

	@Override
	public VoteQuestionRepository createVoteQuestionRepository() {
		return null;
	}
}
