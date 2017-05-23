<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  	<xsl:output method="xml"  indent="yes"/>
  	
  	<xsl:template match="/">
		<Send xmlns="http://service.zenerick.com/notification/command">
		  <type>Birthday</type>
          <data xmlns:ns="http://reverside.co.za/schema">
          	<name></name>
          	<photo></photo>
          </data>
		  <channel>
		    <type>mail</type>
		    <meta>
		      <mail>
		        <to>tns:to</to>
		        <subject>tns:subject</subject>
		      </mail>
		    </meta>
		  </channel>
		</Send>
  	</xsl:template>
  
</xsl:stylesheet>