package classes;

public class Tag {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
    public Tag(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
}
