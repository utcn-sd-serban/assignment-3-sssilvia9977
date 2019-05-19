package ro.utcn.spet.example.a1.commandPattern;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.service.StackUserManagementService;

@Component
@RequiredArgsConstructor
public class CommandFactoryImplementation implements CommandFactory{
    private final StackUserManagementService userManagementService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Command getLoginCommand() {
        return new LoginCommand(userManagementService, passwordEncoder);
    }
}
