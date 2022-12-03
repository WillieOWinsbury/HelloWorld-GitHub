package Day12_3.SoSo.entity;

import cn.soso.common.Common;
import cn.soso.service.CallService;
import cn.soso.service.SendService;

/**
 * �����ײ�
 * 
 * @author ��������
 * 
 */
public class TalkPackage extends ServicePackage implements CallService,
		SendService {
	private int talkTime; // ͨ��ʱ��(����)
	private int smsCount; // ��������������

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

	public TalkPackage() {
		//�ײ����ݳ�ʼ��
		this.talkTime = 500;
		this.smsCount = 30;
		this.price = 58.0;
	}

	public TalkPackage(int talkTime, int smsCount) {
		super();
		this.talkTime = talkTime;
		this.smsCount = smsCount;
	}

	/**
	 * ��ʾ�ײ�����
	 */
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ" + this.talkTime + "����/�£���������Ϊ"
				+ this.smsCount + "��/�£��ʷ�Ϊ" + this.price + "Ԫ/�¡�");
	}
    
	public int call(int minCount, MobileCard card) throws Exception{
		int temp = minCount; 
		for(int i=0;i<minCount;i++){
			if(this.talkTime-card.getRealTalkTime()>=1){
				//��һ��������ײ�ʣ��ͨ��ʱ�����Ը�1����ͨ��			
				card.setRealTalkTime(card.getRealTalkTime()+1); //ʵ��ʹ��������1MB				
			}else if(card.getMoney()>=0.2){
				//�ڶ������:�ײ�ͨ��ʱ�������꣬�˻�������֧��1����ͨ����ʹ���˻����֧��
				card.setRealTalkTime(card.getRealTalkTime()+1); //ʵ��ʹ��ͨ��ʱ��1���� 
				card.setMoney(Common.sub(card.getMoney(),0.2)); //�˻��������0.2Ԫ��1M�������ã�
				card.setConsumAmount(card.getConsumAmount() + 0.2);
			}else{
				temp = i; //��¼ʵ��ͨ��������
				throw new Exception("������ͨ��"+i+"����,�������㣬���ֵ����ʹ�ã�");				
			}
		}
		return temp;
	}
		
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
