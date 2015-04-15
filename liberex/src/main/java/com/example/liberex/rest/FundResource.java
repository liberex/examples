package com.example.liberex.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.config.AppConfig;
import com.example.liberex.util.ErrorUtil;
import com.example.liberex.util.ResponseBuilder;
import com.example.liberex.xdo.AdminService;
import com.example.liberex.xdo.CodeMessage;
import com.example.liberex.xdo.Container;
import com.example.liberex.xdo.ExecuteProgramResponse;
import com.example.liberex.xdo.Param;
import com.ibm.cics.server.Program;

@Path("/funds")
public class FundResource extends AbstractResource
{
    private static final Logger logger = LoggerFactory.getLogger(FundResource.class);

    @Inject
    AppConfig appConfig;

    @Inject
    AdminService adminServiceImpl;
    
    static public class FundRequestResponse {
	public String request = "";
	public String retCode = "";
	public String id = "";
	public String name = "";
	public String rating = "";
	public String price = "";
	public String retComment = "";
	
	void add(StringBuffer sb, String f, int size) {
	    sb.append(StringUtils.rightPad(StringUtils.trimToEmpty(f), size));
	}
	String toCommArea() {
	    StringBuffer sb = new StringBuffer();
	    add(sb, request, 1);
	    add(sb, retCode, 2);
	    add(sb, id, 8);
	    add(sb, name, 50);
	    add(sb, rating, 1);
	    add(sb, price, 15);
	    add(sb, retComment, 50);
	    return sb.toString();
	}
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getFund(@PathParam("id") String id) {
        logger.debug("Started getFund");
        ExecuteProgramResponse rs = ResponseBuilder.of(ExecuteProgramResponse.class).build();
        rs.getSources().get(0).setOperation("getFund");
        try {
            FundRequestResponse rq = new FundRequestResponse();
            rq.request = "R";
            rq.id = id;
            String input = rq.toCommArea();
            rs.getContainers().add(new Container()
                    .withName("COMMAREA-IN")
                    .withValue(input)
                    );
            String charSet = appConfig.getCharSet();
            String cmd = "FUNDPROG";

            byte[] commarea = input.getBytes(charSet);

            if (appConfig.isCicsEnabled()) {
                Program program = new Program();
                program.setName(cmd);
                program.link(commarea);
            }

            rs.getContainers().add(new Container()
                    .withName("COMMAREA-OUT")
                    .withValue(new String(commarea, charSet))
                    );
        }
        catch (com.ibm.cics.server.AbendException ae) {
            ResponseBuilder.setError(rs, new CodeMessage()
            .withCode(1000)
            .withMessage(ae.getMessage())
            .withParams(new Param().withName("ABCODE").withValue(ae.getABCODE())));
        }
        catch (Throwable e) {
            ResponseBuilder.setError(rs, ErrorUtil.convertExceptionToError(e));
        }

        return Response.ok(rs).build();
    }

    /**
     * This will be replaced with POST. It is GET now to simplify testing.
     * @param fundId
     * @param fundName
     * @return
     */

    @GET
    @Path("/add")
    @Produces(MediaType.APPLICATION_XML)
    public Response addFund(@QueryParam("id") String fundId, @QueryParam("nm") String fundName) {
        logger.debug("Started addFund({}, {})", fundId, fundName);
        ExecuteProgramResponse rs = ResponseBuilder.of(ExecuteProgramResponse.class).build();
        rs.getSources().get(0).setOperation("addFund");
        try {
            FundRequestResponse rq = new FundRequestResponse();
            rq.request = "C";
            rq.id = fundId;
            rq.name = fundName;
            String input = rq.toCommArea();
            rs.getContainers().add(new Container()
                    .withName("COMMAREA-IN")
                    .withValue(input)
                    );
            String charSet = appConfig.getCharSet();
            String cmd = "FUNDPROG";

            byte[] commarea = input.getBytes(charSet);

            if (appConfig.isCicsEnabled()) {
                Program program = new Program();
                program.setName(cmd);
                program.link(commarea);
            }
            
            rs.getContainers().add(new Container()
                    .withName("COMMAREA-OUT")
                    .withValue(new String(commarea, charSet))
                    );
        }
        catch (com.ibm.cics.server.AbendException ae) {
            ResponseBuilder.setError(rs, new CodeMessage()
            .withCode(1000)
            .withMessage(ae.getMessage())
            .withParams(new Param().withName("ABCODE").withValue(ae.getABCODE())));
        }
        catch (Throwable e) {
            ResponseBuilder.setError(rs, ErrorUtil.convertExceptionToError(e));
        }

        return Response.ok(rs).build();
    }

}
