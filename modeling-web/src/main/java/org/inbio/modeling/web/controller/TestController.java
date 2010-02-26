package org.inbio.modeling.web.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.inbio.modeling.core.manager.TestManager;

public class TestController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    TestManager testManager;

    @Override
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Starting " + this.getClass());

		List <String> testList = testManager.getTestList();

		for (String value: testList){
			System.out.println("Name: "+value);
		}

        return new ModelAndView("test");
    }

    public TestManager getTestManager() {
        return testManager;
    }

    public void setTestManager(TestManager testManager) {
        this.testManager = testManager;
    }
}
