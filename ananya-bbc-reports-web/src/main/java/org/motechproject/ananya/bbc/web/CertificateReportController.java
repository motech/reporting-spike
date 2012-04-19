package org.motechproject.ananya.bbc.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.domain.Location;
import org.motechproject.ananya.bbc.domain.ReportServeModel;
import org.motechproject.ananya.bbc.request.UsageReportRequest;
import org.motechproject.ananya.bbc.service.CertificateCourseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        final DateTime startDate = DateTime.parse(request.getParameter("startDate"), formatter);
        final DateTime endDate = DateTime.parse(request.getParameter("endDate"), formatter);
        final String district = request.getParameter("location_district");
        final String block = request.getParameter("location_block");
        final String village = request.getParameter("location_panchayat");
        final int from = Integer.parseInt(request.getParameter("from"));
        final int to = Integer.parseInt(request.getParameter("to"));
        final Map<String, String> processedSortParams = processSortParams(request.getParameter("sortBy"), request.getParameter("sortOrder"));
        final String sortBy = processedSortParams.get("sortBy");
        final String sortOrder = processedSortParams.get("sortOrder");
        final String baseSortBy = request.getParameter("baseSortBy");
        final String baseSortOrder = request.getParameter("baseSortOrder");

        // TODO: validation on location

        UsageReportRequest usageReportRequest = new UsageReportRequest(startDate, endDate, from, to - from, district,
                block, village, baseSortBy, baseSortOrder, sortBy == null ? null : Arrays.asList(sortBy.split(",")), sortOrder);

        List<CertificateCourseUsage> usageReport = reportService.getUsageReport(usageReportRequest);

        if(paramsContainsHeaderAndCount(request.getParameterMap())) {
            return new ReportServeModel(reportService.getCount(usageReportRequest), usageReport);
        }

        return new ReportServeModel(usageReport);
    }

    private boolean paramsContainsHeaderAndCount(Map parameterMap) {
        return parameterMap.containsKey("count") && parameterMap.containsKey("header");
    }

    private Map<String, String> processSortParams(String sortBy, String sortOrder){
        Map<String, String> result = new HashMap<String, String>();

        if("courseStartDate".equals(sortBy)){
            sortBy = "startYear " + sortOrder + ", startDay " + sortOrder;
            sortOrder = "";
        } else if ("courseEndDate".equals(sortBy)){
            sortBy = "endYear " + sortOrder + ", endDay " + sortOrder;
            sortOrder = "";
        }

        result.put("sortBy", sortBy);
        result.put("sortOrder", sortOrder);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report/certificatecourse/locations")
    @ResponseBody
    public List<Location> getLocations() {
        return reportService.getLocations();
    }
}
