package com.good.db;

/**
 * 
 * @author wuwei
 *
 */
public interface IPage
{
  public abstract int getCurrentPage();

  public abstract int getPageSize();

  public abstract int getTotalCount();

  public abstract boolean isQueryCount();

  public abstract void setCurrentPage(int paramInt);

  public abstract void setPageSize(int paramInt);

  public abstract void setQueryCount(boolean paramBoolean);

  public abstract void setTotalCount(int paramInt);

  public abstract int getOffset();

  public abstract void setOffset(int paramInt);
}