package org.motechproject.ananya.bbc.request;

import org.joda.time.DateTime;

public class UsageReportRequest {

    private DateTime startDate;
    private DateTime endDate;
    private int offset;
    private int limit;
    private String district;
    private String block;
    private String village;

    public UsageReportRequest(DateTime startDate, DateTime endDate, int offset, int limit, String district, String block, String village) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.offset = offset;
        this.limit = limit;
        this.district = district;
        this.block = block;
        this.village = village;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public String getDistrict() {
        return district;
    }

    public String getBlock() {
        return block;
    }

    public String getVillage() {
        return village;
    }
}
