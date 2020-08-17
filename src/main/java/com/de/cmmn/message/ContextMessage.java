package com.de.cmmn.message;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class ContextMessage {
	private static ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	/**
	 * getReloadableResourceBundleMessageSource()
	 * @param reloadableResourceBundleMessageSource - resource MessageSource
	 * @return ReloadableResourceBundleMessageSource
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

	/**
	 * getReloadableResourceBundleMessageSource()
	 * @return ReloadableResourceBundleMessageSource
	 */
	public static ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * 정의된 메세지 조회
	 * @param code - 메세지 코드
	 * @return String
	 */
	public static String getMessage(String code) {
		return getReloadableResourceBundleMessageSource().getMessage(code, null, Locale.getDefault());
	}

	public static String getMessage(String code, Object[] obj) {
		return getReloadableResourceBundleMessageSource().getMessage(code, obj, Locale.getDefault());
	}

//	public String getMessage(String code, String site_id) {
//		return getReloadableResourceBundleMessageSource().getMessage(code, null, Globals.ENG_ID.equals(site_id)?Locale.US:Locale.getDefault());
//	}
//	
//	public String getMessage(String code, Object[] obj, String site_id) {
//		return getReloadableResourceBundleMessageSource().getMessage(code, obj, Globals.ENG_ID.equals(site_id)?Locale.US:Locale.getDefault());
//	}
}