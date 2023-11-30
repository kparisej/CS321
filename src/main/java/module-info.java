module org.openjfx {
    requires javafx.controls;
    requires json.simple;
    exports org.openjfx;
    opens org.openjfx;
<<<<<<< Updated upstream
=======

    requires com.fasterxml.jackson.databind;
    requires activation;
    requires java.mail;
>>>>>>> Stashed changes
}