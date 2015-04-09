package com.example.liberex.test;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.config.AppConfig;
import com.example.liberex.util.AppException;
import com.example.liberex.xdo.AdminService;
import com.example.liberex.xdo.AdminServiceService;
import com.example.liberex.xdo.Container;
import com.example.liberex.xdo.ExecuteProgramRequest;
import com.example.liberex.xdo.ExecuteProgramResponse;
import com.example.liberex.xdo.GetSystemStatusRequest;
import com.example.liberex.xdo.GetSystemStatusResponse;
import com.example.librerex.client.AbstractJIntTest;

public class JIntTestAdminService extends AbstractJIntTest {
    private static final Logger logger = LoggerFactory.getLogger(JIntTestAdminService.class);
    private static final String BASE_URL = "http://localhost:9090/liberex";

    @BeforeClass
    static public void setupClass() {
        // AbstractJIntTest.setEnv(ENV_LOCAL);
        // AbstractJIntTest.setEnv("rdnt");
        AbstractJIntTest.setUp();
    }

    AdminService getAdminService() {
        try {
            URL wsdl = new URL(BASE_URL + "/AdminService?WSDL");
            AdminServiceService svc = new AdminServiceService(wsdl,
                    new QName("urn:example.com:liberex:v1", "AdminService"));
            AdminService port = svc.getAdminServiceSoap11();
            return port;
        }
        catch (MalformedURLException e) {
            throw AppException.wrap(e, "While building the web-service client");
        }
    }

    @Test
    public void testExecuteProgram() throws MalformedURLException {
        Assume.assumeTrue(AppConfig.isCicsEnabled());
        AdminService port = getAdminService();
        ExecuteProgramRequest rq = createRequest(ExecuteProgramRequest.class)
                .withProgram(new ExecuteProgramRequest.Program()
                        .withName("SS01OS112"))
                .withContainers(new Container()
                        .withName("SERVICE_REQUEST")
                        .withValue("23129293 23234 1    12341 AFAFADSESEWED"));
        ExecuteProgramResponse rs = port.executeProgram(rq);
        logger.debug("Response time: " + rs.getResponseDttm().toString("YYYY-MM-dd HH:mm:ss"));
        assertEquals(0, rs.getErrors().size());
    }

    @Test
    public void testGetSystemStatus() throws MalformedURLException {
        AdminService port = getAdminService();
        GetSystemStatusRequest rq = createRequest(GetSystemStatusRequest.class);
        GetSystemStatusResponse rs = port.getSystemStatus(rq);
        logger.debug("Response time: " + rs.getResponseDttm().toString("YYYY-MM-dd HH:mm:ss"));
    }
}
