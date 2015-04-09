
package org.w3._2001.xmlschema;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.DateTime;

public class Adapter1
    extends XmlAdapter<String, DateTime>
{


    public DateTime unmarshal(String value) {
        return (com.example.liberex.util.DataTypeAdapter.parseDateTime(value));
    }

    public String marshal(DateTime value) {
        return (com.example.liberex.util.DataTypeAdapter.printDateTime(value));
    }

}
