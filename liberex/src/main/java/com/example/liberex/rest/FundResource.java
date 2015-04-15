package com.example.liberex.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.config.AppConfig;
import com.example.liberex.util.ErrorUtil;
import com.example.liberex.util.ResponseBuilder;
import com.example.liberex.xdo.AdminService;
import com.example.liberex.xdo.Container;
import com.example.liberex.xdo.ExecuteProgramResponse;
import com.ibm.cics.server.Program;

@Path("/funds")
public class FundResource extends AbstractResource
{
    private static final Logger logger = LoggerFactory.getLogger(FundResource.class);

    @Inject
    AppConfig appConfig;

    @Inject
    AdminService adminServiceImpl;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getFund(@PathParam("id") String id) {
        logger.debug("Started getFund");
        ExecuteProgramResponse rs = ResponseBuilder.of(ExecuteProgramResponse.class).build();
        rs.getSources().get(0).setOperation("getFund");
        try {
            String input = String.format("%8s%20s%20s", id, "", "");
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
            String input = String.format("%8s%20s%20s", fundId, fundName, "");
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
        catch (Throwable e) {
            ResponseBuilder.setError(rs, ErrorUtil.convertExceptionToError(e));
        }

        return Response.ok(rs).build();
    }

}
