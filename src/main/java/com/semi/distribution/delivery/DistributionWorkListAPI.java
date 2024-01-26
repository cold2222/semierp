package com.semi.distribution.delivery;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.distribution.employee.EmployeeDTO;
import com.semi.distribution.shift.ShiftDTO;

public class DistributionWorkListAPI {

	private static final DistributionWorkListAPI WAPI = new DistributionWorkListAPI();

	private DistributionWorkListAPI() {
	}

	public static DistributionWorkListAPI getWapi() {
		return WAPI;
	}

	public void Shift(ArrayList<EmployeeDTO> empList, ArrayList<ShiftDTO> restMemberList, HttpServletRequest request,
			HttpServletResponse response) {
		if (empList != null) {
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

			response.setContentType("application/json; charset=utf-8");
			Gson gson = new Gson();
			String jsonEmpList = gson.toJson(empList);
			try {
				response.getWriter().print(jsonEmpList);
			} catch (Exception e) {
				System.out.println("workAPI 제이슨 반환에러");
				e.printStackTrace();
			}

		}

	}
}
