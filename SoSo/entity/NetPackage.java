package Day12_3.SoSo.entity;

import cn.soso.common.Common;
import cn.soso.service.NetService;

/**
 * �����ײ�
 * 
 * @author ��������
 * 
 */
public class NetPackage extends ServicePackage implements NetService {
	private int flow; // ����������MB��
	
	public NetPackage() {
        //�ײ����ݳ�ʼ��
		this.flow = 1024 * 3;
		this.price = 68.0;
	}

	public NetPackage(int flow) {
		super();
		this.flow = flow;
	}
	
	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	

	@Override
	public void showInfo() {
		System.out.println("�����ײͣ�����������" + flow / 1024 + "GB/��,���ʷ���"
				+ this.price + "Ԫ/�¡�");
	}

	/**
	 * �ṩ��������
	 */
	public void netPlay2(int flow, MobileCard card) throws Exception {
		int reminFlow = this.flow - card.getRealFlow();  //���п�֧�����������
		// �ж��ײ��е����������Ƿ��㹻֧��������������
		if (this.flow <= reminFlow) {
			// �ײ������������㹻���޸ĸÿ�ʵ��������������
			card.setRealFlow(card.getRealFlow() + flow);
		} else {
			// �ײ��������������������������谴0.1Ԫ/�����ѣ��������ѽ��=0.1*���ÿ�ʵ��������������+����������������-�ײͰ���������������
			double consumeMoney = 0.1 * (flow-reminFlow);
			// �ÿ��˻�����㹻֧�����޸ĸÿ�ʵ��ʹ�õ������������˻����������ѽ��
			if (card.getMoney() >= consumeMoney) {
				//���ĵ���������
				card.setRealFlow(card.getRealFlow() + flow);
				// ��ǰ�˻�����ǰ�˻����������ѽ��
				card.setMoney(card.getMoney() - consumeMoney);
				// �������ѽ��������ѽ��������ѽ��
				card.setConsumAmount(card.getConsumAmount() + consumeMoney);
			} else {
				
				int temp = (int)(card.getMoney()/0.1); //��ǰ����
				throw new Exception("�������㣬���ֵ����ʹ�ã�");
			}
		}
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
}
