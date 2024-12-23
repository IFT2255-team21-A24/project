package ca.umontreal.dir.ift2255.team21;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.*;
import java.text.Normalizer;

public class Normaliser {
    public static String stringNormaliser(String s) {
        if (s.isBlank()){
            return "";
        }
        String normalised = s.toLowerCase();

        normalised = Normalizer.normalize(normalised, Normalizer.Form.NFD);
        normalised = normalised.replaceAll("\\p{M}", "");
        normalised = normalised.replaceAll("\\s+", "");
        return normalised;
    }
    public static boolean checker_for_text_similarity(String s1, String s2) {
        StringMetric stringMetrics = StringMetrics.jaroWinkler();
        double similarity = stringMetrics.compare(s1, s2);

        if (similarity < 0.88) {
            return false;
        }
        return true;
    }
}
