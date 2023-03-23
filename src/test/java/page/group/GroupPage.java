package page.group;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import utils.MyGroupWrapper;
import utils.SuggestedGroupWrapper;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GroupPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement ACTUAL_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Актуально']");
    private static final SelenideElement OFFICIAL_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Официальные']");
    private static final SelenideElement NEW_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Новые']");
    private static final String ACTUAL_GROUP_MESSAGE = "Can't actual group button";
    private static final String OFFICIAL_GROUP_MESSAGE = "Can't official group button";
    private static final String NEW_GROUP_MESSAGE = "Can't new group button";
    private static final String GROUP_CARD_MESSAGE = "Some card is unvisible";
    private static final String MY_GROUP_CARD_MESSAGE = "My card is unvisible";
    private static final String COLLECTION_SIZE_MESSAGE = "Collection size shouldn't be empty";
    private ElementsCollection groupCards = $$x("//li[@class='scroll-slider_item mr-x']");
    private static final ElementsCollection GROUP_COLLECTION = $$x("//div[@data-l='groupCard,POPULAR_GROUPS.popularTop']");

    public GroupPage check() {
        isLoaded(ACTUAL_GROUP_TYPE_BUTTON, ACTUAL_GROUP_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(OFFICIAL_GROUP_TYPE_BUTTON, OFFICIAL_GROUP_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(NEW_GROUP_TYPE_BUTTON, NEW_GROUP_MESSAGE, TIME_OUT_IN_SECONDS);
        return this;
    }

    public GroupPage joinRandomGroup() {
        new SuggestedGroupWrapper(isLoaded(GROUP_COLLECTION, GROUP_CARD_MESSAGE, TIME_OUT_IN_SECONDS)
                .should(sizeNotEqual(0).because(COLLECTION_SIZE_MESSAGE))
                .first()).joinGroup();
        return this;
    }

    public String getNewGroupName() {
        return new SuggestedGroupWrapper(isLoaded(GROUP_COLLECTION, GROUP_CARD_MESSAGE, TIME_OUT_IN_SECONDS)
                .should(sizeNotEqual(0).because(COLLECTION_SIZE_MESSAGE))
                .first()).getGroupName();
    }

    public void refresh() {
        Selenide.refresh();
    }

    public boolean isGroupAddedToMyGroupsList(@NotNull final String groupName) {
        isLoaded(groupCards, MY_GROUP_CARD_MESSAGE, TIME_OUT_IN_SECONDS)
                .should(sizeNotEqual(0).because(COLLECTION_SIZE_MESSAGE));
        for (SelenideElement element: groupCards) {
            String currentMyGroupWrapper = new MyGroupWrapper(element).getGroupName();
            if (currentMyGroupWrapper.equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    public GroupPage deleteAllGroups() {
        isLoaded(groupCards, MY_GROUP_CARD_MESSAGE, TIME_OUT_IN_SECONDS)
                .should(sizeNotEqual(0).because(COLLECTION_SIZE_MESSAGE));
        int myGroupsSize = groupCards.size();
        for (int i = 0; i < myGroupsSize; i++) {
            new MyGroupWrapper(groupCards.get(0)).deleteGroup().check().exitFromGroup().goToGroupsPage();
        }
        return this;
    }

    public boolean areAllMyGroupsDeleted() {
        return isLoaded(groupCards, MY_GROUP_CARD_MESSAGE, TIME_OUT_IN_SECONDS).isEmpty();
    }
}
