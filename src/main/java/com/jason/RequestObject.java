package com.jason;

import org.json.JSONObject;

import java.util.Iterator;

public class RequestObject {

    private String requestId;
    private String alphanumericString;

    public String id() {
        return requestId;
    }

    public String getAlphanumericString() {
        return alphanumericString;
    }

    public RequestObject(String body) {

        JSONObject jsonObject = new JSONObject(body);
        Iterator<String> keys = jsonObject.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            this.requestId = key;
            this.alphanumericString = (String) jsonObject.get(key);
        }

    }

}
