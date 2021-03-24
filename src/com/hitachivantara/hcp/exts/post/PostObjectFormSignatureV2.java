package com.hitachivantara.hcp.exts.post;

import java.util.Collection;

import com.hitachivantara.common.ex.AlgorithmException;
import com.hitachivantara.core.http.define.CustomKey;
import com.hitachivantara.core.http.model.NameValue;
import com.hitachivantara.hcp.standard.define.ACLDefines.CannedACL;

public class PostObjectFormSignatureV2 extends PostObjectForm {
	/**
	 * Specifies the owner of the bucket who grants an anonymous user access for a request that satisfies the constraints in the policy.
	 * 
	 * A Base64-encoded username for a user account
	 */
	// private String accessKey;

	/**
	 * Specifies a value calculated using the secret key and the policy string, as "string to sign."
	 */
	// private String signature;

	private String secretkey;

	public PostObjectFormSignatureV2(String secretkey) {
		this.secretkey = secretkey;
	}

	@Override
	protected void build() {
		super.build();
		setElement(PostFormKeys.SIGNATURE, getSignature());
	}

	public String getAccessKey() {
		return (String) getElement(PostFormKeys.AWS_ACCESSKEY_ID);
	}

	public void setAccessKey(String accessKey) {
		setElement(PostFormKeys.AWS_ACCESSKEY_ID, accessKey);
	}

	public String getSignature() {
		PostSecurityPolicies policy = getPolicy();
		if (policy != null) {
			try {
				return new S3SignatureV2().calculate(secretkey, policy.encode());
			} catch (AlgorithmException e) {
				e.printStackTrace();
				return "Failed to generate signature. " + e.getMessage();
			}
		}
		return "";
	}

}
