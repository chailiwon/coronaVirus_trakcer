package com.ew.coronavirustracker.service;

import cn.hutool.http.HttpUtil;
import com.ew.coronavirustracker.model.LocationStat;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class VirusDataService {

    @Value("${virus.data.url}")
    private String DATA_URL;

    private List<LocationStat> allStats = new ArrayList<>();

    public List<LocationStat> getAllStats() {
        return allStats;
    }

    public void setAllStats(List<LocationStat> allStats) {
        this.allStats = allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException {
        List<LocationStat> newStats = new ArrayList<>();
        String content = HttpUtil.get(DATA_URL);
        StringReader csv = new StringReader(content);
        CSVParser records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csv);
        for (CSVRecord record : records) {
            String province = record.get("Province_State");
            String admin = record.get("Admin2");

            LocationStat stat = new LocationStat();
            stat.setAdmin(admin);
            stat.setState(province);
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int preCases = Integer.parseInt(record.get(record.size() - 2));
            stat.setLatestTotalCases(latestCases);
            stat.setDiffFormPreDay(latestCases - preCases);
            newStats.add(stat);
        }
        this.allStats = newStats;
    }
}


