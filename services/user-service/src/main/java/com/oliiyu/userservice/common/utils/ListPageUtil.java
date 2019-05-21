package com.oliiyu.userservice.common.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Oliiyu
 * Date: 2019/5/14 16:22
 * Description:
 * java8 stream api 实现的List集合分页获取工具
 */
public class ListPageUtil<T> {
    private static final int DEFAULT_PAGE_SIZE = 5; // 每页默认大小

    private int pageCount = 0;          // 分页后总页数
    private int pageSize = 0;           // 每页显示条数
    private List<T> pageData = null;    // 原始集合数据

    public ListPageUtil(List<T> originalPageData, int pageSize) {
        if (null == originalPageData || originalPageData.isEmpty()) {
            throw new IllegalArgumentException("Paging resource list must be not null.");
        }

        if (pageSize <= 0) {
            throw new IllegalArgumentException("Paging size must be greater than zero.");
        }

        this.pageSize = pageSize;
        this.pageData = originalPageData;
        this.pageCount = originalPageData.size() / pageSize;
        if (originalPageData.size() % pageSize != 0) {
            this.pageCount++;
        }
    }

    public ListPageUtil(List<T> originalPageData) {
        this(originalPageData, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取当前页数
     */
    public int getCurrentPageNo() {
        return pageCount - getSurplusPage();
    }

    /**
     * 获取分页后，总页数
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 获取当前剩余页数
     */
    private int getSurplusPage() {
        if (pageData.size() % pageSize > 0) {
            return (pageData.size() / pageSize) + 1;
        } else {
            return pageData.size() / pageSize;
        }
    }

    /**
     * 是否还有下一页数据
     */
    public boolean hasNextPage() {
        return pageData.size() > 0;
    }

    /**
     * 获取下一页数据
     */
    public List<T> next() {
        List<T> pagingData = pageData.stream().limit(pageSize).collect(Collectors.toList());
        pageData = pageData.stream().skip(pageSize).collect(Collectors.toList());
        return pagingData;
    }

    /**
     * 获取分页后的数据
     */
    public List<T> getPagedList(int pageNo) {
        int fromIndex = (pageNo - 1) * pageSize;
        if (fromIndex >= pageData.size()) {
            return Collections.emptyList();
        }

        int toIndex = pageNo * pageSize;
        if (toIndex >= pageData.size()) {
            toIndex = pageData.size();
        }
        return pageData.subList(fromIndex, toIndex);
    }
}
