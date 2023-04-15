package page.group;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GroupPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement ACTUAL_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Актуально']");
    private static final SelenideElement OFFICIAL_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Официальные']");
    private static final SelenideElement NEW_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Новые']");
    private static final ElementsCollection GROUP_CARDS = $$x("//li[@class='scroll-slider_item mr-x']");
    private static final ElementsCollection GROUP_COLLECTION = $$x("//div[@data-l='groupCard,POPULAR_GROUPS.popularTop']");

    public GroupPage() {
        check();
    }

    private void check() {
        isLoaded(ACTUAL_GROUP_TYPE_BUTTON, "Can't actual group button", TIME_OUT_IN_SECONDS);
        isLoaded(OFFICIAL_GROUP_TYPE_BUTTON, "Can't official group button", TIME_OUT_IN_SECONDS);
        isLoaded(NEW_GROUP_TYPE_BUTTON, "Can't new group button", TIME_OUT_IN_SECONDS);
    }

    public GroupPage joinRandomGroup() {
        areElementsVisible(GROUP_COLLECTION, "Some card is invisible")
                .shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"));
        new SuggestedGroupWrapper(GROUP_COLLECTION.first()).joinGroup();
        return this;
    }

    public String getNewGroupName() {
        areElementsVisible(GROUP_COLLECTION, "Some card is invisible")
                .shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"));
        return new SuggestedGroupWrapper(GROUP_COLLECTION.first()).getGroupName();
    }

    public GroupPage refresh() {
        Selenide.refresh();
        return new GroupPage();
    }

    public boolean isGroupAddedToMyGroupsList(@NotNull final String groupName) {
        return areElementsVisible(GROUP_CARDS, "My card is invisible")
                .shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"))
                .asDynamicIterable()
                .stream()
                .map(MyGroupWrapper::new)
                .anyMatch(currentCard -> currentCard.getGroupName().equals(groupName));
    }

    public GroupPage deleteAllGroups() {
        areElementsVisible(GROUP_CARDS, "My card is invisible")
                .shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"));
        int myGroupsSize = GROUP_CARDS.size();
        for (int i = 0; i < myGroupsSize; i++) {
            new MyGroupWrapper(GROUP_CARDS.get(0))
                    .deleteGroup()
                    .exitFromGroup()
                    .goToGroupsPage();
        }
        return this;
    }

    public boolean areAllMyGroupsDeleted() {
        return areElementsVisible(GROUP_CARDS, "My card is invisible").isEmpty();
    }

    private ElementsCollection areElementsVisible(@NotNull final ElementsCollection collection, @NotNull String msg) {
        return collection.shouldHave(size(collection.filter(visible).size()).because(msg));
    }
}
