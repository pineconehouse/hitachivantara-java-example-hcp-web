package com.hitachivantara.example.hcp.util;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hitachivantara.common.define.Constants;
import com.hitachivantara.common.util.DatetimeFormat;
import com.hitachivantara.common.util.RandomUtils;
import com.hitachivantara.core.http.util.URLUtils;
import com.hitachivantara.hcp.exts.post.MatchCondition;
import com.hitachivantara.hcp.exts.post.PostObjectFormSignatureV2;
import com.hitachivantara.hcp.exts.post.PostSecurityPolicies;
import com.hitachivantara.hcp.standard.define.ACLDefines.CannedACL;

public class PostUtils {

	public static Date minuteFlow(Date date, int mm) {

		if (mm <= 0) {
			throw new IllegalArgumentException("Minute must be specificed!");
		}

		Calendar calendar = GregorianCalendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}

		calendar.add(Calendar.MINUTE, mm);

		return calendar.getTime();
	}
}
