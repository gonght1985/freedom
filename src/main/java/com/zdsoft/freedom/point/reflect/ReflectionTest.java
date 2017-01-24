package com.zdsoft.freedom.point.reflect;

public class ReflectionTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setId("10000");
        user.setName("中国电信");
        
        try {
            System.out.println(Reflection.getProperty(user, "name"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
