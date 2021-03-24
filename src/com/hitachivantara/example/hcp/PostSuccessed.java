package com.hitachivantara.example.hcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitachivantara.common.define.Constants;
import com.hitachivantara.common.util.DatetimeFormat;
import com.hitachivantara.common.util.RandomUtils;
import com.hitachivantara.core.http.util.URLUtils;
import com.hitachivantara.example.hcp.util.Account;
import com.hitachivantara.example.hcp.util.PostUtils;
import com.hitachivantara.hcp.common.define.SuccessActionStatus;
import com.hitachivantara.hcp.exts.post.PostFormKeys;
import com.hitachivantara.hcp.exts.post.PostObjectFormSignatureV2;
import com.hitachivantara.hcp.exts.post.PostSecurityPolicies;
import com.hitachivantara.hcp.standard.define.ACLDefines.CannedACL;

public class PostSuccessed extends HttpServlet {
	private static final long serialVersionUID = -6966569954668344215L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//http://localhost:8080/hitachivantara-java-example-hcp-web/PostSuccessed?bucket=s3bucket&key=uploadByPost%2Fstme%2F012911225186_0s3-%E5%BC%80%E5%8F%91%E4%BA%BA%E5%91%98%E6%8C%87%E5%8D%97.docx&etag=%220a7725fdd55a658a0315e44d75c34e8e%22
		String bucket = req.getParameter(PostFormKeys.BUCKET);
		String key = req.getParameter(PostFormKeys.KEY);
		String etag = req.getParameter(PostFormKeys.ETAG);
		
		// Update your meta database
		{
			// Insert into table xxx (bucket, key, etag);
		}
		
		PrintWriter out = resp.getWriter();
    	out.println("<html>");
		out.println("<body>");
		out.println("<h1>Upload successful</h1>");
		out.println("<h2>"+bucket+"</h2>");
		out.println("<h2>"+key+"</h2>");
		out.println("<h2>"+etag+"</h2>");
		out.println("</body>");
		out.println("</html>");
	}

}