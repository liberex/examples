
package com.example.liberex.xdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for AbstractResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Source" type="{urn:example.com:liberex:v1}SystemRef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Error" type="{urn:example.com:liberex:v1}CodeMessage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Warning" type="{urn:example.com:liberex:v1}CodeMessage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="responseDttm" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractResponse", propOrder = {
    "sources",
    "errors",
    "warnings"
})
@XmlSeeAlso({
    GetSystemStatusResponse.class,
    ExecuteProgramResponse.class
})
public class AbstractResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Source")
    protected List<SystemRef> sources;
    @XmlElement(name = "Error")
    protected List<CodeMessage> errors;
    @XmlElement(name = "Warning")
    protected List<CodeMessage> warnings;
    @XmlAttribute(name = "responseDttm", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected DateTime responseDttm;
    @XmlAttribute(name = "transactionId")
    protected String transactionId;
    @XmlAttribute(name = "duration")
    protected Integer duration;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * Gets the value of the errors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeMessage }
     * 
     * 
     */
    public List<CodeMessage> getErrors() {
        if (errors == null) {
            errors = new ArrayList<CodeMessage>();
        }
        return this.errors;
    }

    /**
     * Gets the value of the warnings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warnings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarnings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeMessage }
     * 
     * 
     */
    public List<CodeMessage> getWarnings() {
        if (warnings == null) {
            warnings = new ArrayList<CodeMessage>();
        }
        return this.warnings;
    }

    /**
     * Gets the value of the responseDttm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public DateTime getResponseDttm() {
        return responseDttm;
    }

    /**
     * Sets the value of the responseDttm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseDttm(DateTime value) {
        this.responseDttm = value;
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

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDuration(Integer value) {
        this.duration = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void setSources(List<SystemRef> value) {
        this.sources = null;
        List<SystemRef> draftl = this.getSources();
        draftl.addAll(value);
    }

    public void setErrors(List<CodeMessage> value) {
        this.errors = null;
        List<CodeMessage> draftl = this.getErrors();
        draftl.addAll(value);
    }

    public void setWarnings(List<CodeMessage> value) {
        this.warnings = null;
        List<CodeMessage> draftl = this.getWarnings();
        draftl.addAll(value);
    }

    public AbstractResponse withSources(SystemRef... values) {
        if (values!= null) {
            for (SystemRef value: values) {
                getSources().add(value);
            }
        }
        return this;
    }

    public AbstractResponse withSources(Collection<SystemRef> values) {
        if (values!= null) {
            getSources().addAll(values);
        }
        return this;
    }

    public AbstractResponse withErrors(CodeMessage... values) {
        if (values!= null) {
            for (CodeMessage value: values) {
                getErrors().add(value);
            }
        }
        return this;
    }

    public AbstractResponse withErrors(Collection<CodeMessage> values) {
        if (values!= null) {
            getErrors().addAll(values);
        }
        return this;
    }

    public AbstractResponse withWarnings(CodeMessage... values) {
        if (values!= null) {
            for (CodeMessage value: values) {
                getWarnings().add(value);
            }
        }
        return this;
    }

    public AbstractResponse withWarnings(Collection<CodeMessage> values) {
        if (values!= null) {
            getWarnings().addAll(values);
        }
        return this;
    }

    public AbstractResponse withResponseDttm(DateTime value) {
        setResponseDttm(value);
        return this;
    }

    public AbstractResponse withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    public AbstractResponse withDuration(Integer value) {
        setDuration(value);
        return this;
    }

    public AbstractResponse withSources(List<SystemRef> value) {
        setSources(value);
        return this;
    }

    public AbstractResponse withErrors(List<CodeMessage> value) {
        setErrors(value);
        return this;
    }

    public AbstractResponse withWarnings(List<CodeMessage> value) {
        setWarnings(value);
        return this;
    }

}
