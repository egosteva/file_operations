package ru.gosteva.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadJsonFileTest {

    private ClassLoader cl = ReadFilesFromZipTest.class.getClassLoader();

    @Test
    void readJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("modified-product-info.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            IntelliJIdeaInfo intelliJIdeaInfo = mapper.readValue(isr, IntelliJIdeaInfo.class);

            Assertions.assertEquals("IntelliJ IDEA", intelliJIdeaInfo.name);
            Assertions.assertEquals("2022.3.2", intelliJIdeaInfo.version);
            Assertions.assertEquals("JetBrains", intelliJIdeaInfo.productVendor);
            Assertions.assertEquals("Windows", intelliJIdeaInfo.launch.os);
            Assertions.assertEquals("amd64", intelliJIdeaInfo.launch.arch);
            Assertions.assertEquals("bin/idea64.exe", intelliJIdeaInfo.launch.launcherPath);
            Assertions.assertEquals(List.of("com.intellij.modules.all", "com.intellij.modules.coverage", "com.intellij.modules.json"),
                    intelliJIdeaInfo.modules);
            Assertions.assertEquals(List.of("*.gitignore", "*.gradle", "*.jar", "*.java"),
                    intelliJIdeaInfo.fileExtensions);
        }
    }
}
