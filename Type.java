public enum Type {
    FUNCTION("^[a-zA-Z ]+\\([a-zA-Z ]+\\)[ ]*="), VARIABLE("^[a-zA-Z ]+[ ]*=");

    private String regex;

    private Type(String regex) {
        this.regex = regex;
    }

    public String regex() {
        return this.regex;
    }
}