package page;

import java.time.Duration;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public interface LoadableComponent {
    default SelenideElement isLoaded(SelenideElement element, long timeOutInSeconds) {
        element.should(exist, Duration.ofSeconds(timeOutInSeconds));
        element.should(visible, Duration.ofSeconds(timeOutInSeconds));
        return element;
    }
}
