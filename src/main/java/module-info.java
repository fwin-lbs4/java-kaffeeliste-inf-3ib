module memoFwinAdrian.lbs.vier {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens memoFwinAdrian.lbs.vier to javafx.fxml;
    exports memoFwinAdrian.lbs.vier;
}