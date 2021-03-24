package com.hitachivantara.hcp.exts.post;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.hitachivantara.common.ex.AlgorithmException;
import com.hitachivantara.common.util.DigestUtils;

public class S3SignatureV2 implements S3Signature {

	public String calculateHMAC(String data, String key) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("utf-8"), "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secretKeySpec);
		byte[] ma = mac.doFinal(data.getBytes());
		// String hex =DigestUtils.format2Hex();
		// return DigestUtils.toBase64String(hex);

		return Base64.getEncoder().encodeToString(ma);
	}

	@Override
	public String calculate(String key, String... data) throws AlgorithmException{
		try {
			return calculateHMAC(data[0], key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}

}
