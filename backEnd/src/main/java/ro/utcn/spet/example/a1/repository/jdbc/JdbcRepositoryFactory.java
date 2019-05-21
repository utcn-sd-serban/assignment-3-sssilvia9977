package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {
	private final JdbcTemplate template;

	@Override
	public StackUserRepository createStackUserRepository() {
		return new JdbcStackUserRepository(template);
	}

	@Override
	public QuestionRepository createQuestionRepository() {
		return new JdbcQuestionRepository(template);
	}

	@Override
	public TagRepository createTagRepository() { return new JdbcTagRepository(template); }

	@Override
	public TagQuestionRepository createTagQuestionRepository() {
		return new JdbcTagQuestionRepository(template);
	}

	@Override
	public AnswerRepository createAnswerRepository() {
		return new JdbcAnswerRwpository(template);
	}

	@Override
	public VoteAnswerRepository createVoteAnswerRepository() {
		return new JdbcVoteAnswerRepository(template);
	}

	@Override
	public VoteQuestionRepository createVoteQuestionRepository() {
		return new JdbcVoteQuestionRepository(template);
	}
}
