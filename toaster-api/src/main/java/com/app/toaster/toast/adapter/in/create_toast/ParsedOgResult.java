package com.app.toaster.toast.adapter.in.create_toast;

public record ParsedOgResult(String titleAdvanced, String imageAdvanced) {
	public static ParsedOgResult of(String titleAdvanced, String imageAdvanced){
		return new ParsedOgResult(titleAdvanced, imageAdvanced);
	}
}