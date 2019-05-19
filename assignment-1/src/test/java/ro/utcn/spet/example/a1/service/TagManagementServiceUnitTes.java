

package ro.utcn.spet.example.a1.service;

        import org.junit.Assert;
        import org.junit.Test;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.entity.Tag;
        import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
        import ro.utcn.spet.example.a1.exception.TagNotFoundException;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class TagManagementServiceUnitTes {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createTagRepository().save(new Tag("food"));
        factory.createTagRepository().save(new Tag("books"));
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        TagManagementService service = new TagManagementService(factory);

        service.addTag("cats");

        // assert - find the tag "food"
        Assert.assertEquals(3, factory.createTagRepository().findAll().size());
        Assert.assertTrue(factory.createTagRepository().findTagName("cats").isPresent());
    }


}
