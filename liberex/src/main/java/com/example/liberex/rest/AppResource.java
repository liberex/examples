package com.example.liberex.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import com.example.liberex.xdo.GetSystemStatusResponse;
import com.example.liberex.xdo.GetSystemStatusResponse.AppInfo;
import com.example.liberex.xdo.GetSystemStatusResponse.SystemProperties;
import com.example.liberex.xdo.Param;
import com.example.liberex.xdo.SchemaProperties;

@Path("/app")
public class AppResource extends AbstractResource
{
    private static final Logger logger = LoggerFactory
            .getLogger(AppResource.class);

    @Inject
    AppConfig appConfig;

    @Inject
    AdminService adminServiceImpl;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAppStatus(@QueryParam("options") String options) {
        logger.debug("Started getAppStatus");

        GetSystemStatusResponse rs = ResponseBuilder.of(
                GetSystemStatusResponse.class).build();
        List<String> lopts = Collections.EMPTY_LIST;
        if (options != null)
        {
            lopts = Arrays.asList(options.split(","));
        }
        rs.withAppInfo(new AppInfo().withApiVersion(new SchemaProperties()
                .getApiVersion()));
        addBuildProperties(rs);
        if (lopts.contains("sysprops"))
        {
            addSystemProperties(rs);
        }
        rs.withAppConfig(new GetSystemStatusResponse.AppConfig());

        Properties otherProps = appConfig.getOtherProperties();
        for (Entry<Object, Object> p : otherProps.entrySet())
        {
            rs.getAppConfig()
                    .getParams()
                    .add(new Param()
                            .withName(p.getKey().toString()).withValue(
                                    p.getValue().toString()));
        }

        return Response.ok(rs).build();
    }

    void addSystemProperties(GetSystemStatusResponse rs) {
        if (rs.getSystemProperties() == null)
        {
            rs.setSystemProperties(new GetSystemStatusResponse.SystemProperties());
        }
        SystemProperties sp = rs.getSystemProperties();
        TreeMap<Object, Object> orderedProps = new TreeMap<>();
        orderedProps.putAll(System.getProperties());
        for (Entry<Object, Object> p : orderedProps.entrySet())
        {
            sp.getParams().add(
                    new Param().withName(p.getKey().toString()).withValue(
                            p.getValue().toString()));
        }
    }

    String toString(Properties props) {
        StringBuilder sb = new StringBuilder();
        for (Entry<Object, Object> p : props.entrySet())
        {
            sb.append("  " + p.getKey() + "=" + p.getValue() + "\n");
        }
        return sb.toString();
    }

    void addBuildProperties(GetSystemStatusResponse rs)
    {
        InputStream is = AppResource.class
                .getResourceAsStream("/build.properties");
        if (is == null)
        {
            rs.getWarnings().add(
                    ErrorUtil.createError(ErrorUtil.FAIILED_ASSERTION,
                            "The file build.properties does not exist"));
            return;
        }
        Properties props = new Properties();
        try
        {
            props.load(is);
            AppInfo appInfo = rs.getAppInfo();
            for (Entry<Object, Object> e : props.entrySet())
            {
                appInfo.getParams().add(
                        new Param().withName(e.getKey().toString()).withValue(
                                e.getValue().toString()));
            }
        }
        catch (IOException e)
        {
            rs.getWarnings().add(ErrorUtil.convertExceptionToError(e));
        }
        return;
    }
}
