
package com.example.liberex.xdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for AbstractRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Source" type="{urn:example.com:liberex:v1}SystemRef" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="requestDttm" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="apiVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractRequest", propOrder = {
    "sources"
})
@XmlSeeAlso({
    GetSystemStatusRequest.class,
    ExecuteProgramRequest.class
})
public abstract class AbstractRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Source", required = true)
    protected List<SystemRef> sources;
    @XmlAttribute(name = "requestDttm", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected DateTime requestDttm;
    @XmlAttribute(name = "apiVersion", required = true)
    protected String apiVersion;
    @XmlAttribute(name = "transactionId")
    protected String transactionId;

    /**
     * Gets the value of the sources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SystemRef }
     * 
     * 
     */
    public List<SystemRef> getSources() {
        if (sources == null) {
            sources = new ArrayList<SystemRef>();
        }
        return this.sources;
    }

    /**
     * Gets the value of the requestDttm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public DateTime getRequestDttm() {
        return requestDttm;
    }

    /**
     * Sets the value of the requestDttm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestDttm(DateTime value) {
        this.requestDttm = value;
    }

    /**
     * Gets the value of the apiVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * Sets the value of the apiVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiVersion(String value) {
        this.apiVersion = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    public void setSources(List<SystemRef> value) {
        this.sources = null;
        List<SystemRef> draftl = this.getSources();
        draftl.addAll(value);
    }

    public AbstractRequest withSources(SystemRef... values) {
        if (values!= null) {
            for (SystemRef value: values) {
                getSources().add(value);
            }
        }
        return this;
    }

    public AbstractRequest withSources(Collection<SystemRef> values) {
        if (values!= null) {
            getSources().addAll(values);
        }
        return this;
    }

    public AbstractRequest withRequestDttm(DateTime value) {
        setRequestDttm(value);
        return this;
    }

    public AbstractRequest withApiVersion(String value) {
        setApiVersion(value);
        return this;
    }

    public AbstractRequest withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    public AbstractRequest withSources(List<SystemRef> value) {
        setSources(value);
        return this;
    }

}
