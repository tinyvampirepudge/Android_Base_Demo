package com.tiny.demo.firstlinecode.javareference;

import android.os.Bundle;
import android.widget.Button;

import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.javareference.bean.StudentBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc:    Java并发修改异常
 * Created by tiny on 2017/10/30.
 * Version:
 * http://www.cnblogs.com/pcheng/p/5336903.html
 * http://www.cnblogs.com/andy-zhou/p/5339683.html
 */

public class ConcurrentModificationExceptionActivity extends BaseActivity {
    @BindView(R.id.btn_cme_for)
    Button btnCmeFor;
    @BindView(R.id.btn_cme_foreach)
    Button btnCmeForeach;
    @BindView(R.id.btn_cme_iterator)
    Button btnCmeIterator;
    @BindView(R.id.btn_cme_for_delete)
    Button btnCmeForDelete;
    @BindView(R.id.btn_cme_mutil_thread_bug)
    Button btnCmeMutilThreadBug;
    @BindView(R.id.btn_cme_mutil_thread_result)
    Button btnCmeMutilThreadResult;
    private List<StudentBean> studentBeanList;
    private CopyOnWriteArrayList<StudentBean> studentBeanList1;

    private int indexDelete = 3;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_concurrent_modification_exception;
    }

    @Override
    protected void buildContentView() {
        studentBeanList = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            StudentBean bean = new StudentBean("tiny" + j, j + 1);
            studentBeanList.add(bean);
        }
        studentBeanList1 = new CopyOnWriteArrayList<>();
        for (int j = 0; j < 10; j++) {
            StudentBean bean = new StudentBean("tiny" + j, j + 1);
            studentBeanList1.add(bean);
        }
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_cme_for)
    public void onBtnCmeForClicked() {
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
        //删除单个的元素，下面两个方法都没有问题。
        for (int j = 0; j < studentBeanList.size(); j++) {
            if (indexDelete == studentBeanList.get(j).getId()) {
//                studentBeanList.remove(j);//list.remove(j)没有问题。
                studentBeanList.remove(studentBeanList.get(j));
            }
        }
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
    }

    @OnClick(R.id.btn_cme_foreach)
    public void onBtnCmeForeachClicked() {
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
        for (StudentBean bean : studentBeanList) {
            if (indexDelete == bean.getId()) {
                studentBeanList.remove(bean);//并发修改异常。
            }
        }
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
    }


    @OnClick(R.id.btn_cme_for_delete)
    public void onViewClicked() {
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
        //这样删除也没问题，不过容易fail fast.
        int deleteIndex = -1;
        for (int j = 0; j < studentBeanList.size(); j++) {
            StudentBean bean = studentBeanList.get(j);
            if (indexDelete == bean.getId()) {
                deleteIndex = j;
            }
        }
        if (-1 != deleteIndex) {
            studentBeanList.remove(deleteIndex);
        }
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
    }

    @OnClick(R.id.btn_cme_iterator)
    public void onBtnCmeIteratorClicked() {
        //推荐做法。
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
        Iterator<StudentBean> iterator = studentBeanList.iterator();
        while (iterator.hasNext()) {
            StudentBean bean = iterator.next();
            if (indexDelete < bean.getId()) {
                iterator.remove();
            }
        }
        LogUtils.INSTANCE.e("studentBeanList.toString() --> " + studentBeanList.toString());
    }

    @OnClick(R.id.btn_cme_mutil_thread_bug)
    public void onBtnCmeMutilThreadBugClicked() {
        modifyUnderMutilThread(studentBeanList);
    }

    private void modifyUnderMutilThread(List<StudentBean> list) {
        //多线程下的并发修改异常。
        Thread thread1 = new Thread() {
            public void run() {
                Iterator<StudentBean> iterator = list.iterator();
                while (iterator.hasNext()) {
                    StudentBean bean = iterator.next();
                    System.out.println(bean);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                Iterator<StudentBean> iterator = list.iterator();
                while (iterator.hasNext()) {
                    StudentBean bean = iterator.next();
                    if (bean.getId() == indexDelete)
                        iterator.remove();
                }
            }
        };
        thread1.start();
        thread2.start();
    }

    @OnClick(R.id.btn_cme_mutil_thread_result)
    public void onBtnCmeMutilThreadResultClicked() {
        /**
         * 一般有2种解决办法：
         * 1）在使用iterator迭代的时候使用synchronized或者Lock进行同步；
         * 2）使用并发容器CopyOnWriteArrayList代替ArrayList和Vector。
         */
        modifyUnderMutilThread(studentBeanList1);//这儿也有异常。。。。。。
    }
}
