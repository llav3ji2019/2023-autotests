package page;

import java.time.Duration;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public interface LoadableComponent {
    default SelenideElement isLoaded(SelenideElement element, String message, long timeOutInSeconds) {
        return element.should(visible.because(message), Duration.ofSeconds(timeOutInSeconds));
    }
}
