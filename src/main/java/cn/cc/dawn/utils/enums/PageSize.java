package cn.cc.dawn.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum PageSize {

    _10("10", 10),
    _20("20", 20),
    ;

    public String comment;
    public int value;

//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }

    public int getValue() {
        return value;
    }

//    public void setValue(int value) {
//        this.value = value;
//    }

    PageSize() {
    }

    private PageSize(final String comment, final int value) {
        this.value = value;
        this.comment = comment;
    }

    private static Map<String, PageSize> map = new HashMap<>();

    static {
        PageSize[] pageSizes = PageSize.values();
        for (PageSize pageSize : pageSizes) {
            map.put(pageSize.comment, pageSize);
        }
    }

    public static PageSize getPageSize(String comment) {
        if (map.containsKey(comment)) {
            return map.get(comment);
        }
        return null;
    }

    public static void main(String[] args) {

    }

}
