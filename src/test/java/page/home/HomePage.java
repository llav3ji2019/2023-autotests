package page.home;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement uniqueBlock = $x("//div[@class='navigation']/div/div/a/div");

    public boolean check(final String name) {
        return doCheck(name);
    }

    private boolean doCheck(final String name) {
        return uniqueBlock.text().equals(name);
    }
}
