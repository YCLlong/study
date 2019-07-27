package cn.ycl.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @JacksonXmlProperty注解有三个属性，namespace和localname属性用于指定XML命名空间的名称，isAttribute指定该属性作为XML的属性（）还是作为子标签（）.
 *
 * @JacksonXmlRootElement注解有两个属性，namespace和localname属性用于指定XML根元素命名空间的名称。
 *
 * @JacksonXmlText注解将属性直接作为未被标签包裹的普通文本表现。
 *
 * @JacksonXmlCData将属性包裹在CDATA标签中。
 *
 * 术语 CDATA 指的是不应由 XML 解析器进行解析的文本数据（Unparsed Character MyData）。
 * 在xml中英文问号“?”是可以被正常解析的，但是以下这几种符号是不能正常解析的：分别是“&”、“<”、“>”、“’”、“””。
 * “<” 会产生错误，因为解析器会把该字符解释为新元素的开始。
 * “&” 也会产生错误，因为解析器会把该字符解释为字符实体的开始。
 *
 * 解决方法一：
 * 把实体引用编码后使用，即
 * 把“&” 编码为 “&”
 * 把“<” 编码为“<”
 * 把“>” 编码为 “>”
 * 把“’” 编码为 “'”
 * 把“”” 编码为 “"”
 *
 * 解决方法二：使用<![CDATA[ sql 语句 ]]>
 * 某些文本，比如 JavaScript 代码，包含大量 “<” 或 “&” 字符。为了避免错误，可以将脚本代码定义为 CDATA。
 * CDATA 部分中的所有内容都会被解析器忽略。CDATA 部分由 "<![CDATA[" 开始，由 "]]>" 结束。
 * 在mapper文件中写sql语句时，遇到特殊字符时，如：< 等，建议使用
 * <![CDATA[ sql 语句 ]]>标记，将sql语句包裹住，不被解析器解析
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//根节点的名称
@JsonRootName("Person11")
//在类上的注解，指定属性名称不参加序列化和反序列化
@JsonIgnoreProperties({"name1", "age"})
class Person{
    //如果设置了这个，那么序列化和反序列化以注解内的名称为准，不设置就是默认的属性名称
    @JsonProperty("name1")

    //JacksonXmlCData在字符串属性或者字符串集合上加上CDATA标签
    @JacksonXmlCData
    String name;

    Integer age;

    //时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //加上这个就不会参加序列化和反序列化
    @JsonIgnoreProperties
    Date lastLoginTime;

    List<String> likes;

    Map<String,Integer> course;
}