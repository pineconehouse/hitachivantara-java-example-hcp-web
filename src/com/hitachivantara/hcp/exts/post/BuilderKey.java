package com.hitachivantara.hcp.exts.post;

import com.hitachivantara.common.api.StringValueBuilder;
import com.hitachivantara.common.api.ValueBuilder;
import com.hitachivantara.common.ex.BuildException;

public class BuilderKey<T, R> {
	protected String keyname = null;
	protected ValueBuilder<T, R> valueBuilder;

	public BuilderKey(String keyname) {
		this.keyname = keyname;
	}

	public BuilderKey(String keyname, ValueBuilder<T, R> valueBuilder) {
		this.keyname = keyname;
		this.valueBuilder = valueBuilder;
	}

	public String getKeyname() {
		return keyname;
	}

	public ValueBuilder<T, R> getValueBuilder() {
		return valueBuilder;
	}

	public R build(T value) throws BuildException {
		if (value != null) {
			if (valueBuilder != null) {
				return valueBuilder.build(value);
			}
		}

		return null;
	}
}