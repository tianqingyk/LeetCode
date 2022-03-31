package microsoft;

import java.util.*;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 3/30/2022
 */
public class JsonValidator {

    public boolean isValid(String json) {
        return isValid(json, 0, json.length());
    }

    private boolean isValid(String json, int start, int end) {
        if (json == null || json.length() == 0 || end - start == 0) return true;
        char startChar = json.charAt(start);
        switch (startChar) {
            case '{': {
                if (json.charAt(end - 1) != '}') return false;
                List<String> result = new ArrayList<>();
                if (!divideJson(result, json, start + 1, end - 1)) return false;
                if(!checkObject(result)) return false;
            }
            break;
            case '[': {
                if (json.charAt(end - 1) != ']') return false;
                List<String> result = new ArrayList<>();
                if (!divideJson(result, json, start + 1, end - 1)) return false;
                if(!checkArray(result)) return false;
            }
            break;
            default:if(!checkUnit(json)) return false;
                break;
        }

        return true;
    }


    private boolean divideJson(List<String> result, String json, int start, int end) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = start; i < end; i++) {
            char c = json.charAt(i);
            sb.append(c);
            switch (c) {
                case '{': {
                    if (sb.length() == 0 || sb.charAt(0) == '{') {
                        count++;
                    }
                }
                break;
                case '}': {
                    if (sb.length() == 0 || sb.charAt(0) == '{') {
                        count--;
                    }
                    break;
                }
                case '[': {
                    if (sb.length() == 0 || sb.charAt(0) == '[') {
                        count++;
                    }
                }
                break;
                case ']': {
                    if (sb.length() == 0 || sb.charAt(0) == ']') {
                        count--;
                    }
                }
                break;
                case ',': {
                    if (count == 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        if (sb.length() == 0) return false;
                        result.add(sb.toString());
                        sb = new StringBuilder();
                    }
                }
                break;
                default:
                    break;
            }
        }
        if (count > 0 || sb.length() == 0) return false;
        result.add(sb.toString());
        return true;
    }

    private boolean checkObject(List<String> jsonMap) {
        Set<String> keySet = new HashSet<>();
        for (String keyValue : jsonMap){
            if (!checkKeyValue(keyValue)) return false;
            String[] strArr = keyValue.split(":", 2);
            String key = strArr[0];
            String value = strArr[1];
            if(keySet.contains(key)) return false;
            keySet.add(key);
            if (!isValid(value)) return false;
        }
        return true;
    }

    private boolean checkKeyValue(String json) {
        String[] strs = json.split(":", 2);
        if(strs.length < 2) return false;
        if(!checkString(strs[0])) return false;
        return true;
    }

    private boolean checkArray(List<String> jsonArray) {
        for (String elem : jsonArray){
            if(!isValid(elem)) return false;
        }
        return true;
    }

    private boolean checkString(String json) {
        if (json == null) return false;
        if (json.charAt(0) == '"' && json.charAt(json.length() - 1) == '"') return true;
        return false;
    }

    private boolean checkNumber(String num) {
        for(char c : num.toCharArray()){
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    private boolean checkUnit(String json) {
        if(json.length() == 0 ) return false;
        char start = json.charAt(0);
        if (start == '"') return checkString(json);
        if(Character.isDigit(start)) return checkNumber(json);
        return false;
    }

    public static void main(String[] args) {
        JsonValidator jv = new JsonValidator();
        String json = "{\"a\":1,\"{\":1}";
        System.out.println(jv.isValid(json));
    }

}
