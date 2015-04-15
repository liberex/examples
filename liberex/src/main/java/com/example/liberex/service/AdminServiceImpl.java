package com.example.liberex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.config.AppConfig;
import com.example.liberex.util.ErrorUtil;
import com.example.liberex.util.ResponseBuilder;
import com.example.liberex.xdo.AdminService;
import com.example.liberex.xdo.Container;
import com.example.liberex.xdo.ExecuteProgramRequest;
import com.example.liberex.xdo.ExecuteProgramResponse;
import com.example.liberex.xdo.GetSystemStatusRequest;
import com.example.liberex.xdo.GetSystemStatusResponse;

@WebService(
        serviceName = "AdminService",
        portName = "AdminServiceSoap11",
        targetNamespace = "urn:example.com:liberex:v1",
        endpointInterface = "com.example.liberex.xdo.AdminService")
@HandlerChain(file = "handlers.xml")
public class AdminServiceImpl extends WebServiceBase implements AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Inject
    AppConfig appConfig;

    public GetSystemStatusResponse getSystemStatus(GetSystemStatusRequest getSystemStatusRequest) {
        GetSystemStatusResponse rs = ResponseBuilder.of(GetSystemStatusResponse.class).build();

        logger.debug("Started operation: {}", "getSystemStatus");

        try {
            rs.withAppInfo(new GetSystemStatusResponse.AppInfo());

            List<String> beans = getAllBeanNames();
        }
        catch (NamingException e) {
            logger.error("While executing the program: {}", e);
            logger.debug("Details:", e);
            ResponseBuilder.setError(rs, ErrorUtil.convertExceptionToError(e));
        }

        return rs;
    }

    @SuppressWarnings("unchecked")
    public List<String> getAllBeanNames() throws NamingException {
        //Get the BeanManager via JNDI
        InitialContext initialContext = new InitialContext();
        BeanManager bm = (BeanManager) initialContext.lookup("java:comp/BeanManager");

        //Get all CDI Managed Bean types
        Set<Bean<?>> beans = bm.getBeans(Object.class);
        List<String> beanNames = new ArrayList<>();

        for (Bean bean : beans) {
            if (bean.getName() != null) {
                logger.debug("Bean: {} - {}", bean.getName(), bean.getBeanClass().getName());
            }
        }

        return beanNames;
    }

    public ExecuteProgramResponse executeProgram(ExecuteProgramRequest rq) {
        ExecuteProgramResponse rs = ResponseBuilder.of(ExecuteProgramResponse.class).build();
        try {
            if (rq.getContainers().size() == 0) {
                ResponseBuilder.setError(rs, 2000, "Missing input containers.");
            }
            else if (rq.getContainers().size() == 1
                    && rq.getContainers().get(0).getName() == "COMMAREA") {
                // this is a COMMAREA request
                String input = rq.getContainers().get(0).getValue();
                String output = invokeCicsProgram(rq.getProgram().getName(), input, appConfig.getCharSet());
                rs.getContainers().add(new Container().withValue(output));
            }
            else {
                // the channel are not supported yet
                ResponseBuilder.setError(rs, 1001, "Calls to CICS programs with channels are not supported.");
            }
        }
        catch (Exception e) {
            logger.error("While executing the program: {}", e);
            logger.debug("Details:", e);
            ResponseBuilder.setError(rs, ErrorUtil.convertExceptionToError(e));
        }

        return rs;
    }
}
