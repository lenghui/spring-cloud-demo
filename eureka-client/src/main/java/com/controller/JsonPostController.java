package com.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.inter.RepayPlanRepository;
import com.model.RepayPlan;
import com.utils.CommonUtils;

@RestController
public class JsonPostController {
	
	@Autowired
	private RepayPlanRepository repayPlanRep;
	
	@RequestMapping("/json")
	public String jsonMap(@RequestBody Map<String, Object> requestMap) throws Exception {
		System.out.println(requestMap);
		List<Map<String, Object>> body = (List<Map<String, Object>>) requestMap.get("body");
		System.out.println(body);
		System.out.println(body.size());
		for(Map<String, Object> tempMap: body) {
			RepayPlan repayPlan = new RepayPlan();
			String applyYear = CommonUtils.selectValue(tempMap, "applyYear", String.class);
			String applyMonth = CommonUtils.selectValue(tempMap, "applyMonth", String.class);
			List<Map<String, Object>> loanInfos = CommonUtils.selectValue(tempMap, "loanInfos", List.class);
			repayPlan.setApplyYear(applyYear);
			repayPlan.setApplyMonth(applyMonth);
			String jsonString = JSONArray.toJSONString(loanInfos);
			repayPlan.setLoanInfos(jsonString);
			repayPlan.setCustId("100001");
			repayPlan.setCreateTime(new Date());
			repayPlanRep.save(repayPlan);
		}
		return requestMap.toString();
	}
	
	@RequestMapping("/result")
	public String getResult() {
		List<RepayPlan> list = repayPlanRep.findAll();
		for(RepayPlan repayPlan: list) {
			JSONArray loanArray = JSONArray.parseArray(repayPlan.getLoanInfos());
			System.out.println(loanArray);
		}
		return list.toString();
	}
	
}
