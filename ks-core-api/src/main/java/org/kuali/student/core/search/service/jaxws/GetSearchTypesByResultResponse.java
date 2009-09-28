
package org.kuali.student.core.search.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.core.search.dto.SearchTypeInfo;

/**
 * This class was generated by Apache CXF 2.1.3
 * Tue Jan 27 08:59:03 EST 2009
 * Generated source version: 2.1.3
 */

@XmlRootElement(name = "getSearchTypesByResultResponse", namespace = "http://student.kuali.org/wsdl/search")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSearchTypesByResultResponse", namespace = "http://student.kuali.org/wsdl/search")

public class GetSearchTypesByResultResponse {

    @XmlElement(name = "return")
    private java.util.List<SearchTypeInfo> _return;

    public java.util.List<SearchTypeInfo> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<SearchTypeInfo> new_return)  {
        this._return = new_return;
    }

}

