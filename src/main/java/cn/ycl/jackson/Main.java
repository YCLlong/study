package cn.ycl.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        try {
            testMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * json序列化和反序列化测试
     */
    public static void textJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();
        person.setName("ycl");
        person.setAge(24);
        person.setLastLoginTime(new Date());
        List<String> likes = new ArrayList<String>(3);
        likes.add("电脑游戏");
        likes.add("美女");
        likes.add("美食");
        person.setLikes(likes);
        Map<String,Integer> course = new HashMap<String,Integer>();
        course.put("语文",81);
        course.put("数学",98);
        course.put("体育",100);
        person.setCourse(course);
        readAndWrite(mapper,"Person.json",person);
    }


    public static void textXml() throws IOException {
        XmlMapper mapper = new XmlMapper();
        Person person = new Person();
        person.setName("ycl");
        person.setAge(24);
        person.setLastLoginTime(new Date());
        List<String> likes = new ArrayList<String>(3);
        likes.add("电脑游戏");
        likes.add("美女");
        likes.add("美食");
        person.setLikes(likes);
        Map<String,Integer> course = new HashMap<String,Integer>();
        course.put("语文",81);
        course.put("数学",98);
        course.put("体育",100);
        person.setCourse(course);
        readAndWrite(mapper,"Person.xml",person);
    }

    private static void readAndWrite(ObjectMapper mapper,String fileName,Person person) throws IOException{
        //自动寻找并且注册所有模块
        mapper.findAndRegisterModules();
        // 写为字符串
        String text = mapper.writeValueAsString(person);
        // 写为文件
        mapper.writeValue(new File("C:\\Users\\ycl\\Desktop\\" + fileName), person);
        // 写为字节流
        byte[] bytes = mapper.writeValueAsBytes(person);
        //发现字节流其实就是字符串的内容
        File file = new File("C:\\Users\\ycl\\Desktop\\Byte"  + fileName);
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file));
        bout.write(bytes);
        bout.close();

        System.out.println(text);
        // 从字符串中读取
        Person newPerson = mapper.readValue(text,Person.class);
        System.out.println("从字符串中读取：" + newPerson);

        // 从字节流中读取
        newPerson = mapper.readValue(bytes, Person.class);
        System.out.println("从字节流中读取：" + newPerson);

        // 从文件中读取
        newPerson = mapper.readValue(new File("C:\\Users\\ycl\\Desktop\\" + fileName), Person.class);
        System.out.println("从从文件中读取：" + newPerson);
        //读取集合
        JsonNode node = mapper.readTree(text);
        JsonNode course =  node.get("course");
        Integer intV = course.get("语文").asInt();
        System.out.println("intV=" + intV);
        //读取数组
        JsonNode likes = node.get("likes");
        if(likes.isArray()){
            for(JsonNode tempNode:likes){
                System.out.println(tempNode.asText());
            }
        }
    }


    /**
     * 测试设置
     *
     * @return
     */
    public static ObjectMapper testSetting() {
        ObjectMapper mapper = new ObjectMapper();
        // 美化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 允许序列化空的POJO类（否则会抛出异常）
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 把java.util.MyData, Calendar输出为数字（时间戳）
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 在遇到未知属性的时候不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 强制JSON 空字符串("")转换为null对象值:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许没有引号的字段名（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 强制转义非ASCII字符
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        return mapper;
    }

    /**
     * 节点测试
     */
    public static void testMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("name", "yitian");
        map.put("interests", new String[]{"pc games", "music"});

        String text = mapper.writeValueAsString(map);
        System.out.println(text);

        Map<String, Object> map2 = mapper.readValue(text, new TypeReference<Map<String, Object>>() {});
        System.out.println(map2);

        JsonNode root = mapper.readTree(text);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();

        System.out.println("name:" + name + " age:" + age);
    }
}
