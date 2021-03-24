package com.hitachivantara.hcp.exts.post;

import com.hitachivantara.core.http.model.NameValue;

public class AmzMeta extends NameValue<Object> {

	public AmzMeta(String name, Object value) {
		super(name, value);
	}

	@Override
	public String getName() {
		return "x-amz-meta-" + super.getName();
	}

	public String getMetaName() {
		return super.getName();
	}
	
	

}
