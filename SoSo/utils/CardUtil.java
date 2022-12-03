package Day12_3.SoSo.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cn.soso.common.Common;
import cn.soso.entity.ConsumInfo;
import cn.soso.entity.MobileCard;
import cn.soso.entity.NetPackage;
import cn.soso.entity.Scene;
import cn.soso.entity.ServicePackage;
import cn.soso.entity.SuperPackage;
import cn.soso.entity.TalkPackage;
import cn.soso.service.CallService;
import cn.soso.service.NetService;
import cn.soso.service.SendService;

/**
 * �ֻ���������
 * 
 * @author ��������
 * 
 */
public class CardUtil {
	Map<String, MobileCard> cards = new HashMap<String, MobileCard>(); // �����ֻ������б�
	Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>(); // �����ֻ������Ѽ�¼���б�
	List<Scene> scenes = new ArrayList<Scene>();

	// ��ʼ���û�
	public void init() {
		MobileCard card1 = new MobileCard("������", "123", "13965756432",
				new TalkPackage(), 58.0, 42.0);
		MobileCard card2 = new MobileCard("��¶¶", "123", "13956712467",
				new NetPackage(), 68.0, 32.0);
		MobileCard card3 = new MobileCard("������", "123", "13911568956",
				new SuperPackage(), 78.0, 22.0);
		MobileCard card4 = new MobileCard("������", "123", "13924221868",
				new TalkPackage(), 78.0, 2.0);
		card4.setConsumAmount(98.0);
		card4.setRealTalkTime(500);
		card4.setRealSMSCount(100);
		cards.put("13965756432", card1);
		cards.put("13956712467", card2);
		cards.put("13911568956", card3);
		cards.put("13924221868", card4);
	}
	
	/**
	 * ʹ�ó�����ʼ��
	 */
	public void initScenes(){	
		scenes.add(new Scene("ͨ��",90,"�ʺ�ͻ���˭֪������Ѳ� ͨ��90����"));
		scenes.add(new Scene("ͨ��",30,"ѯ����������״�� ����ͨ��30����"));
		scenes.add(new Scene("����",5,"���뻷������ʵʩ�����ʾ���� ���Ͷ���5��"));
		scenes.add(new Scene("����",50,"֪ͨ�����ֻ����ţ����Ͷ���50��"));
		scenes.add(new Scene("����",1*1024,"��Ů��΢����Ƶ����   ʹ������1G"));
		scenes.add(new Scene("����",2*1024,"�����ֻ����߿����磬������˯������ ʹ������ 2G"));		
	}

	/**
	 * �Ƿ���ڴ˿��û�

	 * 
	 * @param number
	 * @param passWord
	 * @return
	 */
	public boolean isExistCard(String number, String passWord) {
		Set<String> numbers = cards.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String searchNum = it.next();
			if (searchNum.equals(number)
					&& (cards.get(searchNum)).getPassWord().equals(passWord)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ����ָ�������Ƿ���ע��
	 * 
	 * @param searchNumber
	 * @return δע�᣺false ��ע�᣺true
	 */
	public boolean isExistCard(String searchNumber) {
		Set<String> numbers = cards.keySet();
		for (String number : numbers) {
			if (number.equals(searchNumber)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �������ţ���139��ͷ 11λ��
	 * 
	 * @return ���ɵ�����ֻ�����
	 */
	public String createNumber() {
		Random random = new Random();
		boolean isExist = false; // ��¼�����û����Ƿ���ڴ˿����û� �ǣ�true ��false
		String number = "";
		int temp = 0;
		do {
			isExist = false; // ��־λ����Ϊfalse,���ڿ�������ѭ������������
			// ���ɵ��������8λ ����С��10000000��������������
			do {
				temp = random.nextInt(100000000);
			} while (temp < 10000000);
			// ����֮ǰ��ǰ��ӡ�139��
			number = "139" + temp;
			// �������û��Ŀ��űȽϣ��������ظ�
			Set<String> cardNumbers = cards.keySet();
			for (String cardNumber : cardNumbers) {
				if (number.equals(cardNumber)) {
					isExist = true;
					break;
				}
			}
		} while (isExist);
		return number;
	}

	/**
	 * ����ָ���������¿����б�
	 * 
	 * @param count
	 *            ָ������
	 * @return �����б�
	 */
	public String[] getNewNumbers(int count) {

		String[] numbers = new String[count];
		for (int i = 0; i < count; i++) {
			numbers[i] = createNumber();
		}
		return numbers;
	}

	/**
	 * ����¿�
	 * 
	 * @param card
	 *            �¿�
	 */
	public void addCard(MobileCard card) {
		cards.put(card.getCardNumber(), card);
		System.out.print("ע��ɹ���");
		card.showMeg();
	}

	/**
	 * ָ�����Ű�������
	 * 
	 * @param card
	 */
	public void delCard(String delNumber) {
		if (isExistCard(delNumber)) {
			cards.remove(delNumber);
			System.out.println("����" + delNumber + "���������ɹ���");
		} else {
			System.out.println("�Բ��𣬸ÿ���δע�ᣬ���ܰ���������");
		}
	}

	/**
	 * ��ѯָ�����ײ�����
	 * 
	 * @param number
	 */
	public void showRemainDetail(String searchNumber) {
		MobileCard card; // Ҫ��ѯ�Ŀ�
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		StringBuffer meg = new StringBuffer();
			card = cards.get(searchNumber);
			meg.append("���Ŀ�����" + searchNumber + "���ײ���ʣ�ࣺ\n");
			ServicePackage pack = card.getSerPackage();
			if (pack instanceof TalkPackage) {
				//����ת��Ϊ�����ײͶ���
				TalkPackage cardPack = (TalkPackage) pack;
				// �����ײͣ���ѯ�ײ���ʣ���ͨ��ʱ���Ͷ�������
				remainTalkTime = cardPack.getTalkTime() > card
						.getRealTalkTime() ? cardPack.getTalkTime()
						- card.getRealTalkTime() : 0;
				meg.append("ͨ��ʱ����" + remainTalkTime + "����\n");
				remainSmsCount = cardPack.getSmsCount() > card
						.getRealSMSCount() ? cardPack.getSmsCount()
						- card.getRealSMSCount() : 0;
				meg.append("����������" + remainSmsCount + "��");
			} else if (pack instanceof NetPackage) {
				//����ת��Ϊ�����ײͶ���
				NetPackage cardPack = (NetPackage) pack;
				// �����ײͣ���ѯ�ײ���ʣ�����������
				remainFlow = cardPack.getFlow() > card.getRealFlow() ? cardPack
						.getFlow() - card.getRealFlow() : 0;
				meg.append("����������" + Common.dataFormat(remainFlow * 1.0 / 1024)
						+ "GB");
			} else if (pack instanceof SuperPackage) {
				//����ת��Ϊ�����ײͶ���
				SuperPackage cardPack = (SuperPackage) pack;
				// �����ײͣ���ѯ�ײ���ʣ���ͨ��ʱ����������������������
				remainTalkTime = cardPack.getTalkTime() > card
						.getRealTalkTime() ? cardPack.getTalkTime()
						- card.getRealTalkTime() : 0;
				meg.append("ͨ��ʱ����" + remainTalkTime + "����\n");
				remainSmsCount = cardPack.getSmsCount() > card
						.getRealSMSCount() ? cardPack.getSmsCount()
						- card.getRealSMSCount() : 0;
				meg.append("����������" + remainSmsCount + "��\n");
				remainFlow = cardPack.getFlow() > card.getRealFlow() ? cardPack
						.getFlow() - card.getRealFlow() : 0;
				meg.append("����������" + Common.dataFormat(remainFlow * 1.0 / 1024)
						+ "GB");
			}
			System.out.println(meg);
	}

	/**
	 * ��ѯָ�������������굥
	 * 
	 * @param searchNumber
	 */
	public void showAmountDetail(String searchNumber) {
		MobileCard card; // Ҫ��ѯ�Ŀ�
		StringBuffer meg = new StringBuffer();
		card = cards.get(searchNumber);
		meg.append("���Ŀ��ţ�" + card.getCardNumber() + ",�����˵���\n");
		meg.append("�ײ��ʷѣ�" + card.getSerPackage().getPrice() + "Ԫ\n");
		meg.append("�ϼƣ�" + Common.dataFormat(card.getConsumAmount()) + "Ԫ\n");
		meg.append("�˻���" + Common.dataFormat(card.getMoney()) + "Ԫ");
		// ��ʾ����������ϸ��Ϣ
		System.out.println(meg);
	}

	
	/**
	 * ָ�����Ż��ײ�
	 * 
	 * @param number
	 * @param packType
	 */
	public void changingPack(String number, String packNum) {
		MobileCard card; // ָ�����ֻ���
		ServicePackage pack; // Ҫ�����ײ�
		if (isExistCard(number)) {
			card = cards.get(number);
			// ��ȡҪ�����ײͶ���
			switch (packNum) {
			case "1":
				pack = new TalkPackage();
				break;
			case "2":
				pack = new NetPackage();
				break;
			default:
				pack = new SuperPackage();
				break;
			}		
			if (!(card.getSerPackage().getClass().getName().equals(pack.getClass().getName()))) {
				// �ÿ�����м�ȥ�����ײ��ʷ�
				if (card.getMoney() >= pack.getPrice()) {
					card.setMoney(card.getMoney() - pack.getPrice());
					// ���ײ�
					card.setSerPackage(pack);
					// ����ʵ��ʹ����������
					card.setRealTalkTime(0);
					card.setRealFlow(0);
					card.setRealSMSCount(0);
					// �������ѽ������Ϊ���ײ����ʷ�
					card.setConsumAmount(pack.getPrice());
					System.out.print("�����ײͳɹ���");
					pack.showInfo();
				} else {
					System.out.println("�Բ�������������֧�����ײͱ����ʷѣ����ֵ���ٰ�������ײ�ҵ��");
					return;
				}
			} else {
				System.out.println("�Բ������Ѿ��Ǹ��ײ��û������軻�ײͣ�");
			}

		} else {
			System.out.println("�Բ��𣬸ÿ���δע�ᣬ���ܻ��ײͣ�");
		}
	}

	/**
	 * Ϊָ���ֻ�����ֵ
	 * 
	 * @param number
	 *            ָ����ֵ�Ŀ���
	 * @param money
	 *            ��ֵ���
	 */
	public void chargeMoney(String number, double money) {
		MobileCard card; // ָ�����ֻ���
		if (money < 50) {
			System.out.println("�Բ�����ͳ�ֵ���Ϊ50Ԫ��");
			return;
		}
			card = cards.get(number);
			card.setMoney(card.getMoney() + money);
			System.out.println("��ֵ�ɹ�����ǰ�������Ϊ" + Common.dataFormat(card.getMoney()) + "Ԫ��");
	}

	/**
	 * ���һ��ָ���������Ѽ�¼
	 * 
	 * @param number
	 *            Ҫ������Ѽ�¼�Ŀ�
	 * @param info
	 *            Ҫ��ӵ����Ѽ�¼
	 */
	public void addConsumInfo(String number, ConsumInfo info) {
		Set<String> numbers = consumInfos.keySet();
		Iterator<String> it = numbers.iterator();
		List<ConsumInfo> infos = new ArrayList<ConsumInfo>();
		boolean isExist = false; // ���������б����Ƿ���ڴ˿������Ѽ�¼���ǣ�true ��false
		while (it.hasNext()) {
			if (it.next().equals(number)) {
				// ���Ѽ��������иÿ������Ѽ�¼�����ҵ��ÿ��ŵ����Ѽ�¼���ϣ����һ������
				infos = consumInfos.get(number);
				infos.add(info);
				isExist = true;
				System.out.println("�����һ�����Ѽ�¼��");
				break;
			}
		}
		// �ü�����û�д˿������Ѽ�¼�������
		if (!isExist) {
			infos.add(info);
			consumInfos.put(number, infos);
			System.out.println("�����ڴ˿������Ѽ�¼�������һ�����Ѽ�¼��");
		}
	}
	
	//��ӡ���Ѽ�¼
	public void printConsumInfo(String number){
		Writer fileWriter = null;
		try {
			 fileWriter = new FileWriter(number+"���Ѽ�¼.txt");			
			Set<String> numbers = consumInfos.keySet();
			Iterator<String> it = numbers.iterator();
			List<ConsumInfo> infos = new ArrayList<ConsumInfo>();//�洢ָ�����������Ѽ�¼
			boolean isExist = false; // ���������б����Ƿ���ڴ˿������Ѽ�¼���ǣ�true ��false
			while (it.hasNext()) {
				if (it.next().equals(number)) {
					infos = consumInfos.get(number);
					isExist = true;
					break;
				}
			}
			if(isExist){
				//���� �˿����Ѽ�¼��д���ı��ļ�
				StringBuffer content = new StringBuffer("******"+number+"���Ѽ�¼******\n");
				content.append("���\t����\t���ݣ�ͨ��������/������MB��/���ţ�������\n");
				for(int i=0;i<infos.size();i++){
					ConsumInfo info = infos.get(i);
					content.append((i+1)+".\t"+info.getType()+"\t"+info.getConsumData()+"\n");
				}
				fileWriter.write(content.toString());
				fileWriter.flush();
				
				System.out.println("���Ѽ�¼��ӡ��ϣ�");
			}else{
				System.out.println("�Բ��𣬲����ڴ˺�������Ѽ�¼�����ܴ�ӡ��");
			}			
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			if(fileWriter!=null){
				try {
					fileWriter.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ʹ����
	 * @param number ��ǰ����
	 * @throws Exception 
	 */
	public void userSoso(String number)  {		
		MobileCard card = cards.get(number); // ��ȡ�˿�����
		ServicePackage pack = card.getSerPackage(); // ��ȡ�˿������ײ�
		Random random = new Random();
		int ranNum = 0;
		int temp = 0;  //��¼��������ʵ����������
		do{
			
			ranNum = random.nextInt(6);// ����һ��0~5֮ǰ�������
			Scene scene = scenes.get(ranNum); //��ȡ���������Ӧ�ĳ���
			switch (ranNum) {
			//���Ϊ0��1Ϊͨ������
			case 0:
			case 1:
				// �жϸÿ������ײ��Ƿ�֧��ͨ������
				if (pack instanceof CallService) {
					// ִ��ͨ������
					System.out.println(scene.getDescription());
					CallService callService = (CallService) pack;
					try {
						temp = callService.call(scene.getData(), card);
					} catch (Exception e) {							
						e.printStackTrace();
					}
					// ���һ�����Ѽ�¼
					addConsumInfo(number, new ConsumInfo(number,
							scene.getType(), temp));
					break;
				} else {
					// ����ÿ��ײͲ�֧��ͨ�����ܣ����������������ѡ����������
					continue;
				}
				//���Ϊ2��3Ϊ�����ų���
			case 2:
			case 3:
				// �жϸÿ������ײ��Ƿ�֧�ֶ��Ź���
				if (pack instanceof SendService) {
					// ִ�з����ŷ���
					System.out.println(scene.getDescription());
					SendService sendService = (SendService) pack;
					try {
						temp = sendService.sendMessage(scene.getData(), card);
					} catch (Exception e) {													
						e.printStackTrace();
					}
					// ���һ�����Ѽ�¼
					addConsumInfo(number, new ConsumInfo(number,
							scene.getType(), temp));
					break;
				} else {
					// ����ÿ��ײͲ�֧�ַ����Ź��ܣ����������������ѡ����������
					continue;
				}
				//���Ϊ4��5Ϊ����������
			case 4:
			case 5:
				// �жϸÿ������ײ��Ƿ�֧����������
				if (pack instanceof NetService) { 
					System.out.println(scene.getDescription());
					NetService netService = (NetService) pack;
					// ִ����������
					try {
						temp = netService.netPlay(scene.getData(), card);
					} catch (Exception e) {						
						e.printStackTrace();
					}
					// ���һ�����Ѽ�¼
					addConsumInfo(number, new ConsumInfo(number,
							scene.getType(), temp));
					break;
				} else {
					// ����ÿ��ײͲ�֧���������ܣ����������������ѡ����������
					continue;
				}				
			}	
			break;
		}while(true);
	}

	/**
	 * �����ײ���ŷ����ײͶ���
	 * 
	 * @param packNum
	 *            �ײ����
	 * @return �ײͶ���
	 */
	public ServicePackage createPack(int packId) {
		ServicePackage pack = null;
		switch (packId) {
		case 1:
			pack = new TalkPackage();
			break;
		case 2:
			pack = new NetPackage();
			break;
		case 3:
			pack = new SuperPackage();
			break;
		}
		return pack;
	}
	
	/**
	 * ��ʾ�ʷ�˵��
	 */
	public void showDescription(){
		Reader rd = null;
		try {
			rd = new FileReader("�ײ��ʷ�˵��.txt");
			 char[] content = new char[1024];
			 int len = 0;
			 StringBuffer sb = new StringBuffer();
				while((len=rd.read(content))!=-1){
					sb.append(content,0,len);  //ƴ���ַ���
				}
				System.out.println(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
