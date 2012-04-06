package org.motechproject.ananya.bbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportServeModel {
    public List<ReportModel> content;
    public Integer count;
    public Map<String, String> header;

    public ReportServeModel() {
        content = new ArrayList<ReportModel>();
        content.add(new ReportModel("413423414","Imdad","Patna"));
        content.add(new ReportModel("351413413","Sneha","Patna"));
        content.add(new ReportModel("413413451","Vijay","Patna"));
        content.add(new ReportModel("747574557","Vivek","Patna"));
        content.add(new ReportModel("768467457","Pulkit","Patna"));
        content.add(new ReportModel("452525234","Arvind","Patna"));
        content.add(new ReportModel("896967878","Sush","Patna"));
        content.add(new ReportModel("123147846","Priya","Patna"));
        content.add(new ReportModel("978545634","Varun","Patna"));
        content.add(new ReportModel("756346534","Shanz","Patna"));
        header = new HashMap<String, String>();
        header.put("msisdn", "MSISDN #");
        header.put("name", "Name");
        header.put("district", "District");
        count = content.size();
    }
}
