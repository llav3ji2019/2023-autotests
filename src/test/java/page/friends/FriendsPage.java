package page.friends;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import page.chat.ChatPage;
import page.LoadableComponent;
import page.call.CallPage;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
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
    private static final String FRIEND_CARD_MESSAGE = "You have no friends";
    private static final String ALL_FRIEND_BUTTON_MESSAGE = "Can't find all friends button on the page";
    private static final String ONLINE_FRIENDS_BUTTON_MESSAGE = "Can't find online friends button on the page";
    private static final String REQUESTS_BUTTON_MESSAGE = "Can't find request button on the page";
    private static final String SUGGESTIONS_BUTTON_MESSAGE = "Can't find suggestion button on the page";
    private static final String CURRENT_FRIEND_CARD_MESSAGE = "Current friend card is not visible";

    public FriendsPage() {
        check();
    }

    private void check() {
        isLoaded(ALL_FRIEND_BUTTON, ALL_FRIEND_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(ONLINE_FRIENDS_BUTTON, ONLINE_FRIENDS_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(REQUESTS_BUTTON, REQUESTS_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(SUGGESTIONS_BUTTON, SUGGESTIONS_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS);
    }

    public ChatPage openChat(@NotNull final String name) {
        ElementsCollection allFriends = isLoaded($$(FRIEND_CARD), CURRENT_FRIEND_CARD_MESSAGE, TIME_OUT_IN_SECONDS)
                .should(sizeNotEqual(0).because(FRIEND_CARD_MESSAGE));
        for (SelenideElement friend : allFriends) {
            FriendWrapper currentCard = new FriendWrapper(friend);
            if (currentCard.getName().equals(name)) {
                return currentCard.openChatPage();
            }
        }
        return new FriendWrapper(allFriends.get(0)).openChatPage();
    }

    public CallPage startPhoneCall(@NotNull final String name) {
        ElementsCollection allFriends = isLoaded($$(FRIEND_CARD), CURRENT_FRIEND_CARD_MESSAGE, TIME_OUT_IN_SECONDS)
                .should(sizeNotEqual(0).because(FRIEND_CARD_MESSAGE));
        for (SelenideElement friend : allFriends) {
            FriendWrapper currentCard = new FriendWrapper(friend);
            if (currentCard.getName().equals(name)) {
                return currentCard.openCallPage();
            }
        }
        return new FriendWrapper(allFriends.get(0)).openCallPage();
    }
}
