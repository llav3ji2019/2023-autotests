package user;

import java.util.ArrayList;
import java.util.List;

import user.User;
import user.UserHandler;

public class UserContainer {
    private final List<User> users = new ArrayList<>();
    private static int counter;

    public UserContainer() {
        users.add(new UserHandler()
                .setLogin("botS23AT9")
                .setName("botS23AT9 botS23AT9")
                .setPassword("autotests2023")
                .build());

        users.add(new UserHandler()
                .setLogin("botS23AT28")
                .setName("botS23AT28 botS23AT28")
                .setPassword("autotests2023")
                .build());

        users.add(new UserHandler()
                .setLogin("botS23AT27")
                .setName("botS23AT27 botS23AT27")
                .setPassword("autotests2023")
                .build());
    }

    public User getUniqueUser() {
        counter %= 3;
        return users.get(counter++);
    }
}
