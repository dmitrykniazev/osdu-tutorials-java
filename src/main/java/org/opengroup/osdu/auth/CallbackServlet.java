package org.opengroup.osdu.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CallbackServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        String body = prepareResponseBody(req);
        PrintWriter writer = resp.getWriter();
        writer.write(body);
        writer.flush();
    }

    private String prepareResponseBody(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        result.put("access_token", req.getSession().getAttribute("access_token"));
        result.put("id_token", req.getSession().getAttribute("id_token"));
        result.put("refresh_token", req.getSession().getAttribute("refresh_token"));

        try {
            return mapper.writeValueAsString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
