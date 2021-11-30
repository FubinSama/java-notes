package com.wfb.learn.framework.ioc;

/**
 * <p>
 * Test2
 * </p>
 *
 * @author wfb
 * @version 1.0
 * @since 2021-10-02 20:10:14
 */
public class Test2 {
    private String name1;
    private Integer int1;
    private String name2;
    private Integer int2;

    // 实例工厂方法
    static class Test2Factory{
        public Test2 createTest2(String name1, Integer int1, String name2, Integer int2) {
            return Test2.createTest2(name1, int1, name2, int2);
        }
    }

    // 静态工厂方法
    public static Test2 createTest2(String name1, Integer int1, String name2, Integer int2) {
        return new Test2(name1, int1, name2, int2);
    }

    // 无参构造
    public Test2() {}

    // 全参构造
    public Test2(String name1, Integer int1, String name2, Integer int2) {
        this.name1 = name1;
        this.int1 = int1;
        this.name2 = name2;
        this.int2 = int2;
    }

    // getter
    public String getName1() {
        return name1;
    }

    public Integer getInt1() {
        return int1;
    }

    public String getName2() {
        return name2;
    }

    public Integer getInt2() {
        return int2;
    }

    // setter
    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "name1='" + name1 + '\'' +
                ", int1=" + int1 +
                ", name2='" + name2 + '\'' +
                ", int2=" + int2 +
                '}';
    }
}
