package GUI;

import Logic.Cheque;
import Logic.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SecondaryController {
    private Cheque cheque;
    private Long transId;

    @FXML
    private TableView<Cheque> tableCheques;
    @FXML
    private TableColumn<Entry, Long> transIdColumn;
    @FXML
    private TableColumn<Cheque, Boolean> reverseColumn;
    @FXML
    private TableColumn<Cheque, Long> sectionIdColumn;
    @FXML
    private TableColumn<Cheque, Long> itemCodeColumn;
    @FXML
    private TableColumn<Cheque, Float> priceColumn;
    @FXML
    private TableColumn<Cheque, Float> amountColumn;
    @FXML
    private TableColumn<Cheque, Float> discountColumn;
    @FXML

    public void initialize() {
        transIdColumn.setCellValueFactory(new PropertyValueFactory<Entry, Long>("id"));
        transIdColumn.setCellFactory((TableColumn<Entry, Long> column) -> {
            return new TableCell<Entry, Long>() {
                @Override
                protected void updateItem(Long id, boolean empty){
                    super.updateItem(id, empty);
                    if (transId==null || empty) setText(null);
                    else setText(transId.toString());
                }
            };
        });
        reverseColumn.setCellValueFactory(new PropertyValueFactory<Cheque, Boolean>("reverse"));
        reverseColumn.setCellFactory((TableColumn<Cheque, Boolean> column) -> {
            return new TableCell<Cheque,Boolean>() {
                @Override
                protected void updateItem(Boolean reverse, boolean empty){
                    super.updateItem(reverse, empty);
                    if (reverse==null || empty) setText(null);
                    else if(reverse) setText("Есть");
                    else setText("Нет");
                }
            };
        });
        sectionIdColumn.setCellValueFactory(new PropertyValueFactory<Cheque, Long>("section_id"));
        sectionIdColumn.setCellFactory((TableColumn<Cheque, Long> column) -> {
            return new TableCell<Cheque, Long>() {
                @Override
                protected void updateItem(Long sectionId, boolean empty){
                    super.updateItem(sectionId, empty);
                    if(sectionId == null || empty) setText("-");
                    else {
                        setText(sectionId.toString());
                    }
                }
            };
        });
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<Cheque, Long>("itemCode"));
        itemCodeColumn.setCellFactory((TableColumn<Cheque, Long> column) -> {
            return new TableCell<Cheque, Long>() {
                @Override
                protected void updateItem(Long itemCode, boolean empty){
                    super.updateItem(itemCode, empty);
                    if(itemCode == null || empty) setText("-");
                    else {
                        setText(itemCode.toString());
                    }
                }
            };
        });
        priceColumn.setCellValueFactory(new PropertyValueFactory<Cheque, Float>("price"));
        priceColumn.setCellFactory((TableColumn<Cheque, Float> column) -> {
            return new TableCell<Cheque, Float>() {
                @Override
                protected void updateItem(Float price, boolean empty){
                    super.updateItem(price, empty);
                    if(price == null || empty) setText("-");
                    else {
                        setText(price.toString());
                    }
                }
            };
        });
        amountColumn.setCellValueFactory(new PropertyValueFactory<Cheque, Float>("amount"));
        amountColumn.setCellFactory((TableColumn<Cheque, Float> column) -> {
            return new TableCell<Cheque, Float>() {
                @Override
                protected void updateItem(Float amount, boolean empty){
                    super.updateItem(amount, empty);
                    if(amount == null || empty) setText("-");
                    else {
                        setText(amount.toString());
                    }
                }
            };
        });
        discountColumn.setCellValueFactory(new PropertyValueFactory<Cheque, Float>("discount"));
        discountColumn.setCellFactory((TableColumn<Cheque, Float> column) -> {
            return new TableCell<Cheque, Float>() {
                @Override
                protected void updateItem(Float discount, boolean empty){
                    super.updateItem(discount, empty);
                    if(discount == null || empty) setText("-");
                    else {
                        setText(discount.toString());
                    }
                }
            };
        });
    }

    public void initData(Cheque cheque, Long transId){
        this.cheque=cheque;
        this.transId = transId;
        tableCheques.getItems().clear();
        tableCheques.getItems().add(cheque);
    }
}
