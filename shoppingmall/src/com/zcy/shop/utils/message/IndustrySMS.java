package com.zcy.shop.utils.message;


/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private static String to = "18994021992";	//要发送的手机号
//	private static String to = "18061889261"; 
	private static String smsContent = "【新友达文化传媒】您的订单{id}已经支付成功，货物即将发出，请注意查收。";
//	private static String smsContent = "小凤小凤";

	/**
	 * 验证码通知短信
	 */
	public static void execute(String id)	//带参数替换
	{
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent.replace("{id}", id)
				+ HttpUtil.createCommonParam();

		// 提交请求
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
	}
}
