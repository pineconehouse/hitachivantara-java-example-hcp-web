package com.hitachivantara.hcp.exts.post;

import com.hitachivantara.common.api.StringValueBuilder;
import com.hitachivantara.common.api.ValueBuilder;
import com.hitachivantara.common.ex.BuildException;
import com.hitachivantara.core.http.define.CustomKey;

public class MatchCondition2<T> extends BuilderKey<T, Condition> {

	private final static ValueBuilder<String, Condition> conditionBuilder = new ValueBuilder<String, Condition>() {
		@Override
		public Condition build(String value) throws BuildException {
			return null;
		}
	};

	public final static MatchCondition2<String> ExactMatches = new MatchCondition2<String>("eq", conditionBuilder);
	public final static MatchCondition2<String> StartsWith = new MatchCondition2<String>("starts-with", conditionBuilder);
	public final static MatchCondition2<String> MatchingAnyContent = new MatchCondition2<String>("starts-with", conditionBuilder);
	public final static MatchCondition2<Long[]> AcceptRanges = new MatchCondition2<Long[]>("content-length-range", new ValueBuilder<Long[], Condition>() {

		@Override
		public Condition build(Long[] value) throws BuildException {
			return null;
		}
	});

	public MatchCondition2(String keyname, ValueBuilder<T, Condition> valueBuilder) {
		super(keyname, valueBuilder);
	}

	@Override
	public Condition build(T value) throws BuildException {
		return super.build(value);
	}

	// @Override
	// public String toString() {
	// return name;
	// }
}