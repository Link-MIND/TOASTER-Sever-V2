package com.app.toaster.exception;

import jakarta.validation.Payload;

public class Severity {
	public static class Info implements Payload {};
	public static class Error implements Payload {};
}
