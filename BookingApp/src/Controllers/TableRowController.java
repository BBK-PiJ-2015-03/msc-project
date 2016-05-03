package Controllers;

import Model.BookingImpl;
import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.time.LocalTime;
import java.util.function.Function;

public class TableRowController{

    private final PseudoClass future = PseudoClass.getPseudoClass("future");

    public void buildTable(TableView table){
        ObjectProperty<LocalTime> now = new SimpleObjectProperty<>(LocalTime.now());
        startClock(now);
        table.setRowFactory(tv -> {
            TableRow<BookingImpl> row = new TableRow<>();
            ChangeListener<LocalTime> listener = (obs, oldTime, newTime) -> updateRow(row, now.get());
            now.addListener(listener);
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (oldItem != null) {
                    oldItem.getTimeProperty().removeListener(listener);
                }
                if (newItem != null) {
                    newItem.getTimeProperty().addListener(listener);
                }
                updateRow(row, now.get());
            });
            return row ;
        });

        configureTable(table);
    }

    public void updateRow(TableRow<BookingImpl> row, LocalTime now) {
        boolean isFuture = false ;
        if (row.getItem() != null) {
            isFuture = row.getItem().getTime().isBefore(now);
        }
        row.pseudoClassStateChanged(future, isFuture);
    }


    private void configureTable(TableView<BookingImpl> table) {
        TableColumn<BookingImpl, LocalTime> timeCol = column("Time", (t) -> t.getTimeProperty());
        TableColumn<BookingImpl, String> nameCol = column("Name", (t) -> t.getClientNameProperty());
        TableColumn<BookingImpl, String> pickupCol = column("Pickup", (t) -> t.getPickUpAddressProperty());
        TableColumn<BookingImpl, String> dropoffCol = column("Dropoff", (t) -> t.getDropOffAddressProperty());
        TableColumn<BookingImpl, String> commentCol = column("Comment", (t) -> t.getCommentsProperty());
        TableColumn<BookingImpl, String> priceCol = column("Price", (t) -> t.getPriceProperty());
        timeCol.setMinWidth(75);
        nameCol.setMinWidth(120);
        pickupCol.setMinWidth(250);
        dropoffCol.setMinWidth(250);
        commentCol.setMinWidth(213);
        priceCol.setMinWidth(75);
        table.setItems(ObservableLists.bookingsList);
        timeCol.sortTypeProperty().setValue(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(timeCol);
    }

    private <S,T> TableColumn<S,T> column(String title, Function<S, Property<T>> property) {
        TableColumn<S,T> column = new TableColumn<>(title);
        column.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        return column ;
    }

    private void startClock(ObjectProperty<LocalTime> clock) {
        new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                clock.set(LocalTime.now());
            }
        }.start();
    }
}