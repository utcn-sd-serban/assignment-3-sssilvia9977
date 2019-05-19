package ro.utcn.spet.example.a1.repository.memory;

import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.StackUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryStackUserRepository implements StackUserRepository {
	// we want to be thread-safe, because this class will be a singleton (just one instance)
	// in order to not use synchronized methods, we use thread-safe classes (ConcurrentHashMap and AtomicInteger)
	private final Map<Integer, StackUser> data = new ConcurrentHashMap<>();
	private final AtomicInteger currentId = new AtomicInteger(0);

	@Override
	public StackUser save(StackUser stackUser) {
		if (stackUser.getId() == null) {
			stackUser.setId(currentId.incrementAndGet());
		}
		data.put(stackUser.getId(), stackUser);
		return stackUser;
	}

	@Override
	public void remove(StackUser stackUser) {
		data.remove(stackUser.getId());
	}

	@Override
	public Optional<StackUser> findById(int id) {
		return Optional.ofNullable(data.get(id));
	}

	@Override
	public Optional<StackUser> findByEmail(String firstname) {
		return Optional.empty();
	}

	@Override
	public Optional<StackUser> findAuthentication(String firstname, String lastname, String password) {
		return data.values().stream().filter((StackUser user) ->
				user.getFirstName().equals(firstname) &&
						user.getLastName().equals(lastname) &&
						user.getPassword().equals(password)).findFirst();
	}

	@Override
	public List<StackUser> findAll() {
		return new ArrayList<>(data.values());
	}
}
