package com.nedap.archie.adl14.treewalkers;

import com.nedap.archie.adlparser.antlr.Adl14Parser;
import org.apache.commons.text.StringEscapeUtils;

/**
 * Created by pieter.bos on 15/10/15.
 */
public class Odin14ValueParser {

    public static String parseOdinStringValue(Adl14Parser.String_valueContext context) {
        if(context == null) {
            return null;
        }
        String text = context.getText();
        //regexps
        if(text.startsWith("/")) {
            return text;
        }
        if(text.startsWith("^")) {
            return text;
        }

        if(!text.startsWith("\"")) {
            throw new IllegalArgumentException("text should start with '/', '^' or '\"'");
        }
        //strip the quotes
        if(text.length() == 2) { // empty string, ""
            return "";
        }
        return StringEscapeUtils.unescapeJava(text.substring(1, text.length() - 1));
    }
}