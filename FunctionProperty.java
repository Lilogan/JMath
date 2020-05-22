import org.mariuszgromada.math.mxparser.Function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FunctionProperty {

    private Function function;
    private StringProperty name;
    private StringProperty arg;
    private StringProperty expr;

    FunctionProperty(String name, String arg, String expr) {
        this.name = new SimpleStringProperty(name);
        this.arg = new SimpleStringProperty(arg);
        this.expr = new SimpleStringProperty(expr);
        this.function = new Function(name + "(" + arg + ")=" + expr);
    }

    FunctionProperty(String input) {
        this.function = new Function(input);
        this.name = new SimpleStringProperty(function.getFunctionName());
        this.expr = new SimpleStringProperty(function.getFunctionExpressionString());
        String arguments = new String();
        for (int i = 0; i < function.getArgumentsNumber(); i++) {
            arguments += function.getArgument(i).getArgumentName();
        }
        this.arg = new SimpleStringProperty(arguments);
    }

    public String getArg() {
        return arg.get();
    }

    public String getName() {
        return name.get();
    }

    public String getExpr() {
        return expr.get();
    }

    public Function getFunction() {
        return function;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty argProperty() {
        return arg;
    }

    public StringProperty exprProperty() {
        return expr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.getName().equals(((FunctionProperty) obj).getName());
    }
}