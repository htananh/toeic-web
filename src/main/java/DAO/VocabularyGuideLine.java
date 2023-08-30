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


import BEAN.vocabularycontent;
import BEAN.vocabularyguideline;

public class VocabularyGuideLine {
	public static void InsertVocabularyGuideLineName(HttpServletRequest request,HttpServletResponse response,Connection conn, vocabularyguideline gr) {
		String sql = "INSERT INTO vocabularyguideline (vocabularyguidelinename) VALUES (?)";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			String vocabularyguidelinename=gr.getVocabularyguidelinename();
			
			statement.setString(1, vocabularyguidelinename);
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted!=0) {
				request.setAttribute("message", "Thêm thành công!!");
			}
			
		} catch (SQLException e) {
			request.setAttribute("message", "Thêm thất bại!!");
		}
}
	public static List<vocabularyguideline> DisplayVocabularyGuideLine(Connection conn) {
		List<vocabularyguideline> list = new ArrayList<>();
		String sql = "Select * from vocabularyguideline";
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				vocabularyguideline gr=new vocabularyguideline();
				int vocabularyguidelineid = resultSet.getInt("vocabularyguidelineid");
			    String vocabularyguidelinename = resultSet.getString("vocabularyguidelinename");
			    String vocabularyguidelineimage = resultSet.getString("vocabularyguidelineimage");
			   
			    
			   gr.setVocabularyguidelineid(vocabularyguidelineid);
			   gr.setVocabularyguidelinename(vocabularyguidelinename);
			   gr.setVocabularyguidelineimage(vocabularyguidelineimage);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static List<vocabularyguideline> DisplayVocabularyGuideLinePage(int start,int count,Connection conn) {
		List<vocabularyguideline> list = new ArrayList<>();
		String sql = "SELECT * FROM vocabularyguideline LIMIT "+count+" OFFSET "+(start-1);
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				vocabularyguideline gr=new vocabularyguideline();
				int vocabularyguidelineid = resultSet.getInt("vocabularyguidelineid");
			    String vocabularyguidelinename = resultSet.getString("vocabularyguidelinename");
			    String vocabularyguidelineimage = resultSet.getString("vocabularyguidelineimage");
			   
			    
			   gr.setVocabularyguidelineid(vocabularyguidelineid);
			   gr.setVocabularyguidelinename(vocabularyguidelinename);
			   gr.setVocabularyguidelineimage(vocabularyguidelineimage);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	
public static void UploadSingleFile(HttpServletRequest request, HttpServletResponse response,Connection conn, int vocabularyguidelineid) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/FileVocabulary/");
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
                    DAO.VocabularyGuideLine.InsertVocabularyGuidelineImage(request, response, conn, vocabularyguidelineid, fileName);
                    
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
	public static void InsertVocabularyGuidelineImage(HttpServletRequest request, HttpServletResponse response,Connection conn,int vocabularyguidelineid, String image) {
		String sql = "update vocabularyguideline set vocabularyguidelineimage=? where vocabularyguidelineid="+vocabularyguidelineid;
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
	public static void importexcelVocabularyGuideLine(HttpServletRequest request, HttpServletResponse response,Connection conn, int examinationid) {
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/FileVocabulary/");
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
                   DAO.VocabularyGuideLine.importexcel(conn, request, response, filePath, examinationid);
                    
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
public static void importexcel(Connection conn,HttpServletRequest request,HttpServletResponse response,String Address,int vocabularyguidelineid) {
		
		
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
		        	    String vocabularyguidecontentname = "";
		        	    Cell vocabularyguidecontentnameCell = currentRow.getCell(1);
		        	    if (vocabularyguidecontentnameCell != null && vocabularyguidecontentnameCell.getCellType() == CellType.STRING) {
		        	    	vocabularyguidecontentname = vocabularyguidecontentnameCell.getStringCellValue();
		        	    }
		        	 //String imagequestion = currentRow.getCell(1).getStringCellValue();
		        	    String transcrible = "";
		        	    Cell transcribleCell = currentRow.getCell(2);
		        	    if (transcribleCell != null && transcribleCell.getCellType() == CellType.STRING) {
		        	    	transcrible = transcribleCell.getStringCellValue();
		        	    }
		        	 //String audiogg = currentRow.getCell(2).getStringCellValue();
		        	    String audiomp3 = "";
		        	    Cell audiomp3Cell = currentRow.getCell(3);
		        	    if (audiomp3Cell != null && audiomp3Cell.getCellType() == CellType.STRING) {
		        	    	audiomp3 = audiomp3Cell.getStringCellValue();
		        	    }
		        	 //String audiomp3 = currentRow.getCell(3).getStringCellValue();
		        	    String audiogg = "";
		        	    Cell audioggCell = currentRow.getCell(4);
		        	    if (audioggCell != null && audioggCell.getCellType() == CellType.STRING) {
		        	    	audiogg = audioggCell.getStringCellValue();
		        	    }
		        	 //String paragraph = currentRow.getCell(4).getStringCellValue();
		        	    String mean = "";
		        	    Cell meanCell = currentRow.getCell(5);
		        	    if (meanCell != null && meanCell.getCellType() == CellType.STRING) {
		        	    	mean = meanCell.getStringCellValue();
		        	    }
		        	 //String question = currentRow.getCell(5).getStringCellValue();
		        	    String vocabularycontentimage = "";
		        	    Cell vocabularycontentimageCell = currentRow.getCell(6);
		        	    if (vocabularycontentimageCell != null && vocabularycontentimageCell.getCellType() == CellType.STRING) {
		        	    	vocabularycontentimage = vocabularycontentimageCell.getStringCellValue();
		        	    } 
		        	 
		        	 
		        	 
		        	 
		        	 
		        	    vocabularycontent examqt = new vocabularycontent();
		        	 examqt.setNum(num);
		        	 examqt.setVocabularyguidecontentname(vocabularyguidecontentname);
		        	 examqt.setTranscrible(transcrible);
		        	 examqt.setAudiomp3(audiomp3);
		        	 examqt.setAudiogg(audiogg);
		        	 examqt.setMean(mean);
		        	 examqt.setVocabularyguidelineid(vocabularyguidelineid);
		        	 examqt.setVocabularycontentimage(vocabularycontentimage);
		        	 
		        	DAO.VocabularyGuideLine.insertVocabularyContent(conn, examqt, vocabularyguidelineid);
		        }
		       
		    } catch (Exception e) {
		    	request.setAttribute("message", "that bai!");
		       
		    }
		
		 
}
public static Boolean insertVocabularyContent(Connection conn, vocabularycontent examqt,int vocabularyguidelineid) {
		
		String sql = "INSERT INTO vocabularycontent (num, vocabularyguidecontentname, transcrible,audiomp3,audiogg,mean,vocabularyguidelineid,vocabularycontentimage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement=null; 
		try {
			statement = conn.prepareStatement(sql);
			int num= examqt.getNum();
			String vocabularyguidecontentname = examqt.getVocabularyguidecontentname();
			String transcrible = examqt.getTranscrible();
			String audiomp3 = examqt.getAudiomp3();
			String audiogg = examqt.getAudiogg();
			String mean = examqt.getMean();
			String vocabularycontentimage = examqt.getVocabularycontentimage();
			
			
			
			statement.setInt(1, num);
			statement.setString(2, vocabularyguidecontentname);
			statement.setString(3, transcrible);
			statement.setString(4, audiomp3);
			statement.setString(5, audiogg);
			statement.setString(6, mean);
			statement.setInt(7, vocabularyguidelineid);
			statement.setString(8, vocabularycontentimage);
			
			
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
	public static void UploadMultipleFile(HttpServletRequest request, HttpServletResponse response,Connection conn) {
		
		ServletContext context = request.getServletContext();
		
		final String UPLOAD_DIRECTORY=context.getRealPath("/FileVocabulary/");
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
	public static List<vocabularycontent> DisplayVocabularyContent(Connection conn,int vocabularyguidelineid) {
		List<vocabularycontent> list = new ArrayList<>();
		String sql = "Select * from vocabularycontent where vocabularyguidelineid="+vocabularyguidelineid;
		PreparedStatement statement=null;
		 try {
			statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while (resultSet.next()) {
				vocabularycontent gr=new vocabularycontent();
				int num = resultSet.getInt("num");
			    String vocabularyguidecontentname = resultSet.getString("vocabularyguidecontentname");
			    String transcrible = resultSet.getString("transcrible");
			    String audiomp3 = resultSet.getString("audiomp3");
			    String audiogg = resultSet.getString("audiogg");
			    String mean = resultSet.getString("mean");
			    String vocabularycontentimage = resultSet.getString("vocabularycontentimage");
			  
			    
			    gr.setNum(num);
			    gr.setVocabularyguidecontentname(vocabularyguidecontentname);
			    gr.setTranscrible(transcrible);
			    gr.setAudiomp3(audiomp3);
			    gr.setAudiogg(audiogg);
			    gr.setMean(mean);
			    gr.setVocabularycontentimage(vocabularycontentimage);
			    list.add(gr);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return list;
	}
	public static void DeleteVocabularyGuideLine(HttpServletRequest request, HttpServletResponse response,Connection conn, int vocabularyguidelineid) {
		DAO.VocabularyGuideLine.DeleteVocabularyGuideLineContent(request, response, conn, vocabularyguidelineid);
		String sql = "DELETE FROM vocabularyguideline WHERE vocabularyguidelineid = "+vocabularyguidelineid;
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
	public static void DeleteVocabularyGuideLineContent(HttpServletRequest request, HttpServletResponse response,Connection conn, int vocabularyguidelineid) {
		String sql = "DELETE FROM vocabularycontent WHERE vocabularyguidelineid = "+vocabularyguidelineid;
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
