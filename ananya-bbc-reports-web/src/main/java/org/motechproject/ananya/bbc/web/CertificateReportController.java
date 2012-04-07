package org.motechproject.ananya.bbc.web;

import org.motechproject.ananya.bbc.ReportModel;
import org.motechproject.ananya.bbc.ReportServeModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/report")
public class CertificateReportController extends BaseController {
    @RequestMapping(method = RequestMethod.GET, value = "/serve")
    public @ResponseBody ReportServeModel serve(HttpServletRequest request){
        ReportServeModel reportServeModel = new ReportServeModel();
        if(!request.getParameterMap().containsKey("count")){
            reportServeModel.count = null;
        }
        if(!request.getParameterMap().containsKey("header")){
            reportServeModel.header = null;
        }
        int from = Integer.parseInt(request.getParameter("from"));
        int to = Integer.parseInt(request.getParameter("to"));

        List<ReportModel> subList = new ArrayList<ReportModel>();
        for(int i = from; i < to; ++i){
            subList.add(reportServeModel.content.get(i));
        }
        reportServeModel.content = subList;

        return reportServeModel;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/base")
    public ModelAndView base(HttpServletRequest request){
        return new ModelAndView("certificatecourse/report");
    }
}
