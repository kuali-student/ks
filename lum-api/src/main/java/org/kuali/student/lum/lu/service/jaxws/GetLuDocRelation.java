
package org.kuali.student.lum.lu.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Feb 17 15:12:57 EST 2009
 * Generated source version: 2.1.4
 */

@XmlRootElement(name = "getLuDocRelation", namespace = "http://student.kuali.org/lum/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLuDocRelation", namespace = "http://student.kuali.org/lum/lu")

public class GetLuDocRelation {

    @XmlElement(name = "luDocRelationId")
    private java.lang.String luDocRelationId;

    public java.lang.String getLuDocRelationId() {
        return this.luDocRelationId;
    }

    public void setLuDocRelationId(java.lang.String newLuDocRelationId)  {
        this.luDocRelationId = newLuDocRelationId;
    }

}

