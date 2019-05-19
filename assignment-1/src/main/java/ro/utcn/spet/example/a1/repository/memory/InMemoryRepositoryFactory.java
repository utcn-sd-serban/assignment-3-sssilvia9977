package ro.utcn.spet.example.a1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.repository.*;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {
	private final InMemoryStackUserRepository repository = new InMemoryStackUserRepository();
	private final InMemoryQuestionRepository repositoryQuestion = new InMemoryQuestionRepository();
	private final InMemoryTagRepository repositoryTag = new InMemoryTagRepository();
	private final InMemoryTagQuestionRepository repositoryTQ = new InMemoryTagQuestionRepository();
	private final InMemoryAnswerRepository repositoryAns = new InMemoryAnswerRepository();
	private final InMemoryVoteQuestionRepository repositoryVoteQuestion= new InMemoryVoteQuestionRepository();
	private final InMemoryVoteAnswerRepository repositoryVoteAns = new InMemoryVoteAnswerRepository();

	@Override
	public StackUserRepository createStackUserRepository() {
		return repository;
	}

	@Override
	public QuestionRepository createQuestionRepository() {
		return repositoryQuestion;
	}

	@Override
	public TagQuestionRepository createTagQuestionRepository() {
		return repositoryTQ;
	}

	@Override
	public AnswerRepository createAnswerRepository() {
		return repositoryAns;
	}

	@Override
	public VoteAnswerRepository createVoteAnswerRepository() {
		return repositoryVoteAns;
	}

	@Override
	public VoteQuestionRepository createVoteQuestionRepository() {
		return repositoryVoteQuestion;
	}

	@Override
	public TagRepository createTagRepository(){ return repositoryTag; }




}
