
package org.kuali.student.poc.wsdl.learningunit.lu.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by the CXF 2.0.4-incubator
 * Fri Apr 18 16:30:06 EDT 2008
 * Generated source version: 2.0.4-incubator
 * 
 */

@XmlRootElement(name = "createClu", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createClu", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")

public class CreateClu {

    @XmlElement(name = "luTypeId")
    private java.lang.String luTypeId;
    @XmlElement(name = "cluCreateInfo")
    private org.kuali.student.poc.xsd.learningunit.lu.dto.CluCreateInfo cluCreateInfo;

    public java.lang.String getLuTypeId() {
        return this.luTypeId;
    }
    
    public void setLuTypeId( java.lang.String newLuTypeId ) {
        this.luTypeId = newLuTypeId;
    }
    
    public org.kuali.student.poc.xsd.learningunit.lu.dto.CluCreateInfo getCluCreateInfo() {
        return this.cluCreateInfo;
    }
    
    public void setCluCreateInfo( org.kuali.student.poc.xsd.learningunit.lu.dto.CluCreateInfo newCluCreateInfo ) {
        this.cluCreateInfo = newCluCreateInfo;
    }
    
}

