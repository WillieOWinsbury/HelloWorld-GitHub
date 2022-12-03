package Day12_3.SoSo.entity;

import cn.soso.common.ConsumType;


/**
 * ������Ϣ
 * @author ��������
 *
 */
public class ConsumInfo {
	private String cardNumber;  //����
	private String type;  //�������ͣ�ͨ���������š�����
	private int consumData;   //��������   ͨ��������   �����ţ���   ������MB
	
	public ConsumInfo(){}
	public ConsumInfo(String cardNumber, String type, int consumData) {
		super();
		this.cardNumber = cardNumber;
		this.type = type;
		this.consumData = consumData;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getConsumData() {
		return consumData;
	}
	public void setConsumData(int consumData) {
		this.consumData = consumData;
	}	
}
