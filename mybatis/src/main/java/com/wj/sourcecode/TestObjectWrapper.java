package com.wj.sourcecode;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.beans.factory.config.DeprecatedBeanWarner;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * mybatis源码反射中的核心类测试
 */
public class TestObjectWrapper {


    public static void main(String[] args) {

        //反射创建对象
        ObjectFactory objectFactory = new DefaultObjectFactory();
        User user1 = objectFactory.create(User.class);

        ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        Reflector reflector = reflectorFactory.findForClass(User.class);
        Constructor<?> defaultConstructor = reflector.getDefaultConstructor();
        String[] getablePropertyNames = reflector.getGetablePropertyNames();
        String[] setablePropertyNames = reflector.getSetablePropertyNames();
        System.out.println(defaultConstructor.getName());
        System.out.println(Arrays.toString(getablePropertyNames));
        System.out.println(Arrays.toString(setablePropertyNames));

        //利用ObjectWarpper进行赋值操作

        User user = new User();
        //对反射创建的对象进行处理（核心处理类）
        MetaObject metaObject = MetaObject.forObject(user1,objectFactory,objectWrapperFactory,reflectorFactory);
//        ObjectWrapper objectWrapper = new BeanWrapper(metaObject,user);
        ObjectWrapper objectWrapper = metaObject.getObjectWrapper();
        String[] getterNames = objectWrapper.getGetterNames();
        String[] setterNames = objectWrapper.getSetterNames();
        System.out.println(Arrays.toString(getterNames));
        System.out.println(Arrays.toString(setterNames));

        PropertyTokenizer prop = new PropertyTokenizer("name");
        objectWrapper.set(prop,"test");
        System.out.println(user1);

    }

}
