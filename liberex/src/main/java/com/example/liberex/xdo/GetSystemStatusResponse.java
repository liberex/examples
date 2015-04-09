
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
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
 *         &lt;element name="AppInfo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="apiVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AppConfig" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SystemProperties" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
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
    "appInfo",
    "appConfig",
    "systemProperties",
    "params"
})
@XmlRootElement(name = "GetSystemStatusResponse")
public class GetSystemStatusResponse
    extends AbstractResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "AppInfo", required = true)
    protected GetSystemStatusResponse.AppInfo appInfo;
    @XmlElement(name = "AppConfig")
    protected GetSystemStatusResponse.AppConfig appConfig;
    @XmlElement(name = "SystemProperties")
    protected GetSystemStatusResponse.SystemProperties systemProperties;
    @XmlElement(name = "Param")
    protected List<Param> params;

    /**
     * Gets the value of the appInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetSystemStatusResponse.AppInfo }
     *     
     */
    public GetSystemStatusResponse.AppInfo getAppInfo() {
        return appInfo;
    }

    /**
     * Sets the value of the appInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSystemStatusResponse.AppInfo }
     *     
     */
    public void setAppInfo(GetSystemStatusResponse.AppInfo value) {
        this.appInfo = value;
    }

    /**
     * Gets the value of the appConfig property.
     * 
     * @return
     *     possible object is
     *     {@link GetSystemStatusResponse.AppConfig }
     *     
     */
    public GetSystemStatusResponse.AppConfig getAppConfig() {
        return appConfig;
    }

    /**
     * Sets the value of the appConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSystemStatusResponse.AppConfig }
     *     
     */
    public void setAppConfig(GetSystemStatusResponse.AppConfig value) {
        this.appConfig = value;
    }

    /**
     * Gets the value of the systemProperties property.
     * 
     * @return
     *     possible object is
     *     {@link GetSystemStatusResponse.SystemProperties }
     *     
     */
    public GetSystemStatusResponse.SystemProperties getSystemProperties() {
        return systemProperties;
    }

    /**
     * Sets the value of the systemProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSystemStatusResponse.SystemProperties }
     *     
     */
    public void setSystemProperties(GetSystemStatusResponse.SystemProperties value) {
        this.systemProperties = value;
    }

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

    public void setParams(List<Param> value) {
        this.params = null;
        List<Param> draftl = this.getParams();
        draftl.addAll(value);
    }

    public GetSystemStatusResponse withAppInfo(GetSystemStatusResponse.AppInfo value) {
        setAppInfo(value);
        return this;
    }

    public GetSystemStatusResponse withAppConfig(GetSystemStatusResponse.AppConfig value) {
        setAppConfig(value);
        return this;
    }

    public GetSystemStatusResponse withSystemProperties(GetSystemStatusResponse.SystemProperties value) {
        setSystemProperties(value);
        return this;
    }

    public GetSystemStatusResponse withParams(Param... values) {
        if (values!= null) {
            for (Param value: values) {
                getParams().add(value);
            }
        }
        return this;
    }

    public GetSystemStatusResponse withParams(Collection<Param> values) {
        if (values!= null) {
            getParams().addAll(values);
        }
        return this;
    }

    public GetSystemStatusResponse withParams(List<Param> value) {
        setParams(value);
        return this;
    }

    @Override
    public GetSystemStatusResponse withSources(SystemRef... values) {
        if (values!= null) {
            for (SystemRef value: values) {
                getSources().add(value);
            }
        }
        return this;
    }

    @Override
    public GetSystemStatusResponse withSources(Collection<SystemRef> values) {
        if (values!= null) {
            getSources().addAll(values);
        }
        return this;
    }

    @Override
    public GetSystemStatusResponse withErrors(CodeMessage... values) {
        if (values!= null) {
            for (CodeMessage value: values) {
                getErrors().add(value);
            }
        }
        return this;
    }

    @Override
    public GetSystemStatusResponse withErrors(Collection<CodeMessage> values) {
        if (values!= null) {
            getErrors().addAll(values);
        }
        return this;
    }

    @Override
    public GetSystemStatusResponse withWarnings(CodeMessage... values) {
        if (values!= null) {
            for (CodeMessage value: values) {
                getWarnings().add(value);
            }
        }
        return this;
    }

    @Override
    public GetSystemStatusResponse withWarnings(Collection<CodeMessage> values) {
        if (values!= null) {
            getWarnings().addAll(values);
        }
        return this;
    }

    @Override
    public GetSystemStatusResponse withResponseDttm(DateTime value) {
        setResponseDttm(value);
        return this;
    }

    @Override
    public GetSystemStatusResponse withTransactionId(String value) {
        setTransactionId(value);
        return this;
    }

    @Override
    public GetSystemStatusResponse withDuration(Integer value) {
        setDuration(value);
        return this;
    }

    @Override
    public GetSystemStatusResponse withSources(List<SystemRef> value) {
        setSources(value);
        return this;
    }

    @Override
    public GetSystemStatusResponse withErrors(List<CodeMessage> value) {
        setErrors(value);
        return this;
    }

    @Override
    public GetSystemStatusResponse withWarnings(List<CodeMessage> value) {
        setWarnings(value);
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
     *       &lt;sequence>
     *         &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
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
    public static class AppConfig
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Param")
        protected List<Param> params;

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

        public void setParams(List<Param> value) {
            this.params = null;
            List<Param> draftl = this.getParams();
            draftl.addAll(value);
        }

        public GetSystemStatusResponse.AppConfig withParams(Param... values) {
            if (values!= null) {
                for (Param value: values) {
                    getParams().add(value);
                }
            }
            return this;
        }

        public GetSystemStatusResponse.AppConfig withParams(Collection<Param> values) {
            if (values!= null) {
                getParams().addAll(values);
            }
            return this;
        }

        public GetSystemStatusResponse.AppConfig withParams(List<Param> value) {
            setParams(value);
            return this;
        }

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
     *       &lt;sequence>
     *         &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="apiVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;anyAttribute processContents='lax'/>
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
    public static class AppInfo
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Param")
        protected List<Param> params;
        @XmlAttribute(name = "apiVersion", required = true)
        protected String apiVersion;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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

        public void setParams(List<Param> value) {
            this.params = null;
            List<Param> draftl = this.getParams();
            draftl.addAll(value);
        }

        public GetSystemStatusResponse.AppInfo withParams(Param... values) {
            if (values!= null) {
                for (Param value: values) {
                    getParams().add(value);
                }
            }
            return this;
        }

        public GetSystemStatusResponse.AppInfo withParams(Collection<Param> values) {
            if (values!= null) {
                getParams().addAll(values);
            }
            return this;
        }

        public GetSystemStatusResponse.AppInfo withApiVersion(String value) {
            setApiVersion(value);
            return this;
        }

        public GetSystemStatusResponse.AppInfo withParams(List<Param> value) {
            setParams(value);
            return this;
        }

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
     *       &lt;sequence>
     *         &lt;element name="Param" type="{urn:example.com:liberex:v1}Param" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
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
    public static class SystemProperties
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Param")
        protected List<Param> params;

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

        public void setParams(List<Param> value) {
            this.params = null;
            List<Param> draftl = this.getParams();
            draftl.addAll(value);
        }

        public GetSystemStatusResponse.SystemProperties withParams(Param... values) {
            if (values!= null) {
                for (Param value: values) {
                    getParams().add(value);
                }
            }
            return this;
        }

        public GetSystemStatusResponse.SystemProperties withParams(Collection<Param> values) {
            if (values!= null) {
                getParams().addAll(values);
            }
            return this;
        }

        public GetSystemStatusResponse.SystemProperties withParams(List<Param> value) {
            setParams(value);
            return this;
        }

    }

}
