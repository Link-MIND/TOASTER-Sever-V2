package com.app.toaster.application.port.create_toast.out;


public interface ParseOgTagPort {
	ParsedOgResult parse(String linkUrl);
}