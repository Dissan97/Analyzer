module system {
    requires java.logging;
    requires org.jetbrains.annotations;
    requires commons.math;
    requires java.base;
    requires org.json;

    exports dissan.ahmed.api;
    exports dissan.ahmed;
    opens dissan.ahmed.bce to junit;
    opens dissan.ahmed.bce.neural to junit;

}