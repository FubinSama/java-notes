package com.wfb.learn.framework.ioc;

/**
 * <p>
 *  测试bean
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-09-29 17:34:21
 */
public class Test {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Test createTest() {
        return new Test();
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                '}';
    }
}
