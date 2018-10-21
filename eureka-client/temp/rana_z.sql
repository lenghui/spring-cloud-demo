SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE PTP_IPROD_ATTACHS;
DROP TABLE PTP_OUT_IN_REL;
DROP TABLE PTP_FPROJ_ATTACHS;
DROP TABLE IB_ONLINE_JOURNAL;
DROP TABLE PTP_FIN_INTE_BIND;
DROP TABLE PTP_INST_TXN;
DROP TABLE PTP_PROD_REP_HIS;
DROP TABLE PTP_AUTO_INV_REL;
DROP TABLE PTP_AUTO_INV;
DROP TABLE PTP_AUDIT_REP;
DROP TABLE PTP_QUE_SURV;
DROP TABLE PTP_POINT_DETAIL;
DROP TABLE PTP_PROJ_REP_HIS;
DROP TABLE PTP_CST_BLACK_LIST;
DROP TABLE PTP_USR_LOG;
DROP TABLE PTP_TXN_ACCT_CHG;
DROP TABLE PTP_INVE_INTE_BIND;
DROP TABLE PTP_INVESTOR_REP_HIS;
DROP TABLE PTP_LOAN_TRANS;
DROP TABLE PTP_FPROJ_INST_DETAIL;
DROP TABLE PTP_ACCT_CHK_ERRS;
DROP TABLE PTP_BATCH_ERROR_LOG;
DROP TABLE PTP_PREV_REPAY;
DROP TABLE PTP_COOP_USER;
DROP TABLE PTP_REWARD_HIS;
DROP TABLE PTP_SEQUENCE;
DROP TABLE PTP_SMS_DETAIL;
DROP TABLE PTP_COOP_ATTACHS;
DROP TABLE PTP_PROD_INVEST;
DROP TABLE PTP_FPROJ_INF;
DROP TABLE PTP_COOP_COMPANY;
DROP TABLE PTP_CONTENT_MOBILE;
DROP TABLE PTP_MSG_MOBILE;
DROP TABLE PTP_MSG_USR;
DROP TABLE PTP_DEMAND_ATTACHS;
DROP TABLE PTP_USR_ATTACHS;
DROP TABLE PTP_ATTACHS;
DROP TABLE PTP_COM_INF;
DROP TABLE PTP_CST_INF;
DROP TABLE PTP_FEE_HIS;
DROP TABLE PTP_TXN_DETAIL;
DROP TABLE PTP_CARD_BIND;
DROP TABLE PTP_FIN_DEMAND_INFO;
DROP TABLE PTP_AUTO_LOG;
DROP TABLE PTP_USR_LOGIN;
DROP TABLE PTP_USR_QRCODE;
DROP TABLE PTP_MSG_PUB;




/* Create Tables */

CREATE TABLE PTP_IPROD_ATTACHS
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	INVEST_PROD_NO int NOT NULL,
	ATTACH_NO int NOT NULL,
	-- ///
	-- V|有效
	-- N|无效
	ATT_STATUS char(1) NOT NULL COMMENT '///
V|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_OUT_IN_REL
(
	RE_NO int NOT NULL UNIQUE AUTO_INCREMENT,
	TXN_NO int NOT NULL,
	TXN_IN int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_FPROJ_ATTACHS
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	PROJ_NO int NOT NULL,
	ATTACH_NO int NOT NULL,
	-- ///
	-- V|有效
	-- N|无效
	ATT_STATUS char(1) NOT NULL COMMENT '///
V|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE IB_ONLINE_JOURNAL
(
	TRANS_ID int NOT NULL AUTO_INCREMENT,
	TRANS_CODE varchar(20),
	TRANS_NAME varchar(40),
	AMOUNT decimal(15,2) NOT NULL,
	TRANS_TIME timestamp NOT NULL,
	POST_DATE date,
	ORGI_TRANS_ID int,
	CHANNEL varchar(50),
	TRANS_DESC varchar(100),
	MERCHANT_NAME varchar(50),
	FINAL_RESULT varchar(10),
	ERROR_MESSAGE varchar(400),
	JPA_VERSION int,
	PRIMARY KEY (TRANS_ID)
);


CREATE TABLE PTP_FIN_INTE_BIND
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	PROJ_NO int NOT NULL,
	GEARS_SEQ int NOT NULL,
	GEARS_DATE date,
	-- 对应利率表参数 InterestTable
	INTEREST_CODE varchar(20) NOT NULL COMMENT '对应利率表参数 InterestTable',
	-- 当期利率
	RATE decimal(5,3) COMMENT '当期利率',
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_INST_TXN
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	INST_NO int NOT NULL,
	TXN_NO int,
	-- ///
	-- R|红包
	-- P|积分
	-- E|电子现金
	-- C|卡现金
	-- B|优惠券
	FUND_CHANNEL char(1) NOT NULL COMMENT '///
R|红包
P|积分
E|电子现金
C|卡现金
B|优惠券',
	TXN_AMT decimal(15,3) NOT NULL,
	-- 资金渠道对应的原始价值，即未兑换前的金额
	INST_PRICE decimal(15,3) NOT NULL COMMENT '资金渠道对应的原始价值，即未兑换前的金额',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_PROD_REP_HIS
(
	REPAY_PLAN_KEY int NOT NULL AUTO_INCREMENT,
	INVEST_PROD_NO int NOT NULL,
	CYCLE_NUM int,
	PRINCIPAL decimal(15,3),
	FACT_PRINCIPAL decimal(15,3),
	LEFT_PRINCIPAL decimal(15,3),
	INTEREST decimal(15,3),
	FACT_INTEREST decimal(15,3),
	FEE decimal(15,3),
	FACT_FEE decimal(15,3),
	PENALIZED_AMT decimal(15,3),
	FACT_PENALIZED_AMT decimal(15,3),
	DUE_DATE date,
	-- ///
	-- N|未还款
	-- O|逾期
	-- Y|已还款
	-- F|还款失败
	-- P|部分还款
	REPAY_STATS char(1) COMMENT '///
N|未还款
O|逾期
Y|已还款
F|还款失败
P|部分还款',
	FACT_PAY_DATE date,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (REPAY_PLAN_KEY)
);


CREATE TABLE PTP_AUTO_INV_REL
(
	AUTO_INV_NO int NOT NULL,
	INST_NO int NOT NULL,
	REL_ID int NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (REL_ID)
);


CREATE TABLE PTP_AUTO_INV
(
	AUTO_INV_NO int NOT NULL AUTO_INCREMENT,
	INV_AMT decimal(15,3) NOT NULL,
	INV_AMT_MIN decimal(15,3) NOT NULL,
	CYCLE_LIMT_MAX int NOT NULL,
	ANNUAL_YIELD_MIN decimal(5,3) NOT NULL,
	AMT_KEEP decimal(15,3) NOT NULL,
	AUTO_FLG boolean NOT NULL,
	USR_NO int NOT NULL,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	EFFECT_TIME timestamp NOT NULL,
	NEARLY_INV_TIME timestamp,
	PRIMARY KEY (AUTO_INV_NO)
);


CREATE TABLE PTP_AUDIT_REP
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	USR_NO int NOT NULL,
	REP_CONTENT blob NOT NULL,
	BIZ_DATE date,
	JPA_VERSION int,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_QUE_SURV
(
	SURV_NO int NOT NULL AUTO_INCREMENT,
	USR_NO int NOT NULL,
	-- 对应问卷表，现阶段不设计该表，暂用固定值Q001表示；便于今后扩展
	QUES_NO varchar(4) DEFAULT '1' NOT NULL COMMENT '对应问卷表，现阶段不设计该表，暂用固定值Q001表示；便于今后扩展',
	SCORE int NOT NULL,
	-- ///
	-- F|失败
	-- P|通过
	SURV_RESULT char(1) NOT NULL COMMENT '///
F|失败
P|通过',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (SURV_NO)
);


CREATE TABLE PTP_POINT_DETAIL
(
	PT_SERI_NO int NOT NULL AUTO_INCREMENT,
	USR_NO int NOT NULL,
	POINT int NOT NULL,
	-- ///
	-- A0|首次登录
	-- A1|首次充值
	-- A2|投资购买
	-- A3|使用红包
	-- A4|积分兑换
	-- A5|首次绑卡
	-- A6|推荐他人并绑卡
	-- A7|投资成功却流标
	-- A8|按时还清欠款
	-- A9|关注微信
	-- 
	PT_USAGE char(2) NOT NULL COMMENT '///
A0|首次登录
A1|首次充值
A2|投资购买
A3|使用红包
A4|积分兑换
A5|首次绑卡
A6|推荐他人并绑卡
A7|投资成功却流标
A8|按时还清欠款
A9|关注微信
',
	PT_TXN_TIME timestamp,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (PT_SERI_NO)
);


CREATE TABLE PTP_PROJ_REP_HIS
(
	REPAY_PLAN_KEY int NOT NULL AUTO_INCREMENT,
	PROJ_NO int NOT NULL,
	CYCLE_NUM int,
	PRINCIPAL decimal(15,3),
	FACT_PRINCIPAL decimal(15,3),
	LEFT_PRINCIPAL decimal(15,3),
	INTEREST decimal(15,3),
	FACT_INTEREST decimal(15,3),
	FEE decimal(15,3),
	FACT_FEE decimal(15,3),
	PENALIZED_AMT decimal(15,3),
	FACT_PENALIZED_AMT decimal(15,3),
	DUE_DATE date,
	-- ///
	-- N|未还款
	-- O|逾期
	-- Y|已还款
	-- F|还款失败
	-- P|部分还款
	REPAY_STATS char(1) COMMENT '///
N|未还款
O|逾期
Y|已还款
F|还款失败
P|部分还款',
	FACT_PAY_DATE date,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	TXN_NO int,
	PRIMARY KEY (REPAY_PLAN_KEY)
);


CREATE TABLE PTP_CST_BLACK_LIST
(
	USR_NO int NOT NULL,
	REASON varchar(200),
	UPDATE_USER int NOT NULL,
	CREATE_TIME timestamp NOT NULL,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (USR_NO)
);


CREATE TABLE PTP_USR_LOG
(
	LOG_NO bigint NOT NULL AUTO_INCREMENT,
	LOG_TABLE_NAME varchar(40),
	LOG_RECORD_KEY int,
	LOG_COLUMN varchar(40),
	LOG_OLD_VALUE varchar(4000),
	LOG_NEW_VALUE varchar(4000),
	-- ///
	-- I|新增
	-- U|修改
	-- D|删除
	LOG_OPER_TYPE char(1) COMMENT '///
I|新增
U|修改
D|删除',
	LOG_USER_NO int,
	LOG_OPER_TIME datetime,
	PRIMARY KEY (LOG_NO)
);


CREATE TABLE PTP_TXN_ACCT_CHG
(
	ACCT_CHG_NO int NOT NULL AUTO_INCREMENT,
	-- ///
	-- A|转入资金
	-- O|转出资金
	-- 
	ACCT_CHG_FLAG char(1) COMMENT '///
A|转入资金
O|转出资金
',
	USR_NO int,
	SC_ACCT_SEQ int,
	-- 对应账务模块的账户号
	ACCT_SEQ int COMMENT '对应账务模块的账户号',
	TXN_AMT decimal(15,3) NOT NULL,
	TARGET_USR_NO int,
	ACCT_CHG_DESC varchar(500),
	UPDATE_USER varchar(40),
	LOG_OPER_TIME datetime,
	BIZ_DATE date,
	JPA_VERSION int NOT NULL,
	TXN_NO int NOT NULL,
	PRIMARY KEY (ACCT_CHG_NO)
);


CREATE TABLE PTP_INVE_INTE_BIND
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	INVEST_PROD_NO int NOT NULL,
	GEARS_SEQ int NOT NULL,
	GEARS_DATE date,
	-- 对应利率表参数 InterestTable
	INTEREST_CODE varchar(20) NOT NULL COMMENT '对应利率表参数 InterestTable',
	-- 当期利率
	RATE decimal(5,3) COMMENT '当期利率',
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_FPROJ_INST_DETAIL
(
	INST_NO int NOT NULL AUTO_INCREMENT,
	USR_NO int NOT NULL,
	INVEST_PROD_NO int NOT NULL,
	INST_AMT decimal(15,3) NOT NULL,
	INST_TIME timestamp NOT NULL,
	-- ///
	-- I|处理中
	-- O|预约成功
	-- U|预约失败
	-- S|投资成功
	-- F|投资失败
	-- P|超时
	INST_STATUS char NOT NULL COMMENT '///
I|处理中
O|预约成功
U|预约失败
S|投资成功
F|投资失败
P|超时',
	DEBT_TRANS_FLAG boolean NOT NULL,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	-- ///
	-- N|未通知
	-- Y|通知成功
	-- F|通知失败
	NOTICE_FLAG char(1) COMMENT '///
N|未通知
Y|通知成功
F|通知失败',
	BUSINESS_CODE varchar(40),
	PRIMARY KEY (INST_NO)
);


CREATE TABLE PTP_ACCT_CHK_ERRS
(
	ACCT_CHK_NO int NOT NULL AUTO_INCREMENT,
	TXN_NO int NOT NULL,
	TXN_TIME timestamp NOT NULL,
	TXN_AMT decimal(15,3) NOT NULL,
	CHK_DATE date NOT NULL,
	-- ///
	-- A0|加余额
	-- A1|减余额
	ERR_DIRC char(2) COMMENT '///
A0|加余额
A1|减余额',
	-- ///
	-- A0|交易未找到
	-- A1|交易金额不匹配
	-- A2|交易状态不匹配
	-- 
	ERR_REASON char(2) COMMENT '///
A0|交易未找到
A1|交易金额不匹配
A2|交易状态不匹配
',
	ERR_MARK varchar(200),
	BIZ_DATE date,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (ACCT_CHK_NO)
);


CREATE TABLE PTP_BATCH_ERROR_LOG
(
	BATCH_NO bigint NOT NULL AUTO_INCREMENT,
	STEP_EXECUTION_ID bigint NOT NULL,
	BATCH_DATE date,
	JOB_INSTANCE_ID bigint,
	JOB_NAME varchar(100),
	JOB_EXECUTION_ID bigint,
	STEP_NAME varchar(100),
	START_TIME timestamp,
	END_TIME timestamp,
	-- ///
	-- FAILED|失败
	-- COMPLETED|成功
	STATUS varchar(10) COMMENT '///
FAILED|失败
COMPLETED|成功',
	EXIT_CODE varchar(100),
	EXIT_MESSAGE varchar(2500),
	-- ///
	-- Y|已发送
	-- N|未发送
	SEND_FLAG char(1) DEFAULT 'N' COMMENT '///
Y|已发送
N|未发送',
	PRIMARY KEY (BATCH_NO)
);


CREATE TABLE PTP_LOAN_TRANS
(
	TRANS_NO int NOT NULL AUTO_INCREMENT,
	-- 转让前投资流水编号
	INST_NO int NOT NULL COMMENT '转让前投资流水编号',
	TRANS_PRICE decimal(15,3) NOT NULL,
	TRANS_VALUE decimal(15,3) NOT NULL,
	TRANS_FEE decimal(15,3) NOT NULL,
	-- ///
	-- A0|申请中
	-- A1|拒绝
	-- A2|转让中
	-- A3|已转让
	-- A4|撤销
	TRANS_STATUS char(2) NOT NULL COMMENT '///
A0|申请中
A1|拒绝
A2|转让中
A3|已转让
A4|撤销',
	-- 转让后投资流水编号
	IN_INST_NO int COMMENT '转让后投资流水编号',
	TRANS_TIME timestamp,
	TRANS_APP_TIME timestamp NOT NULL,
	AUD_MEMO varchar(500),
	AUDITOR varchar(20),
	IN_USR_NO int NOT NULL,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	TXN_NO int,
	PRIMARY KEY (TRANS_NO)
);


CREATE TABLE PTP_PREV_REPAY
(
	PREV_RP_NO int NOT NULL AUTO_INCREMENT,
	PROJ_NO int NOT NULL,
	RP_AMT decimal(15,3) NOT NULL,
	RP_APPLY_TIME timestamp NOT NULL,
	-- ///
	-- A0|申请中
	-- A1|拒绝
	-- A2|撤销
	-- A3|待扣款
	-- A4|扣款成功
	-- A5|扣款失败
	RP_STATUS char(2) NOT NULL COMMENT '///
A0|申请中
A1|拒绝
A2|撤销
A3|待扣款
A4|扣款成功
A5|扣款失败',
	AUD_MEMO varchar(500),
	AUDITOR varchar(20),
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	TXN_NO int,
	NEXT_DUE_DATE date,
	LAST_DUE_DATE date,
	SURPLUS_NUMBER int,
	PRIMARY KEY (PREV_RP_NO)
);


CREATE TABLE PTP_INVESTOR_REP_HIS
(
	COLL_PLAN_KEY int NOT NULL AUTO_INCREMENT,
	INST_NO int NOT NULL,
	USR_NO int NOT NULL,
	CYCLE_NUM int,
	LEFT_PRINCIPAL decimal(15,3),
	-- ///
	-- N|收款中
	-- Y|收款完成
	-- F|收款失败
	-- P|部分收款
	COLL_STATS char(2) COMMENT '///
N|收款中
Y|收款完成
F|收款失败
P|部分收款',
	C_PRINCIPAL decimal(15,3),
	C_INTEREST decimal(15,3),
	DUE_DATE date,
	FACT_PRINCIPAL decimal(15,3),
	FACT_INTEREST decimal(15,3),
	PENALIZED_AMT decimal(15,3),
	FACT_PENALIZED_AMT decimal(15,3),
	FACT_PAY_DATE date,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	TXN_NO int,
	PRIMARY KEY (COLL_PLAN_KEY)
);


CREATE TABLE PTP_COOP_USER
(
	COOP_USR_ID int NOT NULL AUTO_INCREMENT,
	COOP_COMP_NO int NOT NULL,
	USER_ID int NOT NULL,
	UPDATE_USER varchar(40),
	UPDATE_TIME timestamp,
	JPA_VERSION int,
	BIZ_DATE date,
	PRIMARY KEY (COOP_USR_ID)
);


CREATE TABLE PTP_REWARD_HIS
(
	REWARD_NO int NOT NULL UNIQUE AUTO_INCREMENT,
	OUT_USR_NO int,
	OUT_LOGIN_ID varchar(50) NOT NULL,
	IN_USR_NO int,
	IN_LOGIN_ID varchar(50) NOT NULL,
	REWARD_AMT decimal(15,3) NOT NULL,
	-- ///
	-- S|奖励成功
	-- F|奖励失败
	-- N|待审批
	-- A|审批拒绝
	REWARD_STATUS char(1) COMMENT '///
S|奖励成功
F|奖励失败
N|待审批
A|审批拒绝',
	-- ///
	-- R1|余额不足
	-- R2|未找到发放奖励用户
	-- R3|未找到接收奖励用户
	-- R4|接收奖励用户未绑卡
	-- R5|发放奖励用户未绑卡
	-- R6|审批拒绝
	-- R7|奖励金额为0
	-- R8|奖励报表格式异常
	REASON_CODE char(2) COMMENT '///
R1|余额不足
R2|未找到发放奖励用户
R3|未找到接收奖励用户
R4|接收奖励用户未绑卡
R5|发放奖励用户未绑卡
R6|审批拒绝
R7|奖励金额为0
R8|奖励报表格式异常',
	REWARD_TIME timestamp,
	TXN_NO_ECASH int,
	TXN_NO_DCASH int,
	BIZ_DATE date,
	UPDATE_USER varchar(40),
	REWARD_BATCH_ID varchar(40),
	APPROVE_REJECT_REASON varchar(100),
	APPROVE_USERID varchar(40),
	UPLOAD_TIME timestamp,
	-- ///
	-- M|平台注册用户
	-- P|平台账户
	OUT_USR_TYPE char COMMENT '///
M|平台注册用户
P|平台账户',
	REMARK varchar(50),
	PRIMARY KEY (REWARD_NO)
);


CREATE TABLE PTP_SEQUENCE
(
	SEQ_NO int NOT NULL UNIQUE AUTO_INCREMENT,
	-- ///
	-- A1|普通邀请码
	-- A2|特殊邀请码
	-- A3|特约代码
	SEQ_TYPE varchar(2) NOT NULL UNIQUE COMMENT '///
A1|普通邀请码
A2|特殊邀请码
A3|特约代码',
	SEQ_VALUE bigint NOT NULL,
	JPA_VERSION int,
	PRIMARY KEY (SEQ_NO)
);


CREATE TABLE PTP_SMS_DETAIL
(
	SMS_ID int NOT NULL AUTO_INCREMENT,
	-- ///
	-- LO|放款成功提醒
	-- LI|还款投资人提醒
	-- RD|奖励发放提醒
	-- LP|提前还款投资人提醒
	SMS_TYPE char(2) NOT NULL COMMENT '///
LO|放款成功提醒
LI|还款投资人提醒
RD|奖励发放提醒
LP|提前还款投资人提醒',
	SMS_CONTENT varchar(500) NOT NULL,
	SMS_TIME timestamp,
	-- ///
	-- S|发送成功
	-- W|待发送
	-- F|发送失败
	SMS_STATUS char(1) NOT NULL COMMENT '///
S|发送成功
W|待发送
F|发送失败',
	TEL_NO varchar(11) NOT NULL,
	JPA_VERSION int NOT NULL,
	BIZ_DATE date,
	USR_NO int NOT NULL,
	PRIMARY KEY (SMS_ID)
);


CREATE TABLE PTP_COOP_ATTACHS
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	ATTACH_NO int NOT NULL,
	COOP_COMP_NO int NOT NULL,
	-- ///
	-- V|有效
	-- N|无效
	ATT_STATUS char(1) NOT NULL COMMENT '///
V|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_COOP_COMPANY
(
	COOP_COMP_NO int NOT NULL AUTO_INCREMENT,
	-- ///
	-- D|担保公司
	-- X|小贷公司
	-- T|推广公司
	COOP_TYPE char(1) NOT NULL COMMENT '///
D|担保公司
X|小贷公司
T|推广公司',
	COMP_NAME varchar(100) NOT NULL,
	ORG_ID varchar(20) NOT NULL UNIQUE,
	BIZ_CERT_NO varchar(20) NOT NULL UNIQUE,
	BIZ_CERT_ADD varchar(40),
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.CorpStructure
	COM_TYPE char(2) NOT NULL COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.CorpStructure',
	CORP_NAME varchar(20) NOT NULL,
	CORP_CERT_ID varchar(20) NOT NULL,
	POST_NO int,
	FROM_DATE date,
	TRADE_RANGE varchar(500),
	REG_AMT decimal(15,3),
	COMP_CITY varchar(60),
	ADDRESS varchar(200),
	COMP_PHONE varchar(60),
	CONTACTER varchar(60),
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType
	CONTACTER_POS char(1) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType',
	CONTACT_TEL varchar(11),
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PROFIT_CARD_NO varchar(30),
	PROFIT_BANK_NO varchar(6),
	PROFIT_BANK_NAME varchar(100),
	GUAR_BANK_NO varchar(6),
	GUAR_BANK_NAME varchar(100),
	GUAR_CARD_NO varchar(30),
	USR_NO int NOT NULL,
	WEB_COOP_NAME varchar(30),
	WEB_COOP_DESC varchar(2000),
	WEB_COOP_DETAIL varchar(6000),
	PRIMARY KEY (COOP_COMP_NO)
);


CREATE TABLE PTP_CONTENT_MOBILE
(
	CONTENT_NO int NOT NULL AUTO_INCREMENT,
	TITLE varchar(100),
	TITLE_IMG varchar(200),
	CONTENT_URL varchar(200),
	-- ///
	-- URL|超链接
	-- DIY|自定义
	CONTENT_TYPE varchar(10) COMMENT '///
URL|超链接
DIY|自定义',
	CONTENT_INFO varchar(20000),
	ACTIVE_NAME varchar(200),
	-- ///
	-- Y|有效
	-- N|无效
	IS_VALID varchar(2) COMMENT '///
Y|有效
N|无效',
	CREATE_TIME timestamp,
	UPDATE_TIME timestamp,
	CREATE_USER varchar(40),
	UPDATE_USER varchar(40),
	PRIMARY KEY (CONTENT_NO)
);


CREATE TABLE PTP_MSG_MOBILE
(
	MSG_NO int NOT NULL AUTO_INCREMENT,
	TITLE varchar(100),
	-- ///
	-- OP|公开消息
	-- PR|个人消息
	MSG_OPEN_TYPE varchar(2) COMMENT '///
OP|公开消息
PR|个人消息',
	-- ///
	-- M1|网站消息
	-- M2|标的发布消息
	-- M3|标的提醒消息
	-- M4|借款通知消息
	-- M5|活动消息
	-- M6|其他
	MES_TYPE varchar(2) COMMENT '///
M1|网站消息
M2|标的发布消息
M3|标的提醒消息
M4|借款通知消息
M5|活动消息
M6|其他',
	MSG_INFO varchar(800),
	-- ///
	-- Y|有效
	-- N|无效
	IS_VALID varchar(2) COMMENT '///
Y|有效
N|无效',
	BEGIN_TIME timestamp,
	PUB_DATE timestamp,
	CREATE_TIME timestamp,
	UPDATE_TIME timestamp,
	CREATE_USER varchar(40),
	UPDATE_USER varchar(40),
	PRIMARY KEY (MSG_NO)
);


CREATE TABLE PTP_MSG_USR
(
	USR_MSG_NO int NOT NULL AUTO_INCREMENT,
	MSG_NO int,
	USR_NO int,
	PRIMARY KEY (USR_MSG_NO)
);


CREATE TABLE PTP_ATTACHS
(
	ATTACH_NO int NOT NULL AUTO_INCREMENT,
	ATTACH_NAME varchar(100) NOT NULL,
	ATTACH_MODE char(5) NOT NULL,
	FILE_CONTENT mediumblob NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (ATTACH_NO)
);


CREATE TABLE PTP_USR_ATTACHS
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	-- ///
	-- V|有效
	-- N|无效
	ATT_STATUS char(1) NOT NULL COMMENT '///
V|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	USR_NO int NOT NULL,
	ATTACH_NO int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_COM_INF
(
	USR_NO int NOT NULL,
	COMP_NAME varchar(100) NOT NULL,
	ORG_ID varchar(20),
	BIZ_CERT_NO varchar(20),
	BIZ_CERT_ADD varchar(40),
	COMP_PHONE varchar(60),
	ADDRESS varchar(200),
	POST_NO int,
	FROM_DATE date,
	TRADE_RANGE varchar(500),
	REG_AMT decimal(15,3),
	CONTACTER varchar(60),
	-- ///
	-- T01|IT/互联网/通信/电子
	-- T02|金融/会计/银行/保险
	-- T03|房地产/建筑业
	-- T04|商业服务
	-- T05|教育/培训
	-- T06|广告/媒体
	-- T07|消费品/贸易/批发/零售
	-- T08|加工制造/仪表设备
	-- T09|交通/物流/运输
	-- T10|制药/医疗
	-- T11|服务业
	-- T12|能源/采掘/化工/环保
	-- T13|政府/农林牧渔/其他
	CORP_SIC varchar(6) COMMENT '///
T01|IT/互联网/通信/电子
T02|金融/会计/银行/保险
T03|房地产/建筑业
T04|商业服务
T05|教育/培训
T06|广告/媒体
T07|消费品/贸易/批发/零售
T08|加工制造/仪表设备
T09|交通/物流/运输
T10|制药/医疗
T11|服务业
T12|能源/采掘/化工/环保
T13|政府/农林牧渔/其他',
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType
	CONTACTER_POS char(1) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType',
	CONTACT_TEL varchar(11),
	TAX_NO varchar(30),
	BUSINESS_LIMIT date,
	COM_INCOME decimal(15,3),
	WORK_YEAR varchar(20),
	EARN_AMT decimal(15,3),
	ASSET_AMT decimal(15,3),
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.CorpStructure
	COM_TYPE char(2) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.CorpStructure',
	BIZ_DATE date,
	JPA_VERSION int,
	DEBT_RATIO decimal(5,3),
	-- 法人姓名
	LEGAL_PERSON_NAME varchar(30) COMMENT '法人姓名',
	-- 法人身份证
	LP_IDNO varchar(30) COMMENT '法人身份证',
	PRIMARY KEY (USR_NO)
);


-- 作为CI_CUSTOMER表的补充信息
CREATE TABLE PTP_CST_INF
(
	USR_NO int NOT NULL,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	NATIONALITY varchar(30),
	TEMP_PERMIT varchar(30),
	POST_TITLE varchar(30),
	FAX varchar(30),
	OTHER_INFO varchar(200),
	PRIMARY KEY (USR_NO)
) COMMENT = '作为CI_CUSTOMER表的补充信息';


CREATE TABLE PTP_DEMAND_ATTACHS
(
	RE_NO int NOT NULL AUTO_INCREMENT,
	DEMAND_ID int NOT NULL,
	ATTACH_NO int NOT NULL,
	-- ///
	-- V|有效
	-- N|无效
	ATT_STATUS char(1) NOT NULL COMMENT '///
V|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (RE_NO)
);


CREATE TABLE PTP_USR_LOGIN
(
	USR_NO int NOT NULL AUTO_INCREMENT,
	-- 对应CI_CUSTOMER 表主键
	CUST_ID int NOT NULL COMMENT '对应CI_CUSTOMER 表主键',
	LOGIN_ID varchar(50) NOT NULL UNIQUE,
	MEM_ID varchar(20) NOT NULL UNIQUE,
	MOBILE varchar(11) NOT NULL UNIQUE,
	EMAIL varchar(50),
	LOGIN_PWD varchar(40) NOT NULL,
	TXN_PWD varchar(40),
	-- ///
	-- C|个人用户
	-- P|企业用户
	-- O|机构用户
	CST_TYPE char(1) NOT NULL COMMENT '///
C|个人用户
P|企业用户
O|机构用户',
	REG_TIME timestamp NOT NULL,
	BIZ_DATE date NOT NULL,
	ECASH_ACCT_SEQ int,
	FREEZ_ACCT_SEQ int,
	DCASH_ACCT_SEQ int,
	FREEZ_DCASH_ACCT_SEQ int,
	BENE_ACCT_SEQ int,
	CUST_LEVEL char(2),
	CREDIT_LEVEL char(2),
	-- 对应USR_NO
	REFERRER int COMMENT '对应USR_NO',
	FST_INVEST_TIME timestamp,
	CB_FLAG boolean NOT NULL,
	JPA_VERSION int NOT NULL,
	WRONG_TIMES int,
	INST_NO int,
	EMAIL_ACTIVATION varchar(40),
	EMAIL_FLG boolean DEFAULT '0' NOT NULL,
	LOCK_FLG boolean NOT NULL,
	TRY_CNT int,
	BUSINESS_CODE varchar(40),
	OPEN_ID varchar(40),
	-- ///
	-- Y|是
	-- N|否
	AUTO_LOGIN varchar(1) COMMENT '///
Y|是
N|否',
	PRIMARY KEY (USR_NO)
);


CREATE TABLE PTP_AUTO_LOG
(
	AUTO_ID int NOT NULL UNIQUE AUTO_INCREMENT,
	-- ///
	-- AU|设置自动登录
	-- CA|取消自动登录
	AUTO_FLAG varchar(2) COMMENT '///
AU|设置自动登录
CA|取消自动登录',
	CREATE_TIME timestamp,
	USR_NO int NOT NULL,
	PRIMARY KEY (AUTO_ID)
);


CREATE TABLE PTP_USR_QRCODE
(
	USR_NO int NOT NULL UNIQUE,
	QR_CODE varchar(6000),
	PRIMARY KEY (USR_NO)
);


CREATE TABLE PTP_CARD_BIND
(
	CB_NO int NOT NULL AUTO_INCREMENT,
	USR_NO int NOT NULL,
	CARD_NO varchar(40) NOT NULL,
	BANK_NO varchar(12) NOT NULL,
	BANK_NAME varchar(100) NOT NULL,
	PROTOCOL_NO varchar(100),
	ACCT_NAME varchar(30),
	ID_NO varchar(18),
	E_ACCT_NO varchar(60),
	-- ///
	-- V|有效
	-- N|无效
	CARD_STATUS char(1) NOT NULL COMMENT '///
V|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PROVINCE varchar(40),
	CARD_CITY varchar(40),
	PRIMARY KEY (CB_NO)
);


CREATE TABLE PTP_FEE_HIS
(
	FEE_NO int NOT NULL AUTO_INCREMENT,
	TXN_NO int NOT NULL,
	-- 相应发生业务表的编号
	FEE_SC_NO int NOT NULL COMMENT '相应发生业务表的编号',
	-- ///
	-- @net.engining.lbt.param.model.enums.FeeInd
	FEE_TYPE char(2) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.FeeInd',
	FEE_DESC varchar(300),
	FEE_AMT decimal(15,3) NOT NULL,
	FEE_ACT_AMT decimal(15,3),
	-- ///
	-- U|处理中
	-- A|已收取
	-- R|已退回
	-- F|收取失败
	-- W|退费失败
	FEE_STATUS char(1) NOT NULL COMMENT '///
U|处理中
A|已收取
R|已退回
F|收取失败
W|退费失败',
	-- ///
	-- R|红包
	-- P|积分
	-- E|电子现金
	-- C|卡现金
	-- B|优惠券
	FUND_CHANNEL char(1) NOT NULL COMMENT '///
R|红包
P|积分
E|电子现金
C|卡现金
B|优惠券',
	-- 资金渠道对应的原始价值，即未兑换前的金额
	SC_PRICE decimal(15,3) NOT NULL COMMENT '资金渠道对应的原始价值，即未兑换前的金额',
	FEE_DATE date NOT NULL,
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	-- ///
	-- @net.engining.lbt.param.model.enums.FeeModeType
	-- 
	FEE_MODE_TYPE char(1) COMMENT '///
@net.engining.lbt.param.model.enums.FeeModeType
',
	-- ///
	-- M|人工收取
	-- L|放款时收取
	-- E|到期日收取
	-- 
	FEE_CHARGE_TYPE char(1) COMMENT '///
M|人工收取
L|放款时收取
E|到期日收取
',
	PRIMARY KEY (FEE_NO)
);


CREATE TABLE PTP_MSG_PUB
(
	MSG_NO int NOT NULL AUTO_INCREMENT,
	MSG_TITLE varchar(200),
	MSG_CONTENT varchar(6000),
	-- ///
	-- B|公告
	-- N|新闻
	-- R|还款公告
	MSG_TYPE char(1) COMMENT '///
B|公告
N|新闻
R|还款公告',
	MSG_FROM varchar(50),
	MSG_USER varchar(20),
	MSG_TIME timestamp,
	-- ///
	-- Y|有效
	-- N|无效
	MSG_STATUS char(1) DEFAULT 'Y' COMMENT '///
Y|有效
N|无效',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (MSG_NO)
);


CREATE TABLE PTP_TXN_DETAIL
(
	TXN_NO int NOT NULL AUTO_INCREMENT,
	USR_NO int,
	SC_ACCT_SEQ int,
	-- 对应账务模块的账户号
	ACCT_SEQ int COMMENT '对应账务模块的账户号',
	-- ///
	-- @net.engining.lbt.param.model.enums.TxnTypeDef
	TXN_TYPE char(2) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.TxnTypeDef',
	TXN_AMT decimal(15,3) NOT NULL,
	TXN_TIME timestamp NOT NULL,
	-- ///
	-- S|交易完成
	-- I|处理中
	-- F|交易失败
	TXN_STATUS char NOT NULL COMMENT '///
S|交易完成
I|处理中
F|交易失败',
	ACCT_IN_FLG boolean,
	ACCT_CHK_FLG boolean,
	-- ///
	-- T|外部扣款
	-- I|核心入账
	TXN_CHANNEL char(1) COMMENT '///
T|外部扣款
I|核心入账',
	TXN_CHANNEL_NO varchar(30),
	TXN_SERIAL_NO varchar(30),
	SOURCE_CARD varchar(30),
	SOURCE_BANK varchar(100),
	SOURCE_BANK_NO varchar(12),
	TARGET_CARD varchar(30),
	TARGET_BANK_NO varchar(12),
	TARGET_BANK varchar(100),
	TARGET_USR_NO int,
	BIZ_DATE date,
	JPA_VERSION int NOT NULL,
	PAY_ORDER_TIME varchar(14),
	PAY_RESULT varchar(2),
	PAY_ERROR_CODE varchar(10),
	PLAN_TXN_AMT decimal(15,3),
	PRIMARY KEY (TXN_NO)
);


CREATE TABLE PTP_PROD_INVEST
(
	INVEST_PROD_NO int NOT NULL AUTO_INCREMENT,
	PROJ_NO int NOT NULL,
	COOP_COMP_NO int NOT NULL,
	-- 金融产品模版参数
	PROD_ID char(4) NOT NULL COMMENT '金融产品模版参数',
	-- 对应费用模型参数 FeeModes
	FSPLIT_NO char(4) COMMENT '对应费用模型参数 FeeModes',
	INVEST_PROD_NAME varchar(100) NOT NULL,
	ANNUAL_YIELD decimal(5,3) NOT NULL,
	DEMAND_AMT decimal(15,3) NOT NULL,
	LOW_DEMAND_AMT decimal(15,3) NOT NULL,
	-- ///
	-- @net.engining.lbt.param.model.enums.InterestType
	INTEREST_TYPE char(1) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.InterestType',
	INVE_CYCLE_LIMIT int NOT NULL,
	-- ///
	-- @net.engining.lbt.param.model.enums.TermUnit
	INEV_CYCLE_LIMIT_UN char(1) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.TermUnit',
	BEGIN_DATE timestamp NOT NULL,
	END_DATE timestamp NOT NULL,
	FINISH_DATE timestamp,
	INVE_DAYS int,
	LAST_CHARGE_DATE date,
	-- ///
	-- S0|待复核
	-- A1|投资复核失败
	-- S1|募集中
	-- S2|募集失败
	-- S3|募集完成
	-- B0|通知划账
	-- B1|已通知划账
	-- B2|通知划账失败
	-- B3|扣款中
	-- B4|扣款完成
	-- B5|扣费中
	-- B6|扣费完成
	-- B7|扣费失败
	-- B8|流标退款中
	-- B9|放款中
	-- S4|收益中
	-- S5|到期未收益
	-- S6|收益结清
	INVEST_PROD_STATE char(2) NOT NULL COMMENT '///
S0|待复核
A1|投资复核失败
S1|募集中
S2|募集失败
S3|募集完成
B0|通知划账
B1|已通知划账
B2|通知划账失败
B3|扣款中
B4|扣款完成
B5|扣费中
B6|扣费完成
B7|扣费失败
B8|流标退款中
B9|放款中
S4|收益中
S5|到期未收益
S6|收益结清',
	-- ///
	-- N|正常
	-- Y|流标
	COLL_FAIL_STATUS char(1) COMMENT '///
N|正常
Y|流标',
	APP_REASON varchar(500),
	-- 承诺还款的目标账户；当融资项目还款方式与投资产品还款方式不同时，对应担保公司/租赁公司/小贷公司/平台承诺还款的账户，否则就是融资贷款账户
	REPAY_ACCT_SEQ int COMMENT '承诺还款的目标账户；当融资项目还款方式与投资产品还款方式不同时，对应担保公司/租赁公司/小贷公司/平台承诺还款的账户，否则就是融资贷款账户',
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PUBAUDITOR varchar(20),
	PUBAUDMEMO varchar(500),
	FAILREAUDITOR varchar(20),
	ORDER_NO int DEFAULT 0,
	INVITE_CODE varchar(30),
	PROGRESS_SCORE decimal(15,3) DEFAULT 0,
	-- ///
	-- N|未通知
	-- Y|已通知
	NOTICE_END varchar(1) COMMENT '///
N|未通知
Y|已通知',
	-- ///
	-- Y|是
	-- N|否
	IS_COMM varchar(1) COMMENT '///
Y|是
N|否',
	COMM_SCORE int DEFAULT 0,
	PRIMARY KEY (INVEST_PROD_NO)
);


CREATE TABLE PTP_FPROJ_INF
(
	PROJ_NO int NOT NULL AUTO_INCREMENT,
	DEMAND_ID int NOT NULL,
	-- 金融产品模版参数
	PROD_ID char(4) NOT NULL COMMENT '金融产品模版参数',
	-- 对应费用模型参数 FeeModes
	FSPLIT_NO char(4) NOT NULL COMMENT '对应费用模型参数 FeeModes',
	COOP_COMP_NO int NOT NULL,
	PROJ_NAME varchar(50) NOT NULL,
	DEMAND_AMT decimal(15,3) NOT NULL,
	MIN_FIN_AMT decimal(15,3),
	LAST_AMT decimal(15,3),
	ANNUAL_YIELD decimal(5,3) NOT NULL,
	LAST_LOAN_DATE date,
	LOAN_DATE date,
	-- ///
	-- @net.engining.lbt.param.model.enums.InterestType
	INTEREST_TYPE char(1) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.InterestType',
	FIN_CYCLE_LIMIT int NOT NULL,
	-- ///
	-- @net.engining.lbt.param.model.enums.TermUnit
	FIN_CYCLE_LIMIT_UN char(1) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.TermUnit',
	PURPOSES varchar(500),
	-- 融资人的贷款账户
	DEBIT_ACCT_SEQ int COMMENT '融资人的贷款账户',
	-- ///
	-- A|A
	-- B|AA
	-- C|AAA
	-- D|AAAA
	-- E|AAAAA
	RISK_LEV char(1) COMMENT '///
A|A
B|AA
C|AAA
D|AAAA
E|AAAAA',
	-- ///
	-- A0|待审批
	-- A1|拒绝
	-- A2|待发布
	-- A3|发布申请中
	-- S2|融资中
	-- S3|融资失败
	-- S4|融资完成
	-- S5|还款中
	-- S6|到期未还款
	-- S7|结清
	PROJ_STATUS char(3) NOT NULL COMMENT '///
A0|待审批
A1|拒绝
A2|待发布
A3|发布申请中
S2|融资中
S3|融资失败
S4|融资完成
S5|还款中
S6|到期未还款
S7|结清',
	-- 对应银行参数 BankInfo
	BANK_CODE varchar(10) NOT NULL COMMENT '对应银行参数 BankInfo',
	CARD_NO varchar(30) NOT NULL,
	COMP_CARD_NO varchar(30),
	LOAN_FEE decimal(15,3),
	AUD_MEMO varchar(500),
	AUDIT_DATE timestamp,
	AUDITOR varchar(20),
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	PRIMARY KEY (PROJ_NO)
);


CREATE TABLE PTP_FIN_DEMAND_INFO
(
	DEMAND_ID int NOT NULL AUTO_INCREMENT,
	USR_NO int,
	-- 金融产品模版参数
	PROD_ID char(4) NOT NULL COMMENT '金融产品模版参数',
	-- ///
	-- c|个人用户
	-- p|企业用户
	CUST_TYPE char(1) NOT NULL COMMENT '///
c|个人用户
p|企业用户',
	REAL_NAME varchar(80) NOT NULL,
	ID_NUMBER varchar(20) NOT NULL,
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.Gender
	GENDER char(1) NOT NULL COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.Gender',
	BIRTHDAY date,
	MOBILE varchar(11),
	EMAIL varchar(50),
	FM_PHONE varchar(11),
	FM_ADDRESS varchar(500),
	LC_CITY varchar(30),
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.MaritalStatus
	MARITAL_STATUS char(1) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.MaritalStatus',
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.EducationType
	QUALIFICATION char(1) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.EducationType',
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType
	POSTION char(1) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType',
	INCOME decimal(15,3),
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.CorpStructure
	COM_TYPE char(2) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.CorpStructure',
	-- ///
	-- T01|IT/互联网/通信/电子
	-- T02|金融/会计/银行/保险
	-- T03|房地产/建筑业
	-- T04|商业服务
	-- T05|教育/培训
	-- T06|广告/媒体
	-- T07|消费品/贸易/批发/零售
	-- T08|加工制造/仪表设备
	-- T09|交通/物流/运输
	-- T10|制药/医疗
	-- T11|服务业
	-- T12|能源/采掘/化工/环保
	-- T13|政府/农林牧渔/其他
	CORP_SIC varchar(6) COMMENT '///
T01|IT/互联网/通信/电子
T02|金融/会计/银行/保险
T03|房地产/建筑业
T04|商业服务
T05|教育/培训
T06|广告/媒体
T07|消费品/贸易/批发/零售
T08|加工制造/仪表设备
T09|交通/物流/运输
T10|制药/医疗
T11|服务业
T12|能源/采掘/化工/环保
T13|政府/农林牧渔/其他',
	ORG_ID varchar(20),
	BIZ_CERT_NO varchar(20),
	BIZ_CERT_ADD varchar(40),
	COMPANY varchar(50),
	REG_TIME timestamp,
	REG_AMT decimal(15,3),
	COMP_PHONE varchar(60),
	ADDRESS varchar(200),
	CONTACTER varchar(60),
	-- ///
	-- @net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType
	CONTACTER_POS char(1) COMMENT '///
@net.engining.pcx.gm.infrastructure.shared.enums.EmpPositionAttrType',
	CONTACT_TEL varchar(11),
	-- ///
	-- @net.engining.lbt.param.model.enums.FinanceUsage
	FIN_USEAGE char(2) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.FinanceUsage',
	PROJ_AMT decimal(15,3) NOT NULL,
	EXP_TERM int NOT NULL,
	-- ///
	-- @net.engining.lbt.param.model.enums.TermUnit
	EXP_TEAM_UN char(1) NOT NULL COMMENT '///
@net.engining.lbt.param.model.enums.TermUnit',
	EXP_DATE date NOT NULL,
	FIN_DESC varchar(2000) NOT NULL,
	-- 对应银行参数 BankInfo
	BANK_CODE varchar(10) COMMENT '对应银行参数 BankInfo',
	CARD_NO varchar(30),
	MORTGAGE_FLG boolean,
	APPLY_TIME timestamp NOT NULL,
	-- ///
	-- Y|新建
	-- N|待审批
	-- P|审批通过
	-- R|审批拒绝
	APP_STATS char(1) NOT NULL COMMENT '///
Y|新建
N|待审批
P|审批通过
R|审批拒绝',
	APP_REASON varchar(500),
	AUDIT_DATE timestamp,
	AUDITOR varchar(20),
	REPAY_SOURCE varchar(200),
	BIZ_DATE date NOT NULL,
	JPA_VERSION int NOT NULL,
	NATIONALITY varchar(30),
	TEMP_PERMIT varchar(30),
	POST_TITLE varchar(30),
	FAX varchar(30),
	OTHER_INFO varchar(200),
	BUSINESS_LIMIT date,
	TAX_NO varchar(30),
	WORK_YEAR varchar(20),
	EARN_AMT decimal(15,3),
	ASSET_AMT decimal(15,3),
	DEBT_RATIO decimal(15,3),
	COM_INCOME decimal(15,3),
	LEGAL_PERSON_NAME varchar(30),
	LP_IDNO varchar(30),
	-- ///
	-- Y|是
	-- N|否
	IS_PRE_REPAY varchar(1) COMMENT '///
Y|是
N|否',
	TARGET_COMP_NO int,
	PRIMARY KEY (DEMAND_ID)
);



/* Create Foreign Keys */

ALTER TABLE PTP_AUTO_INV_REL
	ADD FOREIGN KEY (AUTO_INV_NO)
	REFERENCES PTP_AUTO_INV (AUTO_INV_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_INVESTOR_REP_HIS
	ADD FOREIGN KEY (INST_NO)
	REFERENCES PTP_FPROJ_INST_DETAIL (INST_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_LOAN_TRANS
	ADD FOREIGN KEY (INST_NO)
	REFERENCES PTP_FPROJ_INST_DETAIL (INST_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_INST_TXN
	ADD FOREIGN KEY (INST_NO)
	REFERENCES PTP_FPROJ_INST_DETAIL (INST_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_AUTO_INV_REL
	ADD FOREIGN KEY (INST_NO)
	REFERENCES PTP_FPROJ_INST_DETAIL (INST_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FPROJ_INF
	ADD FOREIGN KEY (COOP_COMP_NO)
	REFERENCES PTP_COOP_COMPANY (COOP_COMP_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PROD_INVEST
	ADD FOREIGN KEY (COOP_COMP_NO)
	REFERENCES PTP_COOP_COMPANY (COOP_COMP_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_COOP_USER
	ADD FOREIGN KEY (COOP_COMP_NO)
	REFERENCES PTP_COOP_COMPANY (COOP_COMP_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_COOP_ATTACHS
	ADD FOREIGN KEY (COOP_COMP_NO)
	REFERENCES PTP_COOP_COMPANY (COOP_COMP_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_DEMAND_ATTACHS
	ADD FOREIGN KEY (ATTACH_NO)
	REFERENCES PTP_ATTACHS (ATTACH_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_USR_ATTACHS
	ADD FOREIGN KEY (ATTACH_NO)
	REFERENCES PTP_ATTACHS (ATTACH_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_IPROD_ATTACHS
	ADD FOREIGN KEY (ATTACH_NO)
	REFERENCES PTP_ATTACHS (ATTACH_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FPROJ_ATTACHS
	ADD FOREIGN KEY (ATTACH_NO)
	REFERENCES PTP_ATTACHS (ATTACH_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_COOP_ATTACHS
	ADD FOREIGN KEY (ATTACH_NO)
	REFERENCES PTP_ATTACHS (ATTACH_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_USR_ATTACHS
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_TXN_DETAIL
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_CARD_BIND
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_QUE_SURV
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_AUTO_INV
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_CST_INF
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_SMS_DETAIL
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FIN_DEMAND_INFO
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_COM_INF
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_AUDIT_REP
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_CST_BLACK_LIST
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FPROJ_INST_DETAIL
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_COOP_COMPANY
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_AUTO_LOG
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_POINT_DETAIL
	ADD FOREIGN KEY (USR_NO)
	REFERENCES PTP_USR_LOGIN (USR_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PROJ_REP_HIS
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PREV_REPAY
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_OUT_IN_REL
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_INST_TXN
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_INVESTOR_REP_HIS
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_ACCT_CHK_ERRS
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FEE_HIS
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_TXN_ACCT_CHG
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_LOAN_TRANS
	ADD FOREIGN KEY (TXN_NO)
	REFERENCES PTP_TXN_DETAIL (TXN_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FPROJ_INST_DETAIL
	ADD FOREIGN KEY (INVEST_PROD_NO)
	REFERENCES PTP_PROD_INVEST (INVEST_PROD_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_INVE_INTE_BIND
	ADD FOREIGN KEY (INVEST_PROD_NO)
	REFERENCES PTP_PROD_INVEST (INVEST_PROD_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_IPROD_ATTACHS
	ADD FOREIGN KEY (INVEST_PROD_NO)
	REFERENCES PTP_PROD_INVEST (INVEST_PROD_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PROD_REP_HIS
	ADD FOREIGN KEY (INVEST_PROD_NO)
	REFERENCES PTP_PROD_INVEST (INVEST_PROD_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FPROJ_ATTACHS
	ADD FOREIGN KEY (PROJ_NO)
	REFERENCES PTP_FPROJ_INF (PROJ_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PREV_REPAY
	ADD FOREIGN KEY (PROJ_NO)
	REFERENCES PTP_FPROJ_INF (PROJ_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PROD_INVEST
	ADD FOREIGN KEY (PROJ_NO)
	REFERENCES PTP_FPROJ_INF (PROJ_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_PROJ_REP_HIS
	ADD FOREIGN KEY (PROJ_NO)
	REFERENCES PTP_FPROJ_INF (PROJ_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FIN_INTE_BIND
	ADD FOREIGN KEY (PROJ_NO)
	REFERENCES PTP_FPROJ_INF (PROJ_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_DEMAND_ATTACHS
	ADD FOREIGN KEY (DEMAND_ID)
	REFERENCES PTP_FIN_DEMAND_INFO (DEMAND_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PTP_FPROJ_INF
	ADD FOREIGN KEY (DEMAND_ID)
	REFERENCES PTP_FIN_DEMAND_INFO (DEMAND_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



