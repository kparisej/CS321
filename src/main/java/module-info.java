module org.openjfx {
    requires javafx.controls;
    requires json.simple;

    exports org.openjfx;

    opens org.openjfx;

    requires com.fasterxml.jackson.databind;

}