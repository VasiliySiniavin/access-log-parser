import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    // Поля класса
    final String ipAddress;
    final LocalDateTime dateTime;
    final HttpMethod method;
    final String path;
    final int responseCode;
    final long dataSize;
    final String referer;
    final String userAgent;

    public LogEntry(String logString) {
        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(.*?)\\] \"(\\w+) (.*?)\" (\\d+) (\\d+) \"(.*?)\" \"(.*?)\"";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(logString);

        if (matcher.matches()) {
            this.ipAddress = matcher.group(1);
            this.dateTime = LocalDateTime.parse(matcher.group(2), DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z",  Locale.ENGLISH));
            this.method = HttpMethod.valueOf(matcher.group(3));
            this.path = matcher.group(4);
            this.responseCode = Integer.parseInt(matcher.group(5));
            this.dataSize = Long.parseLong(matcher.group(6));
            this.referer = matcher.group(7);
            this.userAgent = matcher.group(8);
        } else {
            throw new IllegalArgumentException("Неверный формат строки: " + logString);
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public long getDataSize() {
        return dataSize;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }
    enum HttpMethod {
        GET, POST, PUT, DELETE
    }
}