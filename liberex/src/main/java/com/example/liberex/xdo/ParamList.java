
package com.example.liberex.xdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "params"
})
@XmlRootElement(name = "ParamList")
public class ParamList
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Param")
    protected List<Param> params;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the params property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the params property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Param }
     * 
     * 
     */
    public List<Param> getParams() {
        if (params == null) {
            params = new ArrayList<Param>();
        }
        return this.params;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public void setParams(List<Param> value) {
        this.params = null;
        List<Param> draftl = this.getParams();
        draftl.addAll(value);
    }

    public ParamList withParams(Param... values) {
        if (values!= null) {
            for (Param value: values) {
                getParams().add(value);
            }
        }
        return this;
    }

    public ParamList withParams(Collection<Param> values) {
        if (values!= null) {
            getParams().addAll(values);
        }
        return this;
    }

    public ParamList withName(String value) {
        setName(value);
        return this;
    }

    public ParamList withParams(List<Param> value) {
        setParams(value);
        return this;
    }

}
