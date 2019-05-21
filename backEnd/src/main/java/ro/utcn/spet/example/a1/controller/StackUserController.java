

package ro.utcn.spet.example.a1.controller;


        import lombok.RequiredArgsConstructor;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.web.bind.annotation.*;

        import ro.utcn.spet.example.a1.commandPattern.CommandFactory;
        import ro.utcn.spet.example.a1.entity.StackUser;
        import ro.utcn.spet.example.a1.service.StackUserManagementService;

        import java.util.List;

@RestController
@RequiredArgsConstructor
public class StackUserController {
    private final StackUserManagementService stackUserManagementService;
    private final PasswordEncoder passwordEncoder;
    private final CommandFactory commandFactory;

    @GetMapping("/users")
    public List<StackUser> readAll(){
        return stackUserManagementService.listStackUser();
    }

    @PostMapping("/login")
    public void login(@RequestBody StackUser user)
    {
        commandFactory.getLoginCommand().execute(user);
    }



}
