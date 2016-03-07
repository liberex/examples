package net.liberex;

import static org.junit.Assert.assertNull;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.liberex.xdo.pc.GetProductDetailsRequest;
import net.liberex.xdo.pc.GetProductDetailsResponse;
import net.liberex.xdo.pc.ProductCatalogService;
import net.liberex.xdo.pc.ProductCatalogServiceFactory;

public class JPerfTestProductCatalogService {
    private static final Logger logger = LoggerFactory.getLogger(JPerfTestProductCatalogService.class);

    static String SVC_URL = "http://localhost:9090/slow-service";
    private static ProductCatalogService svc = null;

    @BeforeClass
    static public void setupClass() {
        ProductCatalogServiceFactory factory = new ProductCatalogServiceFactory(null,
                new QName("urn:net.liberex:pc:v1", "ProductCatalogServiceFactory"));
        svc = factory.getProductCatalogServiceSoap11();
        BindingProvider bd = (BindingProvider) svc;
        bd.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, SVC_URL + "/ProductCatalogService");
    }

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 10, threads = 10)
    public void getProductCatalogDetails() {
        long start = System.currentTimeMillis();
        GetProductDetailsRequest rq = new GetProductDetailsRequest();
        rq.setProductCode("ABC");
        GetProductDetailsResponse rs = svc.getProductDetails(rq);
        assertNull(rs.getError());
        long end = System.currentTimeMillis();
        logger.debug("Thread: {}, response time: {}", Thread.currentThread().getName(), end - start);
    }
}
