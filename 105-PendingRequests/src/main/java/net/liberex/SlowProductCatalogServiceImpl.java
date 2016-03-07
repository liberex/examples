package net.liberex;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.liberex.xdo.pc.GetProductDetailsRequest;
import net.liberex.xdo.pc.GetProductDetailsResponse;
import net.liberex.xdo.pc.ProductCatalogService;

@WebService(
        serviceName = "ProductCatalogService",
        portName = "ProductCatalogServiceSoap11",
        targetNamespace = "urn:net.liberex:v1",
        endpointInterface = "net.liberex.xdo.pc.ProductCatalogService")

public class SlowProductCatalogServiceImpl implements ProductCatalogService {
    private static final Logger logger = LoggerFactory.getLogger(SlowProductCatalogServiceImpl.class);


    @Override
    public GetProductDetailsResponse getProductDetails(GetProductDetailsRequest rq) {
        GetProductDetailsResponse rs = new GetProductDetailsResponse();

        GetProductDetailsResponse.Product prod = new GetProductDetailsResponse.Product();
        prod.setCode(rq.getProductCode());
        prod.setDescription("SuperHighSpeedInternet");
        rs.setProduct(prod);

        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException e) {
            logger.debug("Interrupted thread: {}, {}", Thread.currentThread().getName(), e.getMessage());
        }

        return rs;
    }

}
