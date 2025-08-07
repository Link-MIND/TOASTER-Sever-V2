package com.app.toaster.toast.port.out;

import com.app.toaster.parse.port.out.ParsedOgResult;

public interface ParseOgTagPort {
	ParsedOgResult parse(String linkUrl);
}