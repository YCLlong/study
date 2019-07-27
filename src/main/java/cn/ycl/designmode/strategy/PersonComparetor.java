package cn.ycl.designmode.strategy;

import java.util.Comparator;

/**
 * 策略模式中的角色是：算法蔟，变动的部分，接口 Comparator 的算法实现
 */
public class PersonComparetor implements Comparator<Person> {
    @Override
    /**
     * o1表示当前对象
     * o2表示要比较的对象
     */
    public int compare(Person o1, Person o2) {
        if(o1.getGender() > o2.getGender()){
            return 1;
        }else if(o1.getGender() == o2.getGender()){
            if(o1.getAge() > o2.getAge()){
                return 1;
            }else if(o1.getAge() == o2.getAge()){
                if(o1.getName().equals(o2.getName())){
                    return 0;
                }else {
                    return o1.getName().compareTo(o2.getName());
                }
            }else {
                return -1;
            }
        }else {
            return -1;
        }
    }
}
