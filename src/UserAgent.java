public class UserAgent {
    private final String userAgentStr;
      private final TypeSys typeSys;
      private final Browser browser;
    public UserAgent(String userAgentStr) {
         this.userAgentStr = userAgentStr;
          this.typeSys = extractTypeSys(userAgentStr);
          this.browser = extractBrowser(userAgentStr);
    }

    public String getUserAgentStr() {
        return userAgentStr;
    }

       public TypeSys getTypeSys() {
           return typeSys;
       }

       public Browser getBrowser() {
           return browser;
       }

    private TypeSys extractTypeSys(String userAgentStr) {
        if (userAgentStr.contains("Windows")) {
            return TypeSys.WINDOWS;
        } else if (userAgentStr.contains("Mac OS")) {
            return TypeSys.MACOS;
        } else if (userAgentStr.contains("Linux")) {
            return TypeSys.LINUX;
        } else {
            return null;
        }
    }

    private Browser extractBrowser(String userAgentStr) {
        if (userAgentStr.contains("Edge")) {
            return Browser.EDGE;
        } else if (userAgentStr.contains("Firefox")) {
            return Browser.FIREFOX;
        } else if (userAgentStr.contains("Chrome")) {
            return Browser.CHROME;
        } else if (userAgentStr.contains("Opera")) {
            return Browser.OPERA;
        } else {
            return null;
        }
    }

    enum TypeSys {
        WINDOWS, MACOS, LINUX;
    }

    enum Browser {
        EDGE, FIREFOX, CHROME, OPERA;
    }
}
