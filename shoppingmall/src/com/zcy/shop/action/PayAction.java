package com.zcy.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcy.shop.model.Order;
import com.zcy.shop.model.User;
import com.zcy.shop.utils.message.IndustrySMS;

@Controller("payAction")
@Scope("prototype")
public class PayAction extends BaseAction<Object> {

//	public void setParameters(Map<String, String[]> arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public String goBank() {
        //1. 补全参数:P2 p3 pd Pa，需要从session中获取
        Order order = (Order) session.get("oldOrder");
        User user = (User) session.get("user");
        
        System.out.println("订单号为："+order.getId());
        System.out.println("用户为："+user.getId()+" 邮箱为："+user.getEmail());
        
        emailUtil.sendEmail(user.getEmail(), order.getId().toString());
        System.out.println("邮件发送成功！");
        // 验证码通知短信接口
        IndustrySMS.execute(order.getId().toString());
        
//        model.setP2_Order(forder.getId().toString()); //商户订单号
//        model.setP3_Amt(forder.getTotal().toString()); //支付金额
//        model.setPa_MP(user.getEmail() + "," + user.getPhone()); //商户扩展信息
        //2. 对参数进行追加        
        //3. 加密获取签名     
        //4. 存储到request域中
//        payService.saveDataToRequest(request, model); //2,3,4的业务逻辑交给service层来处理
        //5. 跳转到支付页面        
        return "pay";
    }
}
