package com.tiny.demo.firstlinecode.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TextView相关工具类
 * @Author wangdandandefather
 * @Date 2019-12-06 22:48
 * @Version
 */
public class TxtUtil {

    /**
     * 匹配字符串中的特殊字符串，并给其设置颜色
     *
     * @param color
     * @param text
     * @param keyword
     * @return
     */
    public static SpannableString matchKeywordText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    /**
     * 匹配html标签，例如"<p>xxx</p>"这种格式
     */
    private static Pattern HTML_TAG_PATTERN = Pattern.compile("<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*?>");

    public static List<String> getResultsFromHtml(String htmlString) {
        List<String> results = new ArrayList<>();
        htmlString = replaceStyle(htmlString);
        if (htmlString != null && htmlString.length() > 0) {
            Matcher imageTagMatcher = HTML_TAG_PATTERN.matcher(htmlString);
            while (imageTagMatcher.find()) {
                String result = "";
                result = imageTagMatcher.group(1).trim();

                if (result != null && result.length() > 0) {
                    result = replaceStartTag(result);
                }

                results.add(result);
            }
        }
        return results;
    }

    /**
     * 替换掉html标签里面的style内容
     *
     * @param content
     * @return
     */
    public static String replaceStyle(String content) {
        if (content == null || content.length() == 0) {
            return content;
        }
        String regEx = " style=\"(.*?)\"";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        if (m.find()) {
            content = m.replaceAll("");
        }
        return content;
    }

    public static String replaceStartTag(String content) {
        if (content == null || content.length() == 0) {
            return content;
        }
        String regEx = "<[a-zA-Z]*?>([\\s\\S]*?)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        if (m.find()) {
            content = m.replaceAll("");
        }
        return content;
    }

    /**
     * 根据给定的宽度，自动缩小TextView的字体，step为1px
     *
     * @param tv
     * @param maxWidth
     * @param value
     */
    public static void adaptTextSize(TextView tv, float maxWidth, String value) {
        if (tv == null) {
            return;
        }
        if (maxWidth <= 0) {
            return;
        }
        if (TextUtils.isEmpty(value)) {
            return;
        }

        float tvWidth = tv.getPaint().measureText(value);

        // 如果超出边界，调整字体
        int count = 0;
        while (tvWidth > maxWidth) {
            //防止无限循环导致anr等问题
            if (count > 100) {
                break;
            }
            count++;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv.getTextSize() - 1);
            tvWidth = tv.getPaint().measureText(value);
        }

        tv.setText(value);
    }

    public static void main(String[] args) {
        String ss = "<ps>红花</ps><p>青草</p><p>游鱼</p><p>飞鸟</p>";
        List<String> results = getResultsFromHtml(ss);
        System.out.println("results:" + results);

        String ss1 = "<p><span style=\"white-space: normal;\"><span style=\"white-space: normal;\">冰心</span></span></p>";
        List<String> results1 = getResultsFromHtml(ss1);
        System.out.println("results1:" + results1);

        String ss2 = "<span>冰心";
        String results2 = replaceStartTag(ss2);
        System.out.println("results2:" + results2);
    }
}
