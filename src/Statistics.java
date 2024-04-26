import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
      private int totalTraffic;
      private LocalDateTime minTime;
      private LocalDateTime maxTime;
      private HashSet<LogEntry> listPages;
      private HashSet<String> existPages = new HashSet<>();
      private HashSet<String> nonExistPages = new HashSet<>();
      private HashMap<String, Integer> typeSysCount = new HashMap<>();
      private HashMap<String, Integer> browserCount = new HashMap<>();

      UserAgent userAgent;
      public Statistics() {
          this.totalTraffic = 0;
            this.minTime = null;
            this.maxTime = null;
            this.listPages = new HashSet<>();
      }
      public HashMap<String, Double> getTypeSysCount() {
            HashMap<String, Double> sysStatistic = new HashMap<>();
            int totalSys = 0;
            for (int count :  typeSysCount.values()) {
                  totalSys += count;
            }
            for (String sys : typeSysCount.keySet()) {
                  int count  = typeSysCount.get(sys);
                          double fraction = (double) count / totalSys;
                  sysStatistic.put(sys, fraction);
            }
            return sysStatistic;
      }

      public HashMap<String, Double> getBrowserCountt() {
            HashMap<String, Double> browserStatistic = new HashMap<>();
            int totalBrowser = 0;
            for (int count :  browserCount.values()) {
                  totalBrowser += count;
            }
            for (String browser : typeSysCount.keySet()) {
                  int count  = typeSysCount.get(browser);
                  double fraction = (double) count / totalBrowser;
                  browserStatistic.put(browser, fraction);
            }
            return browserStatistic;
      }

      public HashSet<String> getExistPages() {
            return existPages;
      }

      public HashSet<String> getNonExistPages() {
            return nonExistPages;
      }

      public void addEntry(LogEntry logEntry) {
            totalTraffic += logEntry.getDataSize();
            LocalDateTime logEntryTime = logEntry.getDateTime();

            if (this.minTime == null || logEntryTime.isBefore(minTime)) {
                  minTime = logEntryTime;
            }
            if (this.maxTime == null || logEntryTime.isBefore(maxTime)) {
                  maxTime = logEntryTime;
            }
            if (logEntry.getResponseCode() == 200) {
                  existPages.add(logEntry.path);
            }
            if (logEntry.getResponseCode() == 404) {
                  nonExistPages.add(logEntry.path);
            }
            typeSysCount.put(String.valueOf(userAgent.getTypeSys()), typeSysCount.getOrDefault(userAgent.getTypeSys(), 0) + 1);
            browserCount.put(String.valueOf(userAgent.getBrowser()), browserCount.getOrDefault(userAgent.getBrowser(), 0) + 1);

          listPages.add(logEntry);
      }

      public int getTrafficRate() {
            if (listPages.isEmpty()) {
                  return 0;
            }
            Duration duration = Duration.between(maxTime, minTime);
            double hour = duration.toHours();
            return (int) (totalTraffic / hour);
      }
}


