package com.bittest.platform.bg.common.utils;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
    
    private static final int SUCCESS_CODE = 200;
    private static final String MAIL_CONTEXT_TYPE_HTML = "html";
    private static final String MAIL_CONTEXT_TYPE_TEXT = "text";

    public static boolean sendHtml(String subject, String recipients, String message) {
    		return send(subject, recipients, message, MAIL_CONTEXT_TYPE_HTML);
        
    }
    
    public static boolean sendText(String subject, String recipients, String message) {
    		return send(subject, recipients, message, MAIL_CONTEXT_TYPE_TEXT);
    }
    
    private static boolean send(String subject, String recipients, String message, String type) {
    		try {
            Client client = ClientBuilder.newClient(); 

            WebTarget mgRoot = client.target("https://api.mailgun.net/v3");

            Form reqData = new Form();
            reqData.param("from", "No-Reply <kuan.yang@bittest123.com>");
            reqData.param("to", recipients);
            reqData.param("subject", subject);
            reqData.param(type, message);

            Response response = mgRoot.path("/bittest123.com/messages").resolveTemplate("domain", "bittest123.com")
                    .request(MediaType.APPLICATION_FORM_URLENCODED)
                    .buildPost(Entity.entity(reqData, MediaType.APPLICATION_FORM_URLENCODED)).invoke();
            logger.info("sendmail response|subject={}, recipients={}, response={}", subject, recipients, response);
            return response != null && response.getStatus() == SUCCESS_CODE;
        } catch (Exception e) {
            logger.error("邮件发送失败|subject={}, recipients={}, message={}", subject, recipients, message, e);
            return false;
        }
    }
    public static void main(String[] args){
        sendHtml("test","kuan.yang@bittest123.com;yangkuan@yeah.net","test123");
    }
}
