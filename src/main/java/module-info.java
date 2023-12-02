module org.openjfx {
    requires javafx.controls;
    requires json.simple;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.mail;
    exports org.openjfx;
    opens org.openjfx;
}