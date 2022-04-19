package amazon.ood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangk
 * @projectName LeetCode
 * @data 4/19/2022
 */
public class FileSystem {

    Directory root;
    public FileSystem() {
        root = new Directory("");
    }

    public List<String> ls(String path) {
        File f = findFile(path);
        if(f == null) return new ArrayList<>();
        List<String> result = f.ls();
        result.sort(String::compareTo);
        return result;
    }

    private File findFile(String path){
        String[] nameArr = path.split("/");
        Directory cur = root;
        for(int i = 1; i < nameArr.length; i++){
            File f = cur.findFile(nameArr[i]);
            if(f == null || f instanceof ConcreteFile) return f;
            cur = (Directory) f;
        }
        return cur;
    }

    public void mkdir(String path) {
        String[] nameArr = path.split("/");
        Directory cur = root;
        for(int i = 1; i < nameArr.length; i++) {
            cur =  cur.mkdir(nameArr[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] fileNames = filePath.split("/");
        Directory cur = root;
        ConcreteFile file = null;
        for (int i = 1; i < fileNames.length; i++){
            if( i == fileNames.length - 1) file = cur.mkCf(fileNames[i]);
            else cur = cur.mkdir(fileNames[i]);
        }
        file.addContent(content);
    }

    public String readContentFromFile(String filePath) {
        ConcreteFile cf = (ConcreteFile) findFile(filePath);
        return cf.getContent();
    }
}

abstract class File{
    protected String name;

    File(String name){
        this.name = name;
    }

    abstract List<String> ls();
}

class ConcreteFile extends File{

    private StringBuilder content;
    ConcreteFile(String name) {
        super(name);
        content = new StringBuilder();
    }

    @Override
    public List<String> ls() {
        return new ArrayList<>(){{add(name);}};
    }

    public void addContent(String content){
        this.content.append(content);
    }

    public String getContent() {
        return content.toString();
    }
}

class Directory extends File{
    private Map<String, File> fileMap;

    Directory(String name) {
        super(name);
        this.fileMap = new HashMap<>();
    }

    @Override
    public List<String> ls() {
        return new ArrayList<>(fileMap.keySet());
    }

    public Directory mkdir(String name){
        return (Directory) fileMap.compute(name, (k,v) -> v == null ? new Directory(k) : v);
    }

    public ConcreteFile mkCf(String name){
        return (ConcreteFile) fileMap.compute(name, (k,v) -> v == null ? new ConcreteFile(k) : v);
    }

    public File findFile(String name){
        return fileMap.get(name);
    }
}
