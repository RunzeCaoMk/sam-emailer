package edu.uchicago.caor.emailer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.json.JSONObject;
import org.junit.Test;

public class AppTest {
    @Test
    public void successfulResponse() {
//        assert(true);
        App app = new App();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subject", "message.getSubject()");
        jsonObject.put("body", "message.getBody()");
        jsonObject.put("email", "message.getEmail()");

        APIGatewayProxyResponseEvent result = app.handleRequest(
                new APIGatewayProxyRequestEvent().withBody(jsonObject.toString()),
                null
        );
        assertEquals(200, result.getStatusCode().intValue());
        assertEquals("application/json", result.getHeaders().get("Content-Type"));
        String content = result.getBody();
        assertNotNull(content);
        assertTrue(content.contains("\"subject\""));
        assertTrue(content.contains("\"body\""));
        assertTrue(content.contains("\"email\""));
    }
}
