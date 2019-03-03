package com.good.db.impl;

import com.good.db.IPage;

public class PageImpl implements IPage
{
  private boolean queryCount = true;

  private int currentPage = 1;

  private int totalCount = 0;

  private int totalPage = 0;

  private int pageSize = 15;

  private int offset = -1;

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
    this.offset = ((getCurrentPage() - 1) * getPageSize());
  }

  public int getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount)
  {
    this.totalCount = totalCount;
    int totalPage = totalCount / getPageSize() + (totalCount % getPageSize() == 0 ? 0 : 1);
    setTotalPage(totalPage);
  }

  public int getTotalPage() {
    return this.totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public boolean isQueryCount() {
    return this.queryCount;
  }

  public void setQueryCount(boolean queryCount) {
    this.queryCount = queryCount;
  }

  public int getOffset() {
    return this.offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
    this.currentPage = (offset / getPageSize() + 1);
  }
}