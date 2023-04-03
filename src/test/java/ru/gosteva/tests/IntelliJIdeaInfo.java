package ru.gosteva.tests;

import java.util.List;

public class IntelliJIdeaInfo {

    public String name;
    public String version;
    public String productVendor;
    public List<String> modules;
    public List<String> fileExtensions;
    public Lunch launch;

    public static class Lunch {
        public String os;
        public String arch;
        public String launcherPath;
    }
}
