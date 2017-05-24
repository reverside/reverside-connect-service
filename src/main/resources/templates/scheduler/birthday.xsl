<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ns="http://reverside.co.za/schema">
  
  	<xsl:output method="xml"  indent="yes"/>
  	
  	<xsl:template match="/ns:birthdayTask">
		<Send xmlns="http://service.zenerick.com/notification/command">
		  <type>Birthday</type>
		  <tns:birthday xmlns:tns="http://reverside.co.za/schema">
			  <tns:name><xsl:value-of select="ns:name"/></tns:name>
			  <tns:photo><xsl:value-of select="ns:photo"/></tns:photo>
		  </tns:birthday>
		  <channel>
		      <mail>
		        <to><xsl:value-of select="ns:email"/></to>
		        <subject>Happy Birthday</subject>
		      </mail>
		      <sms>
		      	<mobile><xsl:value-of select="ns:mobile"/></mobile>
		      </sms>
		  </channel>
		</Send>
  	</xsl:template>
  
</xsl:stylesheet>