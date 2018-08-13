package io.github.guilhermedelemos.ariacrawler;

import io.github.guilhermedelemos.ariacrawler.model.Site;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StrategySiteCSV extends StategySite {

    private String separator = ";";
    public static final int POS_RANK = 0;
    public static final int POS_URL = 1;
    public static final int POS_DATE = 2;

    public StrategySiteCSV(String fileName) throws IOException {
        super(fileName);
    }

    public List<Site> read() {
        try {
            if (this.in == null || !this.in.ready()) {
                return null;
            }
            List<Site> sites = new ArrayList<>();
            String line;
            while ((line = this.in.readLine()) != null) {
                String[] data = line.split(this.separator);
                sites.add(new Site(Integer.parseInt(data[POS_RANK]),
                        data[POS_URL],
                        new SimpleDateFormat("yyyy-MM-dd").parse(data[POS_DATE])));
            }
            this.in.close();
            return sites;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
