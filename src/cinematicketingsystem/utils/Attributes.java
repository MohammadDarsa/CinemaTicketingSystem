package cinematicketingsystem.utils;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    private String tableName;
    private HashMap<String, String> attributes;

    public Attributes(){
        tableName = "";
        attributes = new HashMap<>();
    }

    public Attributes(String tableName) {
        this();
        this.tableName = tableName;
    }

    public void addAttribute(String colName, String value) {
        attributes.put(colName, value);
    }

    public String getAttribute(String colName) {
        return attributes.get(colName);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    protected String getString(String joint, String operator) {
        String s = "";
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            s = s + entry.getKey() + " " + operator + " '" + entry.getValue() + "' " + joint + " ";
        }
        s = s.substring(0, s.length()-(joint.length()+2));
        return s;
    }

    protected String getColumns(String joint) {
        String s = "";
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            s = s + entry.getKey() + joint + " ";
        }
        s = s.substring(0, s.length()-(joint.length()+1));
        return s;
    }
}
