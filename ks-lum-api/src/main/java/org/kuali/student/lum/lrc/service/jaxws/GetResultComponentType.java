
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

@XmlRootElement(name = "getResultComponentType", namespace = "http://student.kuali.org/wsdl/lrc")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getResultComponentType", namespace = "http://student.kuali.org/wsdl/lrc")

public class GetResultComponentType {

    @XmlElement(name = "resultComponentTypeKey")
    private java.lang.String resultComponentTypeKey;

    public java.lang.String getResultComponentTypeKey() {
        return this.resultComponentTypeKey;
    }

    public void setResultComponentTypeKey(java.lang.String newResultComponentTypeKey)  {
        this.resultComponentTypeKey = newResultComponentTypeKey;
    }

}

