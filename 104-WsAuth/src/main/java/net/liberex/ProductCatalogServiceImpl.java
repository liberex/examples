package net.liberex;

import java.security.Principal;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.liberex.xdo.pc.AbstractResponse;
import net.liberex.xdo.pc.GetProductDetailsRequest;
import net.liberex.xdo.pc.GetProductDetailsResponse;
import net.liberex.xdo.pc.LoginRequest;
import net.liberex.xdo.pc.LoginResponse;
import net.liberex.xdo.pc.LogoutRequest;
import net.liberex.xdo.pc.LogoutResponse;
import net.liberex.xdo.pc.ProductCatalogService;

@WebService(
        serviceName = "ProductCatalogService",
        portName = "ProductCatalogServiceSoap11",
        targetNamespace = "urn:net.liberex:v1",
        endpointInterface = "net.liberex.xdo.pc.ProductCatalogService")

public class ProductCatalogServiceImpl implements ProductCatalogService {
    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogServiceImpl.class);

    @Resource
    WebServiceContext ctx;

    @Override
    public LoginResponse login(LoginRequest rq) {
        LoginResponse rs = new LoginResponse();

        HttpServletRequest httpRequest = httpRequest();

        Principal principal = httpRequest.getUserPrincipal();
        logger.debug("Principal: {}", principal);

        if (principal == null) {
            // The user could not be authenticated, but we still got control and can
            //  add details about why the user could not be authenticated

            AbstractResponse.Error err = new AbstractResponse.Error();
            err.setErrorCode(1001);
            err.setErrorMessage("User failed to be authenticated. Details ... ");
            rs.setError(err);
            return rs;
        }

        // Successful authentication. Populate other fields in the response such as the security level.

        return rs;
    }

    @Override
    public GetProductDetailsResponse getProductDetails(GetProductDetailsRequest rq) {
        GetProductDetailsResponse rs = new GetProductDetailsResponse();

        Principal principal = httpRequest().getUserPrincipal();
        if (principal == null) {
            AbstractResponse.Error err = new AbstractResponse.Error();
            err.setErrorCode(1001);
            err.setErrorMessage("Request not authenticated");
            rs.setError(err);
            return rs;
        }

        GetProductDetailsResponse.Product prod = new GetProductDetailsResponse.Product();
        prod.setCode(rq.getProductCode());
        prod.setDescription("SuperHighSpeedInternet");
        rs.setProduct(prod);

        return rs;
    }

    @Override
    public LogoutResponse logout(LogoutRequest rq) {
        LogoutResponse rs = new LogoutResponse();

        return rs;
    }

    private HttpServletResponse httpResponse() {
        HttpServletResponse httpResponse = (HttpServletResponse) ctx.getMessageContext()
                .get(MessageContext.SERVLET_RESPONSE);
        return httpResponse;
    }

    private HttpServletRequest httpRequest() {
        HttpServletRequest httpRequest = (HttpServletRequest) ctx.getMessageContext()
                .get(MessageContext.SERVLET_REQUEST);
        return httpRequest;
    }

}
