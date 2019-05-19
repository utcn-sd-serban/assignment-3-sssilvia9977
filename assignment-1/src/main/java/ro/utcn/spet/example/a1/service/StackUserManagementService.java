package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.StackUserRepository;

import java.util.List;
import java.util.Optional;

// The @Service is a specialized @Component (https://www.baeldung.com/spring-component-repository-service)
@Service
@RequiredArgsConstructor
public class StackUserManagementService {
	private final RepositoryFactory repositoryFactory;

	// Transactional methods ensure that the code contained inside is run in a transaction, which is committed
	// when the methods returns and is rolled-back when an exception is thrown
	// http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
	@Transactional
	public List<StackUser> listStackUser() {
		return repositoryFactory.createStackUserRepository().findAll();
	}

	public Optional<StackUser> findStackUser(String firstname, String lastname, String password){
		StackUserRepository repository = repositoryFactory.createStackUserRepository();
		return repository.findAuthentication( firstname,  lastname,  password);
	}


	public Optional<StackUser> findUserByEmail(String email)
	{
		return repositoryFactory.createStackUserRepository().findByEmail(email);
	}

	@Transactional
	public StackUser addStackUser(String firstName, String lastName) {
		return repositoryFactory.createStackUserRepository().save(new StackUser(firstName, lastName, null, firstName + lastName));
	}

	@Transactional
	public void updateEmailAddress(int id, String emailAddress) {
		StackUserRepository repository = repositoryFactory.createStackUserRepository();
		StackUser stackUser = repository.findById(id).orElseThrow(StackUserNotFoundException::new);
		stackUser.setEmailAddress(emailAddress);
		repository.save(stackUser);
	}


	@Transactional
	public void removeStudent(int id) {
		StackUserRepository repository = repositoryFactory.createStackUserRepository();
		StackUser stackUser = repository.findById(id).orElseThrow(StackUserNotFoundException::new);
		repository.remove(stackUser);
	}
}
