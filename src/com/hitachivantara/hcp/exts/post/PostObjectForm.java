package com.hitachivantara.hcp.exts.post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hitachivantara.core.http.model.NameValue;
import com.hitachivantara.core.http.util.URLUtils;
import com.hitachivantara.hcp.common.define.SuccessActionStatus;
import com.hitachivantara.hcp.standard.define.ACLDefines.CannedACL;

public class PostObjectForm {
	private final Map<String, NameValue<Object>> formElements = new HashMap<String, NameValue<Object>>();

	/**
	 * Specifies the ACL value that must be used in the submitted form.
	 */
	// private CannedACL acl;

	/**
	 * Specifies the acceptable bucket name.
	 */
	// private String bucket;
//	 private String action;

	/**
	 * pecifies a security policy describing what is permitted in the request. Requests without a security policy are considered anonymous and
	 * work only on publicly writable buckets.
	 */
	private PostSecurityPolicies policy;

	/**
	 * Contains file or text content. Can be used with a File or a Textarea form element.
	 * 
	 * HCP ignores any fields that appear after this field.
	 * 
	 * You can only upload one file at a time.
	 */
	// private String file;

	/**
	 * Specifies the acceptable key name or a prefix of the uploaded object.
	 */
	// private String key;

	/**
	 * Specifies the URL to which the client is redirected upon successful upload.
	 */
	// private String successActionRedirect;

	/**
	 * If you don't specify a value for success_action_redirect, this element specifies the status code returned to the client when the upload
	 * succeeds.
	 */
	// private SuccessActionStatus successActionStatus = SuccessActionStatus.STATUS_204;

	/**
	 * User-specified metadata.
	 */
	private final List<AmzMeta> metadatas = new ArrayList<AmzMeta>();

	// x-amz-* Other x-amz- headers.
	// x-hcp-retention Specifies the retention value for the object being stored. This value can be a fixed date, an offset, a retention class,
	// or a special value.
	// x-hcp-retentionhold Specifies whether the object is on hold. This value can be either true or false.
	// x-ignore-* Users can specify additional form fields with this header that should be ignored.

	public PostObjectForm() {
//		setElement(PostFormKeys.SUCCESS_ACTION_STATUS, SuccessActionStatus.STATUS_204);
//		setFormData(PostFormKeys.X_AMZ_META, new ArrayList<AmzMeta>());
	}

	public Object getElement(String name) {
		NameValue<Object> nv = formElements.get(name);
		if (nv != null) {
			return nv.getValue();
		}

		return null;
	}

	public void setElement(String name, Object value) {
		formElements.put(name, new NameValue<Object>(name, value));
	}

	public Collection<NameValue<Object>> getElements() {
		build();
		return formElements.values();
	}
	
	protected void build() {
		setElement(PostFormKeys.POLICY, getEncodedPolicy());
		for (AmzMeta amzMeta : metadatas) {
			setElement(amzMeta.getName(), amzMeta.getStringValue());
		}
	}

	public CannedACL getAcl() {
		return (CannedACL) getElement(PostFormKeys.ACL);
	}

	public void setAcl(CannedACL acl) {
		setElement(PostFormKeys.ACL, acl);
	}

	public String getFile() {
		return (String) getElement(PostFormKeys.FILE);
	}

	public void setFile(String file) {
		setElement(PostFormKeys.FILE, file);
	}

	public PostSecurityPolicies getPolicy() {
		// return (PostSecurityPolicies) getFormData(PostFormKeys.POLICY);
		return policy;
	}

	public void setPolicy(PostSecurityPolicies policy) {
		// setFormData(PostFormKeys.POLICY, policy);
		this.policy = policy;
	}

	public String getEncodedPolicy() {
		if (policy != null) {
			return policy.encode();
		}

		return null;
	}

	public String getBucket() {
		return (String) getElement(PostFormKeys.BUCKET);
	}

	public void setBucket(String bucket) {
		setElement(PostFormKeys.BUCKET, bucket);
	}

	public String getKey() {
		return (String) getElement(PostFormKeys.KEY);
	}

	public void setKey(String key) {
		setElement(PostFormKeys.KEY, key);
	}

	public void setKeyDirectory(String dir) {
		String key = URLUtils.catPath(dir, "/${filename}");
		setElement(PostFormKeys.KEY, key);
	}

	public String getSuccessActionRedirect() {
		return (String) getElement(PostFormKeys.SUCCESS_ACTION_REDIRECT);
	}

	public void setSuccessActionRedirect(String successActionRedirect) {
		setElement(PostFormKeys.SUCCESS_ACTION_REDIRECT, successActionRedirect);
	}

	public SuccessActionStatus getSuccessActionStatus() {
		return (SuccessActionStatus) getElement(PostFormKeys.SUCCESS_ACTION_STATUS);
	}

	/**
	 * If you don't specify success_action_redirect, this status code is returned to the client when the upload succeeds.
	 * 
	 * Accepts the values 200, 201, or 204(the default).
	 * 
	 * If set to 200 or 204, HCP returns an empty document with a 200 or 204 status code.
	 * 
	 * If set to 201, HCP returns an XML document with a 201 status code.
	 * 
	 * If not set or set to an invalid value, HCP returns an empty document with a 204 status code.
	 * 
	 * @param successActionStatus
	 */
	public void setSuccessActionStatus(SuccessActionStatus successActionStatus) {
		setElement(PostFormKeys.SUCCESS_ACTION_STATUS, successActionStatus);
	}

	public List<AmzMeta> getMetadatas() {
		return metadatas;
	}

	public void setMetadatas(List<AmzMeta> metadatas) {
		this.metadatas.clear();

		if (metadatas != null) {
			this.metadatas.addAll(metadatas);
		}
	}

	public void clearMetadatas() {
		this.metadatas.clear();
	}
	
	public void addMetadata(String name, String value) {
		this.metadatas.add(new AmzMeta(name, value));
	}

}
