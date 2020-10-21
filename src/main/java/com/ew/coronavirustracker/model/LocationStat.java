package com.ew.coronavirustracker.model;

public class LocationStat {
    private String state;
    private String admin;
    private int latestTotalCases;
    private int diffFormPreDay;

    public int getDiffFormPreDay() {
        return diffFormPreDay;
    }

    public void setDiffFormPreDay(int diffFormPreDay) {
        this.diffFormPreDay = diffFormPreDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "LocationStat{" +
                "state='" + state + '\'' +
                ", country='" + admin + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
