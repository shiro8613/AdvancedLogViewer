module com.github.shiro8613.advancedlogviewer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;

    opens com.github.shiro8613.advancedlogviewer to javafx.fxml;
    exports com.github.shiro8613.advancedlogviewer;
}