package com.good.rpt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.good.db.IPage;
import com.good.rpt.bean.RptBranchDeposAnalPo;

/** 
*
* @ClassName ：RptBranchDeposAnalDao 
* @Description ： 映射接口类
* @author ：PeterQi
*
*/
public interface RptBranchDeposAnalDao {

	public int deleteByPrimaryKey(String pkId);

	public int insert(RptBranchDeposAnalPo record);

	public int insertSelective(RptBranchDeposAnalPo record);

	public int updateByPrimaryKeySelective(RptBranchDeposAnalPo record);

	public int updateByPrimaryKey(RptBranchDeposAnalPo record);

	public RptBranchDeposAnalPo selectByPrimaryKey(String pkId);

	public List<RptBranchDeposAnalPo> selectByConditionSelective(@Param("condition") Map<String,Object> param, @Param("page") IPage page);

}