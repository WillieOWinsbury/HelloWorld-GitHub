package Day12_3.SoSo.entity;

import cn.soso.common.Common;
import cn.soso.service.CallService;
import cn.soso.service.NetService;
import cn.soso.service.SendService;

/**
 * �����ײ�
 * @author ��������
 *
 */
public class SuperPackage extends ServicePackage implements CallService,
SendService,NetService {
	private int talkTime;   //ͨ��ʱ��(����)
    private int smsCount;   //��������������
    private int flow;  //����������MB��
       
	public int getTalkTime() {
		return talkTime;
	}


	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}


	public int getSmsCount() {
		return smsCount;
	}


	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
    
	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	public SuperPackage(){
		//�ײ����ݳ�ʼ��
		this.talkTime = 200;
		this.smsCount = 50;
		this.flow = 1*1024;  
		this.price = 78.0;
	}
	@Override
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ"+this.talkTime+"����/�£���������Ϊ"+this.smsCount+"��/�£���������Ϊ"+this.flow/1024+"GB/�¡�");
	}


	/**
	 * �ṩ��������
	 */
	public int netPlay(int flow, MobileCard card) throws Exception {
		int temp = flow;
		for(int i=0;i<flow;i++){
			if(this.flow-card.getRealFlow()>=1){
				//��һ��������ײ�ʣ����������֧��ʹ��1M����			
				card.setRealFlow(card.getRealFlow()+1); //ʵ��ʹ��������1MB				
			}else if(card.getMoney()>=0.1){
				//�ڶ������:�ײ����������꣬�˻�������֧��1M������ʹ���˻����֧��
				card.setRealFlow(card.getRealFlow()+1); //ʵ��ʹ��������1MB
				card.setMoney(Common.sub(card.getMoney(),0.1)); //�˻��������0.1Ԫ��1M�������ã�
				card.setConsumAmount(card.getConsumAmount() + 0.1);
			}else{
				temp = i;
				throw new Exception("������ʹ������"+i+"MB,�������㣬���ֵ����ʹ�ã�");
			}
		}
		return temp;
	}

	/**
	 * �ṩͨ������
	 */
	public int call(int minCount, MobileCard card) throws Exception{
		int temp = minCount; 
		for(int i=0;i<minCount;i++){
			if(this.talkTime-card.getRealTalkTime()>=1){
				//��һ��������ײ�ʣ��ͨ��ʱ�����Ը�1����ͨ��			
				card.setRealTalkTime(card.getRealTalkTime()+1); //ʵ��ͨ�����ݼ�1				
			}else if(card.getMoney()>=0.2){
				//�ڶ������:�ײ�ͨ��ʱ�������꣬�˻�������֧��1����ͨ����ʹ���˻����֧��
				card.setRealTalkTime(card.getRealTalkTime()+1); //ʵ��ʹ��ͨ��ʱ��1���� 
				card.setMoney(Common.sub(card.getMoney(),0.2)); //�˻��������0.2Ԫ��1���Ӷ���ͨ����
				card.setConsumAmount(card.getConsumAmount() + 0.2);
			}else{
				temp = i; //��¼ʵ��ͨ��������
				throw new Exception("������ͨ��"+i+"����,�������㣬���ֵ����ʹ�ã�");				
			}
		}
		return temp;
	}
	
	/**
	 * �ṩ���ŷ���
	 */
	public int sendMessage(int smsCount, MobileCard card) throws Exception {
		int temp = smsCount;
		for(int i=0;i<smsCount;i++){
			if(this.smsCount-card.getRealSMSCount()>=1){
				//��һ��������ײ�ʣ������������Ը�1������			
				card.setRealSMSCount(card.getRealSMSCount()+1); //ʵ��ʹ�ö���������1				
			}else if(card.getMoney()>=0.1){
				//�ڶ������:�ײͶ������������꣬�˻�������֧��1�����ţ�ʹ���˻����֧��
				card.setRealSMSCount(card.getRealSMSCount()+1); 
				card.setMoney(Common.sub(card.getMoney(),0.1)); //�˻��������0.1Ԫ��1�����ŷ��ã�
				card.setConsumAmount(card.getConsumAmount() + 0.1);
			}else{
				temp = i;
				throw new Exception("�����ѷ��Ͷ���"+i+"��,�������㣬���ֵ����ʹ�ã�");
			}
		}
		return temp;
	}

}
