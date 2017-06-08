<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ns="http://reverside.co.za/schema">

  	<xsl:output method="xml" omit-xml-declaration="yes"/>
  	
  	<xsl:template match="/ns:BirthdayScheduler">
		<Notify xmlns="http://service.zenerick.com/notification/command">
		    <type>BirthdayCommand</type>
		    <tns:BirthdayCommand xmlns:tns="http://reverside.co.za/schema">
			    <tns:name><xsl:value-of select="ns:name"/></tns:name>
			    <tns:photo><xsl:value-of select="ns:photo"/></tns:photo>
		    </tns:BirthdayCommand>
			<mail>
				<to><xsl:value-of select="ns:email"/></to>
				<cc>manmay.e.mohanty@gmail.com</cc>
				<subject>Happy Birthday</subject>
			</mail>
			<sms>
				<mobile><xsl:value-of select="ns:mobile"/></mobile>
			</sms>
		</Notify>
  	</xsl:template>
  
</xsl:stylesheet>