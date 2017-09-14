package com.feng.com.rxjavade.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class FileUtils {
	public static final String STICK_PATH = new StringBuilder()
			.append(Environment.getExternalStorageDirectory().getAbsolutePath())
			.append(File.separator).append("keegoo").append(File.separator)
			.append("mmshq").append(File.separator).append("sticks").append("sticks-img").append(File.separator).toString();
	private String mFileName;
	public static FileUtils instance;

	public static FileUtils getInstance() {

		if (instance == null) {
			synchronized (FileUtils.class) {
				if (instance == null) {
					instance = new FileUtils();
				}
			}
		}
		return instance;

	}
	
	
	public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
	
	/**
     * 获取文件夹对象
     * 
     * @return 返回SD卡下的指定文件夹对象，若文件夹不存在则创建
     */
    public static File getSaveFolder(String folderName) {
        File file = new File(getSDCardPath() + File.separator + folderName
                + File.separator);
        file.mkdirs();
        return file;
    }
	
	
	/**
     * 获取SD卡下指定文件夹的绝对路径
     * 
     * @return 返回SD卡下的指定文件夹的绝对路径
     */
    public static String getSavePath(String folderName) {
        return getSaveFolder(folderName).getAbsolutePath();
    }
	
	/**
     * 从指定文件夹获取文件
     * 
     * @return 如果文件不存在则创建,如果如果无法创建文件或文件名为空则返回null
     */
    public static File getSaveFile(String folderPath, String fileNmae) {
        File file = new File(getSavePath(folderPath) + File.separator
                + fileNmae);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

	public File createSDFile(String fileName) throws IOException {
		this.mFileName = fileName;
		File file = new File(STICK_PATH + mFileName);
		file.createNewFile();
		return file;
	}

	public File createSDDir(String dirName) {
		File dir = new File(STICK_PATH + dirName);
		dir.mkdirs();
		return dir;
	}

	public boolean isFileExist(String fileName) {
		this.mFileName = fileName;
		File file = new File(STICK_PATH + mFileName);
		return file.exists();
	}

	public File write2SDFromInput(String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(STICK_PATH);
			file = createSDFile(fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			int len = -1;
			while ((len = input.read(buffer)) != -1) {
				output.write(buffer, 0, len);
			}
			// 清掉缓存
			output.flush();
		} catch (Exception e) {
			Log.e("write2SDFromInput", e.getMessage());
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException ioe) {
				Log.e("write2SDFromInput", ioe.getMessage());
			}
		}
		return file;
	}

	public File write2SDFromInput(String fileName, byte[] data) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(STICK_PATH);
			file = createSDFile(fileName);
			output = new FileOutputStream(file);
			output.write(data);
			// 清掉缓存
			output.flush();
		} catch (Exception e) {
			Log.e("write2SDFromInput", e.getMessage());
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException ioe) {
				Log.e("write2SDFromInput", ioe.getMessage());
			}
		}
		return file;
	}

	public String getFileName() {
		return STICK_PATH + mFileName;
	}


	/***********************************************************************/
	/**
	 * 获取目录文件大小
	 *
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
//				Log.e("cache_size","file--"+file.getName());
			} else if (file.isDirectory()) {
//				Log.e("cache_size","dir--"+file.getName());
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * 转换文件大小
	 *
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 判断是否安装目标应用
	 *
	 * @param packageName 目标应用安装后的包名
	 * @return 是否已安装目标应用
	 */
	public static boolean isInstallByread(String packageName) {
		return new File("/data/data/" + packageName).exists();
	}

	public static JSONObject getLocalJson(Context context, int jsonfile){
		StringBuffer stringBuffer = new StringBuffer();
		try {
			InputStream is =context.getResources().openRawResource(jsonfile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String str = null;
			while((str = br.readLine())!=null){
				stringBuffer.append(str);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.parseObject(stringBuffer.toString());
	}

	public static JSONArray getLocalJsonArray(Context context, int jsonfile){
		StringBuffer stringBuffer = new StringBuffer();
		try {
			InputStream is =context.getResources().openRawResource(jsonfile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String str = null;
			while((str = br.readLine())!=null){
				stringBuffer.append(str);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.parseArray(stringBuffer.toString());
	}
	public static JSONObject getAssetLocalJson(Context context, String fileuri){
		StringBuffer stringBuffer = new StringBuffer();
		try {
			InputStream is =context.getAssets().open("localnews/"+fileuri+".json");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String str = null;
			while((str = br.readLine())!=null){
				stringBuffer.append(str);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.parseObject(stringBuffer.toString());
	}
}
