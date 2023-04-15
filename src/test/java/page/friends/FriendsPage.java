package page.friends;

import java.util.Optional;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WrapsElement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import page.chat.ChatPage;
import page.LoadableComponent;
import page.call.CallPage;
import utils.Exceptions.PersonNotFound;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class FriendsPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By FRIEND_CARD = byXpath("//div[@class='user-grid-card __s']");
    private static final SelenideElement ALL_FRIEND_BUTTON = $x("//a[@data-l='t,userFriend' and contains(@hrefattrs,'ViewAll')]");
    private static final SelenideElement ONLINE_FRIENDS_BUTTON = $x("//a[@data-l='t,userFriend' and contains(@hrefattrs,'ViewFriendsOnline')]");
    private static final SelenideElement REQUESTS_BUTTON = $x("//a[@data-l='t,userFriendRequest']");
    private static final SelenideElement SUGGESTIONS_BUTTON = $x("//a[@data-l='t,userFriendSuggest']");

    public FriendsPage() {
        check();
    }

    private void check() {
        isLoaded(ALL_FRIEND_BUTTON, "Can't find all friends button on the page", TIME_OUT_IN_SECONDS);
        isLoaded(ONLINE_FRIENDS_BUTTON, "Can't find online friends button on the page", TIME_OUT_IN_SECONDS);
        isLoaded(REQUESTS_BUTTON, "Can't find request button on the page", TIME_OUT_IN_SECONDS);
        isLoaded(SUGGESTIONS_BUTTON, "Can't find suggestion button on the page", TIME_OUT_IN_SECONDS);
    }

    public ChatPage openChat(@NotNull final String name) throws PersonNotFound {
        ElementsCollection allFriends = areElementsVisible($$(FRIEND_CARD), "Current friend card is not visible")
                .shouldHave(sizeNotEqual(0).because("You have no friends"));
        return findFirstName(allFriends, name)
                .map(FriendWrapper::openChatPage)
                .orElseThrow(PersonNotFound::new);
    }

    public CallPage startPhoneCall(@NotNull final String name) throws PersonNotFound {
        ElementsCollection allFriends = areElementsVisible($$(FRIEND_CARD), "Current friend card is not visible")
                .shouldHave(sizeNotEqual(0).because("You have no friends"));
        return findFirstName(allFriends, name)
                .map(FriendWrapper::openCallPage)
                .orElseThrow(PersonNotFound::new);
    }

    private Optional<FriendWrapper> findFirstName(@NotNull final ElementsCollection collection , @NotNull final String name) {
        return collection
                .asDynamicIterable()
                .stream()
                .map(FriendWrapper::new)
                .filter(currentCard -> currentCard.getName().equals(name))
                .findFirst();
    }

    private ElementsCollection areElementsVisible(@NotNull final ElementsCollection collection, @NotNull final String msg) {
        return collection.shouldHave(size(collection.filter(visible).size()).because(msg));
    }
}
