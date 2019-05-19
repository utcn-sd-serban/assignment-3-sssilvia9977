package ro.utcn.spet.example.a1.commandPattern;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.utcn.spet.example.a1.entity.StackUser;
import ro.utcn.spet.example.a1.exception.StackUserNotFoundException;
import ro.utcn.spet.example.a1.service.StackUserManagementService;

@RequiredArgsConstructor
public class LoginCommand implements Command {


    private final StackUserManagementService userManagementService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(StackUser user) {
        StackUser currentUser = userManagementService.findUserByEmail(user.getEmailAddress()).get();

        if(!passwordEncoder.matches(user.getPassword(), currentUser.getPassword()))
        {
            throw new StackUserNotFoundException();
        }
    }
}
