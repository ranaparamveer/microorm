package org.chalup.microorm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

final class Methods {
    private Methods() {

    }

    static <T> boolean containsPublicGetterForField(String name, Object object) {

        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (("get" + name).equals(method.getName().toLowerCase()) && Modifier.isPublic(method.getModifiers()))
                return true;
        }
        return false;
    }

    static <T> Object getValueFromGetterForField(String name, Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (("get" + name).equals(method.getName().toLowerCase()) && Modifier.isPublic(method.getModifiers())) {
                try {
                    return method.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
