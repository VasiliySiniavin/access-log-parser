import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
      private int totalTraffic;
      private LocalDateTime minTime;
      private LocalDateTime maxTime;
      private HashSet<LogEntry> listPages;

      public Statistics() {
            this.totalTraffic = 0;
            this.minTime = null;
            this.maxTime = null;
            this.listPages = new HashSet<>();
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


