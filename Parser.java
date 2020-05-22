import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String input;
    private Pattern namePattern = Pattern.compile("^[a-zA-Z]+");
    private Pattern exprPattern = Pattern.compile("=[a-zA-Z0-9.^\\(\\)\\/\\+\\-\\* ]+");

    public Parser(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public Type analyse() throws IOException {
        for (Type type : Type.values()) {
            Pattern pattern = Pattern.compile(type.regex());
            if (pattern.matcher(this.input).find()) {
                return type;
            }
        }
        throw new IOException("Input incorrect");

    }

    public String formatString(String str) {
        str = str.replace(" ", "");
        str = str.replace("=", "");
        if (str.charAt(0) == '(') {
            str = str.substring(1);
        }
        return str;
    }

    public FunctionProperty parseFunction() {
        FunctionProperty function = new FunctionProperty(this.input);
        return function;
    }

    public VariableProperty parseVariable() throws IOException {
        Matcher nameMatcher = namePattern.matcher(this.input);
        Matcher exprMatcher = exprPattern.matcher(this.input);
        if (nameMatcher.find() && exprMatcher.find()) {
            VariableProperty variable = new VariableProperty(formatString(nameMatcher.group()),
                    formatString(exprMatcher.group()));
            return variable;
        }
        throw new IOException("Variable format incorrect");

    }

}