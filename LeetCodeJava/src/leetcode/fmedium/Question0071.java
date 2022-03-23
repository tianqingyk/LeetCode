package leetcode.fmedium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 2022/2/26
 */
public class Question0071 {

    /**
     * 71. Simplify Path
     * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
     * <p>
     * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
     * <p>
     * The canonical path should have the following format:
     * <p>
     * The path starts with a single slash '/'.
     * Any two directories are separated by a single slash '/'.
     * The path does not end with a trailing '/'.
     * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
     * Return the simplified canonical path.
     */


    /**
     * Solution 1
     * Runtime: 3 ms, faster than 98.76% of Java online submissions for Simplify Path.
     * Memory Usage: 42.5 MB, less than 42.19% of Java online submissions for Simplify Path.
     */
    public String simplifyPath(String path) {
        List<String> directory = new ArrayList<>();
        path += '/';
        char[] pathArray = path.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = pathArray[i];
            if (c == '/') {
                if (sb.length() > 0) {
                    String str = sb.toString();
                    sb = new StringBuilder();
                    if (str.equals(".")) continue;
                    if (str.equals("..")) {
                        if (directory.size() > 0) directory.remove(directory.size() - 1);
                        continue;
                    }
                    directory.add(str);
                }
                continue;
            }
            sb.append(c);
        }

        sb = new StringBuilder();
        for (String s: directory) {
            sb.append('/').append(s);
        }
        if (sb.length() < 1) sb.append('/');
        return sb.toString();
    }
}
