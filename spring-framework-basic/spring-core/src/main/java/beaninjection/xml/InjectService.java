package beaninjection.xml;

import beaninjection.common.TestDAO;

public class InjectService {

    private TestDAO testDAO;

    public InjectService(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    public void insert(String text) {
        this.testDAO.insert(text);
    }

    public long count() {
        return this.testDAO.count();
    }

    public String getTestDAOType() {
        return this.testDAO.getClass().getName();
    }
}
