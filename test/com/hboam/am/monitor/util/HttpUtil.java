package com.hboam.am.monitor.util;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	static final int TIMEOUT = 900000;

	static final String CHARSET = "utf-8";
	
	static Logger logger = LoggerFactory.getLogger("monitor");

	/**
	 * POST发送数据
	 * 
	 * @param url
	 * @param timeout
	 * @param params
	 * @param values
	 * @return
	 */
	public static byte[] postDataAsStream(String url, byte[] postData) {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		client.getParams().setContentCharset(CHARSET);
		PostMethod method = new PostMethod();
		try {
			method.setURI(new URI(url, true, CHARSET));
		} catch (Exception e) {
			logger.error("error",e);
		}
		method.setRequestEntity(new ByteArrayRequestEntity(postData));
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("POST DATA FAILED!HTTP STATUS:" + statusCode);
				return null;
			} else {
				byte[] responseBody = null;
				Header contentEncodingHeader = method
						.getResponseHeader("Content-Encoding");
				if (contentEncodingHeader != null
						&& contentEncodingHeader.getValue().equalsIgnoreCase(
								"gzip")) {
					GZIPInputStream is = new GZIPInputStream(method
							.getResponseBodyAsStream());
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					IOUtils.copy(is, os);
					responseBody = os.toByteArray();
				} else {
					responseBody = method.getResponseBody();
				}

				byte[] data = formatData(responseBody);
				String encoding = CHARSET;
				Header contentTypeHeader = method
						.getResponseHeader("Content-Type");
				if (contentTypeHeader != null) {
					String contentType = contentTypeHeader.getValue();
					// System.out.println("content-type:" + contentType);
					int offset = contentType.indexOf("=");
					if (offset != -1)
						encoding = contentType.substring(offset + 1);
					else {
						String body = new String(data, encoding);
						offset = body.indexOf("encoding");
						if (offset != -1) {
							int begin = body.indexOf("\"", offset);
							int end = body.indexOf("\"", begin + 1);
							encoding = body.substring(begin + 1, end);
						}
					}
				}
				return data;
			}
		} catch (Exception ex) {
			logger.error("error",ex);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * POST发送数据
	 * 
	 * @param url
	 * @param timeout
	 * @param params
	 * @param values
	 * @return
	 */
	public static String postData(String url, String postData) {
		logger.info("连接:" + url);
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		client.getParams().setContentCharset(CHARSET);
		PostMethod method = new PostMethod();
		try {
			method.setURI(new URI(url, true, CHARSET));
		} catch (Exception e) {
			logger.error("error",e);
		}
		method.setRequestEntity(new StringRequestEntity(postData));
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("POST DATA FAILED!HTTP STATUS:" + statusCode);
				return null;
			} else {
				byte[] responseBody = null;
				Header contentEncodingHeader = method
						.getResponseHeader("Content-Encoding");
				if (contentEncodingHeader != null
						&& contentEncodingHeader.getValue().equalsIgnoreCase(
								"gzip")) {
					GZIPInputStream is = new GZIPInputStream(method
							.getResponseBodyAsStream());
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					IOUtils.copy(is, os);
					responseBody = os.toByteArray();
				} else {
					responseBody = method.getResponseBody();
				}

				byte[] data = formatData(responseBody);
				String encoding = CHARSET;
				Header contentTypeHeader = method
						.getResponseHeader("Content-Type");
				if (contentTypeHeader != null) {
					String contentType = contentTypeHeader.getValue();
					// System.out.println("content-type:" + contentType);
					int offset = contentType.indexOf("=");
					if (offset != -1)
						encoding = contentType.substring(offset + 1);
					else {
						String body = new String(data, encoding);
						offset = body.indexOf("encoding");
						if (offset != -1) {
							int begin = body.indexOf("\"", offset);
							int end = body.indexOf("\"", begin + 1);
							encoding = body.substring(begin + 1, end);
						}
					}
				}
				return new String(data, encoding);
			}
		} catch (HttpException ex) {
			logger.error("error",ex);
		} catch (IOException ex) {
			logger.error("error",ex);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static byte[] requestHttpContentAsStream(String url)
			throws IOException {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		GetMethod method = new GetMethod();
		try {
			method.setURI(new URI(url, false, "utf-8"));

		} catch (URIException ex) {
			logger.error("error",ex);
		} catch (NullPointerException ex) {
			logger.error("error",ex);
		}
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(8, false));
		try {

			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("GET HTTP CONTENT FAILED!HTTP STATUS NOT OK");
				return null;
			}
			byte[] responseBody = null;
			Header contentEncodingHeader = method
					.getResponseHeader("Content-Encoding");
			if (contentEncodingHeader != null
					&& contentEncodingHeader.getValue()
							.equalsIgnoreCase("gzip")) {
				GZIPInputStream is = new GZIPInputStream(method
						.getResponseBodyAsStream());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				IOUtils.copy(is, os);
				responseBody = os.toByteArray();
			} else {
				responseBody = method.getResponseBody();
			}

			return responseBody;

		} catch (HttpException ex) {
			logger.error("error",ex);
		} catch (IOException ex) {
			logger.error("error",ex);
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * Get http content from url
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String requestHttpContent(String url) throws IOException {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		GetMethod method = new GetMethod();
		try {
			method.setURI(new URI(url, false, "utf-8"));

		} catch (URIException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(8, false));
		try {

			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("GET HTTP CONTENT FAILED!HTTP STATUS NOT OK");
				return null;
			}
			byte[] responseBody = null;
			Header contentEncodingHeader = method
					.getResponseHeader("Content-Encoding");
			if (contentEncodingHeader != null
					&& contentEncodingHeader.getValue()
							.equalsIgnoreCase("gzip")) {
				GZIPInputStream is = new GZIPInputStream(method
						.getResponseBodyAsStream());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				IOUtils.copy(is, os);
				responseBody = os.toByteArray();
			} else {
				responseBody = method.getResponseBody();
			}

			byte[] data = formatData(responseBody);
			String encoding = CHARSET;
			Header contentTypeHeader = method.getResponseHeader("Content-Type");
			if (contentTypeHeader != null) {
				String contentType = contentTypeHeader.getValue();
				// System.out.println("content-type:" + contentType);
				int offset = contentType.indexOf("=");
				if (offset != -1)
					encoding = contentType.substring(offset + 1);
				else {
					String body = new String(data, encoding);
					offset = body.indexOf("encoding");
					if (offset != -1) {
						int begin = body.indexOf("\"", offset);
						int end = body.indexOf("\"", begin + 1);
						encoding = body.substring(begin + 1, end);
					}
				}
			}
			return new String(data, encoding);

		} catch (Exception e) {
			logger.error("error",e);
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * @param data
	 * @return
	 */
	private static byte[] formatData(byte[] data) {
		return data;
		// if (data == null) {
		// return null;
		// }
		// int k = 0;
		// for (; k < data.length && data[k] <= 32; k++) {
		// ;
		// }
		//
		// if (k == data.length) {
		// return null;
		// }
		// byte[] formatData = new byte[data.length - k];
		// java.lang.System.arraycopy(data, k, formatData, 0,
		// formatData.length);
		//
		// return formatData;
	}
	
	 /** 
     * Java模拟Post提交 
     * @param url 要提交到的位置 
     * @param data 例如：NameValuePair[] data = {new NameValuePair("key", "nike"),new NameValuePair("proClass", "")}; 
     * @return 返回HTML代码 
     */  
    public static String methodPost(String url,NameValuePair[] data){  
          
        String response= "";//要返回的response信息  
        HttpClient httpClient = new HttpClient();  
        PostMethod postMethod = new PostMethod(url);  
        // 将表单的值放入postMethod中  
        postMethod.setRequestBody(data);  
        // 执行postMethod  
        int statusCode = 0;  
        try {  
            statusCode = httpClient.executeMethod(postMethod);  
        } catch (HttpException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        // HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发  
        // 301或者302  
        if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY  
                || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {  
            // 从头中取出转向的地址  
            Header locationHeader = postMethod.getResponseHeader("location");  
            String location = null;  
            if (locationHeader != null) {  
                location = locationHeader.getValue();  
                System.out.println("The page was redirected to:" + location);  
                response= methodPost(location,data);//用跳转后的页面重新请求。  
            } else {  
                System.err.println("Location field value is null.");  
            }  
        } else {  
            System.out.println(postMethod.getStatusLine());  
  
            try {  
                response= postMethod.getResponseBodyAsString();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            postMethod.releaseConnection();  
        }  
        return response;  
    }  
  
}
