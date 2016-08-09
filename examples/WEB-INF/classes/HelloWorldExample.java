/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * The simplest possible servlet.
 *
 * @author James Duncan Davidson
 */

public class HelloWorldExample extends HttpServlet {

    private static final long serialVersionUID = 1L;
	

	
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        ResourceBundle rb =
            ResourceBundle.getBundle("LocalStrings",request.getLocale());
        response.setContentType("text/html");
        
		Map<String, String> gujratiTranslationMap = new HashMap<String, String>();
		Map<String, String> teluguTranslationMap = new HashMap<String, String>();
		Map<String, String> hindiTranslationMap = new HashMap<String, String>();
		Map<String, String> oriyaTranslationMap = new HashMap<String, String>();
		Map<String, String> bengaliTranslationMap = new HashMap<String, String>();
		Map<String, String> kannadTranslationMap = new HashMap<String, String>();
		Map<String, String> marathiTranslationMap = new HashMap<String, String>();

		gujratiTranslationMap.put( "PERFECT_MESSAGE", "આભાર! તમે");
    	gujratiTranslationMap.put( "male", "પુરુષ" );
        gujratiTranslationMap.put( "female", "સ્ત્રી" );
        gujratiTranslationMap.put( "sideEffect", "સાઇડ ઇફેક્ટ્સ" );


		hindiTranslationMap.put( "PERFECT_MESSAGE", "धन्यवाद! आप ने भेजा");
        hindiTranslationMap.put( "male", "પુરુષ" );
        hindiTranslationMap.put( "female", "સ્ત્રી" );
        hindiTranslationMap.put( "sideEffect", "दुष्प्रभाव" );


        oriyaTranslationMap.put( "PERFECT_MESSAGE", "ପେର୍fଏcଟ୍ ମେସ୍ସଗେ");
        oriyaTranslationMap.put( "male", "ମଲେ" );
        oriyaTranslationMap.put( "female", "fଏମଲେ" );
        oriyaTranslationMap.put( "sideEffect", "ସିଡେEffଏcଟ୍" );

        bengaliTranslationMap.put( "PERFECT_MESSAGE", "ধন্যবাদ! আপনি ");
        bengaliTranslationMap.put( "male", "পুরুষ" );
        bengaliTranslationMap.put( "female", "মহিলা" );
        bengaliTranslationMap.put( "sideEffect", "সাইড এফেক্টস" );

        kannadTranslationMap.put( "PERFECT_MESSAGE", "ಪರಿಪೂರ್ಣ ಸಂದೇಶ");
        kannadTranslationMap.put( "male", "ಪುರುಷ" );
        kannadTranslationMap.put( "female", "ಸ್ತ್ರೀ" );
        kannadTranslationMap.put( "sideEffect", "ಅಡ್ಡ ಪರಿಣಾಮ" );

        
        marathiTranslationMap.put( "PERFECT_MESSAGE", "धन्यवाद!");
        marathiTranslationMap.put( "male", "पुरुष" );
        marathiTranslationMap.put( "female", "स्त्री " );
        marathiTranslationMap.put( "sideEffect", "साइड इफेक्ट्स" );

        teluguTranslationMap.put( "PERFECT_MESSAGE", "ధన్యవాదాలు");
        teluguTranslationMap.put( "male", "పురుషుడు" );
        teluguTranslationMap.put( "female", "పురుషుడు" );
        teluguTranslationMap.put( "sideEffect", "దుష్ప్రభావాన్ని" );



		PrintWriter out = response.getWriter();
		
		String responseMessage = "";
		
		String mobileNo = "";
		String smsMessage = "";
		String smsDate = "";
		
		Map<String, String[]> params = new HashMap<String, String[]>( request.getParameterMap() );
        
		
        if( params != null && params.size() > 0 )
        {
            for( String key : params.keySet() )
            {
                if ( key.equalsIgnoreCase( "mobileno" ) )
                {
                    mobileNo = ((String[]) params.get( key ))[0];
                }
                if ( key.equalsIgnoreCase("message" ) )
                {
                    smsMessage = ((String[]) params.get( key ))[0];
                }
				if ( key.equalsIgnoreCase("date" ) )
                {
                    smsDate = ((String[]) params.get( key ))[0];
                }
                if ( key.equalsIgnoreCase("language" ) )
                {
                    language = ((String[]) params.get( key ))[0];
                }
                if ( key.equalsIgnoreCase("hash" ) )
                {
                    hash = ((String[]) params.get( key ))[0];
                }
            }
			
			/*
			Iterator<String> it = params.keySet().iterator();

            while ( it.hasNext() )
            {
                String key = (String) it.next();
                String messageValue = ((String[]) params.get( key ))[ 0 ];
                
				if( key == "mobileno")
				{
					mobileNo = messageValue;
				}
				if( key == "message")
				{
					smsMessage = messageValue;
				}
				
                System.out.println( key + "---" + messageValue );
                System.out.println( "language" + language );


				
            }  
			*/
			
			
			if (language.equalsIgnoreCase("Gujarati")){
			    responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, gujratiTranslationMap );
			}
			if (language.equalsIgnoreCase("Marathi")){
                responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, marathiTranslationMap );
            }
            if (language.equalsIgnoreCase("Hindi")){
			    responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, hindiTranslationMap );
			}
			if (language.equalsIgnoreCase("Kannad")){
                responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, kannadTranslationMap );
            }
            if (language.equalsIgnoreCase("Telugu")){
			    responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, teluguTranslationMap );
			}
			if (language.equalsIgnoreCase("Bengali")){
                responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, bengaliTranslationMap );
            }
            if (language.equalsIgnoreCase("Oriya")){
                responseMessage = sendSMS(mobileNo, smsMessage, smsDate,hash, oriyaTranslationMap );
            }
            

			
        }
					
        out.println( responseMessage );

		
      
    }
	
    public String sendSMS (String mobileNo, String message, String smsDate, String hash,Map<String, String> translationMap) throws UnsupportedEncodingException
    {
        String resopnseString = "";
		System.out.println(mobileNo +" -- 1 -- " + message );
        //System.out.println(encodeMessage(new String(message.getBytes())));
        try 
		{
            // Construct data
           
		   String [] tempMessage = message.split( "," );
            
            message = gujratiTranslationMap.get( "PERFECT_MESSAGE" ) + " " + translationMap.get( "male" ) +"("+
                        tempMessage[0]+","+tempMessage[1]+","+tempMessage[2]+")"+translationMap.get( "female" )+"("+
                        tempMessage[3]+","+tempMessage[4]+","+tempMessage[5]+")"+translationMap.get( "sideEffect" )+"("+
                        tempMessage[6]+") "+ smsDate;
                
		   
            String user = "username=" + "harsh.atal@gmail.com";
            String hash = "&hash=" + hash;
            message = "&message=" + message;
            String sender = "&sender=" + "NVBDCP";
            //String numbers = "&numbers=" + "919654232779&unicode=1&test=1";
			String numbers = "&numbers=" + mobileNo +"&unicode=1";
 
            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
            String data = user +hash + message + sender+numbers;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) 
			{
                stringBuffer.append(line);
            }
            
			rd.close();
			
			resopnseString = stringBuffer.toString();
			
            System.out.println(stringBuffer.toString());
        }
		
		catch (Exception e) 
		{
			System.out.println("Error SMS "+e);
        }
		
		return resopnseString;
		
    }
 
	
}



