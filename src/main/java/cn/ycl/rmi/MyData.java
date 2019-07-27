package cn.ycl.rmi;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
//不实现 java.io.Serializable; 接口也不会报错
public class MyData<T> {
    //加上lombok这个注解之后，配合类上的RequiredArgsConstructor注解，说明生成的构造函数中存在这个成员属性作为参数
    @NonNull
    int code;

    @NonNull
    String msg;

    T data;
}
