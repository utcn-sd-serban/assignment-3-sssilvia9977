

package ro.utcn.spet.example.a1.service;

        import org.junit.Assert;
        import org.junit.Test;
        import ro.utcn.spet.example.a1.entity.Question;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.exception.QuestionNotFoundException;
        import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

        import java.util.Date;

public class QuestionManagementServiceUnitTest {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createQuestionRepository().save(new Question(1, "Hello", "World?", new Date()));
        factory.createQuestionRepository().save(new Question(1, "No", "Please leave", new Date()));
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        QuestionManagementService service = new QuestionManagementService(factory);

        service.remove("Hello");

        // assert - expect that the student was removed from the repository and the other student is still there
        Assert.assertEquals(1, factory.createQuestionRepository().findAll().size());
        Assert.assertTrue(factory.createQuestionRepository().findById(2).isPresent());
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testRemoveThrowsWithNotExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        QuestionManagementService service = new QuestionManagementService(factory);

        // act - remove a student with a non-existent ID
        service.remove("Despasito");

        // no assert, we expect an exception (see the @Test annotation)
    }

}
