package com.developers.obsidian.lollipopstuff.utils.savagelook.networking.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastSingleton {
	private static Toast instance;
	
	private ToastSingleton() {}
	
	public synchronized static Toast getInstance(Context context) {
		if (instance == null) {
			instance = Toast.makeText(context, "", Toast.LENGTH_SHORT);	
		}
		return instance;
	}
}
