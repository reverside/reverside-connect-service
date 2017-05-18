<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://reverside.co.za/schema">
<xsl:output method="text" media-type="text/plain" indent="no"/>
<xsl:param name="channel"/>
<xsl:template match="/ns:birthday">
<xsl:choose>
<xsl:when test="$channel='mail'">
&lt;html&gt;
&lt;body&gt;
&lt;div style="width:100%; text-align: center;"&gt;
&lt;img src="https://blogs-images.forbes.com/jacobmorgan/files/2015/05/Jacob-Morgan_avatar_1430962685-400x400.jpg" width="100px;" height="100px;"&gt;
&lt;/div&gt;
&lt;div style="width:100%; text-align: center;"&gt;
Dear <xsl:value-of select="ns:firstName"/>
&lt;h3 style="font-style:italic;"&gt;
Wish You A
&lt;/h3&gt;
&lt;h1&gt;
&lt;span style="color: red;"&gt;H&lt;/span&gt;&lt;span style="color: violet;"&gt;a&lt;/span&gt;&lt;span style="color: green;"&gt;p&lt;/span&gt;&lt;span style="color: orange;"&gt;p&lt;/span&gt;&lt;span style="color: blue;"&gt;y&lt;/span&gt; &lt;span style="color: green;"&gt;B&lt;/span&gt;&lt;span style="color: red;"&gt;i&lt;/span&gt;&lt;span style="color: violet;"&gt;r&lt;/span&gt;&lt;span style="color: orange;"&gt;t&lt;/span&gt;&lt;span style="color: blue;"&gt;h&lt;/span&gt; &lt;span style="color: red;"&gt;D&lt;/span&gt;&lt;span style="color: violet;"&gt;a&lt;/span&gt;&lt;span style="color: green;"&gt;y&lt;/span&gt;
&lt;/h1&gt;
&lt;div&gt;
Enjoy Your Day 
&lt;/div&gt;
-Reverside
&lt;/div&gt;
&lt;/body&gt;
&lt;/html&gt;
</xsl:when>
<xsl:when test="$channel='sms'">
Dear <xsl:value-of select="ns:firstName"/>
Wish You A Happy Birthday
From:Reverside
</xsl:when>
</xsl:choose>
</xsl:template>
</xsl:stylesheet>