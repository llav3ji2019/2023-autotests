package page.home;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.SelenideElement;

import user.User;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement uniqueBlock = $x("//div[@class='navigation']/div/div/a/div");

    public boolean isLoaded(@NotNull final String name) {
        return doIsLoaded(name);
    }

    public boolean isLoaded(@NotNull final User user) {
        return isLoaded(user.getFullName());
    }

    private boolean doIsLoaded(@NotNull final String name) {
        return uniqueBlock.text().equals(name);
    }
}
