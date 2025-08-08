package com.app.toaster.toast.port.out;

public record ParsedOgResult(String titleAdvanced, String imageAdvanced) {
	public static ParsedOgResult of(String titleAdvanced, String imageAdvanced){
		return new ParsedOgResult(titleAdvanced, imageAdvanced);
	}
}