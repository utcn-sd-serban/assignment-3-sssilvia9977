
package ro.utcn.spet.example.a1.service;

        import org.junit.Assert;
        import org.junit.Test;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.entity.Tag;
        import ro.utcn.spet.example.a1.entity.Tag_question;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class TagQuestionManagUnitTest {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createTagQuestionRepository().save(new Tag_question(1,2));
        factory.createTagQuestionRepository().save(new Tag_question(2,3));
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        TagQuestionManagementService service = new TagQuestionManagementService(factory);

        service.addTagQuestion (2, 4);

        // assert - find the tag "food"
        Assert.assertEquals(3, factory.createTagQuestionRepository().findAll().size());
        Assert.assertTrue(factory.createTagQuestionRepository().findQuestionTag (4).isEmpty());
    }


}
