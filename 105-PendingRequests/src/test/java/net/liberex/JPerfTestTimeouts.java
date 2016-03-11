package net.liberex;

import static org.junit.Assert.assertNull;

import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.liberex.xdo.pc.GetProductDetailsRequest;
import net.liberex.xdo.pc.GetProductDetailsResponse;
import net.liberex.xdo.pc.ProductCatalogService;
import net.liberex.xdo.pc.ProductCatalogServiceFactory;

public class JPerfTestTimeouts {
    private static final Logger logger = LoggerFactory.getLogger(JPerfTestTimeouts.class);

    static String svcUrl = "http://localhost:9090/slow-service";

    private ProductCatalogService svc = null;

    @BeforeClass
    static public void setupClass() {
        String urlProp = System.getProperty("svc.url");
        if (urlProp != null) {
            svcUrl = urlProp;
        }
    }

    @Before
    public void setup() {
        logger.debug("Starting test connecting to url: {}", svcUrl);
        ProductCatalogServiceFactory factory = new ProductCatalogServiceFactory(null,
                new QName("urn:net.liberex:pc:v1", "ProductCatalogServiceFactory"));
        svc = factory.getProductCatalogServiceSoap11();
        BindingProvider bd = (BindingProvider) svc;
        Map<String, Object> requestContext = bd.getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, svcUrl + "/ProductCatalogService");
        // requestContext.put("javax.xml.ws.client.receiveTimeout", 2);
        requestContext.put("com.sun.xml.internal.ws.request.timeout", 500);

    }

    @Test
    public void getProductCatalogDetails() {
        for (int i = 0; i < 3; i++) {
            long start = System.currentTimeMillis();
            try {
                GetProductDetailsRequest rq = new GetProductDetailsRequest();
                rq.setProductCode("ABC");
                logger.debug("Sending request {} for product code: {}", i, rq.getProductCode());
                GetProductDetailsResponse rs = svc.getProductDetails(rq);
                assertNull(rs.getError());
            }
            catch (WebServiceException e) {
                long end = System.currentTimeMillis();
                logger.debug("Exception {} received after: {}ms", Thread.currentThread().getName(), end - start);
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e1) {
                    logger.error("Interrupted sleep.");
                }
            }
        }
    }
}
