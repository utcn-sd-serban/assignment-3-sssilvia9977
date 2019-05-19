package ro.utcn.spet.example.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.StackUserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcStackUserRepository implements StackUserRepository {
	// The Jdbc template is a helper class for doing JDBC operations (usually "templates" simplify common tasks)
	// check out https://spring.io/guides/gs/relational-data-access/
	private final JdbcTemplate template;


	@Override
	public Optional<StackUser> findByEmail(String email)
	{
		return findAll().stream().filter((StackUser user) ->
				user.getEmailAddress().equals(email)).findFirst();

	}

	@Override
	public List<StackUser> findAll() {
		return template.query("SELECT * FROM stack_user", new StackUserMapper());
	}

	@Override
	public StackUser save(StackUser stackUser) {
		if (stackUser.getId() == null) {
			stackUser.setId(insert(stackUser));
		} else {
			update(stackUser);
		}
		return stackUser;
	}

	@Override
	public void remove(StackUser stackUser) {
		template.update("DELETE FROM stack_user WHERE id = ?", stackUser.getId());
	}

	@Override
	public Optional<StackUser> findById(int id) {
		List<StackUser> stackUsers = template.query("SELECT * FROM stack_user WHERE id = ?", new StackUserMapper(), id);
		return stackUsers.isEmpty() ? Optional.empty() : Optional.of(stackUsers.get(0));
	}

	public Optional<StackUser> findAuthentication(String firstname, String lastname, String password)
	{
		return findAll().stream().filter((StackUser user) ->
				user.getFirstName().equals(firstname) &&
						user.getLastName().equals(lastname) &&
						user.getPassword().equals(password)).findFirst();

	}

	private int insert(StackUser stackUser) {
		// we use the SimpleJdbcInsert to easily retrieve the generated ID
		SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
		insert.setTableName("stack_user");
		insert.usingGeneratedKeyColumns("id");
		Map<String, Object> map = new HashMap<>();
		map.put("first_name", stackUser.getFirstName());
		map.put("last_name", stackUser.getLastName());
		map.put("email_address", stackUser.getEmailAddress());
		map.put("password", stackUser.getPassword());
		return insert.executeAndReturnKey(map).intValue();
	}

	private void update(StackUser stackUser) {
		template.update("UPDATE stack_user SET first_name = ?, last_name = ?, email_address = ?, passwordd = ? WHERE id = ?",
				stackUser.getFirstName(), stackUser.getLastName(), stackUser.getEmailAddress(), stackUser.getId(), stackUser.getPassword());
	}
}
