
package org.kuali.student.lum.lu.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Thu Jan 21 10:05:23 PST 2010
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getCluLoRelationResponse", namespace = "http://student.kuali.org/wsdl/lu")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCluLoRelationResponse", namespace = "http://student.kuali.org/wsdl/lu")

public class GetCluLoRelationResponse {

    @XmlElement(name = "return")
    private org.kuali.student.lum.lu.dto.CluLoRelationInfo _return;

    public org.kuali.student.lum.lu.dto.CluLoRelationInfo getReturn() {
        return this._return;
    }

    public void setReturn(org.kuali.student.lum.lu.dto.CluLoRelationInfo new_return)  {
        this._return = new_return;
    }

}

