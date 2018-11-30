package com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06;

import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official.ConcreteComponent;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official.ConcreteDecoratorA;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.official.ConcreteDecoratorB;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice.ConcretePerson;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice.Hat;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice.Jacket;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice.Shoes;
import com.tiny.demo.firstlinecode.designpattern.talkbig.chapter06.practice.Trousers;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:    装饰模式
 * Created by tiny on 2017/10/18.
 * Version:
 */

public class DecorateActivity extends BaseActivity {
    @BindView(R.id.btn_decorate_official)
    Button btnDecorateOfficial;
    @BindView(R.id.btn_decorate_xiaoming1)
    Button btnDecorateXiaoming1;
    @BindView(R.id.btn_decorate_xiaoming2)
    Button btnDecorateXiaoming2;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_decorate;
    }

    @Override
    protected void buildContentView() {

    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_decorate_official)
    public void onViewClicked() {
        //官方demo
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();

        d1.setComponent(c);
        d2.setComponent(d1);
        d2.Operate();
    }

    @OnClick(R.id.btn_decorate_xiaoming1)
    public void onBtnDecorateXiaoming1Clicked() {
        ConcretePerson person = new ConcretePerson("蛋蛋的爸爸");
        Hat hat = new Hat();
        hat.decorate(person);
        Jacket jacket = new Jacket();
        jacket.decorate(hat);
        Trousers trousers = new Trousers();
        trousers.decorate(jacket);
        Shoes shoes = new Shoes();
        shoes.decorate(trousers);
        shoes.show();
    }

    @OnClick(R.id.btn_decorate_xiaoming2)
    public void onBtnDecorateXiaoming2Clicked() {
        ConcretePerson person = new ConcretePerson("蛋蛋的妈妈");
        Shoes shoes = new Shoes();
        shoes.decorate(person);
        Trousers trousers = new Trousers();
        trousers.decorate(shoes);
        Jacket jacket = new Jacket();
        jacket.decorate(trousers);
        Hat hat = new Hat();
        hat.decorate(jacket);
        hat.show();
    }
}
