package ro.utcn.spet.example.a1.commandPattern;

import ro.utcn.spet.example.a1.entity.StackUser;

public interface Command {

    public void execute(StackUser user);
}
