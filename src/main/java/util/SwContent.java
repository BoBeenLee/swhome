package util;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.bo.SubjectProcBO;
import data.bo.UserBO;
import data.constant.Code;
import data.vo.subjectproc.SubjectProc;
import data.vo.user.ProfessorUser;

/**
 * Servlet implementation class swContent
 */
@WebServlet("/SwContent")
public class SwContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String req_idx = request.getParameter("req");
		String page_idx = null;
		

		page_idx = swRightSwitcher(request, req_idx);
	

		RequestDispatcher rs = request.getRequestDispatcher(page_idx);
		rs.forward(request, response);
	}
	
	private String swRightSwitcher(HttpServletRequest request, String req){
		switch(Integer.parseInt(req)){
		case 1 :
			//request.setAttribute("name", "major"); process
			
			return "/Sub01/major_info.html";
		case 2 :
			//request.setAttribute("name", "professor");
			HashMap<String, String> hashMap = new HashMap<String, String>();
			List<ProfessorUser> professorList;
			
			UserBO userBo = new UserBO();
			
			String userName = "임형', '유상신', '홍은지', '노은하', '이승진";
			hashMap.put("userName", userName);
			hashMap.put("codeId", String.valueOf(Code.PROFESSOR.getValue()));
			
			professorList = userBo.getProfessorUser(hashMap);
			
			request.setAttribute("professorList", professorList);
			
			return "/Sub01/professor_info.jsp";
		case 3 :
			//request.setAttribute("name", "subject");
			SubjectProcBO subjectProcBo = new SubjectProcBO();
			List<SubjectProc>[] gradeList = new List[4];
			
			for(int i=1; i<=4; i++){
				gradeList[i-1] = subjectProcBo.getSubjectProcList(i);
			}
			request.setAttribute("gradeList", gradeList);
			
			return "/Sub01/subject_proc.jsp";
		case 4 :
			//request.setAttribute("name", "direction");
			
			return "/Sub01/admin_info.html";
		}
		return "/Sub01/major_info.html";
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
