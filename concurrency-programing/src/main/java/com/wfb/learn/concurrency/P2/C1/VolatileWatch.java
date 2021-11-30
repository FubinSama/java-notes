package com.wfb.learn.concurrency.P2.C1;

/**
 * @className: VolatileWatch
 * @description: 查看volatile关键字生成的汇编代码。
 * 需要添加VM参数:`-XX:+TraceClassLoading -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=log/VolatileWatch.log`
 * 还要下载HSDIS(HotSpot disassembler)。Arch系统可以使用`yay -S aur/java8-openjdk-hsdis`下载
 * 不过这种方法同样会打印java包下的代码，很难找到某个类的某个方法的汇编代码
 * 设置的VM参数会将日志打印到`log/VolatileWatch.log`，
 * 推荐使用[jitwatch](https://github.com/AdoptOpenJDK/jitwatch/releases)查看该log
 * 只需在config中配置source路径和class路径，然后在`open log`中选中该log，然后点击`start`即可。然后选择想要查看的类，点击`TriView`。
 * 如果提示`not jit compiled`，可以通过点击`inlined into`查看
 * @author: wfb
 * @date: 2021-08-21 22:26:47
 * @version: 1.0
 */
public class VolatileWatch {

    static class Singleton {
        private static volatile Singleton instance;

        private Singleton() {}

        public static Singleton getInstance() {
            if (instance != null) return instance;
            synchronized (Singleton.class) {
                if (instance != null) return instance;
                return instance = new Singleton();
            }
        }
        public void doSomething() {
            System.out.println("Singleton instance do something!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10_005; i++) {
            Singleton instance = Singleton.getInstance();
            instance.doSomething();
        }
    }
}