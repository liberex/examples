
package com.example.liberex.xdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.joda.time.DateTime;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:example.com:liberex:v1}AbstractRequest">
 *       &lt;attribute name="options">
 *         &lt;simpleType>
 *           &lt;list itemType="{http://www.w3.org/2001/XMLSchema}string" />
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "GetSystemStatusRequest")
public class GetSystemStatusRequest
    extends AbstractRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "options")
    protected List<String> options;

    /**
     * Gets the value of the options property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the options property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOptions() {
        if (options == null) {
            options = new ArrayList<String>();
        }
        return this.options;
    }

    public void setOptions(List<String> value) {
        this.options = null;
        List<String> draftl = this.getOptions();
        draftl.addAll(value);
    }

    public GetSystemStatusRequest withOptions(String... values) {
        if (values!= null) {
            for (String value: values) {
                getOptions().add(value);
            }
        }
        return this;
    }

    public GetSystemStatusRequest withOptions(Collection<String> values) {
        if (values!= null) {
            getOptions().addAll(values);
        }
        return this;
    }

    public GetSystemStatusRequest withOptions(List<String> value) {
        setOptions(value);
        return this;
    }

    @Override
    public GetSystemStatusRequest withSources(SystemRef... values) {
        if (values!= null) {
            for (SystemRef value: values) {
                getSources().add(value);
            }
        }
        return this;
    }

    @Override
    public GetSystemStatusRequest withSources(Collection<SystemRef> values) {
        if (values!= null) {
            getSources().addAll(values);
        }
        return this;
    }

    @Override
    public GetSystemStatusRequest withRequestDttm(DateTime value) {
        setRequestDttm(value);
        return this;
    }

    @Override
    public GetSystemStatusRequest withApiVersion(String value) {
        setApiVersion(value);
        return this;
    }

    @Override
    public GetSystemStatusRequest withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    @Override
    public GetSystemStatusRequest withSources(List<SystemRef> value) {
        setSources(value);
        return this;
    }

}
