package beaninjection.common;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TestDAOImpl implements TestDAO {

    static List<String> testMap = new ArrayList<>();

    @Override
    public void insert(String text) {
        testMap.add(text);
    }

    @Override
    public long count() {
        return testMap.size();
    }
}
