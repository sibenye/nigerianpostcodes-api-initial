package com.elsynergy.nigerianpostcodes.web.exception;

import java.util.HashMap;
import java.util.Map;

public class CodeMessage
{
    private static Map<Integer, String> codeMessageMap;

    static
    {
        codeMessageMap = new HashMap<Integer, String>();
        codeMessageMap.put(0, "Success");
        codeMessageMap.put(100, "Invalid Request");
        codeMessageMap.put(404, "Not Found");
        codeMessageMap.put(555, "Internal Server Error");
    }

    public static String getMessage(final Integer code)
    {
        return codeMessageMap.get(code);
    }

}
