package net.engining.lbt.business.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.easipay.ep.tools.util.SerialNumberUtil;
import net.engining.lbt.infrastructure.shared.model.PtpSignApplData;
import net.yuneesoft.tools.dto.CancelRequest;
import net.yuneesoft.tools.dto.CancelResponse;
import net.yuneesoft.tools.dto.CapPrepayRequest;
import net.yuneesoft.tools.dto.CapRepayedRequest;
import net.yuneesoft.tools.dto.CapRepayedResponse;
import net.yuneesoft.tools.dto.CapReprepayResponse;
import net.yuneesoft.tools.dto.DebitDoneRequest;
import net.yuneesoft.tools.dto.DebitDoneResponse;
import net.yuneesoft.tools.dto.MsgCode;
import net.yuneesoft.tools.dto.OrgRegResponse;
import net.yuneesoft.tools.dto.PrepayDoneRequest;
import net.yuneesoft.tools.dto.PrepayDoneResponse;
import net.yuneesoft.tools.dto.StatementChgRequest;
import net.yuneesoft.tools.dto.StatementChgResponse;
import net.yuneesoft.tools.dto.StatementInfoRequest;
import net.yuneesoft.tools.dto.StatementInfoResponse;
import net.yuneesoft.tools.dto.TrxQurRequest;
import net.yuneesoft.tools.dto.TrxQurResponse;
import net.yuneesoft.tools.util.XmlUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allinpay.ets.client.StringUtil;
import com.ibm.mq.jms.MQQueue;

@Service
public class AgentSender {
	
	@Autowired
	private MessageSenderImpl send;
	
	@Autowired
	private AgentUtil agentUtil;
	
	@PersistenceContext
	private EntityManager em;
	
	@Value("#{env['secretkey']}")
	private String secretkey;
	
	@Value("#{env['pubPath']}")
	private String pubPath;
	@Value("#{env['cerpatn']}")
	private String cerpatn;
	@Value("#{env['Q_FROM']}")
	private String Q_FROM;
	@Value("#{env['Q_TO']}")
	private String Q_TO;
	@Value("#{env['node']}")
	private String node;
	@Value("#{env['ydmq_name']}")
	private String ydmq_name;
	
	private SimpleDateFormat sd = new SimpleDateFormat(DateFormatUtils.ISO_DATETIME_FORMAT.getPattern());

	private Logger logger = LoggerFactory.getLogger(getClass());
	//private QPtpInterfaceHis qPtpInterfaceHis = QPtpInterfaceHis.ptpInterfaceHis;
	
	/**
	 * 交易查询应答
	 * @param request
	 * @param code
	 * @return
	 * @throws JMSException
	 */
	@Transactional
	public String A10101Sender(TrxQurRequest request,String code)throws JMSException{
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(request.getTrxSerno());
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			TrxQurResponse response = new TrxQurResponse();
			response.setTrxCode("A101");
			response.setTrxSerno(request.getTrxSerno());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setRtnCode(code);
			if("000000".equals(code)){
				//原交易存在
				response.setOtrxSerno(request.getOtrxSerno());//原交易流水号号
				String oldMessage = agentUtil.getResponseContent(request.getOtrxSerno());
				if(!StringUtil.isEmpty(oldMessage)){
					//TrxQurResponse re = getResp(oldMessage,TrxQurResponse.class,secretkey);
					Map<String,String> map = getOrtn(oldMessage, request.getOtrxCode());
					response.setOrtnCode(map.get("rtnCode"));
					response.setOrtrxSerno(map.get("rtrxSerno"));
				}
			}
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.TRX_QUR_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}

	
	/**
	 * 企业签约应答(审批结束)
	 * @param signId
	 * @throws JMSException
	 */
	@Transactional
	public String A20101Sender(Integer signId,String code) throws JMSException{
		PtpSignApplData ptpSignApplData = em.find(PtpSignApplData.class, signId);
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(ptpSignApplData.getTxnSerialNo());
			if(resendMessage == null){
				logger.info("暂无应答报文，不应答");
				return null;
			}
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			OrgRegResponse response = new OrgRegResponse();
			response.setTrxCode("A201");
			response.setTrxSerno(ptpSignApplData.getTxnSerialNo());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setAuthorizeNo(ptpSignApplData.getAuthNo());
			response.setRtnCode(code);
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.ORG_REG_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}
	
	/**
	 * 税单同步应答
	 * @param request
	 * @param code
	 * @throws JMSException
	 */
	@Transactional
	public String A20401Sender(StatementInfoRequest request,String code) throws JMSException{
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(request.getTrxSerno());
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			StatementInfoResponse response = new StatementInfoResponse();
			response.setTrxCode("A204");
			response.setTrxSerno(request.getTrxSerno());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setRtnCode(code);
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.STATEMENT_INFO_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}
	
	/**
	 * 删单应答
	 * @param request
	 * @param code
	 * @throws JMSException
	 */
	@Transactional
	public String A20501Sender(StatementChgRequest request,String code) throws JMSException{
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(request.getTrxSerno());
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			StatementChgResponse response = new StatementChgResponse();
			response.setTrxCode("A205");
			response.setTrxSerno(request.getTrxSerno());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setRtnCode(code);
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.STATEMENT_CHG_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}
	
	/**
	 * 撤单应答
	 * @param request
	 * @param code
	 * @throws JMSException
	 */
	@Transactional
	public String A30301Sender(CancelRequest request,String code) throws JMSException{
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(request.getTrxSerno());
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			CancelResponse response = new CancelResponse();
			response.setTrxCode("A303");
			response.setTrxSerno(request.getTrxSerno());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setRtnCode(code);
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.CANCEL_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}
	
	/**
	 * 预扣完成应答
	 * @param request
	 * @param code
	 * @throws JMSException
	 */
	@Transactional
	public String A30201Sender(PrepayDoneRequest request,String code) throws JMSException{
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(request.getTrxSerno());
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			PrepayDoneResponse response = new PrepayDoneResponse();
			response.setTrxCode("A302");
			response.setTrxSerno(request.getTrxSerno());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setRtnCode(code);
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.PREPAY_DONE_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}
	
	/**
	 * 实扣完成应答
	 * @param request
	 * @param code
	 * @throws JMSException
	 */
	@Transactional
	public String A30401Sender(DebitDoneRequest request,String code) throws JMSException{
		if("resend".equals(code)){
			//重发
			String resendMessage = agentUtil.getResponseContent(request.getTrxSerno());
			Destination queue = new MQQueue(ydmq_name);
			send.sendMessage(queue, resendMessage);
			return null;
		}else{
			//拼装应答报文
			DebitDoneResponse response = new DebitDoneResponse();
			response.setTrxCode("A304");
			response.setTrxSerno(request.getTrxSerno());
			response.setRdoTime(sd.format(new Date()));
			response.setRtrxSerno(node + SerialNumberUtil.getSerialNum());
			response.setRtnCode(code);
			Destination queue = new MQQueue(ydmq_name);
			String message = formatXml(response,MsgCode.DEBIT_DONE_RESPONSE);
			send.sendMessage(queue, message);
			return message;
		}
	}
	
	/**
	 * 垫税通知请求
	 * @throws JMSException
	 */
	@Transactional
	public String A30100Sender(CapPrepayRequest request) throws JMSException{
		Destination queue = new MQQueue(ydmq_name);
		String message = formatXml(request,MsgCode.CAP_PREPAY_REQUEST);
		send.sendMessage(queue, message);
		return message;
	}
	
	/**
	 * 还款完成通知请求
	 * @param request
	 * @return
	 * @throws JMSException
	 */
	@Transactional
	public String A30700Sender(CapRepayedRequest request) throws JMSException{
		Destination queue = new MQQueue(ydmq_name);
		String message = formatXml(request,MsgCode.CAP_REPAYED_REQUEST);
		send.sendMessage(queue, message);
		return message;
	}
	
	private <T> String formatXml(T request, String msgCode){
		String xml=null;
		try {
			xml = XmlUtils.entityToXml(request,
					pubPath, "00", msgCode,
					"YDTZ000", "YDTZ000", "0", "0", "‎00e4fa14e52c900d75",
					cerpatn, "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}
	
    private <T> T getResp(String xml,Class<T> paramClass,String cerPath){
		try {
			return XmlUtils.parse(xml, paramClass,
					cerPath, "YDTZ000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
    
    private Map<String,String> getOrtn(String message,String trxCode){
    	Map<String,String> map = new HashMap<String,String>();
    	String rtnCode = "",rtrxSerno = "";
    	if("A101".equals(trxCode)){
    		TrxQurResponse re = getResp(message,TrxQurResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A201".equals(trxCode)){
    		OrgRegResponse re = getResp(message,OrgRegResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A204".equals(trxCode)){
    		StatementInfoResponse re = getResp(message,StatementInfoResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A205".equals(trxCode)){
    		StatementChgResponse re = getResp(message,StatementChgResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A301".equals(trxCode)){
    		CapReprepayResponse re = getResp(message,CapReprepayResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A302".equals(trxCode)){
    		PrepayDoneResponse re = getResp(message,PrepayDoneResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A303".equals(trxCode)){
    		CancelResponse re = getResp(message,CancelResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	else if("A304".equals(trxCode)){
    		DebitDoneResponse re = getResp(message,DebitDoneResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
     	else if("A307".equals(trxCode)){
    		CapRepayedResponse re = getResp(message,CapRepayedResponse.class,secretkey);
    		rtnCode = re.getRtnCode();
    		rtrxSerno = re.getRtrxSerno();
    	}
    	map.put("rtnCode",rtnCode);
    	map.put("rtrxSerno",rtrxSerno);
    	return map;
    }
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("*-context.xml");
		//MessageSenderImpl bean = ctx.getBean(MessageSenderImpl.class);
		AgentSender agentSender = ctx.getBean(AgentSender.class);
		try {
			CapRepayedRequest request = new CapRepayedRequest();
			String message= agentSender.A30700Sender(request);
			System.out.println(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ctx.close(); 
	}*/
}
