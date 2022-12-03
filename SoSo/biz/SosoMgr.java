package Day12_3.SoSo.biz;

import java.util.Scanner;

import cn.soso.common.Common;
import cn.soso.entity.MobileCard;
import cn.soso.entity.ServicePackage;
import cn.soso.utils.CardUtil;

/**
 * ҵ����
 * 
 * @author ��������
 * 
 */
public class SosoMgr {
	Scanner input = new Scanner(System.in);
	CardUtil utils = new CardUtil();
	
	public static void main(String[] args) {
		SosoMgr soso = new SosoMgr();
		soso.mainMenu();
		System.out.println("ллʹ�ã�");
	}

	/**
	 * ������
	 */
	public void mainMenu() {
		int menuChoose = 0;
		String mobileNumber= "";
		String password = "";
		utils.init();
		utils.initScenes();
		//Common.typesInit();
		do {
			System.out.println("\n*************��ӭʹ�����ƶ�ҵ�����***************");
			System.out.println("1.�û���¼   2.�û�ע��   3.ʹ����   4.���ѳ�ֵ  5.�ʷ�˵��  6.�˳�ϵͳ");
			System.out.print("��ѡ��");
			menuChoose = input.nextInt();
			// ��֧��䣺���ݹ��ܱ��ִ����Ӧ����
			switch (menuChoose) {
			case 1:
				//�û���¼
				System.out.print("�������ֻ����ţ�");
				mobileNumber = input.next();
				System.out.print("���������룺");
				password = input.next();
				if (utils.isExistCard(mobileNumber, password)) {
					cardMenu(mobileNumber);
				}else{
					System.out.println("�Բ������������Ϣ�����޷���¼��");
				}
				continue;
			case 2:
				//�û�ע��
				registCard();
				continue;
			case 3:
				
				//ʹ����
				System.out.print("�������ֻ����ţ�");
				 mobileNumber = input.next();				
				
				if (utils.isExistCard(mobileNumber)) {
					try {
/*						System.out.println("****ʹ��֮ǰ****");
						utils.showRemainDetail(mobileNumber);
						utils.showAmountDetail(mobileNumber);*/
						utils.userSoso(mobileNumber);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}else{
					System.out.println("�Բ��𣬸ÿ���δע�ᣬ����ʹ�ã�");
				}
				
				/*System.out.println("****ʹ��֮��****");
				utils.showRemainDetail(mobileNumber);
				utils.showAmountDetail(mobileNumber);*/
				continue;
			case 4:
				//���ѳ�ֵ
				System.out.print("�������ֵ���ţ�");
				mobileNumber = input.next();
				if (utils.isExistCard(mobileNumber)) {
				System.out.print("�������ֵ��");
				double money = input.nextDouble();				
				utils.chargeMoney(mobileNumber, money);
				}else{
					System.out.println("�Բ���Ҫ��ֵ�Ŀ���δע�ᣬ�޷���ֵ��");
				}
				continue;				
			case 5:
				System.out.println("\n*****�ʷ�˵��******");
				utils.showDescription();
				continue;	
			case 6:
				//�˳�ϵͳ
				break;
			default:
				//ѡ�����������˳�ϵͳ
				break;
			}
			break;
		} while (true);
	}

	/**
	 * �ֻ������ܲ˵�
	 * 
	 * @param number
	 * @return
	 */
	public int cardMenu(String mobileNumber) {
		   int menuChoose = 0;
		do {
			System.out.println("\n*****���ƶ��û��˵�*****");
			System.out.println("1.�����˵���ѯ");
			System.out.println("2.�ײ�������ѯ");
			System.out.println("3.��ӡ�����굥");
			System.out.println("4.�ײͱ��");
			System.out.println("5.��������");
			System.out.print("��ѡ��(����1~5ѡ���ܣ�������������һ��)��");
			 menuChoose = input.nextInt();
			switch (menuChoose) {
			case 1:
				System.out.println("\n*****�����˵���ѯ******");
				utils.showAmountDetail(mobileNumber);
				continue;
			case 2:
				System.out.println("\n*****�ײ�������ѯ******");
				utils.showRemainDetail(mobileNumber);
				continue;
			case 3:
				System.out.println("\n*****�����굥��ѯ******");
				utils.printConsumInfo(mobileNumber);
				continue;
			case 4:
				System.out.println("\n*****�ײͱ��******");
				System.out.print("1.�����ײ�  2.�����ײ�  3.�����ײ�  ��ѡ����ţ���");				
				utils.changingPack(mobileNumber, input.next());
				continue;
			case 5:
				System.out.println("\n*****��������******");
				utils.delCard(mobileNumber);
				System.out.println("ллʹ�ã�");
				System.exit(1);	 //�����������˳�ϵͳ	
						
			}
			
			break;
		} while (true);
        return menuChoose;
	}
	
	/**
	 * ע���¿�����
	 */
	public void registCard(){
		String[] newNumbers = utils.getNewNumbers(9);
		//��ʾ�ɹ�ѡ����ֻ����б�
		System.out.println("*****��ѡ��Ŀ���*****");
		
		for(int i=0;i<9;i++){
			System.out.print((i+1)+"."+newNumbers[i]+"\t\t");
			if((i+1)%3==0){
				System.out.println();
			}
		}
		//ѡ���ֻ���
		System.out.print("��ѡ�񿨺ţ�����1~9����ţ���");		
		String number = newNumbers[input.nextInt()-1];
		
		//ѡ���ײ�����
		System.out.print("1.�����ײ�  2.�����ײ�  3.�����ײͣ�  ");
		System.out.print("��ѡ���ײ�(�������)��");
		//utils.getPackList();
		//��ȡ�ײͶ��� 
		ServicePackage pack = utils.createPack(input.nextInt());
		
		//�����û���
		System.out.print("������������");
		String name = input.next();
		
		//��������
		System.out.print("���������룺");
		String password = input.next();
		
		//����Ԥ�滰�ѽ��
		double money = 0;
		System.out.print("������Ԥ�滰�ѽ�");
		 money = input.nextDouble();
		while(money<pack.getPrice()){
			System.out.print("��Ԥ��Ļ��ѽ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
			money = input.nextDouble();
		}
				
		//�����¿��������
		MobileCard newCard = new MobileCard(name,password,number,pack,pack.getPrice(),money-pack.getPrice());
		utils.addCard(newCard);		
	}
}
