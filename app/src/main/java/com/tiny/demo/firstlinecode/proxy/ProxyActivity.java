package com.tiny.demo.firstlinecode.proxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.proxy.static0.StaticProxyDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 代理入口
 * https://www.jianshu.com/p/6f6bb2f0ece9
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）
 * @date 2018/8/7 11:51
 * modify by:
 * modify date:
 * modify content:
 */

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        /**
         * 代理的实现分为：\n

         静态代理：代理类是在编译时就实现好的。也就是说 Java 编译完成后代理类是一个实际的 class 文件。\n
         动态代理：代理类是在运行时生成的。也就是说 Java 编译完之后并没有实际的 class 文件，而是在运行时动态生成的类字节码，并加载到JVM中。
         */

        findViewById(R.id.btn_proxy0).setOnClickListener(v -> StaticProxyDemo.main(new String[]{}));

        findViewById(R.id.btn_proxy1).setOnClickListener(v->{
            ClassLoader classLoader = StaticProxyDemo.class.getClassLoader();
            try {
                Class clazz = classLoader.loadClass("com.tiny.demo.firstlinecode.proxy.dynamic.DynamicProxyDemo1");
                Method m = clazz.getMethod("main",String[].class);
                m.invoke(null,new Object[]{new String[]{}});
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
