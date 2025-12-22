package com.app.toaster.toast.adapter.out;


import com.app.toaster.toast.adapter.in.create_toast.ParsedOgResult;

public interface ParseOgTagPort {
	ParsedOgResult parse(String linkUrl);
}