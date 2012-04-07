package org.motechproject.ananya.bbc.velocity;

import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AnanyaView extends VelocityLayoutView {

    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map runtimeProperties = ((CachingPropertyHolder) this.getApplicationContext().getBean("config")).resolvedProperties();
        Context context = super.createVelocityContext(model, request, response);
        context.put("app", runtimeProperties.get("app"));
        return context;
    }

}
