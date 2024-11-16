module client {
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports ite.kubak.model.benefit to com.fasterxml.jackson.databind;
    opens ite.kubak.model.benefit to com.fasterxml.jackson.databind;
    exports ite.kubak.model.index to com.fasterxml.jackson.databind;
    opens ite.kubak.model.index to com.fasterxml.jackson.databind;

    exports ite.kubak.communication to gui;
    exports ite.kubak.model.Hospitality;
}