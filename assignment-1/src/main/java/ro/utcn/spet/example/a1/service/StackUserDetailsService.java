package ro.utcn.spet.example.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.repository.RepositoryFactory;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class StackUserDetailsService implements UserDetailsService {

    //private final StackUserRepository repository;
    private final RepositoryFactory repository;
    //cannot inject abstraction, only concrete
    //private final JdbcStackUserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //specify concrete by using factory
        StackUser stackUser = repository.createStackUserRepository().findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown StakUser!"));
        return new User(stackUser.getEmailAddress(), stackUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("Role_StackUser")));
        //here we could decide if the user is an admin or a regular user,la returnul de sus. Vezi min 30
    }


}
