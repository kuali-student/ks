
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

@XmlRootElement(name = "removeSimpleAntirequisite", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeSimpleAntirequisite", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")

public class RemoveSimpleAntirequisite {

    @XmlElement(name = "cluId")
    private java.lang.String cluId;
    @XmlElement(name = "antireqCluId")
    private java.lang.String antireqCluId;

    public java.lang.String getCluId() {
        return this.cluId;
    }
    
    public void setCluId( java.lang.String newCluId ) {
        this.cluId = newCluId;
    }
    
    public java.lang.String getAntireqCluId() {
        return this.antireqCluId;
    }
    
    public void setAntireqCluId( java.lang.String newAntireqCluId ) {
        this.antireqCluId = newAntireqCluId;
    }
    
}

