package page.group;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class GroupPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement ACTUAL_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Актуально']");
    private static final SelenideElement OFFICIAL_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Официальные']");
    private static final SelenideElement NEW_GROUP_TYPE_BUTTON = $x("//a[@aria-label='Новые']");
    private final ElementsCollection groupCards = $$x("//li[@class='scroll-slider_item mr-x']");
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
        new SuggestedGroupWrapper(isLoaded(GROUP_COLLECTION, "Some card is invisible", TIME_OUT_IN_SECONDS)
                .shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"))
                .first()).joinGroup();
        return this;
    }

    public String getNewGroupName() {
        return new SuggestedGroupWrapper(isLoaded(GROUP_COLLECTION, "Some card is invisible", TIME_OUT_IN_SECONDS)
                .shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"))
                .first()).getGroupName();
    }

    public GroupPage refresh() {
        Selenide.refresh();
        return new GroupPage();
    }

    public boolean isGroupAddedToMyGroupsList(@NotNull final String groupName) {
        for (SelenideElement element : groupCards) {
            element.shouldBe(visible.because("My card is invisible"));
        }
        groupCards.shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"));
        for (SelenideElement element: groupCards) {
            String currentMyGroupWrapper = new MyGroupWrapper(element).getGroupName();
            if (currentMyGroupWrapper.equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    public GroupPage deleteAllGroups() {
        for (SelenideElement element : groupCards) {
            element.shouldBe(visible.because("My card is invisible"));
        }
        groupCards.shouldHave(sizeNotEqual(0).because("Collection size shouldn't be empty"));
        int myGroupsSize = groupCards.size();
        for (int i = 0; i < myGroupsSize; i++) {
            new MyGroupWrapper(groupCards.get(0))
                    .deleteGroup()
                    .exitFromGroup()
                    .goToGroupsPage();
        }
        return this;
    }

    public boolean areAllMyGroupsDeleted() {
        for (SelenideElement element : groupCards) {
            element.shouldBe(visible.because("My card is invisible"));
        }
        return groupCards.isEmpty();
    }
}
