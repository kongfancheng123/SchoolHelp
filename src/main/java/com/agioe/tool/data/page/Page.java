package com.agioe.tool.data.page;

import java.io.Serializable;
import java.util.List;

/**
 * 内存分页类
 *
 * @author yshen
 * @since 2018/3/29
 */
public class Page<T> implements Serializable {
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页显示记录条数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 每页显示的数据
     */
    private List<T> list;
    /**
     * 开始数据
     */
    private int start;

    /**
     * 构造分页结果
     *
     * @param original 原始数据
     * @return
     */
    public Page<T> build(List<T> original) {
        //空判断
        if (null == original) {
            this.setPages(0);
            this.setTotal(0);
            return this;
        }
        this.total = original.size();
        if (this.getPageNum() == null) {
            this.setPageNum(1);
        } else {
            this.setPageNum(this.getPageNum());
        }
        //设置每页数据条数
        this.setPageSize(this.getPageSize());
        //每页的开始数
        this.setStart((this.getPageNum() - 1) * this.getPageSize());
        //list的大小
        int count = original.size();
        //设置总页数
        this.setPages(count % this.getPageSize() == 0 ? count / this.getPageSize() : count / this.getPageSize() + 1);
        //对list进行截取
        this.setList(original.subList(this.getStart(), count - this.getStart() > this.getPageSize() ? this.getStart() + this.getPageSize() : count));
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
