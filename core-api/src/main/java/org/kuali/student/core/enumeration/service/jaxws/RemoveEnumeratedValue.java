
package org.kuali.student.core.enumeration.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1.3
 * Tue Dec 09 14:28:48 PST 2008
 * Generated source version: 2.1.3
 */

@XmlRootElement(name = "removeEnumeratedValue", namespace = "http://student.kuali.org/wsdl/EnumerationService")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeEnumeratedValue", namespace = "http://student.kuali.org/wsdl/EnumerationService", propOrder = {"enumerationKey","code"})

public class RemoveEnumeratedValue {

    @XmlElement(name = "enumerationKey")
    private java.lang.String enumerationKey;
    @XmlElement(name = "code")
    private java.lang.String code;

    public java.lang.String getEnumerationKey() {
        return this.enumerationKey;
    }

    public void setEnumerationKey(java.lang.String newEnumerationKey)  {
        this.enumerationKey = newEnumerationKey;
    }

    public java.lang.String getCode() {
        return this.code;
    }

    public void setCode(java.lang.String newCode)  {
        this.code = newCode;
    }

}

