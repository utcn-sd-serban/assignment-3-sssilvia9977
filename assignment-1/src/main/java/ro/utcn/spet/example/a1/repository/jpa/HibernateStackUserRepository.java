package ro.utcn.spet.example.a1.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.StackUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateStackUserRepository implements StackUserRepository {
	private final EntityManager entityManager;

	@Override
	public List<StackUser> findAll() {
		// the criteria builder is used to create a type-safe query; an alternative would have been
		// to write a JPQL query instead ("SELECT s FROM StackUser s") or to use named queries
		// https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StackUser> query = builder.createQuery(StackUser.class);
		query.select(query.from(StackUser.class));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public StackUser save(StackUser stackUser) {
		if (stackUser.getId() == null) {
			entityManager.persist(stackUser);
			return stackUser;
		} else {
			return entityManager.merge(stackUser);
		}
	}

	@Override
	public void remove(StackUser stackUser) {
		entityManager.remove(stackUser);
	}

	@Override
	public Optional<StackUser> findById(int id) {
		return Optional.ofNullable(entityManager.find(StackUser.class, id));
	}

	@Override
	public Optional<StackUser> findByEmail(String firstname) {
		return Optional.empty();
	}

	@Override
	public Optional<StackUser> findAuthentication(String firstname, String lastname, String password)
	{
		return Optional.empty();
		//de implementat
	}
}
