package com.semi.distribution.receipt;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.distribution.employee.EmployeeDTO;
import com.semi.distribution.shift.ShiftDTO;

public class ReceiptLogic {
	
	private static final ReceiptLogic RLG = new ReceiptLogic();
	
	
	private ReceiptLogic() {
	}
	
	
	public static ReceiptLogic getRlg() {
		return RLG;
	}


	public void Shift(ArrayList<EmployeeDTO> empList, ArrayList<ShiftDTO> restMemberList, HttpServletRequest request) {

		for (int i = 0; i < empList.size(); i++) {
			for (ShiftDTO restMember : restMemberList) {
				if (restMember.getW_n().equals("2") || restMember.getW_n().equals("3")) {
					if (empList.get(i).getE_no().equals(restMember.getE_id())) {
						empList.remove(i);
					}

				}

			}
		}
		request.setAttribute("empList", empList);

	}
}
