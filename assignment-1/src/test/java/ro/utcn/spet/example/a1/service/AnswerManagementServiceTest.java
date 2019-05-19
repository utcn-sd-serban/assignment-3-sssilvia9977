
package ro.utcn.spet.example.a1.service;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.spet.example.a1.entity.Answer;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.exception.AnswerNotFoundException;
import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

    public class AnswerManagementServiceTest {

        private static RepositoryFactory createMockedFactory() {
            RepositoryFactory factory = new InMemoryRepositoryFactory();
            factory.createAnswerRepository().save(new Answer( 1, 1, "A.EA"));
            factory.createAnswerRepository().save(new Answer( 2, 1, "B.EA"));
            return factory;
        }

        @Test
        public void testRemoveWorksWithExistingId() {
            // arrange - create a mocked factory and a service backed up by this factory
            RepositoryFactory factory = createMockedFactory();
            AnswerManagementService service = new AnswerManagementService(factory);


            service.remove(1);


            Assert.assertEquals(1, factory.createAnswerRepository().findAll().size());
            Assert.assertTrue(factory.createAnswerRepository().findById(2).isPresent());
        }

        @Test(expected = AnswerNotFoundException.class)
        public void testRemoveThrowsWithNotExistingId() {
            // arrange - create a mocked factory and a service backed up by this factory
            RepositoryFactory factory = createMockedFactory();
            AnswerManagementService service = new AnswerManagementService(factory);

            // act - remove a student with a non-existent ID
            service.remove(999);

            // no assert, we expect an exception (see the @Test annotation)
        }

    }
