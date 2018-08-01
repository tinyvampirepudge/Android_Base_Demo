package com.tiny.demo.firstlinecode.templates.bean;

import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/11/7.
 * Version:
 */

public class TestObject {
    /**
     * handlerName : showExtPlotSelection
     * data : {"type":1,"data":[{"type":"VOL_MA","text":"成交量","act":1,"chart_type":0,"name":"","tab_type":0,"unit":""},{"type":"MACD","text":"MACD","act":0,"chart_type":0,"name":"","tab_type":0,"unit":""},{"type":"KDJ","text":"KDJ","act":0,"chart_type":0,"name":"","tab_type":0,"unit":""},{"type":"MAIN_FUND","text":"主力资金","act":0,"chart_type":0,"name":"","tab_type":0,"unit":""}]}
     */

    private String handlerName;
    private DataBeanX data;

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "handlerName='" + handlerName + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * type : 1
         * data : [{"type":"VOL_MA","text":"成交量","act":1,"chart_type":0,"name":"","tab_type":0,"unit":""},{"type":"MACD","text":"MACD","act":0,"chart_type":0,"name":"","tab_type":0,"unit":""},{"type":"KDJ","text":"KDJ","act":0,"chart_type":0,"name":"","tab_type":0,"unit":""},{"type":"MAIN_FUND","text":"主力资金","act":0,"chart_type":0,"name":"","tab_type":0,"unit":""}]
         */

        private int type;
        private List<DataBean> data;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "type=" + type +
                    ", data=" + data +
                    '}';
        }

        public static class DataBean {
            /**
             * type : VOL_MA
             * text : 成交量
             * act : 1
             * chart_type : 0
             * name :
             * tab_type : 0
             * unit :
             */

            private String type;
            private String text;
            private int act;
            private int chart_type;
            private String name;
            private int tab_type;
            private String unit;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getAct() {
                return act;
            }

            public void setAct(int act) {
                this.act = act;
            }

            public int getChart_type() {
                return chart_type;
            }

            public void setChart_type(int chart_type) {
                this.chart_type = chart_type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTab_type() {
                return tab_type;
            }

            public void setTab_type(int tab_type) {
                this.tab_type = tab_type;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "type='" + type + '\'' +
                        ", text='" + text + '\'' +
                        ", act=" + act +
                        ", chart_type=" + chart_type +
                        ", name='" + name + '\'' +
                        ", tab_type=" + tab_type +
                        ", unit='" + unit + '\'' +
                        '}';
            }
        }
    }
}
