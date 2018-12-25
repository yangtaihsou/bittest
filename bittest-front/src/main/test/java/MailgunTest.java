

import com.alibaba.fastjson.JSON;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class MailgunTest {
    @Test
    public void testMailSend(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-9677e36228a0c5c4ec5b1d87a3d57162"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "No-Reply <kuan.yang@bittest123.com>");
        formData.add("to", "yangtaishou <yangkuan@yeah.net>");
        formData.add("subject", "Hello yangtaishou bittest123");
        formData.add("text", "Congratulations yangtaishou, you just sent an email with Mailgun!  You are truly awesome!");
        ClientResponse response =   webResource.path("/bittest123.com/messages")
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, formData);
          System.out.println("----"+ JSON.toJSONString(response));
        System.out.println("----"+ response.getStatus());

    }

  /*  @Test
    public void testMailSend(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "0acd7986044bbb399a86cb80377e0d4e-bd350f28-2d2c994f"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandboxed663e52ede9462d8680f1d290b07f7f.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandboxed663e52ede9462d8680f1d290b07f7f.mailgun.org>");
        formData.add("to", "yangtaishou <yangkuan@yeah.net>");
        formData.add("subject", "Hello yangtaishou");
        formData.add("text", "Congratulations yangtaishou, you just sent an email with Mailgun!  You are truly awesome!");
        ClientResponse response =   webResource
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, formData);
        System.out.println("----"+ JSON.toJSONString(response));
        System.out.println("----"+ response.getStatus());

    }*/
    @Test
    public void testMailGun(){

        MailgunTest crunchifyClient = new MailgunTest();
        crunchifyClient.getCtoFResponse();
        crunchifyClient.getFtoCResponse();
    }

    private void getFtoCResponse() {
        try {

            Client client = Client.create();
            WebResource webResource2 = client.resource("http://localhost:8080/CrunchifyRESTJerseyExample/crunchify/ftocservice/90");
            ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
            if (response2.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
            }

            String output2 = response2.getEntity(String.class);
            System.out.println("\n============getFtoCResponse============");
            System.out.println(output2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCtoFResponse() {
        try {

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/CrunchifyRESTJerseyExample/crunchify/ctofservice/40");
            ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("============getCtoFResponse============");
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
