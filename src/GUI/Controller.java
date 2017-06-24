package GUI;

import Logic.Cheque;
import Logic.Entry;
import Logic.EntryList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    @FXML
    private Button openFileButton;
    @FXML
    private TableView<Entry> tableTransactions;
    @FXML
    private TableColumn<Entry, Long> idColumn;
    @FXML
    private TableColumn<Entry, Date> dateColumn;
    @FXML
    private TableColumn<Entry, Date> timeColumn;
    @FXML
    private TableColumn<Entry, Long> cashRegColumn;
    @FXML
    private TableColumn<Entry, Long> chequeIdColumn;
    @FXML
    private TableColumn<Entry, Long> transTypeColumn;
    @FXML
    private TableColumn<Entry, Long> cashierIdColumn;
    @FXML
    private TableColumn<Entry, Float> totalColumn;
    @FXML
    private TableColumn<Entry, Long> discountCardColumn;

    @FXML
    private TableView<Cheque> tableCheques;
    @FXML
    private TableColumn<Entry,Long> chequeColumn;


    private EntryList entryList;

    @FXML
    public void initialize() {

        tableTransactions.setRowFactory( Entry ->{
            TableRow<Entry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                try {
                    Entry entry = row.getItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("chequeTable.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    SecondaryController controller = loader.<SecondaryController>getController();
                    Cheque cheque = entry.getCheque();
                    long id = entry.getId();
                    controller.initData(cheque,id);
                    stage.setTitle("Чек №"+cheque.getId());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return row;
        });

        idColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Entry, Date>("date"));
        dateColumn.setCellFactory((TableColumn<Entry, Date> column) -> {
                    return new TableCell<Entry, Date>() {
                        @Override
                        protected void updateItem(Date date, boolean empty){
                            super.updateItem(date, empty);
                            if(date == null || empty) setText(null);
                            else {
                                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
                                setText(format.format(date));
                            }
                        }
                    };
                });
        timeColumn.setCellValueFactory(new PropertyValueFactory<Entry, Date>("time"));
        timeColumn.setCellFactory((TableColumn<Entry, Date> column) -> {
            return new TableCell<Entry, Date>() {
                @Override
                protected void updateItem(Date date, boolean empty){
                    super.updateItem(date, empty);
                    if(date == null || empty) setText(null);
                    else {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        setText(format.format(date));
                    }
                }
            };
        });
        cashRegColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("cashReg_id"));
        chequeIdColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("chequeId"));
        transTypeColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("transType"));
        cashierIdColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("cashier_id"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<Entry, Float>("total"));
        totalColumn.setCellFactory((TableColumn<Entry, Float> column) -> {
            return new TableCell<Entry, Float>() {
                @Override
                protected void updateItem(Float total, boolean empty){
                    super.updateItem(total, empty);
                    if(total == null || empty) setText("-");
                    else {
                        setText(total.toString());
                    }
                }
            };
        });
        discountCardColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("discountCard_id"));
        discountCardColumn.setCellFactory((TableColumn<Entry, Long> column) -> {
            return new TableCell<Entry, Long>() {
                @Override
                protected void updateItem(Long cardId, boolean empty){
                    super.updateItem(cardId, empty);
                    if(cardId == null || empty) setText("-");
                    else {
                        setText(cardId.toString());
                    }
                }
            };
        });

        openFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Выбор документа");
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".rep (*.rep)", "*.rep");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    try {
                        entryList = new EntryList(file);
                        tableTransactions.setItems(FXCollections.observableList(entryList.list));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}