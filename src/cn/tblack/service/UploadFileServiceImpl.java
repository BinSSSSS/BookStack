package cn.tblack.service;

import java.io.File;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.tblack.dao.AdminDao;
import cn.tblack.dao.FactoryDao;
import cn.tblack.dao.UploadFileDao;
import cn.tblack.dao.UserDao;
import cn.tblack.model.BookUser;
import cn.tblack.model.UploadFile;
import cn.tblack.utils.FileConfigGenerator;
import cn.tblack.utils.FileWriter;
import cn.tblack.utils.NumberFormat;
import cn.tblack.utils.UploadPropertiesMap;

public class UploadFileServiceImpl implements UploadFileService {

	private UploadFileDao ufDao = FactoryDao.getUploadFileDao();

	private UploadPropertiesMap upMap = UploadPropertiesMap.getInstance();

	private AdminDao aDao = FactoryDao.getAdminDao();

	private UserDao uDao = FactoryDao.getUserDao();

	/* @ 只能在包内构造或者是被子类构造 */
	protected UploadFileServiceImpl() {

	}

	@Override
	public boolean insert(UploadFile file) {
		return ufDao.insert(file);
	}

	@Override
	public boolean insert(UploadFile file, Connection conn) {
		return ufDao.insert(file, conn);
	}

	@Override
	public boolean delete(long id) {
		return ufDao.delete(id);
	}

	@Override
	public boolean delete(long id, Connection conn) {
		return ufDao.delete(id, conn);
	}

	@Override
	public int update(UploadFile file, int id) {
		return ufDao.update(file, id);
	}

	@Override
	public int update(UploadFile file, int id, Connection conn) {
		return ufDao.update(file, id, conn);
	}

	@Override
	public UploadFile query(int id) {
		return ufDao.query(id);
	}

	@Override
	public UploadFile queryByRealName(String realName) {
		return ufDao.queryByRealName(realName);
	}

	@Override
	public List<UploadFile> queryByOldName(String oldName) {
		return ufDao.queryByOldName(oldName);
	}

	@Override
	public List<UploadFile> queryBySavePath(String savePath) {
		return ufDao.queryBySavePath(savePath);
	}

	@Override
	public List<UploadFile> getAll() {
		return ufDao.getAll();
	}

	@Override
	public boolean count(int id) {
		return ufDao.count(id);
	}

	@Override
	public long countByOldName(String oldName) {
		return ufDao.countByOldName(oldName);
	}

	@Override
	public long countByRealName(String realName) {
		return ufDao.countByRealName(realName);
	}

	@Override
	public long countBySavePath(String savePath) {
		return ufDao.countBySavePath(savePath);
	}

	@Override
	public long count() {
		return ufDao.count();
	}

	@Override
	public void saveUploadFile(HttpServletRequest req, BookUser user) {
		
		/* @ 非正确的上传格式,任何步骤都无需要做，直接抛出异常 */
		if (!ServletFileUpload.isMultipartContent(req))
			throw new RuntimeException("错误表单的提交数据格式");

		if (user == null)
			throw new RuntimeException("当前用户无权进行操作!");

		System.out.println("正在进行上传文件的操作");

		/* @ 1. 创建工厂类 */
		DiskFileItemFactory factory = new DiskFileItemFactory();

		/* @ 初始化工厂类的参数 */
		factory.setSizeThreshold(Integer.parseInt(upMap.get("sizeThreshold")));
		factory.setRepository(new File(upMap.get("absPath") + upMap.get("tempPath")));

		/* @ 2.创建解析器 */
		ServletFileUpload sfUpload = new ServletFileUpload(factory);

		/* @ 初始化解析器的可接收文件大小 */
		sfUpload.setFileSizeMax(Long.parseLong(upMap.get("fileSizeMax")));
		sfUpload.setSizeMax(Long.parseLong(upMap.get("sizeMax")));

		/* @2.1 设置上传状态监听 */
		sfUpload.setProgressListener(new ProgressListener() {

			String size; // 上传文件的总大小，只初始化一次

			@Override
			public void update(long pBytesRead, long pContentLength, int pItems) {

				if (size == null)
					size = NumberFormat.getExactByteUnit(pContentLength);
				String readLen = NumberFormat.getExactByteUnit(pBytesRead);

				System.out.printf("上传总量为: %s\n当前已上传:%s\n\n", size, readLen);
			}
		});

		/* @ 3.解析数据 */
		try {
			
			System.out.println("解析数据");
			List<FileItem> items = sfUpload.parseRequest(req);

			System.out.println(items.size());
			
			List<String> uploadFiles = new ArrayList<>(); // 保存上传到服务器的所有文件名(是用户上传的文件生成的html等类型的文件名)

			for (FileItem item : items) {
				System.out.println("判断数据");
				/* @ 4.判断是普通表单域还是文件域 */
				if (item.isFormField()) {

					/* @ 5.普通表单域 */

				} else {
					/* @ 6.文件域 */

					/* @ 创建文件信息对象 */
					UploadFile upFile = new UploadFile();

					String oldName = item.getName(); // 上传文件的初始文件名

					String absPath = upMap.get("absPath"); // 上传文件的绝对路径

					/* @ 解决兼容性问题， 如果是IE传入的文件路径是绝对路径，此时只保存文件名 */
					oldName = oldName.substring(oldName.lastIndexOf("\\") + 1);

					String realName = null;
					File dir = null;

					/*
					 * @ 当然还可以有其他选项， 如果我的管理员希望更新当前指定的页面的话，那么我肯定希望使用的是上传的文件名
					 * @ 并且我还希望保存的路径是在根目录下,这样在上传完成之后我才能够进行访问上传的数据
					 */
					if (aDao.count(user.getUserName()) > 0) {
						// 用于管理员文件上传所需要做的步骤
						// 如果上传的是文件夹的话， 那么则需要保留上传文件的路径
						// 我们假设前端已经帮我们完成上传文件是相对路径的处理
						String folderName = getFolderName(item.getName());
						absPath = upMap.get("adminPath"); //设置为管理员保存路径
						dir = new File(absPath + "/" + folderName); // 拿到该文件夹
						realName = oldName;  //实际保存的名字不改变
						
						
						System.out.println("管理员操作");
					}

					/* @ 当如果是单个用户进行文件上传的时候，那我则需要进行控制 */
					else if (uDao.count(user.getUserName()) > 0) {

						realName = getRealName(oldName);  //通过上传时使用的名字来生成一个可以在本地使用的文件名
						uploadFiles.add(realName);	      //添加到新生成文件名的列表中
						// 并且该文件保存在用户上传目录下的用户名字下
						dir = new File(absPath + "/userupload/" + user.getUserName()); // user是上传文件的用户

					} else {
						throw new RuntimeException("当前[" + user.getUserName() + "]用户未拥有上传权限! ");
					}

					/* @ 该文件夹不存在的时候则创建 */
					if (!dir.exists()) {
						dir.mkdirs();
					}

					/* @ 初始化信息 */
					upFile.setOldName(oldName);
					upFile.setRealName(realName);
					upFile.setFileSize(item.getSize());
					upFile.setFileType(item.getContentType());
					upFile.setSavePath(dir.getAbsolutePath());
					upFile.setUploadUser(user.getUserName());
					upFile.setUploadDate(new Timestamp(System.currentTimeMillis()));

					/* @ 插入到数据库中 */
					if (ufDao.insert(upFile)) {
						System.out.println("插入完成");

						/* @ 只有当插入数据库完成的时候才进行保存文件 */
						FileWriter.writeToFile(item.getInputStream(), (int) item.getSize(),
								Integer.parseInt(upMap.get("buffSize")), dir.getAbsolutePath() + "/" + realName);

						System.out.println("文件写入完成!");

					} else
						System.out.println("插入失败!");
				}
			}
			// 将设置了新名字的文件的文件名转交给前台进行处理
			req.setAttribute("fileList", uploadFiles);
		} catch (SizeLimitExceededException e1) {
			throw new RuntimeException(
					"上传的总文件大小超过了限定大小: " + Long.parseLong(upMap.get("sizeMax")) / (1024 * 1024) + " MB");
		} catch (FileSizeLimitExceededException e2) {
			throw new RuntimeException(
					"上传的单个文件大小超过了限定大小: " + Long.parseLong(upMap.get("fileSizeMax")) / (1024 * 1024) + " MB");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * @ 工具函数，根据上传的原始文件名来进行生成新的本地保存文件名
	 * @return
	 */
	private String getRealName(String oldName) {
		
		String realName = null;
		
		// 首先我可能需要对上传的文件类型进行控制
		int suffixIndex = oldName.lastIndexOf(".");

		if (suffixIndex == -1) {
			throw new RuntimeException("不允许上传的文件类型!");
		}
			

		/* @拿到文件后缀(也可以作为是文件类型) */
		String fileSuffix = oldName.substring(suffixIndex + 1);
		fileSuffix = fileSuffix.toLowerCase(); // 转为小写进行比较

		/* @拿到允许上传文件类型的列表，从配置文件中读取 */
		List<String> allowFileTypes = Arrays.asList(upMap.get("allowFileType").split(","));
		if (allowFileTypes.indexOf(fileSuffix) == -1)
			throw new RuntimeException("不允许上传的文件类型!\n" + "允许上传的文件类型为: " + allowFileTypes);

		// 证明是可上传的文件类型， 那么我们则需要进行html文件名字的生成
		if (fileSuffix.equals("html") || fileSuffix.equals("htm") || fileSuffix.equals("jsp")) {
			realName = FileConfigGenerator.createHtmlName() + "." + fileSuffix;
		}
		/*@ 其他类型文件保留原有的名字*/
		else {
			realName = getFileName(oldName);
		}
		return realName;
	}
	
	/**
	 * @ 通过路径来获取文件名字
	 * @param path
	 * @return
	 */
	private String getFileName(String path) {
		
		return path.substring(path.lastIndexOf("\\") + 1);
	}
	
	
	/**
	 * @ 工具类， 用于获取传递的文件路径中的文件夹名称
	 * @return
	 */
	private String getFolderName(String path) {
			
		int sepaIndex = path.lastIndexOf("\\"); // 拿到最后一个分隔符'/'
		sepaIndex = sepaIndex == -1 ? 0 : sepaIndex; // 结束位置,如果上传的是单个文件的话，那么就截取字符串为空
		
		String folderName = path.substring(0, sepaIndex);
		
		return folderName;
	}
	
}
