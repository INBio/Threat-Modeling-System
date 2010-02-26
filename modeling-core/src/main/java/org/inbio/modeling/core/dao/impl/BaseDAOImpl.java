package org.inbio.modeling.core.dao.impl;

import org.inbio.modeling.core.dao.BaseDAO;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class BaseDAOImpl extends SimpleJdbcDaoSupport implements BaseDAO{

    public BaseDAOImpl(){
        super();
    }
}

