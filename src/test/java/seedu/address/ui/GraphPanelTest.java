package seedu.address.ui;

import static guitests.guihandles.WebViewUtil.waitUntilBrowserLoaded;
import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.ui.GraphPanel.DEFAULT_PAGE;
import static seedu.address.ui.UiPart.FXML_FILE_FOLDER;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.GraphPanelHandle;
import seedu.address.MainApp;
import seedu.address.commons.events.ui.StudentPanelSelectionChangedEvent;

public class GraphPanelTest extends GuiUnitTest {
    private StudentPanelSelectionChangedEvent selectionChangedEventStub;

    private GraphPanel graphPanel;
    private GraphPanelHandle graphPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStub = new StudentPanelSelectionChangedEvent(ALICE);

        guiRobot.interact(() -> graphPanel = new GraphPanel());
        uiPartRule.setUiPart(graphPanel);

        graphPanelHandle = new GraphPanelHandle(graphPanel.getRoot());
    }

    @Test
    public void display() throws Exception {
        // default web page
        URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        assertEquals(expectedDefaultPageUrl, graphPanelHandle.getLoadedUrl());

        // associated web page of a person
        postNow(selectionChangedEventStub);
        URL expectedPersonUrl = new URL(GraphPanel.SEARCH_PAGE_URL + ALICE.getName().fullName.replaceAll(" ", "%20"));

        waitUntilBrowserLoaded(graphPanelHandle);
        assertEquals(expectedPersonUrl, graphPanelHandle.getLoadedUrl());
    }
}
