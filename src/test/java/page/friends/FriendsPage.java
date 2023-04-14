package page.friends;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import page.chat.ChatPage;
import page.LoadableComponent;
import page.call.CallPage;

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

    public ChatPage openChat(@NotNull final String name) {
        ElementsCollection allFriends = $$(FRIEND_CARD);
        for (SelenideElement friend : allFriends) {
            friend.shouldBe(visible.because("Current friend card is not visible"));
        }
        allFriends.shouldHave(sizeNotEqual(0).because("You have no friends"));
        for (SelenideElement friend : allFriends) {
            FriendWrapper currentCard = new FriendWrapper(friend);
            if (currentCard.getName().equals(name)) {
                return currentCard.openChatPage();
            }
        }
        return new FriendWrapper(allFriends.get(0)).openChatPage();
    }

    public CallPage startPhoneCall(@NotNull final String name) {
        ElementsCollection allFriends = $$(FRIEND_CARD);
        for (SelenideElement friend : allFriends) {
            friend.shouldBe(visible.because("Current friend card is not visible"));
        }
        allFriends.shouldHave(sizeNotEqual(0).because("You have no friends"));
        for (SelenideElement friend : allFriends) {
            FriendWrapper currentCard = new FriendWrapper(friend);
            if (currentCard.getName().equals(name)) {
                return currentCard.openCallPage();
            }
        }
        return new FriendWrapper(allFriends.get(0)).openCallPage();
    }
}
