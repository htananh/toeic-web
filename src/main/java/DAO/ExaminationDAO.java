package DAO;

import java.io.File;
import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import BEAN.examination;
import BEAN.examinationquestion;


public class ExaminationDAO {
	public static void InsertGrammarGuideLineName(HttpServletRequest request,HttpServletResponse response,Connection conn, examination exam) {
		String sql = "INSERT INTO examination (examinationame) VALUES (?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			String examinationame=exam.getExaminationame();
			
			statement.setString(1, examinationame);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
		 
}
	public static List<examination> DisplayExamination(Connection conn) {
		List<examination> list = new ArrayList<>();
		String sql = "Select * from examination";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				examination exam=new examination();
				int examinationid = resultSet.getInt("examinationid");
			    String examinationame = resultSet.getString("examinationame");
			    String examinationimage = resultSet.getString("examinationimage");
			    
			    
			   exam.setExaminationid(examinationid);
			   exam.setExaminationame(examinationame);
			   exam.setExaminationimage(examinationimage);
			    list.add(exam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static List<examination> DisplayExaminationPage(int start,int count,Connection conn) {
		List<examination> list = new ArrayList<>();
		String sql = "Select * from examination LIMIT "+count+" OFFSET "+(start-1);
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				examination exam=new examination();
				int examinationid = resultSet.getInt("examinationid");
			    String examinationame = resultSet.getString("examinationame");
			    String examinationimage = resultSet.getString("examinationimage");
			    
			    
			   exam.setExaminationid(examinationid);
			   exam.setExaminationame(examinationame);
			   exam.setExaminationimage(examinationimage);
			    list.add(exam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	
	public static void UploadSingleFile(HttpServletRequest request, HttpServletResponse response,Connection conn, int examinationid) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/imageExamination/");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String uploadedFilePath = null;
		if(!isMultipart) {
			request.setAttribute("message", "do not have enctype=\"multipart/form-data\"");
		}
		 // Tạo một thư mục tạm để lưu trữ file upload (nếu muốn)
//        File uploadDir = new File(UPLOAD_DIRECTORY);
//        uploadDir.mkdir();
		  if (ServletFileUpload.isMultipartContent(request)) {
	            try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Đặt ngưỡng kích thước tối đa của file upload (nếu muốn)
        factory.setSizeThreshold(1024 * 1024 * 3); // 3MB

        // Đặt thư mục lưu trữ file upload tạm thời
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
     // Tạo một ServletFileUpload để xử lý file upload
        ServletFileUpload upload = new ServletFileUpload(factory);
        
     // Set overall request size constraint
        upload.setSizeMax(1024 * 1024 * 50); //50MB
        
     // Xử lý file upload
        List<FileItem> formItems = upload.parseRequest(request);
        
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                // Kiểm tra xem item có phải là file upload hay không
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    
                    File storeFile = new File(filePath);

                    // Lưu file upload vào đường dẫn cụ thể
                    item.write(storeFile);
                    DAO.ExaminationDAO.InsertExaminationImage(request, response, conn, examinationid, fileName);
                    
                }
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
        request.setAttribute("message", "Upload thất bại: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
    }
	} else {
    request.setAttribute("message", "Yêu cầu không chứa dữ liệu file upload");
	}

	
}
public static void UploadMultipleFile(HttpServletRequest request, HttpServletResponse response,Connection conn) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/fileExaminationQuestion/");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String uploadedFilePath = null;
		if(!isMultipart) {
			request.setAttribute("message", "do not have enctype=\"multipart/form-data\"");
		}
		 // Tạo một thư mục tạm để lưu trữ file upload (nếu muốn)
//        File uploadDir = new File(UPLOAD_DIRECTORY);
//        uploadDir.mkdir();
		  if (ServletFileUpload.isMultipartContent(request)) {
	            try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Đặt ngưỡng kích thước tối đa của file upload (nếu muốn)
        factory.setSizeThreshold(1024 * 1024 * 3); // 3MB

        // Đặt thư mục lưu trữ file upload tạm thời
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
     // Tạo một ServletFileUpload để xử lý file upload
        ServletFileUpload upload = new ServletFileUpload(factory);
        
     // Set overall request size constraint
        upload.setSizeMax(1024 * 1024 * 50); //50MB
        
     // Xử lý file upload
        List<FileItem> formItems = upload.parseRequest(request);
        
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                // Kiểm tra xem item có phải là file upload hay không
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    
                    File storeFile = new File(filePath);

                    // Lưu file upload vào đường dẫn cụ thể
                    item.write(storeFile);
                    request.setAttribute("message", "Upload thành công: " );
                    
                }
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
        request.setAttribute("message", "Upload thất bại: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
    }
	} else {
    request.setAttribute("message", "Yêu cầu không chứa dữ liệu file upload");
	}
}
	public static void InsertExaminationImage(HttpServletRequest request, HttpServletResponse response,Connection conn,int examinationid, String image) {
		String sql = "update examination set examinationimage=? where examinationid="+examinationid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			
			
			statement.setString(1, image);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
		
	}
	public static void importexcelExamination(HttpServletRequest request, HttpServletResponse response,Connection conn, int examinationid) {
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/imageExamination/");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String uploadedFilePath = null;
		if(!isMultipart) {
			request.setAttribute("message", "do not have enctype=\"multipart/form-data\"");
		}
		 // Tạo một thư mục tạm để lưu trữ file upload (nếu muốn)
//        File uploadDir = new File(UPLOAD_DIRECTORY);
//        uploadDir.mkdir();
		  if (ServletFileUpload.isMultipartContent(request)) {
	            try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Đặt ngưỡng kích thước tối đa của file upload (nếu muốn)
        factory.setSizeThreshold(1024 * 1024 * 3); // 3MB

        // Đặt thư mục lưu trữ file upload tạm thời
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
     // Tạo một ServletFileUpload để xử lý file upload
        ServletFileUpload upload = new ServletFileUpload(factory);
        
     // Set overall request size constraint
        upload.setSizeMax(1024 * 1024 * 50); //50MB
        
     // Xử lý file upload
        List<FileItem> formItems = upload.parseRequest(request);
        
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                // Kiểm tra xem item có phải là file upload hay không
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    
                    File storeFile = new File(filePath);

                    // Lưu file upload vào đường dẫn cụ thể
                    item.write(storeFile);
                   DAO.ExaminationDAO.importexcel(conn, request, response, filePath, examinationid);
                    
                }
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
        request.setAttribute("message", "Upload thất bại: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
    }
	} else {
    request.setAttribute("message", "Yêu cầu không chứa dữ liệu file upload");
	}

		
	}
	public static void importexcel(Connection conn,HttpServletRequest request,HttpServletResponse response,String Address,int examinationid) {
		
		
		// Đọc tệp tin Excel sử dụng POIFSFileSystem
		
		 try {
			
			
			
		     
		      
		        FileInputStream input = new FileInputStream(Address);
		        POIFSFileSystem fs = new POIFSFileSystem(input);
		       
		        HSSFWorkbook wb = new HSSFWorkbook(fs);
	            HSSFSheet sheet = wb.getSheetAt(0);
		       
		        for(int i=1;i<=sheet.getLastRowNum();i++) {
		        	 Row currentRow = sheet.getRow(i);
		        	 int num = 0; // Giá trị mặc định nếu ô trống
		        	    Cell numCell = currentRow.getCell(0);
		        	    if (numCell != null && numCell.getCellType() == CellType.NUMERIC) {
		        	        num = (int) numCell.getNumericCellValue();
		        	    }
		        	    
		        	  // kiểm tra dữ liệu trong file excel có trống không
		        	 //int num = (int) currentRow.getCell(0).getNumericCellValue();
		        	    String imagequestion = "";
		        	    Cell imagequestionCell = currentRow.getCell(1);
		        	    if (imagequestionCell != null && imagequestionCell.getCellType() == CellType.STRING) {
		        	        imagequestion = imagequestionCell.getStringCellValue();
		        	    }
		        	 //String imagequestion = currentRow.getCell(1).getStringCellValue();
		        	    String audiogg = "";
		        	    Cell audioggCell = currentRow.getCell(2);
		        	    if (audioggCell != null && audioggCell.getCellType() == CellType.STRING) {
		        	    	audiogg = audioggCell.getStringCellValue();
		        	    }
		        	 //String audiogg = currentRow.getCell(2).getStringCellValue();
		        	    String audiomp3 = "";
		        	    Cell audiomp3Cell = currentRow.getCell(3);
		        	    if (audiomp3Cell != null && audiomp3Cell.getCellType() == CellType.STRING) {
		        	    	audiomp3 = audiomp3Cell.getStringCellValue();
		        	    }
		        	 //String audiomp3 = currentRow.getCell(3).getStringCellValue();
		        	    String paragraph = "";
		        	    Cell paragraphCell = currentRow.getCell(4);
		        	    if (paragraphCell != null && paragraphCell.getCellType() == CellType.STRING) {
		        	    	paragraph = paragraphCell.getStringCellValue();
		        	    }
		        	 //String paragraph = currentRow.getCell(4).getStringCellValue();
		        	    String question = "";
		        	    Cell questionCell = currentRow.getCell(5);
		        	    if (questionCell != null && questionCell.getCellType() == CellType.STRING) {
		        	    	question = questionCell.getStringCellValue();
		        	    }
		        	 //String question = currentRow.getCell(5).getStringCellValue();
		        	    String option1 = "";
		        	    Cell option1Cell = currentRow.getCell(6);
		        	    if (option1Cell != null && option1Cell.getCellType() == CellType.STRING) {
		        	    	option1 = option1Cell.getStringCellValue();
		        	    } 
		        	 //String option1 = currentRow.getCell(6).getStringCellValue();
		        	    String option2 = "";
		        	    Cell option2Cell = currentRow.getCell(7);
		        	    if (option2Cell != null && option2Cell.getCellType() == CellType.STRING) {
		        	    	option2 = option2Cell.getStringCellValue();
		        	    } 
		        	 //String option2 = currentRow.getCell(7).getStringCellValue();
		        	    String option3 = "";
		        	    Cell option3Cell = currentRow.getCell(8);
		        	    if (option3Cell != null && option3Cell.getCellType() == CellType.STRING) {
		        	    	option3 = option3Cell.getStringCellValue();
		        	    } 
		        	 //String option3 = currentRow.getCell(8).getStringCellValue();
		        	    String option4 = "";
		        	    Cell option4Cell = currentRow.getCell(9);
		        	    if (option4Cell != null && option4Cell.getCellType() == CellType.STRING) {
		        	    	option4 = option4Cell.getStringCellValue();
		        	    } 
		        	 //String option4 = currentRow.getCell(9).getStringCellValue();
		        	    String correctanswer = "";
		        	    Cell correctanswerCell = currentRow.getCell(10);
		        	    if (correctanswerCell != null && correctanswerCell.getCellType() == CellType.STRING) {
		        	    	correctanswer = correctanswerCell.getStringCellValue();
		        	    } 
		        	 //String correctanswer = currentRow.getCell(10).getStringCellValue();
		        	 
		        	 
		        	 
		        	 
		        	 examinationquestion examqt = new examinationquestion();
		        	 examqt.setNum(num);
		        	 examqt.setImagequestion(imagequestion);
		        	 examqt.setAudiogg(audiogg);
		        	 examqt.setAudiomp3(audiomp3);
		        	 examqt.setParagraph(paragraph);
		        	 examqt.setQuestion(question);
		        	 examqt.setOption1(option1);
		        	 examqt.setOption2(option2);
		        	 examqt.setOption3(option3);
		        	 examqt.setOption4(option4);
		        	 examqt.setCorrectanswer(correctanswer);
		        	 examqt.setExaminationid(examinationid);
		        	 DAO.ExaminationDAO.insertExaminationQuestion(conn, examqt,examinationid);
		        }
		       
		    } catch (Exception e) {
		    	request.setAttribute("message", "that bai!");
		       
		    }
		
		 
}
public static Boolean insertExaminationQuestion(Connection conn, examinationquestion examqt,int examinationid) {
		
		String sql = "INSERT INTO examinationquestion (num, imagequestion, audiogg,audiomp3,paragraph,question,option1,option2,option3,option4,correctanswer,examinationid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement=null; 
		try {
			statement = conn.prepareStatement(sql);
			int num= examqt.getNum();
			String imagequestion = examqt.getImagequestion();
			String audiogg = examqt.getAudiogg();
			String audiomp3 = examqt.getAudiomp3();
			String paragraph = examqt.getParagraph();
			String question = examqt.getQuestion();
			String option1 = examqt.getOption1();
			String option2 = examqt.getOption2();
			String option3 = examqt.getOption3();
			String option4 = examqt.getOption4();
			String correctanswer = examqt.getCorrectanswer();
			
			
			statement.setInt(1, num);
			statement.setString(2, imagequestion);
			statement.setString(3, audiogg);
			statement.setString(4, audiomp3);
			statement.setString(5, paragraph);
			statement.setString(6, question);
			statement.setString(7, option1);
			statement.setString(8, option2);
			statement.setString(9, option3);
			statement.setString(10, option4);
			statement.setString(11, correctanswer);
			statement.setInt(12, examinationid);
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return false;
	}
	public static int countRow(Connection conn) {
	String sql = "SELECT count(*) FROM examination";
	int count=0;
	PreparedStatement statement=null;
	 try {
		statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		count=resultSet.getInt(1);
		
		

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return count;
}

	public static void DeleteExamination(HttpServletRequest request, HttpServletResponse response,Connection conn, int examinationid) {
		DAO.ExaminationDAO.DeleteExaminationContent(request, response, conn, examinationid);
		DAO.ExaminationDAO.DeleteExaminationResult(request, response, conn, examinationid);
		String sql = "DELETE FROM examination WHERE examinationid = "+examinationid;
	    PreparedStatement statement = null;

	    try {
	        statement = conn.prepareStatement(sql);
	        statement.executeUpdate();
	        request.setAttribute("message", "Xóa thành công " );
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	        	 request.setAttribute("message", "Xóa thất bại: " + e.getMessage());
	        }
	    }
	}
	public static void DeleteExaminationContent(HttpServletRequest request, HttpServletResponse response,Connection conn, int examinationid) {
		String sql = "DELETE FROM examinationquestion WHERE examinationid = "+examinationid;
	    PreparedStatement statement = null;

	    try {
	        statement = conn.prepareStatement(sql);
	        statement.executeUpdate();
	        request.setAttribute("message", "Xóa thành công " );
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	        	 request.setAttribute("message", "Xóa thất bại: " + e.getMessage());
	        }
	    }
	}
	public static void DeleteExaminationResult(HttpServletRequest request, HttpServletResponse response,Connection conn, int examinationid) {
		String sql = "DELETE FROM result WHERE examinationid = "+examinationid;
	    PreparedStatement statement = null;

	    try {
	        statement = conn.prepareStatement(sql);
	        statement.executeUpdate();
	        request.setAttribute("message", "Xóa thành công " );
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	        	 request.setAttribute("message", "Xóa thất bại: " + e.getMessage());
	        }
	    }
	}
}
