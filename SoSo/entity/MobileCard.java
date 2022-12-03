package Day12_3.SoSo.entity;
/**
 * �ֻ���
 * @author ��������
 *
 */
public class MobileCard {
	private String cardNumber;  //����
	private String userName;  //�û���
	private String passWord;  //����	
	private ServicePackage serPackage;  //�����ײ�
	private double consumAmount;  //�������ѽ��
	private double money;  //�˻����
	private int realTalkTime;  //ʵ��ͨ��ʱ�������ӣ�
	private int realSMSCount;  //ʵ�ʷ��Ͷ�������������
	private int realFlow;  //ʵ����������
	
	public MobileCard(){}

	public MobileCard(String userName, String passWord, String cardNumber,
			ServicePackage serPackage, double consumAmount, double money) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.cardNumber = cardNumber;
		this.serPackage = serPackage;
		this.consumAmount = consumAmount;
		this.money = money;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public ServicePackage getSerPackage() {
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage) {
		this.serPackage = serPackage;
	}

	public double getConsumAmount() {
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRealTalkTime() {
		return realTalkTime;
	}

	public void setRealTalkTime(int realTalkTime) {
		this.realTalkTime = realTalkTime;
	}

	public int getRealSMSCount() {
		return realSMSCount;
	}

	public void setRealSMSCount(int realSMSCount) {
		this.realSMSCount = realSMSCount;
	}

	public int getRealFlow() {
		return realFlow;
	}

	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
	
	/**
	 * ��ʾ����Ϣ
	 */
	public void showMeg(){
		System.out.println("���ţ�"+this.cardNumber+" �û�����"+this.userName+" ��ǰ��"+this.money+"Ԫ��");
		this.serPackage.showInfo();
	}	
}
