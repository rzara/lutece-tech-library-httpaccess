/*
 * Copyright (c) 2002-2021, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.util.httpaccess;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.spi.http.HttpExchange;

import org.junit.Test;



/**
 * Http net Object Accessor
 */
public class HttpAccessTest 
{
	
	@Test
	public void testDoGet()
	{
	
		
		String strUrlTestHttp="https://httpbin.org/anything";
		
		HttpClientConfiguration configuration=new HttpClientConfiguration();
		configuration.setConnectionTimeout("1000");
		configuration.setSocketTimeout("10000");
		configuration.setProxyHost("192.168.64.41");
		configuration.setNoProxyFor("*.paris.mdp");
		configuration.setProxyPort("8080");
		
		Map<String,String> mapHeaders=new HashMap<String, String>();
		Map<String,String> mapHeadersResponse=new HashMap<String, String>();
		
		
		mapHeaders.put("Authorization", " Basic token");
		
		HttpAccessService httpAccessService=new HttpAccessService(configuration);
		HttpAccess httpAccess=new HttpAccess(httpAccessService,new MockResponseStatusValidator());
		
		
		
		
		try {
			String strTest=httpAccess.doGet(strUrlTestHttp, null,null, mapHeaders, mapHeadersResponse);
			System.out.println("*************************************");
			System.out.println(strTest);
			System.out.println("*************************************");
			
			mapHeadersResponse.forEach((k,v)->System.out.println("***"+k+"***"+v));
		} catch (HttpAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	
	@Test
	public void testDoPost()
	{
	
		String strUrlTestHttp="https://httpbin.org/anything";
		
		HttpClientConfiguration configuration=new HttpClientConfiguration();
		configuration.setConnectionTimeout("10000");
		configuration.setSocketTimeout("10000");
		configuration.setProxyHost("192.168.64.41");
		configuration.setNoProxyFor("*.paris.mdp");
		configuration.setProxyPort("8080");
		
		Map<String,String> mapHeaders=new HashMap<String, String>();
		Map<String,String> mapParameters=new HashMap<String, String>();
		
		Map<String,String> mapHeadersResponse=new HashMap<String, String>();
		
		
		mapHeaders.put("Authorization", " Basic ");
		mapParameters.put("grant_type", "client_credentials");
		HttpAccessService httpAccessService=new HttpAccessService(configuration);
		HttpAccess httpAccess=new HttpAccess(httpAccessService,new MockResponseStatusValidator());
		
		
		
		
		try {
			String strTest=httpAccess.doPost(strUrlTestHttp, mapParameters, null, null, mapHeaders, mapHeadersResponse);
			System.out.println(strTest);
			
			mapHeadersResponse.forEach((k,v)->System.out.println("***"+k+"***"+v));
		} catch (HttpAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	
	
	
	
	
	@Test
	public void testDoPostMultipart()		
	{
		String strJson= "{\n"
				+ "  \"identity_change\" : {\n"
				+ "    \"identity\" : {\n"
				+ "    \"customer_id\" : \"b59f9424-6c5f-4bc7-a6c7-efd1e29655d4\",\n"
				+ "    \"attributes\" : {\n"
				+ "      \"birthcountry\" : {\n"
				+ "        \"key\" : \"birthcountry\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"FRANCE\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"birthdate\" : {\n"
				+ "        \"key\" : \"birthdate\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"24/08/1962\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"gender\" : {\n"
				+ "        \"key\" : \"gender\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"1\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"birthplace\" : {\n"
				+ "        \"key\" : \"birthplace\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"PARIS 07\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"login\" : {\n"
				+ "        \"key\" : \"login\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"testbp2022@yopmail.com\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"family_name\" : {\n"
				+ "        \"key\" : \"family_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"DUBOIS\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"first_name\" : {\n"
				+ "        \"key\" : \"first_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"Angela Claire Louise\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"email\" : {\n"
				+ "        \"key\" : \"email\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"testbp2022@yopmail.com\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : false,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : null\n"
				+ "      },\n"
				+ "    \"fc_birthdate\" : {\n"
				+ "        \"key\" : \"fc_birthdate\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"1962-08-85\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "   \n"
				+ "      \"fc_given_name\" : {\n"
				+ "        \"key\" : \"fc_given_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"Angela Claire Louise 3\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"fc_birthplace\" : {\n"
				+ "        \"key\" : \"fc_birthplace\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"75107\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "  \n"
				+ "      \"fc_gender\" : {\n"
				+ "        \"key\" : \"fc_gender\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"female\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "     \n"
				+ "      \"fc_family_name\" : {\n"
				+ "        \"key\" : \"fc_family_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"DUBOIS\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"fc_birthcountry\" : {\n"
				+ "        \"key\" : \"fc_birthcountry\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"99100\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ",\n"
				+ "    \"author\" : {\n"
				+ "      \"id\" : \"usager\",\n"
				+ "      \"type\" : 1,\n"
				+ "      \"application_code\" : \"RhssoFranceConnect\"\n"
				+ "    }\n"
				+ "  }\n"
				+ "}";
		
		
        String strUrlTestHttp="https://httpbin.org/anything";
		
		HttpClientConfiguration configuration=new HttpClientConfiguration();
		configuration.setConnectionTimeout("1000");
		configuration.setSocketTimeout("10000");
		configuration.setProxyHost("192.168.64.41");
		configuration.setNoProxyFor("*.paris.mdp");
		configuration.setProxyPort("8080");
		
		Map<String,String> mapHeaders=new HashMap<String, String>();
	
		   Map<String, List<String>> mapParameters = new HashMap<String, List<String>>( );
		List<String> params=new ArrayList<String>();
		params.add(strJson);
		mapParameters.put( "identityChange", params);
		Map<String,String> mapHeadersResponse=new HashMap<String, String>();
		
		
		//mapHeaders.put("Content-Type", "application/json; charset=utf-8");
		mapHeaders.put("client_code", "RhssoFranceConnect");
		
		//mapParameters.put("grant_type", "client_credentials");
		HttpAccessService httpAccessService=new HttpAccessService(configuration);
		HttpAccess httpAccess=new HttpAccess(httpAccessService,new MockResponseStatusValidator());
		
		
		
		
		try {
			String strTest=httpAccess.doPostMultiPart(strUrlTestHttp, mapParameters, null, null, null, mapHeadersResponse, mapHeadersResponse);
			System.out.println(strTest);
			
			mapHeadersResponse.forEach((k,v)->System.out.println("***"+k+"***"+v));
		} catch (HttpAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}
	
	
	
	

	@Test
	public void testDoPostJson()		
	{
		String strJson= "{\n"
				+ "  \"identity_change\" : {\n"
				+ "    \"identity\" : {\n"
				+ "    \"customer_id\" : \"b59f9424-6c5f-4bc7-a6c7-efd1e29655d4\",\n"
				+ "    \"attributes\" : {\n"
				+ "      \"birthcountry\" : {\n"
				+ "        \"key\" : \"birthcountry\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"FRANCE\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"birthdate\" : {\n"
				+ "        \"key\" : \"birthdate\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"24/08/1962\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"gender\" : {\n"
				+ "        \"key\" : \"gender\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"1\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"birthplace\" : {\n"
				+ "        \"key\" : \"birthplace\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"PARIS 07\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"login\" : {\n"
				+ "        \"key\" : \"login\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"testbp2022@yopmail.com\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"family_name\" : {\n"
				+ "        \"key\" : \"family_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"DUBOIS\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"first_name\" : {\n"
				+ "        \"key\" : \"first_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"Angela Claire Louise\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"email\" : {\n"
				+ "        \"key\" : \"email\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"testbp2022@yopmail.com\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : false,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : null\n"
				+ "      },\n"
				+ "    \"fc_birthdate\" : {\n"
				+ "        \"key\" : \"fc_birthdate\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"1962-08-85\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "   \n"
				+ "      \"fc_given_name\" : {\n"
				+ "        \"key\" : \"fc_given_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"Angela Claire Louise 3\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"fc_birthplace\" : {\n"
				+ "        \"key\" : \"fc_birthplace\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"75107\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "  \n"
				+ "      \"fc_gender\" : {\n"
				+ "        \"key\" : \"fc_gender\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"female\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "     \n"
				+ "      \"fc_family_name\" : {\n"
				+ "        \"key\" : \"fc_family_name\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"DUBOIS\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "      },\n"
				+ "      \"fc_birthcountry\" : {\n"
				+ "        \"key\" : \"fc_birthcountry\",\n"
				+ "        \"type\" : \"string\",\n"
				+ "        \"value\" : \"99100\",\n"
				+ "        \"application_last_update\" : \"RhssoFranceConnect\",\n"
				+ "        \"date_last_update\" : 1622468105000,\n"
				+ "        \"certified\" : true,\n"
				+ "        \"writable\" : true,\n"
				+ "        \"certificate\" : {\n"
				+ "          \"certifier_code\" : \"fccertifier\",\n"
				+ "          \"certifier_name\" : \"France Connect Certifier\",\n"
				+ "          \"certificate_level\" : 3,\n"
				+ "          \"certificate_exp_date\" : null\n"
				+ "        }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ",\n"
				+ "    \"author\" : {\n"
				+ "      \"id\" : \"usager\",\n"
				+ "      \"type\" : 1,\n"
				+ "      \"application_code\" : \"RhssoFranceConnect\"\n"
				+ "    }\n"
				+ "  }\n"
				+ "}";
		
		
        String strUrlTestHttp="https://httpbin.org/anything";
		
		HttpClientConfiguration configuration=new HttpClientConfiguration();
		configuration.setConnectionTimeout("1000");
		configuration.setSocketTimeout("10000");
		configuration.setProxyHost("192.168.64.41");
		configuration.setNoProxyFor("*.paris.mdp");
		configuration.setProxyPort("8080");
		
		Map<String,String> mapHeaders=new HashMap<String, String>();
	
		   Map<String, List<String>> mapParameters = new HashMap<String, List<String>>( );
		List<String> params=new ArrayList<String>();
		params.add(strJson);
		mapParameters.put( "identityChange", params);
		Map<String,String> mapHeadersResponse=new HashMap<String, String>();
		
		
		//mapHeaders.put("Content-Type", "application/json; charset=utf-8");
		mapHeaders.put("client_code", "RhssoFranceConnect");
		
		//mapParameters.put("grant_type", "client_credentials");
		HttpAccessService httpAccessService=new HttpAccessService(configuration);
		HttpAccess httpAccess=new HttpAccess(httpAccessService,new MockResponseStatusValidator());
		
		
		
		
		try {
			String strTest=httpAccess.doPostJSON(strUrlTestHttp, strJson, mapHeaders, mapHeadersResponse);
			
			System.out.println(strTest);
			
			mapHeadersResponse.forEach((k,v)->System.out.println("***"+k+"***"+v));
		} catch (HttpAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}
	
	
	
	

}