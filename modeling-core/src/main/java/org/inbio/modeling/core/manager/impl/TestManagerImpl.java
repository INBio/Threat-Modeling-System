package org.inbio.modeling.core.manager.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dao.TestDAO;
import org.inbio.modeling.core.manager.TestManager;

public class TestManagerImpl implements TestManager {

	TestDAO testDAO;
	
    protected final Log logger = LogFactory.getLog(getClass());


    @Override
    public List<String> getTestList() {
        logger.info("Starting getList()");
        return testDAO.getTestList();
    }

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }
}
