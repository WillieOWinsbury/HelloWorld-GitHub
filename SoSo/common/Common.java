package Day12_3.SoSo.common;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * ������
 * @author ��������
 *
 */
public class Common {
	/**
	 * double���͸�ʽ��
	 * @param data
	 * @return
	 */
	public static String dataFormat(double data) {
		DecimalFormat formatData = new DecimalFormat("#.0");
		return formatData.format(data);
	}
	
	/**
	 * double�����������
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static double sub(double num1,double num2){
		return (num1*10-num2*10)/10;
	}
}
