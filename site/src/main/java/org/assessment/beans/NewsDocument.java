package org.assessment.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;

@Node(jcrType="assessment:newsdocument")
public class NewsDocument extends BaseDocument{

    public String getTitle() {
        return getProperty("assessment:title");
    }
    
    public String getSummary() {
        return getProperty("assessment:summary");
    }
    
    public Calendar getDate() {
        return getProperty("assessment:date");
    }

    public HippoHtml getHtml(){
        return getHippoHtml("assessment:body");    
    }

    /**
     * Get the imageset of the newspage
     *
     * @return the imageset of the newspage
     */
    public HippoGalleryImageSetBean getImage() {
        return getLinkedBean("assessment:image", HippoGalleryImageSetBean.class);
    }


}
