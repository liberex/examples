
package com.example.liberex.xdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *     &lt;extension base="{urn:example.com:liberex:v1}AbstractResponse">
 *       &lt;sequence>
 *         &lt;element name="Container" type="{urn:example.com:liberex:v1}Container" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "containers"
})
@XmlRootElement(name = "ExecuteProgramResponse")
public class ExecuteProgramResponse
    extends AbstractResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Container")
    protected List<Container> containers;

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

    public ExecuteProgramResponse withContainers(Container... values) {
        if (values!= null) {
            for (Container value: values) {
                getContainers().add(value);
            }
        }
        return this;
    }

    public ExecuteProgramResponse withContainers(Collection<Container> values) {
        if (values!= null) {
            getContainers().addAll(values);
        }
        return this;
    }

    public ExecuteProgramResponse withContainers(List<Container> value) {
        setContainers(value);
        return this;
    }

    @Override
    public ExecuteProgramResponse withSources(SystemRef... values) {
        if (values!= null) {
            for (SystemRef value: values) {
                getSources().add(value);
            }
        }
        return this;
    }

    @Override
    public ExecuteProgramResponse withSources(Collection<SystemRef> values) {
        if (values!= null) {
            getSources().addAll(values);
        }
        return this;
    }

    @Override
    public ExecuteProgramResponse withErrors(CodeMessage... values) {
        if (values!= null) {
            for (CodeMessage value: values) {
                getErrors().add(value);
            }
        }
        return this;
    }

    @Override
    public ExecuteProgramResponse withErrors(Collection<CodeMessage> values) {
        if (values!= null) {
            getErrors().addAll(values);
        }
        return this;
    }

    @Override
    public ExecuteProgramResponse withWarnings(CodeMessage... values) {
        if (values!= null) {
            for (CodeMessage value: values) {
                getWarnings().add(value);
            }
        }
        return this;
    }

    @Override
    public ExecuteProgramResponse withWarnings(Collection<CodeMessage> values) {
        if (values!= null) {
            getWarnings().addAll(values);
        }
        return this;
    }

    @Override
    public ExecuteProgramResponse withResponseDttm(DateTime value) {
        setResponseDttm(value);
        return this;
    }

    @Override
    public ExecuteProgramResponse withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    @Override
    public ExecuteProgramResponse withDuration(Integer value) {
        setDuration(value);
        return this;
    }

    @Override
    public ExecuteProgramResponse withSources(List<SystemRef> value) {
        setSources(value);
        return this;
    }

    @Override
    public ExecuteProgramResponse withErrors(List<CodeMessage> value) {
        setErrors(value);
        return this;
    }

    @Override
    public ExecuteProgramResponse withWarnings(List<CodeMessage> value) {
        setWarnings(value);
        return this;
    }

}
