package beanscope;

import java.util.ArrayList;
import java.util.List;

public class StringHolder {

    private final List<String> stringList = new ArrayList<>();

    public void addString(List<String> strs) {
        stringList.addAll(strs);
    }

    public List<String> getStringList() {
        return stringList;
    }
}
