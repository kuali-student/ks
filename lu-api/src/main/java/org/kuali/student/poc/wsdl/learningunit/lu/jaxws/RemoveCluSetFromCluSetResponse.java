
package org.kuali.student.poc.wsdl.learningunit.lu.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by the CXF 2.0.4-incubator
 * Fri Apr 18 16:30:07 EDT 2008
 * Generated source version: 2.0.4-incubator
 * 
 */

@XmlRootElement(name = "removeCluSetFromCluSetResponse", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeCluSetFromCluSetResponse", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")

public class RemoveCluSetFromCluSetResponse {

    @XmlElement(name = "return")
    private org.kuali.student.poc.xsd.learningunit.lu.dto.Status _return;

    public org.kuali.student.poc.xsd.learningunit.lu.dto.Status get_return() {
        return this._return;
    }
    
    public void set_return( org.kuali.student.poc.xsd.learningunit.lu.dto.Status new_return ) {
        this._return = new_return;
    }
    
}

