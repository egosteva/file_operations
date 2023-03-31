package ru.gosteva.tests;

import java.util.List;

public class IntelliJIdeaInfo {

    public String name;
    public String version;
    public String buildNumber;
    public String productVendor;
    public List<String> bundledPlugins;
    public List<String> modules;
    public List<String> fileExtensions;
    public static Lunch launch;

    public static class Lunch {
     //   public String launch;
        public String os;
        public String arch;
        public String launcherPath;
    }
}
