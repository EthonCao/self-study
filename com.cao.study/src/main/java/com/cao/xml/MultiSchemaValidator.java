/***********************************************************************
 * Module:  XsdUtils.java
 * Author:  hw321
 * Purpose: Defines the Class XsdUtils
 ***********************************************************************/

package com.cao.xml;

import static org.junit.Assume.assumeNoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.alibaba.fastjson.JSONObject;

public class MultiSchemaValidator {

	// private static final Logger logger =
	// LoggerFactory.getLogger(MultiSchemaValidator.class);
	static {
		System.setProperty("jdk.xml.maxOccurLimit", "9999");// 默认的maxOccur为5000,而我们项目中要求9999
		Locale.setDefault(Locale.CHINA);// 如果项目不考虑国际化的话
	}

	public static List<SAXParseException> validateXMLSchema(String xsdPath, String xml) throws SAXException, IOException {
		final List<SAXParseException> errors = new ArrayList<SAXParseException>();
//		try {
			//SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.XMLNS_ATTRIBUTE_NS_URI);
			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			//String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			//Schema schema = factory.newSchema(new File(path + xsdPath));
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(SAXParseException exception) throws SAXException {
					// logger.debug("warning ex", exception);
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					// logger.debug("fatalError ex", exception);
				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					// logger.debug("error ex", exception);
					errors.add(exception);
				}
			});
//			StringReader reader = new StringReader(xml);
//			StreamSource streamSource = new StreamSource(reader);
			
			String encoding="UTF-8";
			InputStream inPut =new FileInputStream(new File(xml));
			StreamSource streamSource = new StreamSource(inPut);
			validator.validate(streamSource);
//		} catch (IOException | SAXException e) {
//			System.out.println("Exception: " + e.getMessage());
//			e.printStackTrace();
//		}
		System.out.println("Return ");
		return errors;
	}

	// 测试代码
	public static void main(String[] args) throws Exception {
		String xml = "D:\\\\data_proall\\\\xmlValidate\\\\模型文件-首次来陕1.xml";
		String schemaURI = "D:\\data_proall\\xmlValidate\\bus_model.xsd";
		JSONObject jsonObject = new JSONObject();
		List<SAXParseException> errors = new ArrayList<SAXParseException>();
		try {
			errors = validateXMLSchema(schemaURI, xml);
		} catch (Exception e) {
			jsonObject.put("error", e.getMessage());
			//System.out.println("Exception: " + e.getMessage());
		}
		
		List<String> errMsg = new ArrayList<String>();
		if (errors != null) {
			for (SAXParseException ex : errors) {
				
				errMsg.add(ex.getLineNumber() + "行," + ex.getColumnNumber() + "列," + ex.getMessage());
				//System.out.println(ex.getLineNumber() + "行," + ex.getColumnNumber() + "列," + ex.getMessage());
			}
		} else {
			//System.out.println("Errors is Null");
		}
		jsonObject.put("errorList", errMsg);
		System.out.println(jsonObject);
		
	}
}