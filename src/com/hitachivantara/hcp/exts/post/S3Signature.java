package com.hitachivantara.hcp.exts.post;

import com.hitachivantara.common.ex.AlgorithmException;

public interface S3Signature {
	String calculate(String key, String ... data ) throws AlgorithmException;

}
