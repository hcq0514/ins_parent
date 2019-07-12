package com.ins.common.redis;

import java.text.MessageFormat;

public class RedisKeyConstant {

	public static final Formater COMM_SMS_APP = new Formater("com:sms:app:id:{0}");

	public static class Formater {
		private String pattern;
		private MessageFormat format;

		private Formater(String pattern) {
			this.pattern = pattern;
			this.format = new MessageFormat(this.pattern);
		}

		public String setArg(Object...args) {
			return this.format.format(args);
		}

		public String getPattern() {
			return this.pattern;
		}

		@Override
		public String toString() {
			return this.format.format(null);
		}
	}
}
