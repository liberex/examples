package com.example.liberex.test;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.liberex.util.ResponseBuilder;
import com.example.liberex.util.XdoMarshaller;
import com.example.liberex.xdo.GetSystemStatusResponse;
import com.example.liberex.xdo.Param;
import com.example.librerex.client.AbstractJTest;

public class JTestMarshall extends AbstractJTest {
    XdoMarshaller marshaller = new XdoMarshaller();

    @BeforeClass
    static public void setup() {
        AbstractJTest.setUp();        
    }
    
    @Test
    public void marshallGetSystemStatusResponse() throws Exception {
        GetSystemStatusResponse rs = ResponseBuilder.of(GetSystemStatusResponse.class).build();
        rs.getParams().add(new Param().withName("aaaa").withValue("bbbb\nbbb"));
        marshaller.toXml(rs, new File(outDir, "GetSystemStatusResponse1.xml"));
    }

}
