package ru.gosteva.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadFilesFromZipTest {

    private ClassLoader cl = ReadFilesFromZipTest.class.getClassLoader();

    @Test
    void readPdfFromZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("archived_files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            boolean found = false;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("Rentaphoto_client_form.pdf")) {
                    found = true;
                    PDF pdf = new PDF(zs);
                    Assertions.assertEquals("anketa_gotovaya.eps", pdf.title);
                    Assertions.assertEquals("Влад", pdf.author);
                    Assertions.assertTrue(pdf.text.contains("Анкета"));
                }
            }
            if (!found) {
                Assertions.fail("File not found");
            }
        }
    }

    @Test
    void readXlsFromZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("archived_files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            boolean found = false;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("Wild Escape Results.xlsx")) {
                    found = true;
                    XLS xls = new XLS(zs);
                    Assertions.assertEquals(("Участник"),xls.excel.getSheetAt(0).getRow(1).getCell(1).toString());
                    Assertions.assertEquals(("Результат на 13:00"),xls.excel.getSheetAt(0).getRow(1).getCell(2).toString());
                    Assertions.assertEquals(("Время финиша"),xls.excel.getSheetAt(0).getRow(1).getCell(3).toString());
                }
            }
            if (!found) {
                Assertions.fail("File not found");
            }
        }
    }

    @Test
    void readCsvFromZip()throws Exception {
        try (InputStream is = cl.getResourceAsStream("archived_files.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            boolean found = false;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("distance.csv")) {
                    found = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    List<String[]> string = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[] {"Moscow","Vladivostok","9127"}, string.get(1));
                }
            }
            if (!found) {
                Assertions.fail("File not found");
            }
        }
    }
}
