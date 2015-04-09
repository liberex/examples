
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
 *       &lt;sequence>
 *         &lt;element name="Program">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Container" type="{urn:example.com:liberex:v1}Container" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "program",
    "containers"
})
@XmlRootElement(name = "ExecuteProgramRequest")
public class ExecuteProgramRequest
    extends AbstractRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Program", required = true)
    protected ExecuteProgramRequest.Program program;
    @XmlElement(name = "Container", required = true)
    protected List<Container> containers;

    /**
     * Gets the value of the program property.
     * 
     * @return
     *     possible object is
     *     {@link ExecuteProgramRequest.Program }
     *     
     */
    public ExecuteProgramRequest.Program getProgram() {
        return program;
    }

    /**
     * Sets the value of the program property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecuteProgramRequest.Program }
     *     
     */
    public void setProgram(ExecuteProgramRequest.Program value) {
        this.program = value;
    }

    /**
     * Gets the value of the containers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the containers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContainers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Container }
     * 
     * 
     */
    public List<Container> getContainers() {
        if (containers == null) {
            containers = new ArrayList<Container>();
        }
        return this.containers;
    }

    public void setContainers(List<Container> value) {
        this.containers = null;
        List<Container> draftl = this.getContainers();
        draftl.addAll(value);
    }

    public ExecuteProgramRequest withProgram(ExecuteProgramRequest.Program value) {
        setProgram(value);
        return this;
    }

    public ExecuteProgramRequest withContainers(Container... values) {
        if (values!= null) {
            for (Container value: values) {
                getContainers().add(value);
            }
        }
        return this;
    }

    public ExecuteProgramRequest withContainers(Collection<Container> values) {
        if (values!= null) {
            getContainers().addAll(values);
        }
        return this;
    }

    public ExecuteProgramRequest withContainers(List<Container> value) {
        setContainers(value);
        return this;
    }

    @Override
    public ExecuteProgramRequest withSources(SystemRef... values) {
        if (values!= null) {
            for (SystemRef value: values) {
                getSources().add(value);
            }
        }
        return this;
    }

    @Override
    public ExecuteProgramRequest withSources(Collection<SystemRef> values) {
        if (values!= null) {
            getSources().addAll(values);
        }
        return this;
    }

    @Override
    public ExecuteProgramRequest withRequestDttm(DateTime value) {
        setRequestDttm(value);
        return this;
    }

    @Override
    public ExecuteProgramRequest withApiVersion(String value) {
        setApiVersion(value);
        return this;
    }

    @Override
    public ExecuteProgramRequest withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    @Override
    public ExecuteProgramRequest withSources(List<SystemRef> value) {
        setSources(value);
        return this;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Program
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlAttribute(name = "name", required = true)
        protected String name;

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

        public ExecuteProgramRequest.Program withName(String value) {
            setName(value);
            return this;
        }

    }

}
