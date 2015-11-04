package com.varela.dao.sharding;

/**
 * 可选部分
 * 根据需要进行调整
 */
public final class DataSourceContextHolder {

    private static final String dataSource1 = "dataSource1";
    private static final String dataSource2 = "dataSource2";

    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();

    public static void switchDataSource1() {
        dataSourceKey.set(dataSource1);
    }

    public static void switchDataSource2() {
        dataSourceKey.set(dataSource2);
    }

    public static void setDataSourceType(String dataSourceType) {
        dataSourceKey.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return dataSourceKey.get();
    }

    public static void clear() {
        dataSourceKey.remove();
    }
}
