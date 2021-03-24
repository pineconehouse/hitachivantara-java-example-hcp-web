package com.hitachivantara.hcp.exts.post;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitachivantara.common.util.DatetimeFormat;
import com.hitachivantara.common.util.StringUtils;
import com.hitachivantara.core.http.model.NameValue;

public class PostSecurityPolicies {
	// { "expiration": "2020-11-02T123:01:00.000Z",
	// "conditions": [
	// {"bucket": "Finance"},
	// ["starts-with", "$key", "user/user1/"],
	// {"acl": "public-read"},
	// {"success_action_redirect": "http://www.company.com/success"},
	// {"x-amz-meta-uuid": "14365123651274"},
	// {"x-amz-credential": "AKIAIOSFODNN7EXAMPLE/20151229/us-east-1/s3/aws4_request"},
	// {"x-amz-algorithm": "AWS4-HMAC-SHA256"},
	// {"x-amz-date": "20201102T000000Z" }
	// ]
	// }
	private final static String[] keys = new String[] { "" };

	private String expiration;
	private Map<String, Condition> conditions = new HashMap<String, Condition>();

	public PostSecurityPolicies() {
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		// this.expiration = "2019-12-31T12:00:00.000Z"; // 2019-05-31T14:21:21+0800
		if (expiration != null)
			this.expiration = DatetimeFormat.ALTERNATE_ISO8601_DATEFORMAT.format(expiration);
		// this.expiration = DatetimeFormat.COMPRESSED_ISO8601_DATE_FORMAT.format(expiration);
	}

	public Collection<Condition> getConditions() {
		return conditions.values();
	}

	public String encode() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String policy = objectMapper.writeValueAsString(this);
			return Base64.getEncoder().encodeToString(policy.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void generateDefaultCondition(PostObjectForm form) {
		Collection<NameValue<Object>> elms = form.getElements();

		for (NameValue<Object> nv : elms) {
			String name = nv.getName();
			String value = nv.getStringValue();
			if (PostFormKeys.KEY.equalsIgnoreCase(name)) {
				String key = value;
				int i = key.toLowerCase().indexOf("${filename}");
				if (i != -1) {
					String dir = key.substring(0, i);
					dir = "/".equals(dir) ? "" : dir;
					this.setStartsWithCondition(PostFormKeys.KEY, dir);
				} else {
					this.setExactMatchCondition(PostFormKeys.KEY, key);
				}
			} else if (name.startsWith(PostFormKeys.X_AMZ_META)) {
					this.setStartsWithCondition(name, value);
			} else if (StringUtils.isNotEmpty(value)) {
				this.setExactMatchCondition(name, value);				
			}

		}

	}

	public void setExactMatchCondition(String key, Object value) {
		Condition c = new ExactMatches(key, value == null ? "" : value.toString());
		conditions.put(key, c);
	}

	public void setStartsWithCondition(String key, String value) {
		Matching c = new Matching();
		c.setCondition(MatchCondition.StartsWith, key, value == null ? "" : value);
		conditions.put(key, c);
	}

	public void setAnyContentCondition(String key) {
		Matching c = new Matching();
		c.setCondition(MatchCondition.MatchingAnyContent, key, "");
		conditions.put(key, c);
	}

	public void setAccpetLengthCondition(long from, long to) {
		MatchingObject c = new MatchingObject();
		c.setCondition(MatchCondition.SpecifyingRanges, Math.max(from, to), Math.min(to, from));
		conditions.put(MatchCondition.SpecifyingRanges.toString(), c);
	}
}

class ExactMatches extends HashMap<String, Object> implements Condition {

	public ExactMatches(String head, Object value) {
		this.put(head, value);
	}
}

class Matching extends ArrayList<String> implements Condition {

	public void setCondition(MatchCondition matcher, String head, String value) {
		this.add(matcher.toString());
		this.add(head.charAt(0) == '$' ? head : "$" + head);
		this.add(value);
	}
}

class MatchingObject extends ArrayList<Object> implements Condition {

	public void setCondition(MatchCondition matcher, Object value1, Object value2) {
		this.add(matcher.toString());
		this.add(value1);
		this.add(value2);
	}
}
