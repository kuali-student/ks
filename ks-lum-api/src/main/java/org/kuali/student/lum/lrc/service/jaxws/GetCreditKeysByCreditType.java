
package org.kuali.student.lum.lrc.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Wed Apr 22 09:59:25 EDT 2009
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getCreditKeysByCreditType", namespace = "http://student.kuali.org/wsdl/lrc")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCreditKeysByCreditType", namespace = "http://student.kuali.org/wsdl/lrc")

public class GetCreditKeysByCreditType {

    @XmlElement(name = "creditTypeKey")
    private java.lang.String creditTypeKey;

    public java.lang.String getCreditTypeKey() {
        return this.creditTypeKey;
    }

    public void setCreditTypeKey(java.lang.String newCreditTypeKey)  {
        this.creditTypeKey = newCreditTypeKey;
    }

}

