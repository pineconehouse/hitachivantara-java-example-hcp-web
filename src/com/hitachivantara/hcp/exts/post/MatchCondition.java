package com.hitachivantara.hcp.exts.post;

import com.hitachivantara.common.api.StringValueBuilder;
import com.hitachivantara.common.api.ValueBuilder;
import com.hitachivantara.common.ex.BuildException;
import com.hitachivantara.core.http.define.CustomKey;

public enum MatchCondition{
	ExactMatches("eq"),
	StartsWith("starts-with"),
	MatchingAnyContent("starts-with"),
	SpecifyingRanges("content-length-range");
	
	private String name;
	MatchCondition(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}