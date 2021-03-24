package com.hitachivantara.hcp.exts.post;

import com.hitachivantara.common.ex.AlgorithmException;
import com.hitachivantara.hcp.standard.define.ACLDefines.CannedACL;

public class PostObjectFormSignatureV4 extends PostObjectForm {

	/**
	 * (AWS Signature Version 4 authentication only field) Specifies the signing algorithm that must be used during signature calculation. The
	 * value is AWS4-HMAC-SHA256.
	 */
//	private String xAmzAlgorithm = "AWS4-HMAC-SHA256";

	/**
	 * (AWS Signature Version 4 authentication only field)</br>
	 * 
	 * Specifies the credentials that you used to calculate the signature. Required if you include a POST policy document with the request. Use
	 * this format:</br>
	 * 
	 * access_key_id/date/aws_region/aws_service/aws4_request</br>
	 * 
	 * For Amazon S3, the value of aws_service is s3.</br>
	 * 
	 * For example:</br>
	 * 
	 * AKIAIOSFODNN7EXAMPLE/20191024/us-east-1/s3/aws4_request
	 */
//	private String xAmzCredential;

	/**
	 * (AWS Signature Version 4 authentication only field)</br>
	 * Specifies a date value in ISO8601 format. Required if you include a POST policy document with the request.</br>
	 * For example:</br>
	 * 20191024T000000Z</br>
	 */
//	private String xAmzDate;
	
	/**
	 * Specifies a value calculated using the secret key and the policy string, as "string to sign."
	 */
//	private String signature;

	public String getSignature() {
		return null;
//		PostSecurityPolicies policy = getPolicy();
//		try {
//			return new S3SignatureV2().calculate(secretkey, policy.encode());
//		} catch (AlgorithmException e) {
//			e.printStackTrace();
//		}
//		return accessKey;
	}

}
