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

import BEAN.listenexecise;
import BEAN.listenquestion;
import BEAN.readingquestion;

public class ListenexeciseDAO {
	public static void InsertListenexeciseName(HttpServletRequest request,HttpServletResponse response,Connection conn, listenexecise exam) {
		String sql = "INSERT INTO listenexecise (listenexecisename) VALUES (?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			String listenexecisename=exam.getListenexecisename();
			
			statement.setString(1, listenexecisename);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
		 
}
	public static List<listenexecise> Displaylistenexecise(Connection conn) {
		List<listenexecise> list = new ArrayList<>();
		String sql = "Select * from listenexecise";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				listenexecise exam=new listenexecise();
				int listenexeciseid = resultSet.getInt("listenexeciseid");
			    String listenexecisename = resultSet.getString("listenexecisename");
			    String listenexeciseimage = resultSet.getString("listenexeciseimage");
			    
			    
			   exam.setListenexeciseid(listenexeciseid);
			   exam.setListenexecisename(listenexecisename);
			   exam.setListenexeciseimage(listenexeciseimage);
			    list.add(exam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static List<listenquestion> DisplayListenquestion(Connection conn, int listenexeciseid) {
		List<listenquestion> list = new ArrayList<>();
		String sql = "Select * from listenquestion where listenexeciseid="+listenexeciseid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				listenquestion exam=new listenquestion();
				int num = resultSet.getInt("num");
			    String imagename = resultSet.getString("imagename");
			    String audiomp3 = resultSet.getString("audiomp3");
			    String question = resultSet.getString("question");
			    String option1 = resultSet.getString("option1");
			    String option2 = resultSet.getString("option2");
			    String option3 = resultSet.getString("option3");
			    String option4 = resultSet.getString("option4");
			    String correctanswer = resultSet.getString("correctanswer");
			    
			    
			   exam.setNum(num);
			   exam.setImagename(imagename);
			   exam.setAudiomp3(audiomp3);
			   exam.setQuestion(question);
			   exam.setQuestion(question);
			   exam.setOption1(option1);
			   exam.setOption2(option2);
			   exam.setOption3(option3);
			   exam.setOption4(option4);
			   exam.setCorrectanswer(correctanswer);
			    list.add(exam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static List<listenexecise> DisplayListenexecisePage(int start,int count,Connection conn) {
		List<listenexecise> list = new ArrayList<>();
		String sql = "Select * from listenexecise LIMIT "+count+" OFFSET "+(start-1);
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				listenexecise exam=new listenexecise();
				int listenexeciseid = resultSet.getInt("listenexeciseid");
			    String listenexecisename = resultSet.getString("listenexecisename");
			    String listenexeciseimage = resultSet.getString("listenexeciseimage");
			    
			    
			   exam.setListenexeciseid(listenexeciseid);
			   exam.setListenexecisename(listenexecisename);
			   exam.setListenexeciseimage(listenexeciseimage);
			    list.add(exam);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
public static void UploadMultipleFile(HttpServletRequest request, HttpServletResponse response,Connection conn) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/fileListenexecise/");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String uploadedFilePath = null;
		if(!isMultipart) {
			request.setAttribute("message", "do not have enctype=\"multipart/form-data\"");
		}
		 // Tạo một thư mục tạm để lưu trữ file upload (nếu muốn)
	//    File uploadDir = new File(UPLOAD_DIRECTORY);
	//    uploadDir.mkdir();
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
public static void UploadSingleFile(HttpServletRequest request, HttpServletResponse response,Connection conn, int listenexeciseid) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/fileListenexecise/");
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
                    DAO.ListenexeciseDAO.InsertListenexeciseImage(request, response, conn, listenexeciseid, fileName);
                    
                    
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
public static void importexcelListenexecise(HttpServletRequest request, HttpServletResponse response,Connection conn, int listenexeciseid) {
	ServletContext context = request.getServletContext();
	
	final String UPLOAD_DIRECTORY=context.getRealPath("/fileListenexecise/");
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	String uploadedFilePath = null;
	if(!isMultipart) {
		request.setAttribute("message", "do not have enctype=\"multipart/form-data\"");
	}
	 // Tạo một thư mục tạm để lưu trữ file upload (nếu muốn)
//    File uploadDir = new File(UPLOAD_DIRECTORY);
//    uploadDir.mkdir();
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
               
               DAO.ListenexeciseDAO.importexcel(conn, request, response, filePath, listenexeciseid);
               request.setAttribute("message", "Upload thành công "); 
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
public static void importexcel(Connection conn,HttpServletRequest request,HttpServletResponse response,String Address,int listenexeciseid) {
	
	
	// Đọc tệp tin Excel sử dụng POIFSFileSystem
	
	 try {
		

	      
	        FileInputStream input = new FileInputStream(Address);
	        POIFSFileSystem fs = new POIFSFileSystem(input);
	       
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
	       
	        for(int i=1;i<=sheet.getLastRowNum();i++) {
	        	 Row currentRow = sheet.getRow(i);
	        	 
	        	//String audiomp3 = currentRow.getCell(3).getStringCellValue();
	        	    String imagename = "";
	        	    Cell imagenameCell = currentRow.getCell(0);
	        	    if (imagenameCell != null && imagenameCell.getCellType() == CellType.STRING) {
	        	    	imagename = imagenameCell.getStringCellValue();
	        	    }
	        	    String audiomp3 = "";
	        	    Cell audiomp3Cell = currentRow.getCell(1);
	        	    if (audiomp3Cell != null && audiomp3Cell.getCellType() == CellType.STRING) {
	        	    	audiomp3 = imagenameCell.getStringCellValue();
	        	    }
	        	 int num = 0; // Giá trị mặc định nếu ô trống
	        	    Cell numCell = currentRow.getCell(2);
	        	    if (numCell != null && numCell.getCellType() == CellType.NUMERIC) {
	        	        num = (int) numCell.getNumericCellValue();
	        	    }
	        	    
	        	   
	        	 //String audiomp3 = currentRow.getCell(3).getStringCellValue();
	        	  
	        	 //String paragraph = currentRow.getCell(4).getStringCellValue();
	        	    String question = "";
	        	    Cell questionCell = currentRow.getCell(3);
	        	    if (questionCell != null && questionCell.getCellType() == CellType.STRING) {
	        	    	question = questionCell.getStringCellValue();
	        	    }
	        	 //String question = currentRow.getCell(5).getStringCellValue();
	        	    String option1 = "";
	        	    Cell option1Cell = currentRow.getCell(4);
	        	    if (option1Cell != null && option1Cell.getCellType() == CellType.STRING) {
	        	    	option1 = option1Cell.getStringCellValue();
	        	    } 
	        	 //String option1 = currentRow.getCell(6).getStringCellValue();
	        	    String option2 = "";
	        	    Cell option2Cell = currentRow.getCell(5);
	        	    if (option2Cell != null && option2Cell.getCellType() == CellType.STRING) {
	        	    	option2 = option2Cell.getStringCellValue();
	        	    } 
	        	 //String option2 = currentRow.getCell(7).getStringCellValue();
	        	    String option3 = "";
	        	    Cell option3Cell = currentRow.getCell(6);
	        	    if (option3Cell != null && option3Cell.getCellType() == CellType.STRING) {
	        	    	option3 = option3Cell.getStringCellValue();
	        	    } 
	        	 //String option3 = currentRow.getCell(8).getStringCellValue();
	        	    String option4 = "";
	        	    Cell option4Cell = currentRow.getCell(7);
	        	    if (option4Cell != null && option4Cell.getCellType() == CellType.STRING) {
	        	    	option4 = option4Cell.getStringCellValue();
	        	    } 
	        	 //String option4 = currentRow.getCell(9).getStringCellValue();
	        	    String correctanswer = "";
	        	    Cell correctanswerCell = currentRow.getCell(8);
	        	    if (correctanswerCell != null && correctanswerCell.getCellType() == CellType.STRING) {
	        	    	correctanswer = correctanswerCell.getStringCellValue();
	        	    } 
	        	 //String correctanswer = currentRow.getCell(10).getStringCellValue();
	        	 
	        	 
	        	 
	        	 
	        	    listenquestion examqt = new listenquestion();

	        	 
	        	 examqt.setImagename(imagename);
	        	 examqt.setAudiomp3(audiomp3);
	        	 examqt.setQuestion(question);
	        	 examqt.setNum(num);
	        	 examqt.setOption1(option1);
	        	 examqt.setOption2(option2);
	        	 examqt.setOption3(option3);
	        	 examqt.setOption4(option4);
	        	 examqt.setCorrectanswer(correctanswer);
	        	 examqt.setListenexeciseid(listenexeciseid);
	        	 DAO.ListenexeciseDAO.insertListenquestion(conn, examqt, listenexeciseid);
	        }
	       
	    } catch (Exception e) {
	    	request.setAttribute("message", "that bai!");
	       
	    }
	
	 
}
	public static void InsertListenexeciseImage(HttpServletRequest request, HttpServletResponse response,Connection conn,int listenexeciseid, String image) 
	{
		String sql = "update listenexecise set listenexeciseimage=? where listenexeciseid="+listenexeciseid;
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

	public static Boolean insertListenquestion(Connection conn, listenquestion readqt,int listenexeciseid) {
		
		String sql = "INSERT INTO listenquestion (imagename,audiomp3,num,question,option1,option2,option3,option4,correctanswer,listenexeciseid) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement=null; 
		try {
			statement = conn.prepareStatement(sql);
			int num= readqt.getNum();
			String imagename = readqt.getImagename();
			String audiomp3 = readqt.getAudiomp3();
			String question = readqt.getQuestion();
			String option1 = readqt.getOption1();
			String option2 = readqt.getOption2();
			String option3 = readqt.getOption3();
			String option4 = readqt.getOption4();
			String correctanswer = readqt.getCorrectanswer();
			
			
			statement.setString(1, imagename);
			statement.setString(2, audiomp3);
			statement.setInt(3, num);
			statement.setString(4, question);
			statement.setString(5, option1);
			statement.setString(6, option2);
			statement.setString(7, option3);
			statement.setString(8, option4);
			statement.setString(9, correctanswer);
			statement.setInt(10, listenexeciseid);
			
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
	String sql = "SELECT count(*) FROM listenexecise";
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
	public static int lengtQuestion(Connection conn,int listenexeciseid) {
		int count =0;
		
		String sql = "Select count(*) from listenquestion where listenexeciseid="+listenexeciseid;
		PreparedStatement statement=null;
		
		try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			count=resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
		
	}
	public static String GetCorrectanswer(Connection conn,int num) {
		String correctanswer ="";
		
		String sql = "Select correctanswer from listenquestion where num="+num;
		PreparedStatement statement=null;
		
		try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			correctanswer=resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return correctanswer;
		
	}
	
	public static void DeleteListenexecise(HttpServletRequest request, HttpServletResponse response,Connection conn, int listenexeciseid) {
		DAO.ListenexeciseDAO.DeleteListenquestion(request, response, conn, listenexeciseid);
		String sql = "DELETE FROM listenexecise WHERE listenexeciseid = "+listenexeciseid;
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
	public static void DeleteListenquestion(HttpServletRequest request, HttpServletResponse response,Connection conn, int listenexeciseid) {
		String sql = "DELETE FROM listenquestion WHERE listenexeciseid = "+listenexeciseid;
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
