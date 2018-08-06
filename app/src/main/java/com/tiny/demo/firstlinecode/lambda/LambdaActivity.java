package com.tiny.demo.firstlinecode.lambda;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tiny.demo.firstlinecode.R;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * lambda表达式
 *
 * @author wangjianzhou@qding.me
 * @version APP版本号（以修改为准）
 * @date 2018/8/6 9:21
 * modify by:
 * modify date:
 * modify content:
 */

public class LambdaActivity extends AppCompatActivity {

    @BindView(R.id.btn_lambda0)
    Button btnLambda0;
    @BindView(R.id.btn_lambda1)
    Button btnLambda1;
    @BindView(R.id.btn_lambda2)
    Button btnLambda2;
    @BindView(R.id.btn_lambda3)
    Button btnLambda3;
    @BindView(R.id.btn_lambda4)
    Button btnLambda4;
    @BindView(R.id.btn_lambda5)
    Button btnLambda5;
    @BindView(R.id.btn_lambda6)
    Button btnLambda6;
    @BindView(R.id.btn_lambda7)
    Button btnLambda7;
    @BindView(R.id.btn_lambda8)
    Button btnLambda8;
    @BindView(R.id.btn_lambda9)
    Button btnLambda9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        ButterKnife.bind(this);
    }

    /**
     * 用lambda表达式实现Runnable
     */
    @OnClick(R.id.btn_lambda0)
    public void onBtnLambda0Clicked() {
        //Java 8 之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8工具
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks!!")).start();
    }

    @OnClick(R.id.btn_lambda1)
    public void onBtnLambda1Clicked() {
        Button btnShow = findViewById(R.id.btn_lambda1_1);
        //Java8 之前
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Event handling without lambda is boring");
            }
        });

        //Java8方式：
        btnShow.setOnClickListener(v -> System.out.println("Light, Camera, Action !! Lambda expressions Rocks"));
    }

    /**
     * 使用lambda表达式对列表进行迭代
     */
    @OnClick(R.id.btn_lambda2)
    public void onBtnLambda2Clicked() {
        //Java8 之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        //Java 8之后：
        List<String> features1 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            features1.forEach(n -> System.out.println(n));

            //使用Java8的方法引用更方便，方法引用由::双冒号操作符标识
            features1.forEach(System.out::println);
        }
    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda3)
    public void onBtnLambda3Clicked() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which end with a :");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no languages :");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void filter(List<String> names, Predicate<String> condition) {
//        for (String name : names) {
//            if (condition.test(name)) {
//                System.out.println(name + "  ");
//            }
//        }

        //更好的方法
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    /**
     * Predicate合并
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda4)
    public void onBtnLambda4Clicked() {
        /**
         * 可以用and()、or()和xor()逻辑函数来合并Predicate，
         * 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
         */
        List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        names.stream()
                .filter(startWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.println("nMame, which starts with 'J' and four letter long is :" + n));
    }

    /**
     * Map，将对象进行转换
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda5)
    public void onBtnLambda5Clicked() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        //不使用lambda表达式为每个订单加上12%的税
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println("normal method: cost:" + cost + ",price:" + price);
        }

        //使用lambda表达式
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
    }

    /**
     * Reduce，折叠操作
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda5_1)
    public void onBtnLambda51Clicked() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        //为每个订单加上12%的税，然后求和
        //老方法
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total:" + total);

        //新方法
        double bill = costBeforeTax.stream()
                .map((cost) -> cost + .12 * cost)
                .reduce((sum, cost) -> sum + cost)
                .get();
        System.out.println("Bill:" + bill);
    }

    /**
     * 通过过滤创建一个String列表
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda6)
    public void onBtnLambda6Clicked() {
        List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jkl");
        //创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("Original list: %s, filtered list: %s %n", strList, filtered);
    }

    /**
     * 对列表的每个元素应用函数
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda7)
    public void onBtnLambda7Clicked() {
        //将字符串换成大写并用逗号连接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "france", "Germany", "Italy", "U.K", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    /**
     * distinct, 去重
     * 复制不同的值，创建一个子列表
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda8)
    public void onBtnLambda8Clicked() {
        //用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original list: %s, Square without duplicates: %s %n", numbers, distinct);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btn_lambda9)
    public void onBtnLambda9Clicked() {
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 6, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List :" + stats.getMax());
        System.out.println("Lowest prime umber in List :" + stats.getMin());
        System.out.println("Sum of all prime numbers :" + stats.getSum());
        System.out.println("Average of all prime numbers :" + stats.getAverage());
    }
}
