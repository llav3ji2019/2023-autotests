package page.home;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import user.User;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private final SelenideElement uniqueBlock = $x("//div[@class='navigation']/div/div/a/div");

    public boolean checkPage(@NotNull final String name) {
        return doCheckPage(name);
    }

    public boolean checkPage(@NotNull final User user) {
        return doCheckPage(user.getFullName());
    }

    private boolean doCheckPage(@NotNull final String name) {
        return isLoaded(uniqueBlock, TIME_OUT_IN_SECONDS)
                .text()
                .equals(name);
    }
}
