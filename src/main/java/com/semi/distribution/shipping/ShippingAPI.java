package com.semi.distribution.shipping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.semi.distribution.db.DBManger;

public class ShippingAPI {

	public static void getDelivery(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String temp = request.getParameter("temp");
		System.out.println(temp);
		int yearInt = Integer.parseInt(request.getParameter("selectedYear"));
		int monthInt = Integer.parseInt(request.getParameter("selectedMonth"));
		System.out.println(yearInt+"asd"+(monthInt));

//		System.out.println(yearInt+"년"+monthInt+"월");
//		String year = yearInt+"";
//		String month = monthInt+"";
//		if(monthInt < 10) {
//			month = "0"+month;
//		}
		System.out.println(yearInt+"년"+monthInt+"월");
		
		
		
		
		
		
	    String sql = "select c_name, e_name ,e_rank, c_type, s_contract_no, c_delivery_date "
	    		+ "from shipping a inner join contract b on a.s_contract_no = b.c_contract_no "
	    		+ "inner join company c on b.c_c_no = c.c_no "
	    		+ "inner join employee d on a.s_e_no = d.e_no "
	    		+ "where EXTRACT(YEAR FROM b.c_delivery_date) = ? "
	    		+ "AND EXTRACT(MONTH FROM b.c_delivery_date) = ? and b.c_status = 2";
		try {
			con = DBManger.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, yearInt);
			pstmt.setInt(2, monthInt);
			rs = pstmt.executeQuery();
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd");
			Gson gson = gsonBuilder.create();
			
			response.setContentType("application/json; charset=utf-8");
			ArrayList<ShippingDTO> events = new ArrayList<ShippingDTO>();
			ShippingDTO event = null;
			while (rs.next()) {
				event = new ShippingDTO();
				event.setC_name(rs.getString("c_name"));
				event.setE_name(rs.getString("e_name"));
				event.setE_rank(rs.getString("e_rank"));
				event.setC_type(rs.getString("c_type"));
				event.setS_contract_no(rs.getString("s_contract_no"));
				event.setC_delivery_date(rs.getDate("c_delivery_date"));
				events.add(event);
			}
		
			 JsonArray jsonArray = new JsonArray();
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 String backgroundColor = "green";
			 
		        // ArrayList에 있는 각 DTO 객체를 JsonObject로 변환하고 JSON 배열에 추가
		        for (ShippingDTO dto : events) {
		            String dtoJson = gson.toJson(dto); // DTO 객체를 JSON 문자열로 변환
		            String delivery_date = dateFormat.format(dto.getC_delivery_date());
		            if(dto.getC_type().equals("2")) {
		            	backgroundColor = "gray";
		            }
		            
		            JsonObject jsonObject = gson.fromJson(dtoJson, JsonObject.class); // JSON 문자열을 JsonObject로 파싱
		            jsonObject.addProperty("title", dto.getC_name()+" "+dto.getE_name()+" "+dto.getE_rank()); // title 필드 추가
		            jsonObject.addProperty("start", delivery_date); // start 필드 추가
		            jsonObject.addProperty("backgroundColor", backgroundColor); //배경색 필드 추가

		            jsonArray.add(jsonObject); // JsonArray에 JsonObject 추가
		        }

		        String eventsJson = gson.toJson(jsonArray); // JsonArray를 JSON 문자열로변환
		        response.getWriter().print(eventsJson);
			
			
			
			
		}  catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManger.close(con, pstmt, rs);
		}
	}
	
}
