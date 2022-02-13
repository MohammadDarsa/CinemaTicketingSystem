package cinematicketingsystem.modules.ticketselector;


import cinematicketingsystem.models.ticket.Ticket;
import cinematicketingsystem.modules.ticketcard.TicketCardController;
import cinematicketingsystem.utils.Attributes;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import cinematicketingsystem.utils.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TicketSelectorController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private HBox topNav;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    private DBManager dbManager;
    private SceneManager sceneManager;
    private UserManager userManager;

    private List<Ticket> ticketList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flowPane.setFocusTraversable(true);
        dbManager = DBManager.getInstance();
        userManager = UserManager.getInstance();
        Attributes attributes = new Attributes("ticket");
        attributes.addAttribute("customer_id", userManager.getCustomer().getId().toString());
        ticketList = dbManager.selectAll(Ticket.class, attributes);
        ticketList = ticketList.stream().peek(this::addTicket).collect(Collectors.toList());
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    @SneakyThrows
    private void addTicket(Ticket ticket) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/ticketCard.fxml"));
        VBox anchorPane = fxmlLoader.load();
        TicketCardController ticketCardController = fxmlLoader.getController();
        ticketCardController.setData(ticket);
        flowPane.getChildren().add(anchorPane);
    }
}
