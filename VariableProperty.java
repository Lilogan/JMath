import org.mariuszgromada.math.mxparser.Constant;
import org.mariuszgromada.math.mxparser.Expression;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VariableProperty {
    private StringProperty name;
    private DoubleProperty value;
    private Constant constant;

    VariableProperty(String name, String expr) {
        Expression e = new Expression(expr);
        constant = new Constant(name, e.calculate());
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleDoubleProperty(constant.getConstantValue());
    }

    public String getName() {
        return name.get();
    }

    public double getValue() {
        return value.get();
    }

    public Constant getConstant() {
        return constant;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.getName().equals(((VariableProperty) obj).getName());
    }
}