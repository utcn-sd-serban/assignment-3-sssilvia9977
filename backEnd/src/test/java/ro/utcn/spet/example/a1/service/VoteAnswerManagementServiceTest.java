
package ro.utcn.spet.example.a1.service;

        import org.junit.Assert;
        import org.junit.Test;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.entity.VoteAnswer;
        import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
        import ro.utcn.spet.example.a1.exception.VoteAnswerNotFoundException;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class VoteAnswerManagementServiceTest {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createVoteAnswerRepository().save(new VoteAnswer(1, 1,-1));

        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        VoteAnswerManagementService service = new VoteAnswerManagementService(factory);

        // act - remove a student with a well-known ID
        service.updateVoteAnswer(1, 0);

        // assert - expect that the student was removed from the repository and the other student is still there
        Assert.assertEquals(0, factory.createVoteAnswerRepository().findById(1).get().getValue());

    }

    @Test(expected = VoteAnswerNotFoundException.class)
    public void testRemoveThrowsWithNotExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        VoteAnswerManagementService service = new VoteAnswerManagementService(factory);

        // act - remove a student with a non-existent ID
        service.updateVoteAnswer(999, 0);

        // no assert, we expect an exception (see the @Test annotation)
    }

}
