package com.termmed.owl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sarah on 23-03-17.
 */
public class URITransformer {

    /**
     *  Transforms the string to compatible format uri
     * @param string
     * @return string compatible
     */
    public String transform(String string) {
        string = string.replaceAll("\\/", " or ");
        Pattern pattern = Pattern.compile("\\([^\\(]*?\\)$");
        Matcher m = pattern.matcher(string);
        String prefix = "";
        while (m.find()) {
            prefix = m.group().replaceAll("\\(","").replaceAll("\\)","") + "/";
        }
        List uriList = Arrays.asList(string.split("\\([^\\(]*?\\)$"));
        string = prefix + uriList.get(0);
        return clean(string);
    }

    /**
     *  Cleans special characters
     * @param string
     * @return string clean
     */
    private String clean(String string) {
        string = string.replaceAll("[\\+:,.\\(\\)]", " ");
        string = string.replaceAll("\\<", "lt");
        string = string.replaceAll("\\>", "gt");
        string = string.replaceAll("\\=", "eq");
        string = string.replaceAll("\\s+", "_").replaceAll("_+$", "");
        return string;
    }
}
