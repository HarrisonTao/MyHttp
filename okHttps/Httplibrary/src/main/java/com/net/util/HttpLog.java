package com.net.util;

import android.util.Log;




public  final class HttpLog {




	public static void  showDLog(String context) {
		if (!StatConfig.isLog) {
			return;
		}

		Log.d("HttpLib-->", context);
	}

	public static void  showDLog(String title,String context) {
		if (!StatConfig.isLog) {
			return;
		}

		Log.d(title, context);
	}


		

	
	
}
