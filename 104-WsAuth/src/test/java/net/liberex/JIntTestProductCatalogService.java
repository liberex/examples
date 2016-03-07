package net.liberex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.junit.Test;

import net.liberex.xdo.pc.GetProductDetailsRequest;
import net.liberex.xdo.pc.GetProductDetailsResponse;
import net.liberex.xdo.pc.LoginRequest;
import net.liberex.xdo.pc.LoginResponse;
import net.liberex.xdo.pc.LogoutRequest;
import net.liberex.xdo.pc.LogoutResponse;
import net.liberex.xdo.pc.ProductCatalogService;
import net.liberex.xdo.pc.ProductCatalogServiceFactory;

public class JIntTestProductCatalogService {

    String pcUrl = "http://localhost:19090/ws-auth";

    ProductCatalogService getProductCatalogService(String url, String userId, String password) {
        ProductCatalogServiceFactory factory = new ProductCatalogServiceFactory(null,
                new QName("urn:net.liberex:pc:v1", "ProductCatalogServiceFactory"));
        ProductCatalogService svc = factory.getProductCatalogServiceSoap11();
        BindingProvider bd = (BindingProvider) svc;
        bd.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url + "/ProductCatalogService");
        bd.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
        bd.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bd.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        return svc;
    }

    @Test
    public void loginSuccess() {
        ProductCatalogService svc = getProductCatalogService(pcUrl, "DEVAB", "123");

        {
            LoginRequest rq = new LoginRequest();
            LoginResponse rs = svc.login(rq);
            assertNull(rs.getError());
        }

        {
            GetProductDetailsRequest rq = new GetProductDetailsRequest();
            rq.setProductCode("ABC");
            GetProductDetailsResponse rs = svc.getProductDetails(rq);
            assertNull(rs.getError());
        }

        {
            LogoutRequest rq = new LogoutRequest();
            LogoutResponse rs = svc.logout(rq);
            assertNull(rs.getError());
        }
    }

    @Test
    public void loginFailed() {
        ProductCatalogService svc = getProductCatalogService(pcUrl, "DEVAB", "badpass");

        {
            LoginRequest rq = new LoginRequest();
            LoginResponse rs = svc.login(rq);
            assertNotNull(rs.getError());
        }

        {
            GetProductDetailsRequest rq = new GetProductDetailsRequest();
            rq.setProductCode("ABC");
            GetProductDetailsResponse rs = svc.getProductDetails(rq);
            assertNotNull(rs.getError());
        }

        {
            LogoutRequest rq = new LogoutRequest();
            LogoutResponse rs = svc.logout(rq);
            assertNull(rs.getError());
        }
    }

}
