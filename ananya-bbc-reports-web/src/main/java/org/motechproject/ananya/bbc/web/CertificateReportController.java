package org.motechproject.ananya.bbc.web;

import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.domain.ReportServeModel;
import org.motechproject.ananya.bbc.service.CertificateCourseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CertificateReportController extends BaseController {

    private CertificateCourseReportService reportService;

    @Autowired
    public CertificateReportController(CertificateCourseReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report/certificatecourse")
    public ModelAndView show() {
        return new ModelAndView("certificatecourse/report");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report/certificatecourse/data")
    @ResponseBody
    public ReportServeModel serveData(HttpServletRequest request) {
        int from = Integer.parseInt(request.getParameter("from"));
        int to = Integer.parseInt(request.getParameter("to"));
        List<CertificateCourseUsage> usageReport = reportService.getUsageReport(from, to);

        if(paramsContainsHeaderAndCount(request.getParameterMap())) {
            return new ReportServeModel(reportService.getCount(), usageReport);
        }

        return new ReportServeModel(usageReport);
    }

    private boolean paramsContainsHeaderAndCount(Map parameterMap) {
        return parameterMap.containsKey("count") && parameterMap.containsKey("header");
    }
}
