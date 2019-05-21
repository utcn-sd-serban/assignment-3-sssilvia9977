package ro.utcn.spet.example.a1.service;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class StackUserManagementServiceUnitTests {

	private static RepositoryFactory createMockedFactory() {
		RepositoryFactory factory = new InMemoryRepositoryFactory();
		factory.createStackUserRepository().save(new StackUser(1, "A.FN", "A.LN", "A.EA", "A.FN"));
		factory.createStackUserRepository().save(new StackUser(2, "B.FN", "B.LN", "B.EA", "B.FN"));
		return factory;
	}

	@Test
	public void testRemoveWorksWithExistingId() {
		// arrange - create a mocked factory and a service backed up by this factory
		RepositoryFactory factory = createMockedFactory();
		StackUserManagementService service = new StackUserManagementService(factory);

		// act - remove a student with a well-known ID
		service.removeStudent(1);

		// assert - expect that the student was removed from the repository and the other student is still there
		Assert.assertEquals(1, factory.createStackUserRepository().findAll().size());
		Assert.assertTrue(factory.createStackUserRepository().findById(2).isPresent());
	}

	@Test(expected = StackUserNotFoundException.class)
	public void testRemoveThrowsWithNotExistingId() {
		// arrange - create a mocked factory and a service backed up by this factory
		RepositoryFactory factory = createMockedFactory();
		StackUserManagementService service = new StackUserManagementService(factory);

		// act - remove a student with a non-existent ID
		service.removeStudent(999);

		// no assert, we expect an exception (see the @Test annotation)
	}

}
