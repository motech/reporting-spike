package org.motechproject.ananya.bbc.request;

import org.joda.time.DateTime;

import java.util.Date;

public class UsageReportRequest {

    private Date startDate;
    private Date endDate;
    private int offset;
    private int limit;
    private String district;
    private String block;
    private String village;
    private String baseSortBy;
    private String baseSortOrder;
    private String sortBy;
    private String sortOrder;

    public UsageReportRequest(DateTime startDate, DateTime endDate, int offset, int limit, String district,
                              String block, String village, String baseSortBy, String baseSortOrder, String sortBy, String sortOrder) {
        this.startDate = startDate.toDate();
        this.endDate = endDate.toDate();
        this.offset = offset;
        this.limit = limit;
        this.district = district;
        this.block = block;
        this.village = village;
        this.baseSortBy = baseSortBy;
        this.baseSortOrder = baseSortOrder;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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

    public String getBaseSortBy() {
        return baseSortBy;
    }

    public String getBaseSortOrder() {
        return baseSortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public boolean getLocationProvided() {
        boolean val =
            (district != null && !district.equalsIgnoreCase("all")) ||
            (block != null && !block.equalsIgnoreCase("all")) ||
            (village != null && !village.equalsIgnoreCase("all"));
        return val;
    }
}
