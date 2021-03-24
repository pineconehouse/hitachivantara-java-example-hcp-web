package com.hitachivantara.example.hcp;

import java.util.Date;

import javax.servlet.http.HttpServlet;

import com.hitachivantara.common.util.RandomUtils;
import com.hitachivantara.core.http.util.URLUtils;
import com.hitachivantara.example.hcp.util.Account;
import com.hitachivantara.example.hcp.util.PostUtils;
import com.hitachivantara.hcp.exts.post.PostObjectFormSignatureV2;
import com.hitachivantara.hcp.exts.post.PostSecurityPolicies;

public class PostDemo extends HttpServlet {
	public static PostObjectFormSignatureV2 generateV2PostFormForFileUpload() {
		// ！！！！！！！！！！！！如使用post请开启version或在上传前检查目标key是否存在，如存在需提前删除！！！！！！！！！！！！
		PostObjectFormSignatureV2 form = new PostObjectFormSignatureV2(Account.secretKey);

		// 上传至桶的名称
		form.setBucket(Account.namespace);
		// 桶用户名
		form.setAccessKey(Account.accessKey);

		// 生成一个随机的上传目录，需要根据业务调整保存目录
		String dir = URLUtils.catPath(Account.dafaultUploadPath, RandomUtils.randomString(4));
		// 指定保存目录，hcp中key的文件名将与上传文件名一致
		form.setKeyDirectory(dir);
		// or
		// 指定文件key方式，web客户端上传的文件将保存至此key，原始文件名称将不被保存
		// String key = "uploadByPost/blog.txt";// URLUtils.catPath(dir, "thisisfilename.xxx");
		// form.setKey(key);

		// form.setAcl(CannedACL.PUBLIC_READ);
		// form.setFile(file);
		
		// form.addMetadata("idnumber", "14365123651274");
		// form.addMetadata("companyName", "hitachivantara");
		form.addMetadata("id", "ID" + RandomUtils.randomInt(1000, 9999));

		// form.setSuccessActionStatus(SuccessActionStatus.STATUS_204);
		// 上传成功后跳转的地址
		form.setSuccessActionRedirect("http://localhost:8080/hitachivantara-java-example-hcp-web/PostSuccessed");

		// form.setElement("content-type", "plain/text");

		PostSecurityPolicies policy = new PostSecurityPolicies();
		// 设置签名有效期
		policy.setExpiration(PostUtils.minuteFlow(new Date(), 1));

		policy.generateDefaultCondition(form);

		// policy.setExactMatchCondition(PostFormKeys.BUCKET, "s3bucket");
		// policy.setStartsWithCondition(PostFormKeys.KEY, "/".equals(dir) ? "" : dir);
		// policy.setAnyContentCondition(PostFormKeys.ACL);
		// policy.setAnyContentCondition(PostFormKeys.SUCCESS_ACTION_REDIRECT);
		// policy.setExactMatchCondition(PostFormKeys.SUCCESS_ACTION_STATUS, SuccessActionStatus.STATUS_200);
		// policy.setStartsWithCondition("Content-Type", "plain/");
		// 请勿设置接受长度条件
		// policy.setAccpetLengthCondition(Constants.SIZE_1MB, Constants.SIZE_50MB);
		form.setPolicy(policy);

		return form;
	}

	/**
	 * 示例演示，创建一个为文本输入上传至S3的Post form
	 * 
	 * @return
	 */
	public static PostObjectFormSignatureV2 generateV2PostFormForDirectTextSave() {
		PostObjectFormSignatureV2 form = new PostObjectFormSignatureV2(Account.secretKey);

		// 上传至桶的名称
		form.setBucket("s3bucket");
		// 桶用户名
		form.setAccessKey(Account.accessKey);
		// 生成存储在hcp的文本名称，即key
		String filename = "input_" + RandomUtils.randomInt(1, 10000) + ".txt";
		String key = URLUtils.catPath(Account.dafaultUploadPath, filename);
		form.setKey(key);

		// 此处为保存至hcp的文本创建了三个元数据，可以根据需要定制，也可以不添加元数据
		form.addMetadata("id", "ID" + RandomUtils.randomInt(1000, 9999));
		form.addMetadata("author", "hitachivantara");
		form.addMetadata("email", RandomUtils.randomMailAddress());

		// form.setSuccessActionStatus(SuccessActionStatus.STATUS_204);
		// 上传成功后跳转的地址
		form.setSuccessActionRedirect("http://localhost:8080/hitachivantara-java-example-hcp-web/PostSuccessed");

		// 声明上传内容为文本
		form.setElement("content-type", "plain/text");

		PostSecurityPolicies policy = new PostSecurityPolicies();
		// 设置签名有效期
		policy.setExpiration(PostUtils.minuteFlow(new Date(), 1));

		policy.generateDefaultCondition(form);
		form.setPolicy(policy);

		return form;
	}

	// public static void main(String[] s) {
	// PostObjectFormSignatureV2 form = PostDemo.generateV2PostFormForFileUpload();
	// System.out.println(form.getKey());
	// System.out.println(form.getSignature());
	// System.out.println(form.getEncodedPolicy());
	// System.out.println(new String(Base64.getDecoder().decode(form.getEncodedPolicy())));
	//
	// System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'").format(new Date()));
	// System.out.println(DatetimeFormat.ALTERNATE_ISO8601_DATEFORMAT.format(new Date()));
	// }

}