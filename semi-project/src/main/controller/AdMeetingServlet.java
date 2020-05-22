package main.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

import main.model.service.MainService;
import meetingDetail.model.vo.Meeting;
import moim.model.service.MoimService;

/**
 * Servlet implementation class AdMeetingServlet
 */
@WebServlet(name = "AdMeeting", urlPatterns = { "/adMeeting" })
public class AdMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int count = new MainService().countCol();
		
		
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		Random r = new Random();
		
		ArrayList<moim.model.vo.Meeting> meetingList = new MoimService().selectAllMeeting();
		int arr[] = new int [3];
		arr[0] = meetingList.get(r.nextInt(meetingList.size())).getMeetingNo();
		arr[1] = meetingList.get(r.nextInt(meetingList.size())).getMeetingNo();
		arr[2] = meetingList.get(r.nextInt(meetingList.size())).getMeetingNo();
		for(int i=0; i<arr.length ; i++) {
			nums.add(arr[i]);
			System.out.println(arr[i]);
		}		
		ArrayList<Meeting> adlist = new MainService().selectAdMeeting(nums);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(adlist, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
