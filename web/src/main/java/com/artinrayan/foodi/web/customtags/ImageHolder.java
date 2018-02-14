package com.artinrayan.foodi.web.customtags;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.Attachment;
import exception.BusinessException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tldgen.annotations.Attribute;
import org.tldgen.annotations.BodyContent;
import org.tldgen.annotations.Tag;

import javax.inject.Inject;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by asus on 7/19/2017.
 */
@Component
@Tag(dynamicAttributes = true, bodyContent = BodyContent.SCRIPTLESS, example = "<m:showImage ownerId=\"1\"/>")
public class ImageHolder extends SimpleTagSupport {

    @Attribute(required=true)
    private int ownerId;

    @Inject
    HostService hostService;

    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("tag lib");
        HostService hostService = WebApplicationContextUtils.getRequiredWebApplicationContext(
                ((PageContext)getJspContext()).getServletContext()).getBean(HostService.class);

        try {
            Host host = hostService.findHostByHostId(ownerId);
            if (host.getAttachments().size() > 0) {
                Attachment attachment = (Attachment) host.getAttachments().toArray()[0];

                JspWriter out = getJspContext().getOut();
                byte[] encodeBase64 = Base64.encode(attachment.getFileContent());
                String base64Encoded = new String(encodeBase64, "UTF-8");
                String outputStr = "data:image" + (attachment.getFileType() != null ? attachment.getFileType()
                        : "jpg") + ";base64," + base64Encoded;
                out.print(outputStr);
            }
        }
        catch (BusinessException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            throw new JspException("Error: " + e.getMessage());
        }
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
