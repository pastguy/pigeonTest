package ymm.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class UtilTest {

	public static void main(String[] args) {
		StringUtils.isEmpty("");
		try {
			Date date = DateUtils.parseDate("2014-04-22 11:11:11",new String[]{"yyyy-MM-dd HH:mm:ss"});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
