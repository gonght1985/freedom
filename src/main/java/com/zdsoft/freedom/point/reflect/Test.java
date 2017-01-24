package com.zdsoft.freedom.point.reflect;

public class Test {
    
    public Test(){
        System.out.println("我是初始化");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test t1 = new Test();
        Class tClass = t1.getClass();
        //一个Class对象描述了一个特定类的特定属性，而这个方法就是返回String形式的该类的简要描述。由于历史原因，对数组的Class对象调用该方法会产生奇怪的结果。
        System.out.println(tClass.getName());
        System.out.println(tClass.getClassLoader().toString());
        
        try {
            //该方法可以根据某个Class对象产生其对应类的实例。需要强调的是，它调用的是此类的默认构造方法
            Test test = (Test) tClass.newInstance();
            
            System.out.println("返回某子类所对应的直接父类所对应的Class对象 "+test.getClass().getSuperclass().getName());
            
            int[] ints = new int[]{1,2,3};

            Class class1 = ints.getClass();

            Class class2 = class1.getComponentType();
            
            System.out.println("该方法针对数组对象的Class对象，可以得到该数组的组成元素所对应对象的Class对象 "+class2.toString());
            System.out.println("判定此Class对象所对应的是否是一个数组对象。"+class1.isArray());
            
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
