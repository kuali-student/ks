
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

@XmlRootElement(name = "deleteCluRelation", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteCluRelation", namespace = "http://student.kuali.org/poc/wsdl/learningunit/lu")

public class DeleteCluRelation {

    @XmlElement(name = "cluId")
    private java.lang.String cluId;
    @XmlElement(name = "relatedCluId")
    private java.lang.String relatedCluId;
    @XmlElement(name = "luRelationTypeId")
    private java.lang.String luRelationTypeId;

    public java.lang.String getCluId() {
        return this.cluId;
    }
    
    public void setCluId( java.lang.String newCluId ) {
        this.cluId = newCluId;
    }
    
    public java.lang.String getRelatedCluId() {
        return this.relatedCluId;
    }
    
    public void setRelatedCluId( java.lang.String newRelatedCluId ) {
        this.relatedCluId = newRelatedCluId;
    }
    
    public java.lang.String getLuRelationTypeId() {
        return this.luRelationTypeId;
    }
    
    public void setLuRelationTypeId( java.lang.String newLuRelationTypeId ) {
        this.luRelationTypeId = newLuRelationTypeId;
    }
    
}

