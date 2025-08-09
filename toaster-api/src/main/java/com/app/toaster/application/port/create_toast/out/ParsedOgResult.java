package com.app.toaster.application.port.create_toast.out;

public record ParsedOgResult(String titleAdvanced, String imageAdvanced) {
	public static ParsedOgResult of(String titleAdvanced, String imageAdvanced){
		return new ParsedOgResult(titleAdvanced, imageAdvanced);
	}
}