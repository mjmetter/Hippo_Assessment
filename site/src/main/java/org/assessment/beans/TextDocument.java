package org.assessment.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@Node(jcrType="assessment:textdocument")
public class TextDocument extends BaseDocument{
    
    public String getTitle() {
        return getProperty("assessment:title");
    }

    public String getSummary() {
        return getProperty("assessment:summary");
    }
    
    public HippoHtml getHtml(){
        return getHippoHtml("assessment:body");    
    }

}
