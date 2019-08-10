package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 普通Object（包含数组、List、Map）
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 11:03
 * @Version
 */
public class ObjectSerializableDeepCopyBean implements Serializable {
    private String name;
    private int age;
    private List<ListBean> list;
    private Map<String, MapBean> map;
    private ArrayBean[] arrays;

    public ObjectSerializableDeepCopyBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public Map<String, MapBean> getMap() {
        return map;
    }

    public void setMap(Map<String, MapBean> map) {
        this.map = map;
    }

    public ArrayBean[] getArrays() {
        return arrays;
    }

    public void setArrays(ArrayBean[] arrays) {
        this.arrays = arrays;
    }

    @Override
    public String toString() {
        return "ObjectSerializableDeepCopyBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", list=" + list +
                ", map=" + map +
                ", arrays=" + Arrays.toString(arrays) +
                '}';
    }

    public static class ListBean implements Serializable {
        private String listName;
        private int listAge;
        private Map<String, ListMapBean> map;
        private ListArrayBean[] array;

        public ListBean() {
        }

        public ListBean(String listName, int listAge, Map<String, ListMapBean> map, ListArrayBean[] array) {
            this.listName = listName;
            this.listAge = listAge;
            this.map = map;
            this.array = array;
        }

        public String getListName() {
            return listName;
        }

        public void setListName(String listName) {
            this.listName = listName;
        }

        public int getListAge() {
            return listAge;
        }

        public void setListAge(int listAge) {
            this.listAge = listAge;
        }

        public Map<String, ListMapBean> getMap() {
            return map;
        }

        public void setMap(Map<String, ListMapBean> map) {
            this.map = map;
        }

        public ListArrayBean[] getArray() {
            return array;
        }

        public void setArray(ListArrayBean[] array) {
            this.array = array;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "listName='" + listName + '\'' +
                    ", listAge=" + listAge +
                    ", map=" + map +
                    ", array=" + Arrays.toString(array) +
                    '}';
        }

        public static class ListMapBean implements Serializable {
            private String listMapName;
            private int listMapAge;

            public ListMapBean(String listMapName, int listMapAge) {
                this.listMapName = listMapName;
                this.listMapAge = listMapAge;
            }

            public String getListMapName() {
                return listMapName;
            }

            public void setListMapName(String listMapName) {
                this.listMapName = listMapName;
            }

            public int getListMapAge() {
                return listMapAge;
            }

            public void setListMapAge(int listMapAge) {
                this.listMapAge = listMapAge;
            }

            @Override
            public String toString() {
                return "ListMapBean{" +
                        "listMapName='" + listMapName + '\'' +
                        ", listMapAge=" + listMapAge +
                        '}';
            }
        }

        public static class ListArrayBean implements Serializable {
            private String listArrayName;
            private int listArrayAge;

            public ListArrayBean(String listArrayName, int listArrayAge) {
                this.listArrayName = listArrayName;
                this.listArrayAge = listArrayAge;
            }

            public String getListArrayName() {
                return listArrayName;
            }

            public void setListArrayName(String listArrayName) {
                this.listArrayName = listArrayName;
            }

            public int getListArrayAge() {
                return listArrayAge;
            }

            public void setListArrayAge(int listArrayAge) {
                this.listArrayAge = listArrayAge;
            }

            @Override
            public String toString() {
                return "ListArrayBean{" +
                        "listArrayName='" + listArrayName + '\'' +
                        ", listArrayAge=" + listArrayAge +
                        '}';
            }
        }
    }

    public static class MapBean implements Serializable {
        private String mapName;
        private int mapAge;
        private List<MapListBean> list;
        private MapArrayBean[] arrays;

        public MapBean() {
        }

        public MapBean(String mapName, int mapAge, List<MapListBean> list, MapArrayBean[] arrays) {
            this.mapName = mapName;
            this.mapAge = mapAge;
            this.list = list;
            this.arrays = arrays;
        }

        public String getMapName() {
            return mapName;
        }

        public void setMapName(String mapName) {
            this.mapName = mapName;
        }

        public int getMapAge() {
            return mapAge;
        }

        public void setMapAge(int mapAge) {
            this.mapAge = mapAge;
        }

        public List<MapListBean> getList() {
            return list;
        }

        public void setList(List<MapListBean> list) {
            this.list = list;
        }

        public MapArrayBean[] getArrays() {
            return arrays;
        }

        public void setArrays(MapArrayBean[] arrays) {
            this.arrays = arrays;
        }

        @Override
        public String toString() {
            return "MapBean{" +
                    "mapName='" + mapName + '\'' +
                    ", mapAge=" + mapAge +
                    ", list=" + list +
                    ", arrays=" + Arrays.toString(arrays) +
                    '}';
        }

        public static class MapListBean implements Serializable {
            private String mapListName;
            private int mapListAge;

            public MapListBean(String mapListName, int mapListAge) {
                this.mapListName = mapListName;
                this.mapListAge = mapListAge;
            }

            public String getMapListName() {
                return mapListName;
            }

            public void setMapListName(String mapListName) {
                this.mapListName = mapListName;
            }

            public int getMapListAge() {
                return mapListAge;
            }

            public void setMapListAge(int mapListAge) {
                this.mapListAge = mapListAge;
            }

            @Override
            public String toString() {
                return "MapListBean{" +
                        "mapListName='" + mapListName + '\'' +
                        ", mapListAge=" + mapListAge +
                        '}';
            }
        }

        public static class MapArrayBean implements Serializable {
            private String mapArrayName;
            private int mapArrayAge;

            public MapArrayBean(String mapArrayName, int mapArrayAge) {
                this.mapArrayName = mapArrayName;
                this.mapArrayAge = mapArrayAge;
            }

            public String getMapArrayName() {
                return mapArrayName;
            }

            public void setMapArrayName(String mapArrayName) {
                this.mapArrayName = mapArrayName;
            }

            public int getMapArrayAge() {
                return mapArrayAge;
            }

            public void setMapArrayAge(int mapArrayAge) {
                this.mapArrayAge = mapArrayAge;
            }

            @Override
            public String toString() {
                return "MapArrayBean{" +
                        "mapArrayName='" + mapArrayName + '\'' +
                        ", mapArrayAge=" + mapArrayAge +
                        '}';
            }
        }
    }

    public static class ArrayBean implements Serializable {
        private String arrayName;
        private int arrayAge;
        private List<ArrayListBean> list;
        private Map<String, ArrayMapBean> map;

        public ArrayBean() {
        }

        public ArrayBean(String arrayName, int arrayAge, List<ArrayListBean> list, Map<String, ArrayMapBean> map) {
            this.arrayName = arrayName;
            this.arrayAge = arrayAge;
            this.list = list;
            this.map = map;
        }

        public String getArrayName() {
            return arrayName;
        }

        public void setArrayName(String arrayName) {
            this.arrayName = arrayName;
        }

        public int getArrayAge() {
            return arrayAge;
        }

        public void setArrayAge(int arrayAge) {
            this.arrayAge = arrayAge;
        }

        public List<ArrayListBean> getList() {
            return list;
        }

        public void setList(List<ArrayListBean> list) {
            this.list = list;
        }

        public Map<String, ArrayMapBean> getMap() {
            return map;
        }

        public void setMap(Map<String, ArrayMapBean> map) {
            this.map = map;
        }

        @Override
        public String toString() {
            return "ArrayBean{" +
                    "arrayName='" + arrayName + '\'' +
                    ", arrayAge=" + arrayAge +
                    ", list=" + list +
                    ", map=" + map +
                    '}';
        }

        public static class ArrayListBean implements Serializable {
            private String arrayListName;
            private int arrayListAge;

            public ArrayListBean(String arrayListName, int arrayListAge) {
                this.arrayListName = arrayListName;
                this.arrayListAge = arrayListAge;
            }

            public String getArrayListName() {
                return arrayListName;
            }

            public void setArrayListName(String arrayListName) {
                this.arrayListName = arrayListName;
            }

            public int getArrayListAge() {
                return arrayListAge;
            }

            public void setArrayListAge(int arrayListAge) {
                this.arrayListAge = arrayListAge;
            }

            @Override
            public String toString() {
                return "ArrayListBean{" +
                        "arrayListName='" + arrayListName + '\'' +
                        ", arrayListAge=" + arrayListAge +
                        '}';
            }
        }

        public static class ArrayMapBean implements Serializable {
            private String arrayMapName;
            private int arrayMapAge;

            public ArrayMapBean(String arrayMapName, int arrayMapAge) {
                this.arrayMapName = arrayMapName;
                this.arrayMapAge = arrayMapAge;
            }

            public String getArrayMapName() {
                return arrayMapName;
            }

            public void setArrayMapName(String arrayMapName) {
                this.arrayMapName = arrayMapName;
            }

            public int getArrayMapAge() {
                return arrayMapAge;
            }

            public void setArrayMapAge(int arrayMapAge) {
                this.arrayMapAge = arrayMapAge;
            }

            @Override
            public String toString() {
                return "ArrayMapBean{" +
                        "arrayMapName='" + arrayMapName + '\'' +
                        ", arrayMapAge=" + arrayMapAge +
                        '}';
            }
        }
    }
}
